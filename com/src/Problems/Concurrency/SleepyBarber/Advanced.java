package Problems.Concurrency.SleepyBarber;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Advanced {
    public static void main(String[] args) {

    }
}
class Barber {

    private int count=0;

    int operate(int type) {
        if(type==1) return getCount();
        return incrementAndGetCount();
    }
    int getCount() {
        try{
            Thread.sleep(1000);
        } catch(Exception e){}
        return count;
    }
    int incrementAndGetCount() {
        try{
            Thread.sleep(1000);
        } catch(Exception e){}
        return ++count;
    }
}
class Request {
    int ind,type;
    Request(int ind,int type) {
        this.ind=ind;
        this.type=type;
    }
}
class Response {
    int res;
    Response(int res) {
        this.res=res;
    }
}
class Share {
    private final Semaphore consumer=new Semaphore(0,true);
    private final Semaphore barber=new Semaphore(0);
    private final Semaphore mutex=new Semaphore(1);
    private final Queue<Request> req=new LinkedList<>();
    private final Queue<Response> res=new LinkedList<>();
    private final Semaphore resqueue=new Semaphore(0);
    private int capacity;
    private int waiting;
    private Barber bar;
    Share(Barber bar,int n) {
        this.bar=bar;
        this.capacity=n;
        waiting=0;
    }
    void doOperation() {
        try{
            while(true) {
                consumer.acquire();//take the consumer
                //now we have to notify  and take the request
                Request r=req.remove();
                mutex.acquire();
                waiting--;
                mutex.release();
                int curr=bar.operate(r.type);
                res.add(new Response(curr));
                resqueue.release();
                barber.release();
            }

        } catch(Exception e){}

    }
    int getOperation(int ind,int type) {
        try {
            mutex.acquire();
            if(waiting<capacity) {
                Entry(ind);
                waiting++;
                mutex.release();
                req.add(new Request(ind,type));
                consumer.release();//now they entered in waiting line
                //adding request for barber to execute
                Wait(ind);
                barber.acquire();//waiting for barber to start it's execution
                //now it acquired barber
                resqueue.acquire();//now wait until you get  response
                int val=res.remove().res;
                display(ind,type,val);
                //System.out.println(ind+" "+type+" "+);
                return val;

            } else {
                NoEntry(ind);
                Exit(ind);
                mutex.release();
                return -1;
            }
        } catch(Exception e){
            mutex.release();
            return -1;
        }
    }
    void display(int ind,int type,int val) {
        System.out.println("After applying operation of type" + type + " by thread " + ind + " got val "+val);
    }
    void NoEntry(int i){
        System.out.println("No sit available for customer" + i);
    }
    void Wait(int i) {
        System.out.println("Customer " + i + " is Waiting");
    }
    void Entry(int i) {
        System.out.println("Customer "+ i + " Entered");
    }
    void Exit(int i) {
        System.out.println("Customer " + i + " Exited");
    }
}
class Consumer {
    Share s;

    Consumer(Share s) {

    }
    int operate(int i,int type) {
        return s.getOperation(i, type);
    }
}
//now here what we are defining
//we have a hashmap that is only accessible by a thread (barber)
//now suppose if we have many threads(consumers) coming and wanted to read data from hashmap using barber
//now here data transfer is happening
//now let us suppose our barber have a class


