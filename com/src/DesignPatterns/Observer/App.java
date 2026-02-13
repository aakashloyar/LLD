package DesignPatterns.Observer;
import java.util.*;
//when many actions are subscribed to 1 subject
public class App {
    public static void main(String[] args) {
        OrderService os=new OrderService();
        EmailService es=new EmailService();
        InventoryService is=new InventoryService();
        os.registerObserver(es);
        os.registerObserver(is);
        os.setOrderStatus("SHIPPED");
        os.setOrderStatus("Delivered");
    }
}

interface Observer {
    void update(String message);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class OrderService implements Subject {
    private List<Observer> observers=new ArrayList<>();
    private String orderStatus;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void setOrderStatus(String status) {
        this.orderStatus = status;
        notifyObservers();
    }
    @Override
    public void notifyObservers() {
        for(Observer curr: observers){
            curr.update(this.orderStatus);
        }
    }

}

class EmailService implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Email send: Order status changed to: "+message);
    }
}

class InventoryService implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Inventory updated due to order status: " + message);
    }
}
