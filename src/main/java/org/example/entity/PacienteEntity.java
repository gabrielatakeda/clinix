package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "cpf", unique = true) //CPF é único, não pode se repetir
    private String cpf;

    @Column(name = "medico")
    private String medico;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "fk_usuario")
    private Long fk_usuario;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> endereco = new ArrayList<>();

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> consulta = new ArrayList<>();

    @OneToMany(mappedBy = "amostralab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> amostralab = new ArrayList<>();

    public PacienteEntity(){}

    public PacienteEntity(Long id, String nome, int idade, LocalDate data, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.data = data;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<EnderecoEntity> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<EnderecoEntity> endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Nome: "  + nome +
               "\nIdade: " + idade ;
    }
}