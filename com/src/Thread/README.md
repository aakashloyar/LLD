-> condition.notify()
-> does not wake thread at this time
-> it will awake threads only after the lock is removed


-> atomic vs using a lock 
-> atomic is fast and lock free
-> it uses compare and set
-> can be used for add, increment, decrement, cands

//it do something like check if what is expected is so then do otherwise do nothing
if (current_value == expected_value)
current_value = new_value
else
do nothing
while (true) {
int current = value;
int next = current + 1;
if (compareAndSet(current, next))
return next;
}
//it do until this happens
-> why this is faster than lock as here no context switch

->🔹 What Happens Under High Contention?

-> If 100 threads try to update:
-> Many CAS operations fail
-> Threads keep retrying
-> CPU usage increases
-> This is called spinning
-> Still no blocking — just retrying.

//small example of atomic
-> AtomicInteger counter = new AtomicInteger(5);
-> boolean success = counter.compareAndSet(5, 6);
-> System.out.println(success); // true if value was 5


-> 🚨 Important: Locks Do TWO Things
-> Mutual Exclusion (only one thread enters)
-> Memory Visibility (flush + invalidate caches)
-> Most beginners only think about #1.

-> valatile gurantees no cache issues but does not gurantee mutual exclusion
