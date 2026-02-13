package Thread.Locks;

public class Main1 {
    public static void main(String[] args) {
        LockWithoutFinalObject obj=new LockWithoutFinalObject();
        //Counter counter=new Counter();
        Thread t1=new Thread(()->{
            for(int i=0;i<10000;i++) obj.increment();
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10000;i++) obj.increment();
        });
        t1.start();
        t2.start();
    }
}

class Counter {
    final Object o;
    int c;
    Counter() {
        o=new Object();
        c=0;
    }
    void increment() {
        synchronized(o) {
            try{
                Thread.sleep(2);
            } catch(Exception e){

            }

            c++;
            System.out.println(c);
        }
    }
}
