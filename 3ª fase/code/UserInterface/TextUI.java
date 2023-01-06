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

    // Leitura
    private Scanner scin;

    public void run() throws IOException {
        Menu.Logo();

        System.out.println("\n\033[1;35mBem vindo ao Sistema da Loja!\033[0m\n");
        this.menuInicial();

        System.out.println("Saindo!...");
    }

    public TextUI() {
        Menu menuInicial = new Menu(new String[] { "Jogador", "Guest" });

        Menu menuJogador = new Menu(new String[] { "Criar conta", "Fazer login" });
        // se entrar no "Criar conta" fazer "inserir username, password e versão
        // se entrar no "Fazer login" pedir username e password

        Menu menuCampeonato = new Menu(new String[] {"Consultar Campeonatos","Ver Ranking global"}); // listar campeonatos existentes
        Menu menuCircuito = new Menu(new String[] {}); // listar circuitos existentes
        Menu menuCorrida = new Menu(new String[] {}); // listar corridas existentes
        Menu menuCarro = new Menu(new String[] {}); // listar carros existentes
        Menu menuPiloto = new Menu(new String[] {}); // listar pilotos existentes

        // simular corrida .. "Visualizar Ranking Global"
        Menu menuRanking = new Menu(new String[] {}); // listar ranking global



        menuInicial.setHandler(1, () -> menuJogador.runOnce());
        menuInicial.setHandler(2, () -> menuCampeonato.runOnce());
        menuJogador.setHandler(1, () -> criarConta.runOnce());
        menuJogador.setHandler(2, () -> fazerLogin.runOnce());


        menuCampeonato.setHandler(1, () -> {
            System.out.println("Lista de Campeonatos");
            for (int i = 0; i < campeonatos.size(); i++) {
                System.out.println((i + 1) + " - " + campeonatos.get(i).getNome());
            }

            System.out.print("Selecione um campeonato: ");
            int op = is.nextInt();
            if (op > 0 && op <= campeonatos.size());
        });
        menuCampeonato.setHandler(2, () -> menuRanking.runOnce());

        this.model = new TurmasFacade();

    /**
     * Executa o menu principal e invoca o método correspondente à opção
     * selecionada.
     */
    

    // Métodos auxiliares
    private void trataAdicionarAluno() {
        try {
            System.out.println("Número da novo aluno: ");
            String num = scin.nextLine();
            if (!this.model.existeAluno(num)) {
                System.out.println("Nome da novo aluno: ");
                String nome = scin.nextLine();
                System.out.println("Email da novo aluno: ");
                String email = scin.nextLine();
                this.model.adicionaAluno(new Aluno(num, nome, email));
                System.out.println("Aluno adicionado");
            } else {
                System.out.println("Esse número de aluno já existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataConsultarAluno() {
        try {
            System.out.println("Número a consultar: ");
            String num = scin.nextLine();
            if (this.model.existeAluno(num)) {
                System.out.println(this.model.procuraAluno(num).toString());
            } else {
                System.out.println("Esse número de aluno não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataListarAlunos() {
        // Scanner scin = new Scanner(System.in);
        try {
            System.out.println(this.model.getAlunos().toString());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataAdicionarTurma() {
        // Scanner scin = new Scanner(System.in);
        try {
            System.out.println("Número da turma: ");
            String tid = scin.nextLine();
            if (!this.model.existeTurma(tid)) {
                System.out.println("Sala: ");
                String sala = scin.nextLine();
                System.out.println("Edifício: ");
                String edif = scin.nextLine();
                System.out.println("Capacidade: ");
                int cap = scin.nextInt();
                scin.nextLine(); // Limpar o buffer depois de ler o inteiro
                this.model.adicionaTurma(new Turma(tid, new Sala(sala, edif, cap)));
                System.out.println("Turma adicionada");
            } else {
                System.out.println("Esse número de turma já existe!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataMudarSalaTurma() {
        try {
            System.out.println("Número da turma: ");
            String tid = scin.nextLine();
            if (this.model.existeTurma(tid)) {
                System.out.println("Sala: ");
                String sala = scin.nextLine();
                System.out.println("Edifício: ");
                String edif = scin.nextLine();
                System.out.println("Capacidade: ");
                int cap = scin.nextInt();
                scin.nextLine(); // Limpar o buffer depois de ler o inteiro
                this.model.alteraSalaDeTurma(tid, new Sala(sala, edif, cap));
                System.out.println("Sala da turma alterada");
            } else {
                System.out.println("Esse número de turma não existe!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataListarTurmas() {
        try {
            System.out.println(this.model.getTurmas().toString());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataAdicionarAlunoATurma() {
        try {
            System.out.println("Número da turma: ");
            String tid = scin.nextLine();
            if (this.model.existeTurma(tid)) {
                System.out.println("Número do aluno: ");
                String num = scin.nextLine();
                if (this.model.existeAluno(num)) {
                    this.model.adicionaAlunoTurma(tid, num);
                    System.out.println("Aluno adicionado à turma");
                } else {
                    System.out.println("Esse número de aluno não existe!");
                }
            } else {
                System.out.println("Esse número de turma não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataRemoverAlunoDaTurma() {
        try {
            System.out.println("Número da turma: ");
            String tid = scin.nextLine();
            if (this.model.existeTurma(tid)) {
                System.out.println("Número do aluno: ");
                String num = scin.nextLine();
                if (this.model.existeAlunoEmTurma(tid, num)) {
                    this.model.removeAlunoTurma(tid, num);
                    System.out.println("Aluno removido da turma");
                } else {
                    System.out.println("Esse número de aluno não existe na turma!");
                }
            } else {
                System.out.println("Esse número de turma não existe!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void trataListarAlunosTurma() {
        try {
            System.out.println("Número da turma: ");
            String tid = scin.nextLine();
            List<Aluno> als = this.model.getAlunos(tid).stream()
                    .map((na) -> this.model.procuraAluno(na))
                    .collect(Collectors.toList());
            System.out.println(als);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
}
