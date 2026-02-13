# Factory (Structural)

-> before factory
public class OrderService {

    public void placeOrder(String paymentType, double amount) {

        if (paymentType.equals("CARD")) {
            CardPayment cardPayment = new CardPayment();
            cardPayment.pay(amount);

        } else if (paymentType.equals("UPI")) {
            UPIPayment upiPayment = new UPIPayment();
            upiPayment.pay(amount);

        } else if (paymentType.equals("CASH")) {
            CashPayment cashPayment = new CashPayment();
            cashPayment.pay(amount);
        }
    }
}
-> here the problem is now order service know everything about 
-> here it is tight coupling 
-> now tomorrow if you add a new payment class
-> then you must modify payment service
-> voilates OCP
-> right now orderservice decide how to create payment objects
-> but orderservice work is only to place order


-> after this also OCP violates 

-> You want to centralize object creation logic and hide new keyword from client code.
