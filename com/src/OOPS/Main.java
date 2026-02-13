package OOPS;

public class Main {
    public static void main(String[] args) {
        Outer outer=new Outer();
        Outer.Inner inner=outer.new Inner();
        inner.display();
    }
}
class Outer {
    class Inner {
        void display() {
            System.out.println("I am printing");
        }
    }
}
