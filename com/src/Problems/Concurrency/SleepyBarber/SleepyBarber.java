package Problems.Concurrency.SleepyBarber;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SleepyBarber {
    public static void main(String[] args) {
        SB sb=new SB(10);
        Thread t1=new Thread(sb::barber);
        t1.start();
        for(int i=0;i<20;i++) {
            final int x=i;//value in lambda expression needs to be final bcz the take copy not reference to varaible
            Thread t=new Thread(()->{
                sb.consumer(x);
            });
            t.start();
        }
    }
}

class SB {
    final private int capacity;
    final private ReentrantLock lock = new ReentrantLock(true);
    final private Condition condition = lock.newCondition();
    final private Condition barber = lock.newCondition();
    final private Queue<Integer> q;

    SB(int capacity) {
        this.capacity = capacity;
        q = new LinkedList<>();
    }

    void barber() {
        while (true) {
            int curr = 0;
            try {
                lock.lock();
                while (q.isEmpty()) barber.await();
                curr = q.remove();
                condition.signalAll();
            } catch (Exception e) {
            } finally {
                lock.unlock();
                HairCut(curr);
            }
        }
    }

    void consumer(int i) {
        //entry
        try {
            lock.lock();
            if (q.size() == capacity) {
                NoEntry(i);
                Exit(i);
                return;
            }
            Entry(i);
            q.add(i);
            Waiting(i);
            barber.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
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





















class SB1 {
    int conscount;
    int capacity;
    ReentrantLock entryexitlock=new ReentrantLock();
    Condition barber=entryexitlock.newCondition();
    Semaphore chair=new Semaphore(1);
    SB1(int capacity) {
        this.capacity=capacity;
        conscount=0;
    }
    void Barber(){
        while(true) {

        }
    }
    void Consumer() {
        //consumerentry
        try{
            entryexitlock.lock();
            if(conscount==capacity) {
                NoEntry();
                return;
            }
            conscount++;
        } finally {
            entryexitlock.unlock();
        }
        Arrived();


        //waiting to get it's turn

        try{
            chair.acquire();


        } catch(Exception e){}finally{
            chair.release();
        }
        //consumerexit
        try{
            entryexitlock.lock();
            conscount--;
        } finally{
            Exited();
            entryexitlock.unlock();
        }
    }
    void Cutting() {
        System.out.println("I am cutting");
        try{
            Thread.sleep(1000);
        } catch(Exception e) {}
        System.out.println("Done waiting for next");
    }
    void Arrived() {
        System.out.println("I have arrived to shop");
    }
    void Exited() {
        System.out.println("I have exited");
    }
    void NoEntry(){
        System.out.println("No space for new customer");
    }
}
