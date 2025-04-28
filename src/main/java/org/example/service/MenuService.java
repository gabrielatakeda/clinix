package org.example.service;

import org.example.service.AmostrasLabService;
import org.example.repository.ConsultaRepository;

import org.example.repository.ProdutoRepository;



import java.util.Scanner;

public class MenuService {

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
                    AmostrasLabService amostrasLabService = new AmostrasLabService();

                    consultaService.printMenu(sc, consultaService, amostrasLabService);
                    break;

                case 4:
                    System.out.println("Prontuarios");
                    ConsultaService prontuarioService = new ConsultaService();
                    //prontuarioService.printMenu(sc, prontuarioService);
                    break;
                case 5:
                    System.out.println("Gerenciar Estoque");
                    ProdutoRepository produtoRepository = new ProdutoRepository();
                    ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

                    produtoServices.printMenu(sc, produtoServices);

                    break;
                case 6:
                    System.out.println("Exame");
                    break;
                case 7:
                    ConsultaRepository consultaRepository = new ConsultaRepository();
                    boolean saida = true;
                    while (saida) {
                        System.out.println("\n=== Relatorios. ===");
                        System.out.println("1 - Relatório de Pacientes por Médico");
                        System.out.println("2 - Relatório de Consultas por Paciente");
                        System.out.println("3 - Sair");
                        System.out.print("Escolha uma opção: ");
                        int op = sc.nextInt();
                        switch (op) {
                            case 1:
                                System.out.println("Relatório de Pacientes por Médico");
                                System.out.print("Digite o CRM do medico para gerar o relatório: ");
                                String crm;
                                crm = sc.nextLine();
                                RelatorioPacienteMedicoService relatorioPacienteMedicoService = new RelatorioPacienteMedicoService(crm, consultaRepository);
                                relatorioPacienteMedicoService.gerarRelatorio();
                                break;
                            case 2:
                                System.out.println("Relatório de Consultas por Paciente");
//                            ConsultaRepository consultaRepository = new ConsultaRepository(em);
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
                                System.out.println("Opcao Invalida");
                                break;
                        }
                    }
                    break;

                case 8:
                    System.out.println("\nSaindo...");
                    sc.close();
//                session.close();
//                factory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
