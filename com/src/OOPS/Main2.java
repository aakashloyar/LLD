package OOPS;

public class Main2 {
    public static void main(String[] args) {
        Out out=new Out();
        out.display();
    }
}
class Out {
    void display() {
        class LocalInner {
            void message() {
                System.out.println("Inside method");
            }
        }

        LocalInner obj = new LocalInner();
        obj.message();
    }
}
