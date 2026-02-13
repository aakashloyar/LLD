package Problems.Concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SleepyBarber {
    public static void main(String[] args) {

    }
}
class SB {
    int conscount;
    int capacity;
    ReentrantLock entryexitlock=new ReentrantLock();
    Condition barber=entryexitlock.newCondition();
    Semaphore chair=new Semaphore(1);
    SB(int capacity) {
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
