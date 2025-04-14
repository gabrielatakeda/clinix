package org.example.service;

import org.example.repository.ConsultaRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);
    boolean executando = true;
    while (executando) {
        System.out.println("\n======= MENU =======");
        System.out.println("1- Cadastrar Paciente");
        System.out.println("2- Cadastrar Medico");
        System.out.println("3- Agendar consulta.");
        System.out.println("4- Prontuarios.");
        System.out.println("5- Estoque de insumos.");
        System.out.println("6- Relatorios.");
        System.out.println("7- Sair ");
        System.out.print("Escolha uma opção: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Cadastrar Paciente");
                break;

            case 2:
                System.out.println("Cadastrar Medico");
                break;

            case 3:
                System.out.println("Agendar Consulta");
                break;

            case 4:
                System.out.println("Prontuarios");
                break;
            case 5:
                ProdutoServices produtoServices;
                boolean saida = true;
                while (saida) {
                    System.out.println("\n=== Controle de Estoque ===");
                    System.out.println("1 - Cadastrar Produto");
                    System.out.println("2 - Listar Estoque");
                    System.out.println("3 - Atualizar Quantidade");
                    System.out.println("4 - Remover Produto");
                    System.out.println("5 - Sair");
                    System.out.print("Escolha uma opção: ");
                    int opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            produtoServices.cadastrarProduto();
                            break;
                        case 2:
                            produtoServices.listarProdutos();
                            break;
                        case 3:
                            produtoServices.atualizarQuantidade();
                            break;
                        case 4:
                            produtoServices.removerProduto();
                            break;
                        case 5:
                            System.out.println("Voltando...");
                            saida = false;
                            break;
                        default:
                            System.out.println("\nOpção inválida! Digite um número entre 1 e 5.");
                            break;
                    }
                }
                break;
            case 6:
                boolean saida = true;
                while (saida) {
                    System.out.println("\n=== Relatorios. ===");
                    System.out.println("1 - Relatório de Pacientes por Médico");
                    System.out.println("2 - Relatório de Consultas por Paciente");
                    System.out.println("3 - Sair");
                    System.out.print("Escolha uma opção: ");
                    int opcao = sc.nextInt();
                    switch (opcao) {
                        case 1:
                            System.out.println("Relatório de Pacientes por Médico");
                            System.out.print("Digite o CRM do medico para gerar o relatório: ");
                            String crm = sc.nextLine();
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
                        case 4:
                            System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                            break;
                    }
                }
                break;

            case 7:
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
