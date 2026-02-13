# Adapter (Structural)
-> Adapter makes incompatible things work together

interface PaymentProcessor {
    void pay(int amount);
}
-> your code wants this

class RazorPay {
    public void makePayment(int money) {
        System.out.println("Paid " + money + " using RazorPay");
    }
}
-> Existing / third-party class (incompatible)

-> without adapter
-> PaymentProcessor p = new RazorPay(); // ❌ Not possible


class RazorPayAdapter implements PaymentProcessor {

    private RazorPay razorPay;

    public RazorPayAdapter(RazorPay razorPay) {
        this.razorPay = razorPay;
    }

    @Override
    public void pay(int amount) {
        razorPay.makePayment(amount); // translation happens here
    }
}
-> with adapter
PaymentProcessor payment = new RazorPayAdapter(new RazorPay()); 
payment.pay(500);