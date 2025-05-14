package org.example;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;
import org.example.service.AmostrasLabService;
import org.example.service.ConsultaService;

import org.example.repository.*;
import org.example.service.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class Main {


    //testando
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

        // Instancia os repositórios passando EntityManager onde necessário
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
                    System.out.print("\nDigite seu e-mail ou CPF: ");
                    String loginOuCpf = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    if (usuarioServices.autenticarUsuario(loginOuCpf, senha)) {
                        System.out.println("\nLogin bem-sucedido!");
                        menu.abrirMenu();
                    } else {
                        System.out.println("\nLogin falhou. Verifique suas credenciais.");
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