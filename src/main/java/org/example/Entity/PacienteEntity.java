package org.example.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "medico")
    private String medico;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "fk_usuario")
    private Long fk_usuario;

    public PacienteEntity(){}

    public PacienteEntity(Long id, String nome, int idade, String medico, LocalDate data, Long fk_usuario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.medico = medico;
        this.data = data;
        this.fk_usuario = fk_usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getMedico() {
        return medico;
    }

    public LocalDate getData() {
        return data;
    }

    public Long getFk_usuario() {
        return fk_usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setFk_usuario(Long fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    @Override
    public String toString() {
        return "PacienteEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", data=" + data +
                ", medico=" + medico +
                '}';
    }

}