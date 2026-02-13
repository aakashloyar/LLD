package Thread.Create;

public class Thread1 extends Thread{
    Thread1() {
        //super();
    }
    @Override
    public void run() {
        System.out.println("Running Thread1");
    }
}
//in java only 1 class can be public
//bcz the public class name must match filename