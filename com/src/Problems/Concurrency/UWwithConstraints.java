package Problems.Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UWwithConstraints {
    public static void main(String[] args) {
        UW2 obj=new UW2(3);
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
class UW2 {
    //only men or only women are allowed //means only unisex are allowed
    AtomicInteger mc=new AtomicInteger(0);
    AtomicInteger wc=new AtomicInteger(0);
    ReentrantLock lock=new ReentrantLock();
    private Condition men = lock.newCondition();
    private Condition women = lock.newCondition();
    int n;
    UW2(int n){
        this.n=n;
    }
    void Men() throws InterruptedException {
        lock.lock();
        try{
            while(wc.get()!=0 ||mc.get()>=n) {
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
            else if(mc.get()==n-1) men.signal();
        } finally {
            lock.unlock();
        }

    }
    void Women() throws InterruptedException{
        lock.lock();
        try{
            while(mc.get()!=0 ||wc.get()>=n) {
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
            else if(wc.get()==n-1) women.signal();
        } finally {
            lock.unlock();
        }
    }

}