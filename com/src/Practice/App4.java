package Practice;

public class App4 {
    public static void main(String[] args) {
        DB db=DB.getDBInstance();
    }
}
class DB {
    private static DB db;
    private DB() {

    }
    static DB getDBInstance() {
        if(db==null) {
            db=new DB();
        }
        return db;
    }
}
