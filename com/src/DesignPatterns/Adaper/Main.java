package DesignPatterns.Adaper;

//adapter is suppose
//you have a interface
//and you have some 3rd party class
//now they donot subscribe to interface
//so you make adapter to make it subscribe to interface
public class Main {
    public static void main(String[] args) {
        PaymentProcessor p1=new StripeAdapter(new StripeAPI());
        PaymentProcessor p2=new RazorPayAdapter(new RazorPayAPI());
        PaymentProcessor p3=new PaymentProcessor() {
            @Override
            public void Pay(int amount) {
                System.out.println("Paying normally "+amount);
            }
        };
        PaymentProcessor p4= amount -> System.out.println("Paying using Lambda "+amount);
        p1.Pay(10);
        p2.Pay(10);
        p3.Pay(10);
        p4.Pay(10);
    }
}
//class Payment(){
//
//}

interface PaymentProcessor{
    void Pay(int amount);
}

class StripeAPI {
    void makePayment(int amount) {
        System.out.println("Paying using stripe "+amount);
    }
}
class RazorPayAPI {
    void payAmount(int amount) {
        System.out.println("Paying using razorpay "+amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeAPI stripeAPI;
    StripeAdapter(StripeAPI stripeAPI) {
        this.stripeAPI=stripeAPI;
    }
    @Override
    public void Pay(int amount) {
        stripeAPI.makePayment(amount);
    }
}
class RazorPayAdapter implements PaymentProcessor {
    private final RazorPayAPI razorPayAPI;
    RazorPayAdapter(RazorPayAPI razorPayAPI) {
        this.razorPayAPI = razorPayAPI;
    }
    @Override
    public void Pay(int amount) {
        razorPayAPI.payAmount(amount);
    }
}
//**** Interface methods in java must be public
//the methods in interface are always considererd to be public

//***********************
//private final RazorPayAPI razorPayAPI;
//RazorPayAdapter(RazorPayAPI razorPayAPI) {
//    //this.razorPayAPI = razorPayAPI;
//    razorPayAPI=razorPayAPI;
//}//if it is final then parameter=parameter will not work otherwise it will work