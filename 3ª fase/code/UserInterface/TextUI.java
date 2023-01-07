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

    // Leitura
    private Scanner scin;

    public TextUI() {
        this.model = new SimuladorLNFacade();
        this.scan = new Scanner(System.in);
    }

    public void run() throws IOException {
        Menu.Logo();

        System.out.println("\n\033[1;35m Bem vindo ao FÓRMULA 1! \033[0m\n");
        this.menu();

        System.out.println("Saindo!...");
    }

    public void menu() {
        Menu menuInicial = new Menu(new String[] {
                "Fazer login",
                "Criar conta",
                "Menu Campeonato", });
        menuInicial.setPreCondition(1, this.model.existeConta());

        menuInicial.setHandler(1, () -> {
            System.out.print("Insira username: ");
            String usern = scan.nextLine();
            System.out.print("Insira password: ");
            String pass = scan.nextLine();
            /*
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
            /*
             * (this.model.conta(id, pass, versao)) { //fazer put(id) na ContaDAO
             * System.out.print("\nConta criada com sucesso.\n\n");
             * }
             */
        });
        menuInicial.setHandler(3, () -> gestaoCampeonato());

    }

    public void gestaoCampeonato() {
        Menu menuCampeonato = new Menu(new String[] {
                "Consultar Campeonatos", });

        menuCampeonato.setPreCondition(1, this.model.existeCampeonato());
        menuCampeonato.setHandler(1, () -> {
            System.out.print("Escolha um campeonato: ");
            String camp = scan.nextLine(); // id do campeonato
        });

        Menu menuDetalhes = new Menu(new String[] {
                "Menu Circuito",
                "Menu Carro",
                "Menu Piloto", });

        menuDetalhes.setHandler(1, () -> gestaoCircuito());
        menuDetalhes.setHandler(2, () -> gestaoCarro());
        menuDetalhes.setHandler(3, () -> gestaoPiloto());

    }

    public void gestaoCircuito() {
        Menu menuCircuito = new Menu(new String[] {
                "Consultar Circuitos", });

        menuCircuito.setHandler(1, () -> {
            System.out.print("Escolha um circuito: ");
            String circ = scan.nextLine(); // id do circuito
        });
    }
    /*
     * Menu menuJogador = new Menu(new String[] { "Criar conta", "Fazer login" });
     * // se entrar no "Criar conta" fazer "inserir username, password e versão
     * // se entrar no "Fazer login" pedir username e password
     * 
     *
     * "Ver Ranking global" }); // listar
     * // campeonatos
     * // existentes
     * Menu menuCircuito = new Menu(new String[] {}); // listar circuitos existentes
     * Menu menuCorrida = new Menu(new String[] {}); // listar corridas existentes
     * Menu menuCarro = new Menu(new String[] {}); // listar carros existentes
     * Menu menuPiloto = new Menu(new String[] {}); // listar pilotos existentes
     * 
     * // simular corrida .. "Visualizar Ranking Global"
     * Menu menuRanking = new Menu(new String[] {}); // listar ranking global
     */
}
