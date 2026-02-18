package Problems.Concurrency.SleepyBarber;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Shop sb=new Shop(10);
        Thread t1=new Thread(()->{
            try {
                sb.cutHair();
            } catch(Exception e){}
        });
        t1.start();
        for(int i=0;i<20;i++) {
            final int x=i;//value in lambda expression needs to be final bcz the take copy not reference to varaible
            Thread t=new Thread(()->{
                try {
                    sb.getHairCut(x);
                } catch(Exception e){}
            });
            t.start();
        }
    }
}
class Shop {
    final private Semaphore customers=new Semaphore(0);//barber is waiting here
    final private Semaphore barber=new Semaphore(0);//customer is waiting here
    final private Semaphore mutex =new Semaphore(1);//just a lock for waiting variable to be protected
    private int waiting=0;
    int capacity;
    Shop(int n) {
        this.capacity=n;
    }
    //barber logic
    void cutHair() throws InterruptedException{
        while(true) {
            //so when it tries to enter here
            customers.acquire();
            //got that a customer is there
            //now it need to process it
            //doing hair cut
            mutex.acquire();
            waiting--;
            mutex.release();
            barber.release();
            Thread.sleep(1000);   // Simulate haircut time
        }
    }
    //customer logic
    void getHairCut(int i) throws InterruptedException{
        mutex.acquire();
        if(waiting<capacity) {
            waiting++;
            customers.release();
            mutex.release();
            barber.acquire();
            HairCut(i);
        } else {
            NoEntry(i);
            Exit(i);
            mutex.release();
        }
    }
    void NoEntry(int i){
        System.out.println("No sit available for customer" + i);
    }
    void Waiting(int i) {
        System.out.println("Customer " + i + " is Waiting");
    }
    void Entry(int i) {
        System.out.println("Customer "+ i + " Entered");
    }
    void Exit(int i) {
        System.out.println("Customer " + i + " Exited");
    }
    void SitTaken(int i) {
        System.out.println("Customer " + i + " took sit");
    }
    void SitLeaved(int i) {
        System.out.println("Customer "+ i + "left sit");
    }
    void HairCut(int i) {
        System.out.println("Customer " + i + " hair cut is going on");
    }
}

