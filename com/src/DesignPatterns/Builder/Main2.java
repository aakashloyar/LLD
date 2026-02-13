package DesignPatterns.Builder;
//suppose you want to initialise a class
//some attributes are fixed
//some are optional
//so you will have multiple constructor
//now instead of that use builder pattern
public class Main2 {
    public static void main(String[] args) {
        Car c=new Car.Builder(2,3).c(2).build();
        System.out.println(c);
    }
}
class Car{
    //important
    int a, b;
    //optional
    int c, d, e;

    private Car(Builder build) {
        this.a=build.a;
        this.b=build.b;
        this.c=build.c;
        this.d=build.d;
        this.e=build.e;
    }
    static class Builder{
        private int a, b, c, d, e;
        Builder(int a, int b){
            this.a=a;
            this.b=b;
        }
        Builder c(int c){
            this.c=c;
            return this;
        }
        Builder d(int d) {
            this.d=d;
            return this;
        }
        Builder e(int e) {
            this.e=e;
            return this;
        }
        Car build() {
            return new Car(this);
        }

    }
}
