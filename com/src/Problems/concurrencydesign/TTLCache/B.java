package Problems.concurrencydesign.TTLCache;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

public class B {
    public static void main(String[] args) {

    }
}
class Cache2{
    ConcurrentHashMap<Integer,Pair> map;
    int duration;
    boolean running;
    Cache2(int duration) {
        map=new ConcurrentHashMap<>();
        this.duration=duration;
        running=true;
    }
    int get(int key) {
        Pair p=map.get(key);
        int ans=-1;
        if(p==null) return -1;
        else if(Instant.now().isAfter(p.ttl)) map.remove(key,p);
        else ans=p.val;
        return ans;
    }
    void add(int key,int val) {
        map.put(key,new Pair(val,duration));
    }
    void cleanup() {
        Instant now = Instant.now();
        for (Map.Entry<Integer,Pair> entry:map.entrySet()) {
            if (now.isAfter(entry.getValue().ttl)) {
                map.remove(entry.getKey(), entry.getValue());
            }
        }
    }
    void startcleaner() {
        Thread cleaner = new Thread(() -> {
            while (running) {
                try {
                    cleanup();
                    Thread.sleep(1000); // run cleanup every 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        cleaner.setDaemon(true);
        //bcz this is background job
        //we should make it demon instead of user
        cleaner.start();
    }
    void stop() {
        running = false;
    }
}



















//Instant now = Instant.now();
//        System.out.println(now);
//
//Instant future = now
//        .plus(Duration.ofMinutes(10))
//        .plus(Duration.ofSeconds(20));
//
//        System.out.println("After 10 min 20 sec: " + future);