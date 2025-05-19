package org.example.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "paciente")
@Table (name = "paciente")
public class PacienteEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecos = new ArrayList<>();

    public PacienteEntity(){ //Construtor vazio

    }

    public PacienteEntity(Long id, String nome, String cpf, LocalDate dataNascimento, String telefone, List<EnderecoEntity> enderecos){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNomeCompleto(String nomeCompleto){
        this.nome = nomeCompleto;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento(){
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public List<EnderecoEntity> getEnderecos(){
        return enderecos;
    }

    public void setEnderecos(List<EnderecoEntity> enderecos){
        this.enderecos = enderecos;
    }
}