package org.example.service;

import org.example.Repository.ConsultaRepository;
import org.example.Repository.ProdutoRepository;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class MenuService {

    private final EntityManager em;

    public MenuService(EntityManager em) {
        if (em == null) {
            throw new IllegalArgumentException("Erro: EntityManager não pode ser nulo!");
        }
        this.em = em;
    }

    public void abrirMenu() {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;
        while (executando) {
            System.out.println("\n======= MENU =======");
            System.out.println("1- Gerenciar Paciente");
            System.out.println("2- Gerenciar Medico");
            System.out.println("3- Agendar consulta.");
            System.out.println("4- Prontuarios.");
            System.out.println("5- Estoque de insumos.");
            System.out.println("6- Exames");
            System.out.println("7- Relatorios.");
            System.out.println("8- Sair ");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Gerenciar Paciente");
                    PacienteService pacienteService = new PacienteService();
                    pacienteService.printMenu(sc, pacienteService);
                    break;

                case 2:
                    System.out.println("Gerenciar Medico");
                    MedicoService medicoService = new MedicoService();
                    medicoService.printMenu(sc, medicoService);
                    break;

                case 3:
                    System.out.println("Agendar Consulta");
                    ConsultaService consultaService = new ConsultaService();
                    consultaService.printMenu(sc, consultaService);
                    break;

                case 4:
                    System.out.println("Prontuarios");
                    ProntuarioService prontuarioService = new ProntuarioService();
                    prontuarioService.printMenu(sc);
                    break;

                case 5:
                    System.out.println("Gerenciar Estoque");
                    ProdutoRepository produtoRepository = new ProdutoRepository(em); // Passando EntityManager corretamente
                    ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

                    produtoServices.printMenu(sc, produtoServices);
                    break;

                case 6:
                    System.out.println("Exame");
                    break;

                case 7:
                    ConsultaRepository consultaRepository = new ConsultaRepository(em); // Passando EntityManager
                    boolean saida = true;
                    while (saida) {
                        System.out.println("\n=== Relatorios. ===");
                        System.out.println("1 - Relatório de Pacientes por Médico");
                        System.out.println("2 - Relatório de Consultas por Paciente");
                        System.out.println("3 - Sair");
                        System.out.print("Escolha uma opção: ");
                        int op = sc.nextInt();
                        sc.nextLine();

                        switch (op) {
                            case 1:
                                System.out.println("Relatório de Pacientes por Médico");
                                System.out.print("Digite o CRM do médico para gerar o relatório: ");
                                String crm = sc.nextLine();
                                RelatorioPacienteMedicoService relatorioPacienteMedicoService = new RelatorioPacienteMedicoService(crm, consultaRepository);
                                relatorioPacienteMedicoService.gerarRelatorio();
                                break;
                            case 2:
                                System.out.println("Relatório de Consultas por Paciente");
                                System.out.print("Digite o CPF do paciente para gerar o relatório: ");
                                String cpf = sc.nextLine();
                                RelatorioConsultaPacienteService relatorioConsultaPacienteService = new RelatorioConsultaPacienteService(cpf, consultaRepository);
                                relatorioConsultaPacienteService.gerarRelatorio();
                                break;
                            case 3:
                                System.out.println("Voltando...");
                                saida = false;
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    }
                    break;

                case 8:
                    System.out.println("\nSaindo...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}