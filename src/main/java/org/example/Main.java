package org.example;

import org.example.repository.ProdutoRepository;
import org.example.service.ProdutoServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializa Hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        ProdutoRepository produtoRepository = new ProdutoRepository(session);
        ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Controle de Estoque ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Estoque");
            System.out.println("3 - Atualizar Quantidade");
            System.out.println("4 - Remover Produto");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            if (!entrada.matches("\\d+")) { // Verifica se é um número
                System.out.println("Opção inválida! Digite um número entre 1 e 5.");
                continue;
            }

            int opcao = Integer.parseInt(entrada);

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
                    System.out.println("Saindo...");
                    scanner.close();
                    session.close();
                    factory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número entre 1 e 5.");
            }
        }
    }
}