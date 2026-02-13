# Strategy (Behavioural)

-> if long if/else
-> let us suppose payment method
-> instead of doing if else
class PaymentService {
    void pay(String type, double amount) {
        if (type.equals("CARD")) {
            // card logic
        } else if (type.equals("UPI")) {
            // upi logic
        } else if (type.equals("WALLET")) {
            // wallet logic
        }
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
    private PaymentStrategy strategy;

    PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void pay(double amount) {
        strategy.pay(amount);
    }
}

PaymentService service = new PaymentService(new CardPayment());
service.pay(100);

-> this differ from factory 
-> as it is used after creation
-> while that is used at the time of creation of class