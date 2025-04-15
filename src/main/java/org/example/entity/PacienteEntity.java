package org.example.Entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "paciente")
public class PacienteEntity{

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

    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id = id;
    }

    public String getNomeCompleto(){
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
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