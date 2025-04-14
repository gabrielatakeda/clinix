package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estoque") // Retornando para "produto"
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private int nivelMinimo;

    @Column
    private LocalDate dataValidade;

    @Column(nullable = false)
    private String nome;

    public ProdutoEntity() {}

    public ProdutoEntity(LocalDate dataCadastro, int quantidade, int nivelMinimo, LocalDate dataValidade, String nome) {
        this.dataCadastro = dataCadastro;
        this.quantidade = quantidade;
        this.nivelMinimo = nivelMinimo;
        this.dataValidade = dataValidade;
        this.nome = nome;
    }

    // Getters e Setters
    public Long getIdProduto() { return idProduto; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public int getQuantidade() { return quantidade; }
    public int getNivelMinimo() { return nivelMinimo; }
    public LocalDate getDataValidade() { return dataValidade; }
    public String getNome() { return nome; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setNivelMinimo(int nivelMinimo) { this.nivelMinimo = nivelMinimo; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    public void setNome(String nome) { this.nome = nome; }

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