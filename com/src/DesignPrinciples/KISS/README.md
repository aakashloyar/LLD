# KISS - Keep It Simple Stupid

-> donot do overengineering 
-> keep things simple 
-> donot think which design pattern i can apply here
-> what is the simplest thing that solves today's problem
-> pattern are tools not goals

interface Operation {
    int execute(int a, int b);
}

class AddOperation implements Operation {
    public int execute(int a, int b) {
    return a + b;
    }
}

-> simply int sum = a+b
