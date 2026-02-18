package Problems.concurrencydesign.TTLCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.time.Instant;
public class A {
    public static void main(String[] args) {
        Cache1 cache=new Cache1(10);
    }
}
class Cache1{
    ConcurrentHashMap<Integer,Pair> map;
    int duration;
    Cache1(int duration) {
        map=new ConcurrentHashMap<>();
        this.duration=duration;
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
}
class Pair {
    int val;
    Instant ttl;
    Pair(int val,int duration) {
        this.val=val;
        ttl=Instant.now().plusSeconds(duration);
    }
}
//now what we need to do
//we have to store key-value
//when required we need to give result
//expired pair value will not returned
//can be done in 2 ways
//Approach1 -> while reading only remove the expired pair
//when ready heavy
//not taking care about memory
//only problem is expired values that you are not reading remain in cache
//Approach2-> separately removing the pair
//nice
//but complex

