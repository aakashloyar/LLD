package Practice;

public class App3 {
    public static void main(String[] args) {
        Coffee coffee=new SimpleCoffee();
        coffee=new MilkCoffee(coffee);
        coffee=new SugarCoffee(coffee);
        System.out.println(coffee.price());
    }
}
interface Coffee {
    int price();
}
class SimpleCoffee implements Coffee {
    SimpleCoffee() {

    }
    public int price() {
        return 50;
    }
}
class MilkCoffee implements Coffee {
    Coffee coffee;
    MilkCoffee(Coffee coffee) {
        this.coffee=coffee;
    }
    public int price() {
        return this.coffee.price()+30;
    }
}

class SugarCoffee implements Coffee {
    Coffee coffee;
    SugarCoffee(Coffee coffee) {
        this.coffee=coffee;
    }
    public int price() {
        return this.coffee.price()+20;
    }
}
