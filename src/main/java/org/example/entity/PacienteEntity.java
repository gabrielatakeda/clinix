package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "paciente")
public class PacienteEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nomeCompleto;

    @Column(name = "cpf", unique = true) //CPF é único, não pode se repetir
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    //Um paciente pode ter vários endereços
    //Salvar, atualizar ou deletar o paciente, essas ações também afetam os endereços
    //Se um endereço for removido da lista, ele também será removido do banco
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecos = new ArrayList<>();

    public PacienteEntity(){ //Construtor vazio

    }

    public PacienteEntity(Long id, String nomeCompleto, String cpf, LocalDate dataNascimento, String telefone, List<EnderecoEntity> enderecos){ //Construtor com os atributos
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    //Metodos getters e setters, permitem acessar e modificar os atributos
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
    }
}