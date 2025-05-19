package org.example.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table (name = "medico")
@Entity(name = "medico")
public class MedicoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nomeCompleto;

    @Column(name = "crm", unique = true)
    private String crm;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "telefone")
    private String telefone;

    public MedicoEntity(){
    }

    public MedicoEntity(Long id, String nomeCompleto, String crm, String especialidade, String telefone){
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
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

    public String getCrm(){
        return crm;
    }

    public void setCrm(String crm){
        this.crm = crm;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
}

