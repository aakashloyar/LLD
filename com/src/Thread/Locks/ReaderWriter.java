package Thread.Locks;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriter {
    public static void main(String[] args) {
        RW obj=new RW();
        Thread t1=new Thread(()->{
            for(int i=0;i<100;i++) obj.Read();
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10;i++) obj.Write();
        });
        t1.start();
        t2.start();
    }
}
class RW {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    void Read() {
        rwLock.readLock().lock();
        try {
            System.out.println("I am Reading");
            Thread.sleep(1);
        } catch(Exception e){}
        finally{
            rwLock.readLock().unlock();
        }
    }
    void Write() {
        rwLock.writeLock().lock();
        try {
            System.out.println("I am Writing");
            Thread.sleep(1);
        } catch(Exception e){}
        finally{
            rwLock.writeLock().unlock();
        }
    }
}
//here multiple thread can acquire reader lock
//1 only writer allowed
//many reader together are allowed
//reader and writer cannot come together



//if we remove unlock
//then suppose write acquire lock
//then it will execute fully complete it's execution
//also if reader takes it then it can also do the same
