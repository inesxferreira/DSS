package UserInterface;

import SimuladorLN.*;

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

    public TextUI() {
        // Criar o menu
        this.menu = new Menu(new String[] {
                // "Listar Campeonatos",
                "Configurar Campeonato",

                "Configurar Corrida",
                "Listar Circuitos",
                "Consultar Circuito",

                "Adicionar Carro",
                "Consultar Carro",
                "Listar Carros",
                "Remover Carro",
                "Adicionar Piloto",
                "Consultar Piloto",
                "Listar Pilotos",
                "Remover Piloto",
                "Adicionar Participante",
                "Consultar Participante",
                "Listar Participante",
                "Remover Participante"
        });
        this.menu.setHandler(1, this::adicionarCampeonato);
        this.menu.setHandler(2, this::consultarCampeonato);
        this.menu.setHandler(3, this::listarCampeonatos);
        this.menu.setHandler(4, this::removerCampeonato);

        this.menu.setHandler(5, this::adicionarCircuito);
        this.menu.setHandler(6, this::consultarCircuito);
        this.menu.setHandler(7, this::listarCircuitos);
        this.menu.setHandler(8, this::removerCircuito);

        this.menu.setHandler(9, this::adicionarCarro);
        this.menu.setHandler(10, this::consultarCarro);
        this.menu.setHandler(11, this::listarCarros);
        this.menu.setHandler(12, this::removerCarro);

        this.menu.setHandler(13, this::adicionarPiloto);
        this.menu.setHandler(14, this::consultarPiloto);
        this.menu.setHandler(15, this::listarPilotos);
        this.menu.setHandler(16, this::removerPiloto);

        this.menu.setHandler(17, this::adicionarParticipante);
        this.menu.setHandler(18, this::consultarParticipante);
        this.menu.setHandler(19, this::listarParticipantes);
        this.menu.setHandler(20, this::removerParticipante);

        this.model = new TurmasFacade();

        // pré-condições
        this.menu.setPreCondition(7,
                () -> this.model.haAlunos() && this.model.haTurmas());

        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção
     * selecionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("Saindo!...");
    }

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
