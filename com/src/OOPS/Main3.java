package OOPS;

public class Main3 {
    public static void main(String[] args) {

    }
}
interface A {
    void display();
}
abstract class B implements A {
    B(A a) {

    }
}
class C extends B {
    C(A a) {
        super(a);
    }
    @Override
    public void display() {
        System.out.println("I am in Class C");
    }
}

//B(A a) {
//
//}
//when you do this you need to create a super method in child
//only required when there are parameters
//bcz you have to follow abstraction