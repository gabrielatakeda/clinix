package org.example.service;

import org.example.entity.ProdutoEntity;
import org.example.repository.ProdutoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProdutoServices {
    private ProdutoRepository produtoRepository;
    private Scanner scanner = new Scanner(System.in);

    public ProdutoServices(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

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

        ProdutoEntity novoProduto = new ProdutoEntity(LocalDate.now(), quantidade, nivelMinimo, dataValidade, nome);
        produtoRepository.salvarProduto(novoProduto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos(){
        List<ProdutoEntity> produtos = produtoRepository.buscarTodos();

        if (produtos.isEmpty()){
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n=== Estoque ===");
        for (ProdutoEntity produto : produtos){
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

        ProdutoEntity produto = produtoRepository.buscarPorId(id).orElse(null);
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

        ProdutoEntity produto = produtoRepository.buscarPorId(id).orElse(null);
        if (produto == null){
            System.out.println("O produto não existe.");
            return;
        }

        produtoRepository.removerProduto(id);
        System.out.println("Produto removido com sucesso!");
    }
}