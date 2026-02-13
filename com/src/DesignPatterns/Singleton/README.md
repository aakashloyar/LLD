# Singleton (Builder)

-> used when only 1 instance of a object is required
-> now let us suppose take example of database connection
-> now if we donot use singleton then 1 connection each request
-> now connection is time consuming 5ms to 100ms 
-> bcz it do encrytion password matching username tcp connection 3 way handshake session setup
-> db assign a worker thread 
-> now 100mb to 1000mb of ram is used 
-> also db connection has a limit let us suppose 100 
-> that is why precreate N connection and reuse them


public class Human {
    private static volatile Human humanInstance=null;
    private Human() {
        System.out.println("Huamn is initialized");
    }
    public static Human getHumanInstance() {
        if(humanInstance==null) {
            synchronized(Human.class) {
                if(humanInstance==null) {
                    System.out.println("Initialized");
                    humanInstance=new Human();
                }
            }
        }
        return humanInstance;
    }
}
