
import UserInterface.*;

/**
 * @author Grupo 31
 */
public class Main {
    /**
     * O método main cria a aplicação e invoca o método run()
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            new TextUI().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nO sistema será encerrado agora.");
        System.out.println("\033[1;36m" + "Sessão Terminada!" + "\033[0m");
    }
}