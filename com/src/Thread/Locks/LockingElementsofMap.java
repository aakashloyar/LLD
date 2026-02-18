package Thread.Locks;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockingElementsofMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, ValueWrapper> map =
                new ConcurrentHashMap<>();
        ValueWrapper wrapper = map.get("A");
        wrapper.lock.lock();
        try {
            wrapper.value++;
        } finally {
            wrapper.lock.unlock();
        }


    }
}
class ValueWrapper {
    int value;
    final ReentrantLock lock = new ReentrantLock();
}
//But I am locking the value, so HashMap is safe.
//No. Locking the value does not protect the internal state of HashMap.
// Concurrent structural modifications can corrupt the map.
//you need to use concurrenthashmap to avoid this

//When put() happens:
//HashMap may need to resize.
//Resizing means:
//Creating a new bucket array.
//Rehashing all existing entries.
//Moving them.
//During this time:
//Internal structure is being modified.
//If Thread 1 calls get() during this process:
//It may:
//See partially moved data.
//See null unexpectedly.
//See corrupted structure.
//Your wrapper.lock does NOTHING to stop this.
//Because:
//You locked the account.
//But nobody locked the map.

//but if only balance changes
//no adding or removing happens
//then alone hashset is enough
//so during map resizes the Object remain the same
//concurrent hashmap locking happens at bucket level

//HashMap → no locks
//SynchronizedMap → 1 big lock
//ConcurrentHashMap → many small locks
