package Problems.Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnisexWashroom{
    public static void main(String[] args) {
        UW obj=new UW();
        Thread a=new Thread(()->{
            for(int i=0;i<10;i++) {
                Thread t1=new Thread(()->{
                    try {
                        obj.Men();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                t1.start();
            }
        });
        Thread b=new Thread(()->{
            for(int i=0;i<10;i++) {
                Thread t2=new Thread(()->{
                    try {
                        obj.Women();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                t2.start();
            }
        });
        a.start();
        b.start();
    }
}
class UW {
    //only men or only women are allowed //means only unisex are allowed
    AtomicInteger mc=new AtomicInteger(0);
    AtomicInteger wc=new AtomicInteger(0);
    ReentrantLock lock=new ReentrantLock();
    private Condition men = lock.newCondition();
    private Condition women = lock.newCondition();
    UW() {

    }
    void Men() throws InterruptedException {
        lock.lock();
        try{
            while(wc.get()!=0) {
                men.await();
            }
            mc.incrementAndGet();
            System.out.println("Men Entered now mencount = "+mc.get());
        }
        finally {
            lock.unlock();
        }
        Thread.sleep(1000);

        lock.lock();
        try{
            mc.decrementAndGet();
            System.out.println("Men Exited now mencount = "+mc.get());
            if(mc.get()==0) women.signalAll();
        } finally {
            lock.unlock();
        }

    }
    void Women() throws InterruptedException{
        lock.lock();
        try{
            while(mc.get()!=0) {
                women.await();
            }
            wc.incrementAndGet();
            System.out.println("Women Entered now womencount = "+wc.get());
        }
        finally {
            lock.unlock();
        }
        Thread.sleep(1000);

        lock.lock();
        try{
            wc.decrementAndGet();
            System.out.println("Women Exited now womencount = "+wc.get());
            if(wc.get()==0) men.signalAll();
        } finally {
            lock.unlock();
        }
    }

}