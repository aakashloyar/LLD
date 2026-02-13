package Problems.Concurrency;

public class PrintZeroEvenOdd {
    public static void main(String[] args) {
        IntConsumer ic=new IntConsumer();
        ZeroEvenOdd obj=new ZeroEvenOdd(2);
        Thread t1=new Thread(()->{
            for(int i=0;i<1;i++) {
                try {
                    obj.zero(ic);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<1;i++) {
                try {
                    obj.odd(ic);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t3=new Thread(()->{
            for(int i=0;i<1;i++) {
                try {
                    obj.even(ic);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
class IntConsumer {
    IntConsumer() {

    }
    void accept(int x) {
        System.out.print(x);
    }
}

class ZeroEvenOdd {
    private int n;
    private final Object o;
    private int c;
    private int on=1,en=2;
    public ZeroEvenOdd(int n) {
        this.n = n;
        o =new Object();
        c=1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            synchronized(o) {
                if(c==2*n+1) {
                    o.notifyAll();
                    return;
                }
                while((c&1)==0) {
                    o.wait();
                    if (c == 2 * n + 1) return;
                }
                printNumber.accept(0);
                c++;
                o.notifyAll();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            synchronized(o) {
                if(c==2*n+1) {
                    o.notifyAll();
                    return;
                }
                while((c%4)!=0) {
                    o.wait();
                    if (c == 2 * n + 1) return;
                }
                printNumber.accept(en);
                en+=2;
                c++;
                o.notifyAll();
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(true) {
            synchronized(o) {
                if(c==2*n+1) {
                    o.notifyAll();
                    return;
                }
                while((c&1)==1 ||(c%4)==0) {
                    o.wait();
                    if (c == 2 * n + 1) return;
                }
                printNumber.accept(on);
                on+=2;
                c++;
                o.notifyAll();
            }
        }

    }
}
