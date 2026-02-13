package Thread.Locks;

public class LockWithoutFinalObject {
    Object o;
    int c;
    LockWithoutFinalObject() {
        o=new Object();
        c=0;
    }
    void increment() {
        synchronized(o) {
            try{
                o=new Object();
                Thread.sleep(2);
            } catch(Exception e){

            }

            c++;
            System.out.println(c);
        }
    }
}
//playing with non final lock
//let us suppose o1 is object now T1 get the lock
//now T2 didnot get the lock
//now T1 make the object o2
//now T2 get to know that o2 does not have any lock it enters critical section
//race condition occurs
//so that is why this object need to be final
//also final object can be initialised only once
//also it cannot be initialised after constructor
//it need to be initialised before or at the time of constructor
