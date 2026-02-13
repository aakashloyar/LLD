//package DesignPatterns.Decorator;
//
//public class App {
//    public static void main(String[] args) {
//        Coffee coffee = new SimpleCoffee();       // base
//        coffee = new MilkDecorator(coffee);       // add milk
//        coffee = new SugarDecorator(coffee);      // add sugar
//
//        System.out.println(coffee.description());
//        System.out.println(coffee.cost());
//    }
//}
//
//interface Coffee {
//    double cost();
//    String description();
//}
//
//class SimpleCoffee implements Coffee {
//    @Override
//    public double cost() {
//        return 50;
//    }
//    @Override
//    public String description() {
//        return "Simple ";
//    }
//}
//abstract class CoffeeDecorator implements Coffee {
//    protected Coffee coffee;
//    public CoffeeDecorator(Coffee coffee) {
//        this.coffee = coffee;
//    }
//}
//class MilkDecorator extends CoffeeDecorator {
//
//    public MilkDecorator(Coffee coffee) {
//        super(coffee);
//    }
//    @Override
//    public double cost() {
//        return coffee.cost() + 20;
//    }
//
//    @Override
//    public String description() {
//        return coffee.description() + ", Milk";
//    }
//}
//
//
//class SugarDecorator extends CoffeeDecorator {
//
//    public SugarDecorator(Coffee coffee) {
//        super(coffee);
//    }
//
//    @Override
//    public double cost() {
//        return coffee.cost() + 10;
//    }
//
//    @Override
//    public String description() {
//        return coffee.description() + ", Sugar";
//    }
//}
