# SOLID 

-> 5 rules to keep class clean flexible and easy to expand

# S Single Responsibility Principle (SRP)
-> each class must have 1 responsibility 
-> let us now deep dive 

class Invoice {
    double calculateTotal() { }

    void printInvoice() { }

    void saveToDatabase() { }
}

-> here this class is handling 3 things
-> Business logic
-> Presentation
-> Persistance
-> It breaks SRP

-> So does SRP means only 1 method allowed per class 
-> no

class Invoice {
    double subtotal;
    double tax;

    double calculateTotal() {
        return subtotal + tax;
    }

    boolean isValid() {
        return subtotal > 0;
    }

    String getSummary() {
        return "Total = " + calculateTotal();
    }
}


-> Here this class has multiple method 
-> but all are business logic referred to Invoice
-> so it means multiple methods but single responsibility
-> think of it as if a new requirement comes then how many reasons would force me to modify this class


# O Open Closed Principle (OCP)
-> Open for extension, closed for modification

-> suppose discountcalcular for flipkart 
class DiscountCalculator {
    double calculate(String type) {
        if (type.equals("FESTIVAL")) return 10;
        if (type.equals("VIP")) return 20;
        return 0;
    }
}
-> here for any new discount we have to modify this class 

interface Discount {
    double apply();
}

class FestivalDiscount implements Discount {
    public double apply() { return 10; }
}

class VipDiscount implements Discount {
    public double apply() { return 20; }
}

-> here it is nice example of polymorphism 
-> here we are extinding not modifying 
-> for any new discount we donot need to modify

# L (Liskov Substitution Principle)
-> Subclass should be replacable by parent without breaking the behaviour

class Bird {
    void fly() { }
}

class Penguin extends Bird {
    void fly() {
    throw new UnsupportedOperationException();
    }
}

-> here as we can see that subclass needs to throw exception for some of the parent methods
-> so our design is wrong

interface Flyable {
    void fly();
}

class Sparrow implements Flyable {
    public void fly() { }
}

class Penguin {
    void swim() { }
}

-> ROT : if subclass need to remove behaviour or throw exceptions : design is wrong


# I - Interface Segregation Principle (ISP)
-> Donot force classes to implement methods they donot need

interface Worker {
    void code();
    void test();
    void deploy();
}

interface Coder {
    void code();
}

interface Tester {
    void test();
}

interface Deployer {
    void deploy();
}

-> classes implement only what they need


# D - Dependency Inversion Principle (DIP)
-> Depend on abstractions not concrete classes

-> Tight coupling

class OrderService {
    private MySQLDatabase db = new MySQLDatabase();
}
-> cannot change db easily 

interface Database {
    void save();
}

class MySQLDatabase implements Database {
    public void save() { }
}

class OrderService {
    private Database db;

    OrderService(Database db) {
        this.db = db;
    }
}
-> easy to swap db
-> easy to test 
-> clean design



# All interface methods must be Public
