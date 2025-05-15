package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estoque")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque") // Ajuste para corresponder ao banco de dados
    private Long idProduto;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "estoque_minimo", nullable = false) // Ajuste para corresponder à tabela SQL
    private int nivelMinimo;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "nome_produto", nullable = false)
    private String nome;

    public ProdutoEntity() {}

    public ProdutoEntity(LocalDate dataCadastro, int quantidade, int nivelMinimo, LocalDate dataValidade, String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Nome do produto não pode ser nulo ou vazio!");
        }
        this.dataCadastro = dataCadastro;
        this.quantidade = quantidade;
        this.nivelMinimo = nivelMinimo;
        this.dataValidade = dataValidade;
        this.nome = nome;
    }

    // Getters e Setters
    public Long getIdProduto() {
        return idProduto;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public String getNome() {
        return nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: Nome do produto não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ID: " + idProduto +
                "\nNome: " + nome +
                "\nQuantidade: " + quantidade +
                "\nNivel Mínimo: " + nivelMinimo +
                "\nData Cadastro: " + dataCadastro +
                "\nData Validade: " + (dataValidade != null ? dataValidade : "Sem validade") +
                "\n--------------------------------------------";
    }
}