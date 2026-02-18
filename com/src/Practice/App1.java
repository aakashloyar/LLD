package Practice;

public class App1 {
    public static void main(String[] args) {
        PaymentProcessor p1=new StripeAdapter(new Stripe());
        p1.Pay(100);

    }
}
interface PaymentProcessor{
    void Pay(int amount);
}
class RazorPay {
    void payamount(int amount) {
        System.out.println("Processing " + amount + " using RazorPay");
    }
}
class Stripe {
    void processamount(int amount) {
        System.out.println("Processing " + amount + " using Stripe");
    }
}
class PaymentService {
    PaymentProcessor processor;
    PaymentService(PaymentProcessor processor) {
        this.processor=processor;
    }

}
class OrderService {
    OrderService() {

    }
    void Execute() {

    }
}
class RazorPayAdapter implements PaymentProcessor {
    RazorPay razorpay;
    RazorPayAdapter(RazorPay razorpay) {
        this.razorpay=razorpay;
    }
    public void Pay(int amount) {
        this.razorpay.payamount(amount);
    }
}
class StripeAdapter implements PaymentProcessor {
    Stripe stripe;
    StripeAdapter(Stripe stripe) {
        this.stripe=stripe;
    }
    public void Pay(int amount) {
        this.stripe.processamount(amount);
    }
}
