package org.example.model.entity;

import javax.persistence.*;

@Table (name = "endereco")
@Entity(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String logradouro;
    private String cidade;
    private String estado;
    private Integer numero;

    @Column(name = "isPrincipal", nullable = false, columnDefinition = "boolean default false")
    private boolean isPrincipal;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    public Endereco(){} //Construtor vazio

    public Endereco(Long id, String logradouro, String cidade, String estado, Integer numero, boolean isPrincipal, Paciente paciente){
        this.id = id;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.isPrincipal = isPrincipal;
        this.paciente = paciente;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getLogradouro(){
        return logradouro;
    }

    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public Integer getNumero(){
        return numero;
    }

    public void setNumero(Integer numero){
        this.numero = numero;
    }

    public boolean isPrincipal(){
        return isPrincipal;
    }

    public void setPrincipal(boolean principal){
        isPrincipal = principal;
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
}