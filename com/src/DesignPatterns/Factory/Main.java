package DesignPatterns.Factory;

//suppose you are building payment methods
//Object creation logic is scattered if you are doing it everytime and everywhere
//so what factory does is move this creation to a separate class
public class Main {
    public static void main(String[] args) {
        OrderService.Execute("stripe",10);
    }
}
interface PaymentMethod {
    void Pay(int amount);
}
class PaymentFactory {
    static PaymentMethod getPaymentMethod(String name) {
        switch(name) {
            case "razor":
                return new RazorPay();
            case "stripe":
                return new Stripe();
            default:
                throw new IllegalArgumentException("Invalid payment type");
        }
    }
}
class OrderService {
    static void Execute(String name,int amount) {
        PaymentMethod pm=PaymentFactory.getPaymentMethod(name);
        pm.Pay(amount);
    }
}
class RazorPay implements PaymentMethod {
    public void Pay(int amount) {
        System.out.println("Paid using Razaor Pay "+amount);
    }
}

class Stripe implements PaymentMethod {
    public void Pay(int amount) {
        System.out.println("Paid using Stripe "+amount);
    }
}