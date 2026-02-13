package Problems.Concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
}

class FB {
    private int n;
    Semaphore fizzsem=new Semaphore(0);
    Semaphore buzzsem=new Semaphore(0);
    Semaphore fizzbuzzsem =new Semaphore(0);
    Semaphore numsem =new Semaphore(0);
    AtomicInteger count=new AtomicInteger(1);
    public FB(int n) {
        this.n = n;
        Notify(1);
    }
    public void Notify(int c) {
        if(c>n) {
            fizzsem.release();
            buzzsem.release();
            fizzbuzzsem.release();
            numsem.release();
        }
        else if(c%3==0 &&c%5==0) {
            fizzbuzzsem.release();
        } else if(c%3==0) {
            fizzsem.release();
        } else if(c%5==0) {
            buzzsem.release();
        } else {
            numsem.release();
        }
    }
    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true) {
            fizzsem.acquire();
            if(count.get()>n) return;
            printFizz.run();
            Notify(count.incrementAndGet());
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true) {
            buzzsem.acquire();
            if(count.get()>n) return;
            printBuzz.run();
            Notify(count.incrementAndGet());
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true) {
            fizzbuzzsem.acquire();
            if(count.get()>n) return;
            printFizzBuzz.run();
            Notify(count.incrementAndGet());
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            numsem.acquire();
            if(count.get()>n) return;
            printNumber.accept(count.get());
            Notify(count.incrementAndGet());
        }
    }
}