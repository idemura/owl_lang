package id.owl_lang;

public class App {
    public String getGreeting() {
        return "Hello";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
