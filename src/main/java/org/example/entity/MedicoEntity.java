package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "medico")
public class MedicoEntity {


    @Id //Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) //Gera o ID automaticamente
    private Long id;

    @Column(name = "nome")
    private String nomeCompleto;

    @Column(name = "crm", unique = true) //CRM é único, não pode se repetir
    private String crm;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "telefone")
    private String telefone;

    // @OneToMany(mappedBy = "medico")
    // private List<ConsultaEntity> consultas = new ArrayList<>();

    public MedicoEntity(){ //Construtor vazio
    }

    public MedicoEntity(Long id, String nomeCompleto, String crm, String especialidade, String telefone /*,List<ConsultaEntity> consultas*/){ //Construtor com atributos
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.crm = crm;
        this.especialidade = especialidade;
        this.telefone = telefone;
        //this.consultas = consultas;
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

    //public List<ConsultaEntity> getConsultas(){
    //     return consultas;
    // }

    //public void setConsultas(List<ConsultaEntity> consultas){
    //     this.consultas = consultas;
    // }
}

