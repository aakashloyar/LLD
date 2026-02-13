package Thread.Semaphore;

import java.util.concurrent.Semaphore;

public class Example {
    static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            try {
                System.out.println("Worker doing work...");
                Thread.sleep(2000);
                System.out.println("Worker finished work.");
                semaphore.release();   // Signal main thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        worker.start();

        try {
            System.out.println("Main waiting...");
            semaphore.acquire();      // Wait until worker releases
            System.out.println("Main resumes.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//Initial permits = 0
//release() called
//Permits = 1
//now it will exceed it's limit

