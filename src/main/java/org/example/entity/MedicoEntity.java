package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Table(name = "medico")
@Entity(name = "medico)")
public class MedicoEntity { //Classe Médico com os atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "nome")
    private String nome;


    private String cpf;
    private String especialidade;
    private String telefone;
    private String email;
    @Column(name = "crm", unique = true)
    private String crm;

    public MedicoEntity(){ //Construtor vazio

    }

    public MedicoEntity(int ID, String nome, String cpf, String especialidade, String telefone, String email, String crm) {
        this.ID = ID;
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
        this.crm = crm;
    }

    //Metodos Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
