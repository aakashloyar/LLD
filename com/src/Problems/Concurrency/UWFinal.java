package Problems.Concurrency;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class UWFinal {
}



class UW4 {

    private int menInside = 0;
    private int womenInside = 0;

    private int menWaiting = 0;
    private int womenWaiting = 0;

    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition menCond = lock.newCondition();
    private final Condition womenCond = lock.newCondition();

    UW4(int n) {
        this.capacity = n;
    }

    public void enterMen() throws InterruptedException {
        lock.lock();
        try {
            menWaiting++;

            while (womenInside > 0 ||
                    menInside >= capacity ||
                    womenWaiting > 0) {  // 🚨 stop refill if women waiting
                menCond.await();
            }

            menWaiting--;
            menInside++;
            System.out.println(Thread.currentThread().getName() +
                    " ENTERED (Men inside: " + menInside + ")");
        } finally {
            lock.unlock();
        }

        Thread.sleep(1000);  // simulate usage

        exitMen();
    }

    private void exitMen() {
        lock.lock();
        try {
            menInside--;
            System.out.println(Thread.currentThread().getName() +
                    " EXITED (Men inside: " + menInside + ")");

            if (menInside == 0) {
                // Bathroom empty → give priority to women if waiting
                if (womenWaiting > 0) {
                    womenCond.signalAll();
                } else {
                    menCond.signalAll();
                }
            } else {
                // still men inside, allow next man only if no women waiting
                if (womenWaiting == 0) {
                    menCond.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void enterWomen() throws InterruptedException {
        lock.lock();
        try {
            womenWaiting++;

            while (menInside > 0 ||
                    womenInside >= capacity ||
                    menWaiting > 0) {  // 🚨 stop refill if men waiting
                womenCond.await();
            }

            womenWaiting--;
            womenInside++;
            System.out.println(Thread.currentThread().getName() +
                    " ENTERED (Women inside: " + womenInside + ")");
        } finally {
            lock.unlock();
        }

        Thread.sleep(1000);

        exitWomen();
    }

    private void exitWomen() {
        lock.lock();
        try {
            womenInside--;
            System.out.println(Thread.currentThread().getName() +
                    " EXITED (Women inside: " + womenInside + ")");

            if (womenInside == 0) {
                if (menWaiting > 0) {
                    menCond.signalAll();
                } else {
                    womenCond.signalAll();
                }
            } else {
                if (menWaiting == 0) {
                    womenCond.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
