package DesignPatterns.Decorator;

public class Main {
    public static void main(String[] args) {
        Milk milk=new SimpleMilk();
        System.out.println(milk.amount());
        milk= new TurmericDecorator(milk);
        System.out.println(milk.amount());
        milk=new ButterDecorator(milk);
        System.out.println(milk.amount());
    }
}
interface Milk{
    int amount();
}
class SimpleMilk implements Milk{
    public int amount() {
        return 10;
    }
}
abstract class MilkDecorator implements Milk {
    Milk md;
    MilkDecorator(Milk milk) {
        this.md=milk;
    }
}
class TurmericDecorator extends MilkDecorator{
    TurmericDecorator(Milk milk) {
        super(milk);
    }

    @Override
    public int amount() {
        return this.md.amount()+20;
    }
}
class ButterDecorator extends MilkDecorator{
    ButterDecorator(Milk milk) {
        super(milk);
    }
    @Override
    public int amount() {
        return this.md.amount()+30;
    }
}

//here the problem is supppose you want milk with turmeric, Butter, icecream
//suppose if you will follow making a basic class with any methods that add amount to final amount
//this voilated Open Close Principle

//second method is using interface
//then there are many cases as you can see by P & C
//so now use decorator
