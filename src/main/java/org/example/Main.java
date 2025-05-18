package org.example;

import org.example.enums.TypeUser;
import org.example.service.ConsultaService;
import org.example.repository.*;
import org.example.service.*;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        // Inicializa Hibernate
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityManagerFactory.createEntityManager(); // Correção: Criando EntityManager corretamente

        if (em == null) {
            throw new IllegalStateException("Erro crítico: EntityManager está nulo. Verifique a inicialização.");
        }


        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

        ProdutoRepository produtoRepository = new ProdutoRepository(em);
        ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

        AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);
        RelatorioRepository relatorioRepository = new RelatorioRepository(em);
        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        PacienteService pacienteService = new PacienteService();
        ConsultaService consultaService = new ConsultaService();

        // Criando MenuService com EntityManager correto
        MenuService menu = new MenuService(em);

        while (executando) {
            System.out.println("---BEM VINDO AO CLINIX---");
            System.out.println("\n===CADASTRO / LOGIN ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = sc.nextLine();

            if (!entrada.matches("\\d+")) {
                System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                continue;
            }

            int opcao = Integer.parseInt(entrada);
            switch (opcao) {
                case 1:
                    usuarioServices.cadastrarUsuario();
                    break;
                case 2:
                    System.out.println("\n=== LOGIN ===");
                    System.out.print("Informe o tipo de usuário para login (ADMIN, PACIENTE, MEDICO, LAB, RECEPCAO): ");
                    String tipoLoginInput = sc.nextLine().toUpperCase();

                    TypeUser tipoLogin;
                    try {
                        tipoLogin = TypeUser.valueOf(tipoLoginInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de usuário inválido. Tente novamente.");
                        break;
                    }

                    String identificador = null;
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    switch (tipoLogin) {
                        case PACIENTE:
                            System.out.print("Digite seu CPF (somente números): ");
                            identificador = sc.nextLine();
                            if (!identificador.matches("\\d{11}")) {
                                System.out.println("CPF inválido.");
                                break;
                            }
                            break;

                        case MEDICO:
                            System.out.print("Digite seu CRM: ");
                            identificador = sc.nextLine();
                            if (identificador == null || identificador.trim().isEmpty()) {
                                System.out.println("CRM inválido.");
                                break;
                            }
                            break;

                        case LAB:
                        case RECEPCAO:
                            System.out.print("Digite seu nome de usuário: ");
                            identificador = sc.nextLine();
                            if (identificador == null || identificador.trim().isEmpty()) {
                                System.out.println("Usuário inválido.");
                                break;
                            }
                            break;

                        case ADMIN:
                            System.out.print("Digite seu e-mail: ");
                            identificador = sc.nextLine();
                            if (!identificador.contains("@")) {
                                System.out.println("E-mail inválido.");
                                break;
                            }
                            break;
                    }

                    if (identificador == null || identificador.trim().isEmpty()) {
                        System.out.println("Identificador inválido. Tente novamente.");
                        break;
                    }

                    // Passa o tipo também para autenticar corretamente
                    if (usuarioServices.autenticarUsuario(tipoLogin, identificador, senha)) {
                        menu.abrirMenu();
                    } else {
                        System.out.println("Falha no login.");
                    }

                    break;

                case 3:
                    System.out.println("\nSaindo...");
                    sc.close();
                    em.close(); // Fecha EntityManager
                    entityManagerFactory.close(); // Fecha Factory
                    System.exit(0);

                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;
            }
        }
    }
}
