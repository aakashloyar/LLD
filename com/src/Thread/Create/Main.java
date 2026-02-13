package Thread.Create;

public class Main {
    public static void main(String[] args) {
        //using inheritance by exenting Thread
        Thread1 t1=new Thread1();
        t1.start();

//        Thread2 t2=new Thread2();
//        t2.start();//this implementation does not work like this
        //using interface by implementing Thread
        Thread t2=new Thread(new Thread2());
        t2.start();

        //by using lambda function
        Thread t3=new Thread(()->{
           System.out.println("Running Thread3");
        });
        t3.start();

    }
}
