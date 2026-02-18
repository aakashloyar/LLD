package Problems.concurrencydesign.RateLimitter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Instant;
public class A {
    public static void main(String[] args) {

    }
}
class RL1 {
    ConcurrentHashMap<Integer,UserWindow> map;
    private long windowsize,capacity;
    RL1(long windowsize,long capacity) {
        this.windowsize=windowsize;
        this.capacity=capacity;
    }
    boolean allowed(int userId) {
        UserWindow window=map.computeIfAbsent(userId,k->new UserWindow());
        return window.allow();
    }
    private class UserWindow {
        private Deque<Long> deque;
        UserWindow() {
            deque=new ArrayDeque<>();
        }
        public synchronized boolean allow() {
            long now=Instant.now().getNano();
            while(!deque.isEmpty() &&now-deque.peekFirst()>windowsize) {
                deque.removeFirst();
            }
            if(deque.size()==capacity) return false;
            deque.add(now);
            return true;
        }
    }
}

