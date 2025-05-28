package org.example.controller;

import org.example.model.entity.Produto;
import org.example.DAO.ProdutoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProdutoController {
    private ProdutoRepository produtoRepository;
    Scanner scanner = new Scanner(System.in);

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoController(){}

    public void cadastrarProduto(){
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Nivel Mínimo antes de alerta: ");
        int nivelMinimo = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataValidade = null;
        while (true) {
            System.out.print("Data de Validade (YYYY-MM-DD ou deixe vazio): ");
            String dataValidadeStr = scanner.nextLine();

            if (dataValidadeStr.isEmpty()){
                break;
            }

            try {
                dataValidade = LocalDate.parse(dataValidadeStr);
                break;
            } catch (Exception e) {
                System.out.println("Formato inválido! Use YYYY-MM-DD.");
            }
        }

        Produto novoProduto = new Produto(LocalDate.now(), quantidade, nivelMinimo, dataValidade, nome);
        produtoRepository.salvarProduto(novoProduto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos(){
        List<Produto> produtos = produtoRepository.buscarTodos();

        if (produtos.isEmpty()){
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n=== Estoque ===");
        for (Produto produto : produtos){
            System.out.println(produto);

            if (produto.getQuantidade() <= produto.getNivelMinimo()) {
                System.out.println("ALERTA: Estoque crítico! Produto próximo do esgotamento!");
            }

            System.out.println("--------------------------------------------");
        }
    }

    public void atualizarQuantidade(){
        System.out.print("Digite o ID do produto: ");
        Long id = scanner.nextLong();

        System.out.print("Nova quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Produto produto = produtoRepository.buscarPorId(id).orElse(null);
        if (produto == null){
            System.out.println("O produto não existe.");
            return;
        }

        produtoRepository.atualizarQuantidade(id, quantidade);
        System.out.println("Quantidade atualizada!");

        if (quantidade <= produto.getNivelMinimo()){
            System.out.println("ALERTA: Estoque crítico! Produto próximo do esgotamento!");
        }
    }

    public void removerProduto(){
        System.out.print("Digite o ID do produto a ser removido: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Produto produto = produtoRepository.buscarPorId(id).orElse(null);
        if (produto == null){
            System.out.println("O produto não existe.");
            return;
        }

        produtoRepository.removerProduto(id);
        System.out.println("Produto removido com sucesso!");
    }

    public void printMenu(Scanner sc, ProdutoController produtoController){
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