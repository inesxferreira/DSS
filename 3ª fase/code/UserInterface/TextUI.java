package UserInterface;

import SimuladorLN.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private ISimuladorLN model;

    // Menus da aplicação
    private Menu menu;
    private Scanner scan;
    private int nJog;
    private Boolean admin;

    // Leitura
    private Scanner scin;

    public TextUI() throws IOException {
        this.model = new SimuladorLNFacade();
        this.scan = new Scanner(System.in);
    }

    public void run() throws IOException {
        Menu.Logo();

        System.out.println("\n\033[1;35m Bem vindo ao FÓRMULA 1! \033[0m\n");
        this.inicio();
        for (int i = 0; i < nJog; i++) {
            if (i == 0)
                admin = true;
            else
                admin = false;
            this.menuInicial();
        }
        // this.menuSimular();

        System.out.println("Saindo!...");
    }

    public void inicio() {
        System.out.println("Insira o número de jogadores:");
        String n = scan.nextLine();
        nJog = Integer.parseInt(n);
    }

    public void menuInicial() {
        Menu menuInicial = new Menu(new String[] {
                "Fazer login",
                "Criar conta",
                "Guest", });
        menuInicial.setHandler(1, () -> {
            System.out.print("Insira username: ");
            String usern = scan.nextLine();
            System.out.print("Insira password: ");
            String pass = scan.nextLine();
            boolean loginValido = this.model.getContaFacade().verificarCredenciais(usern, pass);
            /*
             * if (loginValido) {
             * menuJogador();
             * } else {
             * System.out.println("Login inválido!");
             * }/
             * /*
             * if (this.model.conta(id, pass, versao)) { //fazer containsKey(id) com a
             * ContaDAO e verificar se pass é igual à existente
             * System.out.print("\nLogin com sucesso.\n\n");
             * } else
             * System.out.print("\u001B[31m\nDados incorretos!\n\u001b[0m");
             */
        });
        menuInicial.setHandler(2, () -> {
            System.out.print("Insira username: ");

            String usern = scan.nextLine();
            System.out.print("Insira password: ");
            String pass = scan.nextLine();
            System.out.print("Insira a versão (Base/Premium): ");
            String versao = scan.nextLine();
            // AQUI criar objeto conta
            /*
             * menuJogador();
             * (this.model.conta(id, pass, versao)) { //fazer put(id) na ContaDAO
             * System.out.print("\nConta criada com sucesso.\n\n");
             * }
             */
        });
        // menuInicial.setHandler(3, () -> menuJogador());

    }
    /*
     * public void menuJogador() {
     * Menu menuJog = new Menu(new String[] {
     * "Configurar Campeonato",
     * "Ver Ranking Global" });
     * menuJog.setHandler(1, () -> menuConfigCamp());
     * menuJog.setHandler(2, () -> showRankingG());
     * }
     * 
     * public void menuConfigCamp() {
     * if (admin) {
     * Menu menuAdmin = new Menu(new String[] {
     * "Escolher Campeonato",
     * "Escolher Carro",
     * "Escolher Piloto", });
     * menuAdmin.setHandler(1, () -> showCamp());
     * menuAdmin.setHandler(2, () -> showCarro());
     * menuAdmin.setHandler(3, () -> showPiloto());
     * 
     * } else {
     * Menu menuJ = new Menu(new String[] {
     * "Escolher Carro",
     * "Escolher Piloto", });
     * menuJ.setHandler(1, () -> showCarro());
     * menuJ.setHandler(2, () -> showPiloto());
     * }
     * 
     * }
     */
}
