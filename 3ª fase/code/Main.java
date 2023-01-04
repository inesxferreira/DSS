package code;

public class Main {

    public static void main(String[] args) {
        try {
            new TextUI().run();
        } catch (Exception e) {
            System.out.println("Não arrancou: " + e.getMessage());
        }
    }

}
