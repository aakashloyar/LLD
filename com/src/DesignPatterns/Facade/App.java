package DesignPatterns.Facade;

public class App {
    public static void main(String[] args) {
        ZomatoFacade f=new ZomatoFacade();
        f.order("aakash","gajarpaak",500);
    }
}

//now here instead of client getting to know about each service
//and call each service
//what we can do is just create a facade that do all things
class ZomatoFacade {
    private RestaurantService restaurantService;
    private PaymentService paymentService;
    private DeliveryService deliveryService;
    private NotificationService notificationService;
    public ZomatoFacade() {
        restaurantService = new RestaurantService();
        paymentService = new PaymentService();
        deliveryService = new DeliveryService();
        notificationService = new NotificationService();
    }
    public void order(String user,String item,double amount) {
        paymentService.makePayment(user,amount);
        restaurantService.placeOrder(item);
        deliveryService.assignDeliveryPartner(item);
        notificationService.sendNotification(user);
    }
}

class RestaurantService {
    public void placeOrder(String item) {
        System.out.println("Restaurant preparing: " + item);
    }
}

class PaymentService {
    public void makePayment(String user, double amount) {
        System.out.println("Payment of " + amount + " done by " + user);
    }
}

class DeliveryService {
    public void assignDeliveryPartner(String item) {
        System.out.println("Delivery partner assigned for " + item);
    }
}

class NotificationService {
    public void sendNotification(String user) {
        System.out.println("Notification sent to " + user);
    }
}
