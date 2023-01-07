package UserInterface;

import java.util.*;

public class Menu {

    // Interfaces auxiliares
    public interface Handler {
        void execute();
    }

    public interface PreCondition {
        boolean validate();
    }

    // Leitura
    private static Scanner is = new Scanner(System.in);

    // Variáveis de instância
    private String titulo; // Titulo
    private List<String> opcoes; // Opções
    private List<PreCondition> disponivel; // Pré-condições
    private List<Handler> handlers; // Handlers

    // Construtor
    public Menu() {
        this.titulo = "Fórmula 1";
        this.opcoes = new ArrayList<>();
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
    }

    public Menu(String titulo, List<String> opcoes) {
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s -> {
            this.disponivel.add(() -> true);
            this.handlers.add(() -> System.out.println("\nOpção impossível!"));
        });
    }

    public Menu(List<String> opcoes) {
        this("Fórmula 1", opcoes);
    }

    public Menu(String titulo, String[] opcoes) {
        this(titulo, Arrays.asList(opcoes));
    }

    public Menu(String[] opcoes) {
        this(Arrays.asList(opcoes));
    }

    public void option(String name, PreCondition p, Handler h) {
        this.opcoes.add(name);
        this.disponivel.add(p);
        this.handlers.add(h);
    }

    public void runOnce() {
        int op;
        show();
        op = readOption();
        // verificar pré-condição
        if (op > 0 && !this.disponivel.get(op - 1).validate()) {
            System.out.println("Opção não existe!");
        } else if (op > 0) {
            // executar handler
            this.handlers.get(op - 1).execute();
        }
    }

    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // verificar pré-condição
            if (op > 0 && !this.disponivel.get(op - 1).validate()) {
                System.out.println("Opção impossível! Tente outra vez.");
            } else if (op > 0) {
                // executar handler
                this.handlers.get(op - 1).execute();
            }
        } while (op != 0);
    }

    /**
     * Método para registar uma pré-condição numa opção do menu
     *
     * @param i índice da opção(começa em 1)
     * @param b pré-condição a registar
     */
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i - 1, b);
    }

    // Métodos auxiliares

    private void show() {
        System.out.println("\n *** " + this.titulo + " *** ");
        for (int i = 0; i < this.opcoes.size(); i++) {
            System.out.print(i + 1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate() ? this.opcoes.get(i) : "---");
        }
        System.out.println("0 - Sair");
    }

    private int readOption() {
        int op;

        System.out.print("Opção: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        } catch (NumberFormatException e) { // Não foi inscrito um int
            op = -1;
        }
        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;

    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void Logo() {
        System.out.print("\n\033[H\033[2J");
        System.out.flush();
        System.out.println(
                ANSI_RED + "        UMFormula1       " + ANSI_GREEN
                        + ANSI_RESET);
    }

    public void setHandler(int i, Handler h) {
        this.handlers.set(i - 1, h);
    }

}
