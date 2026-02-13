package Thread.Semaphore;

import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {
        Counter obj=new Counter();
        Thread t1=new Thread(()->{
            for(int i=0;i<100;i++) obj.increment();
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10;i++) obj.increment();
        });
        t1.start();
        t2.start();
    }
}

class Counter {
    Semaphore semaphore=new Semaphore(1);
    private final Semaphore fairSemaphore = new Semaphore(3, true);
    int c;
    final Object obj;
    Counter() {
        obj=new Object();
        c=0;
    }
    void increment() {
        try{
            semaphore.acquire();
            Thread.sleep(2);
            c++;
            System.out.println(c);
            semaphore.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    // Non-blocking variant: returns immediately with success/failure
    public boolean tryAccessResource() {
        // tryAcquire() returns false immediately if no permit available
        // Use this when you have a fallback option
        if (semaphore.tryAcquire()) {
            try {
                doWork();
                return true;
            } finally {
                semaphore.release();
            }
        }
        return false;  // No permit available, caller can try alternative
    }

    private void doWork() {
        System.out.println(Thread.currentThread().getName() + " working...");
    }
}
//semaphore does not make you to protect from race condition
//they are used in some important problem
//suppose database connection limit
//api rate limitting

//there are two types of exception in java
//check(compile time) vs unchecked(run time)
//checked -> like InterruptedException  -> you need to handle
//unchecked-> like null pointerexception -> you donot need to handle


// Optional: fair=true ensures FIFO ordering
// Threads acquire permits in the order they requested

//difference between acquire and tryacquire
//acquire try to get lock if not then threadwaiting
//while in tryacquire it will exit if it will not get

//when you give preference to priority use mutex
//semaphore donot care priority

