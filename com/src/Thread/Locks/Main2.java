package Thread.Locks;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    public static void main(String[] args) {
        RenteelCounter obj=new RenteelCounter();
        Thread t1=new Thread(()->{
            for(int i=0;i<10000;i++) obj.increment();
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10000;i++) obj.increment();
        });
        t1.start();
        t2.start();
    }
}
class RenteelCounter {
    private int count = 0;
    private final Lock lock=new ReentrantLock();
    // Approach 1: Explicit lock
    public void increment() {
        lock.lock();
        try {
            count++;
            System.out.println(count);
        } finally {
            lock.unlock();  // Always in finally!
        }
    }
}