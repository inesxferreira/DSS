package UserInterface;

import SimuladorLN.*;
import SimuladorLN.SSConta.Conta;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private ISimuladorLN model;

    // Menus da aplicação
    private Scanner scan;
    private int nJog;
    private Boolean admin;

    private Boolean flagCamp = false;
    private Boolean flagCarro = false;
    private Boolean flagPiloto = false;

    public TextUI() throws IOException {
        this.model = new SimuladorLNFacade();
        this.scan = new Scanner(System.in);
    }

    public void run() throws IOException {
        Menu.Logo();

        System.out.println("\n\033[1;35m Bem vindo ao FÓRMULA UM! \033[0m\n");
        nJog = 0;
        nJog = this.inicio();
        for (int i = 0; i < nJog; i++) {
            if (i == 0) {
                admin = true;
            } else {
                admin = false;
            }
            this.menuInicial();
        }
        // this.menuSimular();

        System.out.println("Saindo!...");
    }

    public int inicio() {
        System.out.println("Insira o número de jogadores:");
        String n = scan.nextLine();
        nJog = Integer.parseInt(n);
        return nJog;
    }

    /**
     * Estado - Menu Principal
     */
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

            if (loginValido) {
                menuJogador();
            } else
                System.out.println("Login inválido!");
        });

        menuInicial.setHandler(2, () -> {
            System.out.print("Insira username: ");

            String usern = scan.nextLine();
            System.out.print("Insira password: ");
            String pass = scan.nextLine();
            System.out.print("Insira a versão (Base/Premium): ");
            String versao = scan.nextLine();
            Boolean vs;
            // AQUI criar objeto conta

            switch (versao) {
                case "True":
                    vs = true;
                    break;
                case "False":
                    vs = false;
                    break;
                default:
                    vs = false;
            }

            Random random = new Random();
            Integer id = random.nextInt();

            // menuJogador();
            Conta c = new Conta();
            c.setIdConta(id);
            c.setUsername(usern);
            c.setPassword(pass);
            c.setVersao(vs);

            // this.model.getContaFacade().Conta(id,usern,pass,versao); //fazer put(id) na
            // ContaDAO
            this.model.getContaFacade().putConta(c);
            System.out.print("\nConta criada com sucesso.\n\n");

        });

        menuInicial.setHandler(3, () -> menuJogador());
        menuInicial.run();
    }

    public void menuJogador() {
        Menu menuJog = new Menu(new String[] {
                "Configurar Campeonato",
                "Ver Ranking Global" });
        menuJog.setHandler(1, () -> menuConfigCamp());
        menuJog.setHandler(2, () -> showRankingG());
        menuJog.run();
    }

    public void menuConfigCamp() {
        if (admin) {
            Menu menuAdmin = new Menu(new String[] {
                    "Escolher Campeonato",
                    "Escolher Carro",
                    "Escolher Piloto", });
            menuAdmin.setHandler(1, () -> showCamp());
            menuAdmin.setHandler(2, () -> showCarro());
            menuAdmin.setHandler(3, () -> showPiloto());
            if (flagCamp && flagCarro && flagPiloto) {
                System.out.println("Todos os dados inseridos com sucesso!");
                // Como este participante já selecionou tudo, vai para o próximo
            }
            menuAdmin.run();
        } else {
            Menu menuJ = new Menu(new String[] {
                    "Escolher Carro",
                    "Escolher Piloto", });
            menuJ.setHandler(1, () -> showCarro());
            menuJ.setHandler(2, () -> showPiloto());
            if (flagCarro && flagPiloto) {
                System.out.println("Todos os dados inseridos com sucesso!");
                // Como este participante já selecionou tudo, vai para o próximo
            }
            menuJ.run();
        }
    }

    public void showRankingG() {
        // mostrar ranking global para o campeonato em causa

    }

    public void showCamp() {
        // mostrar lista de campeonatos disponiveis
        this.model.getCampeonatoFacade().listarCampeonatos();
        System.out.println("Escolha campeonato desejado:");
        String id = scan.nextLine();
        flagCamp = true;

        // para podermos selecionar ENTRE os varios campeonatos

    }

    public void showCarro() {
        // mostrar lista de carros disponiveis
        this.model.getCarroFacade().listarCarros();
        System.out.println("\nEscolha o carro desejado:");
        String id = scan.nextLine();
        flagCarro = true;
    }

    public void showPiloto() {
        // mostrar lista de carros disponiveis
        this.model.getCarroFacade().listarPilotos();
        System.out.println("\nEscolha o piloto desejado:");
        String id = scan.nextLine();
        flagPiloto = true;
    }
}
