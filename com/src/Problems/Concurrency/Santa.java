package Problems.Concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Santa {
    public static void main(String[] args) {

    }
}
class SP{
    Semaphore santa=new Semaphore(0);
    Semaphore reindeer=new Semaphore(0);
    Semaphore elves=new Semaphore(0);
    AtomicInteger rcount=new AtomicInteger(0);
    AtomicInteger ecount=new AtomicInteger(0);
    static final int TOTAL_REINDEER = 9;
    static final int TOTAL_ELVES = 3;
    ReentrantLock lock = new ReentrantLock();
    SP(){

    }
    void Santa() {
        while(true) {
            try{
                santa.acquire();
                lock.lock();
                if(rcount.get()==TOTAL_REINDEER) {
                    System.out.println("Santa: Preparing sleigh 🎅");
                    reindeer.release(TOTAL_REINDEER);
                    rcount.set(0);
                } else {
                    System.out.println("Santa: Helping elves 🧝");
                    elves.release(TOTAL_ELVES);
                    ecount.set(0);
                }
                lock.unlock();
            } catch(Exception e) {}
        }
    }
    void Reindeer(int id) {
        while(true) {
            try {
                if(rcount.get()<TOTAL_REINDEER) {
                    rcount.incrementAndGet();
                    if(rcount.get()==TOTAL_REINDEER) {
                        santa.release();
                    }
                    reindeer.acquire();
                    System.out.println("Reindeer " + id + " getting hitched 🛷");
                } else {

                }

            } catch(Exception e) {

            }
        }
    }
    void Elves(int id) {
        while(true) {
            try {
                if(ecount.get()<TOTAL_ELVES) {
                    ecount.incrementAndGet();
                    if(ecount.get()==TOTAL_ELVES) {
                        santa.release();
                    }
                    elves.acquire();
                    System.out.println("Elf " + id + " getting help 🎁");
                } else {

                }

            } catch(Exception e) {

            }
        }
    }
}
