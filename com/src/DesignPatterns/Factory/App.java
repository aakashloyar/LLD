//package DesignPatterns.Factory;
//
//public class App {
//    public static void main(String[] args) {
//        Payment p=PaymentFactory.getPayment("upi");
//    }
//}
//
//class OrderService {
//
//    public void placeOrder(String paymentType, double amount) {
//
//        Payment payment = PaymentFactory.getPayment(paymentType);
//        payment.pay(amount);
//    }
//}
//
//interface Payment {
//    void pay(double amount);
//}
//
//class CardPayment implements Payment {
//    public void pay(double amount) {
//        System.out.println("Paid " + amount + " using Card");
//    }
//}
//
//class UPIPayment implements Payment {
//    public void pay(double amount) {
//        System.out.println("Paid " + amount + " using UPI");
//    }
//}
//
//class CashPayment implements Payment {
//    public void pay(double amount) {
//        System.out.println("Paid " + amount + " using Cash");
//    }
//}
//
//class PaymentFactory {
//
//    public static Payment getPayment(String paymentType) {
//
//        switch (paymentType) {
//            case "CARD":
//                return new CardPayment();
//            case "UPI":
//                return new UPIPayment();
//            case "CASH":
//                return new CashPayment();
//            default:
//                throw new IllegalArgumentException("Invalid payment type");
//        }
//    }
//}
//
