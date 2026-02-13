package Problems.Concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O {
}

class HOH {
    Semaphore hsem =new Semaphore(2);
    Semaphore osem =new Semaphore(1);
    CyclicBarrier cb =new CyclicBarrier(3);//this will making on await until 3 thread comes to hit it
    public HOH() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hsem.acquire();
        try {
            cb.await();
            releaseHydrogen.run();
            //releaseHydrogen.run();
            hsem.release(1);
        } catch(Exception e) {}
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        osem.acquire();
        try {
            cb.await();
            releaseOxygen.run();
            osem.release(1);
        } catch(Exception e) {}
    }
}

//A CyclicBarrier is a synchronization tool that:
//Allows a fixed number of threads to wait for each other before continuing.
//When a thread calls:
//        barrier.await();
//Internally:
//Thread count is incremented
//If count < required → thread blocks
//If count == required:
//All waiting threads are released
//Counter resets to 0 (this is why it's called cyclic)