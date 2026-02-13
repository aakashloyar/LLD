package Problems.Concurrency;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    public static void main(String[] args) {
        Foo obj=new Foo(10);
        Thread t1=new Thread(()->{
           obj.foo();
        });
        Thread t2=new Thread(()->{
            obj.bar();
        });
        t1.start();
        t2.start();

    }
}
class Foo {
    private int n;
    int c;
    private ReentrantLock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    public Foo(int n) {
        this.n = n;
        c=0;
    }

    public void foo() {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try{
                while((c&1)==1) {
                    a.await();
                }
                System.out.println("Foo");
                c++;
                b.signal();
            } catch(Exception e){}
            finally{
                lock.unlock();
            }
        }
    }

    public void bar() {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try{
                while((c&1)==0) {
                    b.await();
                }
                System.out.println("Bar");
                c++;
                a.signal();
            } catch(Exception e){}
            finally{
                lock.unlock();
            }
        }
    }
}