# Locks
-> many ways
-> synchronized method => synchronized method()
-> synchronized block  => synchronized(lock);
-> explicit lock ->ReentrantLock


* synchronized(obj)
* this object need to be final
* bcz let us suppose if you reassign this object mistakenly 
* then other thread enter he thinks that this object is different

-> now some points to keep in mind 
-> try to avoid lock whenever possible 
-> donot keep compute heavy things in lock 
-> they will waste the cpu
-> if two operation does not touch same data made a different lock for these
-> for small counter use Atomic 
-> for ready heavy use ReaderWriterLock 