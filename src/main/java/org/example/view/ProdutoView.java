package org.example.view;

import org.example.controller.ProdutoController;

import java.util.Scanner;

public class ProdutoView {

    ProdutoController produtoController = new ProdutoController();
    Scanner sc = new Scanner(System.in);

    public void printMenu(){
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
                    produtoController.cadastrarProduto();
                    break;
                case 2:
                    produtoController.listarProdutos();
                    break;
                case 3:
                    produtoController.atualizarQuantidade();
                    break;
                case 4:
                    produtoController.removerProduto();
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

    }

}
