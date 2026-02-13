# Builder (Creational)
class User {
    String name;
    int age;
    String email;
    String phone;
    String address;

    User(String name) { }
    User(String name, int age) { }
    User(String name, int age, String email) { }
    User(String name, int age, String email, String phone) { }
}

-> now here it looks messy here we have many methods to build it 

User user = new User();
user.setName("Aakash");
user.setAge(25);
-> Another bed approach using setters
-> Object can be partially built ,No immutability  ,Hard to enforce required fields 
-> object can be in incomplete/invalid state
