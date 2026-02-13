package DesignPatterns.Strategy;

public class App {
    public static void main(String[] args) {
        PaymentService p=new PaymentService(new CardPayment());
        p.makePayment(100);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}
class PaymentService {
    private PaymentStrategy paymentStrategy;
    PaymentService(PaymentStrategy strategy) {
        this.paymentStrategy=strategy;
    }
    public void makePayment(int amount) {
        paymentStrategy.pay(amount);
    }
}