package org.example;


import org.example.repository.UsuarioRepository;
import org.example.service.UsuarioServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.entity.RelatorioEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.RelatorioRepository;
import org.example.service.RelatorioService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.security.auth.login.Configuration;
import java.time.LocalDate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Inicializa Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        UsuarioRepository usuarioRepository = new UsuarioRepository(session);
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema de Login ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            if (!entrada.matches("\\d+")) {
                System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                continue;
            }

            int opcao = Integer.parseInt(entrada);

            switch (opcao) {
                case 1:
                    usuarioServices.cadastrarUsuario(); // Retorna para o cadastro
                    break;

                case 2:
                    System.out.print("\nDigite seu e-mail ou CPF: ");
                    String loginOuCpf = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (usuarioServices.autenticarUsuario(loginOuCpf, senha)) {
                        System.out.println("\nLogin bem-sucedido!");
                        return;
                    } else {
                        System.out.println("\nLogin falhou. Verifique suas credenciais.");
                    }
                    break;

                case 3:
                    System.out.println("\nSaindo...");
                    scanner.close();
                    session.close();
                    factory.close();
                    System.exit(0);
                    break;

        Scanner sc = new Scanner(System.in);
        EntityManager em = CustomizerFactory.getEntityManager();

        RelatorioService relatorioService = new RelatorioService();

        RelatorioRepository relatorioRepository = new RelatorioRepository(em);

        boolean executando = true;

        while (executando) {
            System.out.println("\n======= MENU DE RELATÓRIOS =======");
            System.out.println("1 - Relatório de Consultas por Médico");
            System.out.println("2 - Relatório de Pacientes por Médico");
            System.out.println("3 - Relatório de Exames Realizados");
            System.out.println("4 - Relatório de Estoque de Insumos Médicos");
            System.out.println("5 - Relatório de Consultas por Paciente");
            System.out.println("6 - Relatório de Prontuários por Paciente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o CRM do médico: ");
                    String crm = sc.nextLine();
                    System.out.print("Data de início (AAAA-MM-DD): ");
                    LocalDate inicio1 = LocalDate.parse(sc.nextLine());
                    System.out.print("Data de fim (AAAA-MM-DD): ");
                    LocalDate fim1 = LocalDate.parse(sc.nextLine());

                    relatorioService.gerarRelatorioConsultasPorMedico(crm,
                            inicio1.atStartOfDay(),
                            fim1.atTime(23, 59, 59));
                    break;

                case 2:
                    System.out.print("Digite o CRM do médico: ");
                    String crm2 = sc.nextLine();
                    relatorioService.gerarRelatorioPacientesPorMedico(crm2);
                    break;

                case 3:
                    System.out.print("Data de início (AAAA-MM-DD): ");
                    LocalDate inicio3 = LocalDate.parse(sc.nextLine());
                    System.out.print("Data de fim (AAAA-MM-DD): ");
                    LocalDate fim3 = LocalDate.parse(sc.nextLine());
                    relatorioService.gerarRelatorioExamesRealizados(
                            inicio3.atStartOfDay(),
                            fim3.atTime(23, 59, 59));
                    break;

                case 4:
                    relatorioService.gerarRelatorioEstoque();
                    break;

                case 5:
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = sc.nextLine();
                    relatorioService.gerarRelatorioConsultasPorPaciente(cpf);
                    break;

                case 6:
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf2 = sc.nextLine();
                    relatorioService.gerarRelatorioProntuariosPorPaciente(cpf2);
                    break;

                case 0:
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();


                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;
            }
        }
    }
}