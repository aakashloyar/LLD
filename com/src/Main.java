//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {
        Object obj=new Object();
        synchronized(obj) {

        }
        ReentrantLock lock=new ReentrantLock();
        ConcurrentHashMap<Integer,Integer> map=new ConcurrentHashMap<>();
        //map.computeIfAbsent();
        Semaphore s=new Semaphore(0);
        Condition c=lock.newCondition();

    }
}
//Singleton
//factory
//Builder
//facade
//observer
//Iterator
//strategy
//adapter
//state
//command
//decorator
