# Facade (Structural)

-> instead of client calling many services they only talk to 1 service
-> here it is tight coupling

InventoryService inventory = new InventoryService();
PaymentService payment = new PaymentService();
InvoiceService invoice = new InvoiceService();
NotificationService notification = new NotificationService();
ShippingService shipping = new ShippingService();

if (inventory.isAvailable(productId)) {
    payment.pay(userId, amount);
    invoice.generate(userId, productId);
    shipping.ship(productId);
    notification.send(userId);
}

-> Facade (1 class that hide all this complexity)

class InventoryService {
    boolean isAvailable(String productId) {
        return true;
    }
}

class PaymentService {
    void pay(String userId, double amount) {
        System.out.println("Payment successful");
    }
}

class InvoiceService {
    void generate(String userId, String productId) {
        System.out.println("Invoice generated");
    }
}

class ShippingService {
    void ship(String productId) {
        System.out.println("Product shipped");
    }
}

class NotificationService {
    void send(String userId) {
        System.out.println("Notification sent");
    }
}

public class OrderFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.invoiceService = new InvoiceService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }

    public void placeOrder(String userId, String productId, double amount) {
        if (!inventoryService.isAvailable(productId)) {
            throw new RuntimeException("Product not available");
        }

        paymentService.pay(userId, amount);
        invoiceService.generate(userId, productId);
        shippingService.ship(productId);
        notificationService.send(userId);

        System.out.println("Order placed successfully");
    }
}
public class OrderController {

    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder("user123", "product456", 999.0);
    }
}
