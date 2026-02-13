package DesignPatterns.Builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Aakash", "a@gmail.com")
                .age(25)
                .phone("9999999999")
                .address("Delhi")
                .build();

    }
}
