package Thread.ConditionalVariable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

    }
}


class MQ {
    private Queue<Integer> q;
    private int capacity;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    public MQ(int cap) {
        q=new LinkedList<>();
        capacity=cap;
    }
    public boolean add(int item) {
        lock.lock();
        try {
            while(this.full()) {
                notFull.await();
            }
            Thread.sleep(1000);
            q.add(item);
            notEmpty.signal(); // wake only consumers
            return true;
        } catch(Exception e){}
        finally {
            lock.unlock();
        }
        return false;
    }
    public int remove() {
        lock.lock();
        try {
            while(this.empty()) {
                notEmpty.await();
            }
            Thread.sleep(1000);
            int item= q.remove();
            notFull.signal(); // wake only producers
            return item;
        } catch(Exception e){}
        finally {
            lock.unlock();
        }
        return -1;
    }
    public boolean empty() {
        return q.isEmpty();
    }
    public boolean full() {
        return q.size()==capacity;
    }
}

//now here why do we require conditional variable
//here it will make two waiting queue
//also if producer executed it will signal only to consumer
//also consumer to producers
//Producers wait on notFull
//Consumers wait on notEmpty