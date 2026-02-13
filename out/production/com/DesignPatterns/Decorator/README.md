# Decorator (Behavioural)

-> what is solves
-> i want to add a new behaviour to an object without modifying it's class and without creating tons of classes

-> imagine you have coffee machine and you are setting price and description
-> Simple Coffee
-> Coffee + Milk
-> Coffee + Sugar
-> Coffee + Milk + Sugar

-> naive way
public class Coffee {

    boolean milk;
    boolean sugar;

    public Coffee(boolean milk, boolean sugar) {
        this.milk = milk;
        this.sugar = sugar;
    }

    public double cost() {
        double cost = 50;
        if (milk) cost += 20;
        if (sugar) cost += 10;
        return cost;
    }
}
-> now this does not follow OCP principle

-> using subclasses
class SimpleCoffee {
    public double cost() {
    return 50;
    }
}
class MilkCoffee extends SimpleCoffee {
    @Override
    public double cost() {
    return super.cost() + 20;
    }
}
class SugarCoffee extends SimpleCoffee {
    @Override
    public double cost() {
    return super.cost() + 10;
    }
}

class MilkSugarCoffee extends SimpleCoffee {
    @Override
    public double cost() {
    return super.cost() + 30;
    }
}
-> now here subclasses are increasing by P&C you will get to know with each feacture 
-> how many new classes will be formed


-> now 3rd way
-> instead of creating new classes can we wrap an object and add behaviour
-> coffee stays coffee
-> extra feature wraps coffee
-> wrappers can be stacked
-> this behaviour leads to decorator



