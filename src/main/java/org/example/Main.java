package org.example;


import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;
import org.example.service.AmostrasLabService;
import org.example.service.ConsultaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.example.repository.*;
import org.example.service.*;

import java.util.Scanner;

import org.example.repository.CustomizerFactory;
import org.example.repository.ConsultaRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean executando = true;
        //talvez isso nao possa ficar aqui, vai dar conflito com o customizerfactor usado pelos outros


        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        EntityManager em = CustomizerFactory.getEntityManager();

        UsuarioRepository usuarioRepository = new UsuarioRepository(session);
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

        ProdutoRepository produtoRepository = new ProdutoRepository(session);
        ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

        AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);
        MenuService menu = new MenuService();

        RelatorioRepository relatorioRepository = new RelatorioRepository(em);
        ConsultaRepository consultaRepository = new ConsultaRepository(em);
        PacienteService pacienteService = new PacienteService();
        ConsultaService consultaService = new ConsultaService();

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
                    usuarioServices.cadastrarUsuario(); // Retorna para o cadastro
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
                    session.close();
                    factory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;
            }
        }
    }
}




