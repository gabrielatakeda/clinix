package org.example.service;

import org.example.entity.EnderecoEntity;
import org.example.entity.PacienteEntity;
import org.example.repository.ConsultaRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
                PacienteService pacienteService = new PacienteService();
                System.out.println("=== MENU DE INSERÇÃO DE PACIENTE ===");


                //ele nao deve pedir o id do paciente, o programa gera automaticamente um id para cada paciente
                System.out.print("ID do paciente: ");
                long pacienteId = sc.nextLong();
                sc.nextLine(); // limpa o buffer

                System.out.print("Nome completo: ");
                String nomeCompleto = sc.nextLine();

                System.out.print("CPF: ");
                String cpf = sc.nextLine();

                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                String dataString = sc.nextLine();
                LocalDate dataNascimento = null;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataNascimento = LocalDate.parse(dataString, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
                    return; // encerra o programa ou volta ao menu
                }

                System.out.print("Telefone: ");
                String telefone = sc.nextLine();

                var pacienteTeste = new PacienteEntity(
                        null,
                        nomeCompleto,
                        cpf,
                        dataNascimento,
                        telefone, // telefone de exemplo
                        new ArrayList<>() // lista de endereços vazia
                );

                var listaEndereco = new ArrayList<EnderecoEntity>();
                String opcao;

                do {
                    System.out.println("\n=== INSERÇÃO DE ENDEREÇO ===");

                    System.out.print("Logradouro: ");
                    String logradouro = sc.nextLine();

                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();

                    System.out.print("Estado: ");
                    String estado = sc.nextLine();

                    System.out.print("Número: ");
                    int numero = sc.nextInt();
                    sc.nextLine(); // limpa o buffer

                    System.out.print("Esse é o endereço principal? (s/n): ");
                    String principalInput = sc.nextLine();
                    boolean isPrincipal = principalInput.equalsIgnoreCase("s");

                    listaEndereco.add(new EnderecoEntity(
                            null, logradouro, cidade, estado, numero, isPrincipal, pacienteTeste
                    ));

                    System.out.print("Deseja adicionar outro endereço? (s/n): ");
                    opcao = sc.nextLine();

                } while(opcao.equalsIgnoreCase("s"));

                var salvo = pacienteService.salvarPaciente(pacienteTeste, listaEndereco);

                if (salvo.getId() != null) {
                    System.out.println("Paciente salvo com sucesso! ID: " + salvo.getId());
                } else {
                    System.out.println("Paciente não foi salvo. CPF já existe!");
                }

                pacienteService.salvarPaciente(pacienteTeste, listaEndereco);

                sc.close();
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
