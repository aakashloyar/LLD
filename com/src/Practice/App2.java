package Practice;

public class App2 {
    public static void main(String[] args) {
        Car c=new Car.Builder(2,3).C(4).build();
        //new Car.Builder(2,3);
    }
}

class Car{
    int a,b,c,d;
    Car(Builder build) {
        this.a=build.a;
        this.b=build.b;
        this.c=build.c;
        this.d=build.d;
    }
    public static class Builder {
        int a,b,c,d;
        Builder(int a,int b) {
            this.a=a;
            this.b=b;
        }
        Builder C(int c) {
            this.c=c;
            return this;
        }
        Builder D(int d) {
            this.d=d;
            return this;
        }
        Car build() {
            return new Car(this);
        }
    }
}