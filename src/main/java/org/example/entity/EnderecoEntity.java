package org.example.entity;

import javax.persistence.*;
import org.example.entity.PacienteEntity;

@Entity(name = "endereco")
public class EnderecoEntity{

    @Id //Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) //Gera o ID automaticamente
    private Long id; //Identificador único do endereço

    //Atributos
    private String logradouro;
    private String cidade;
    private String estado;
    private Integer numero;

    @Column(name = "isPrincipal", nullable = false, columnDefinition = "boolean default false")
    private boolean isPrincipal;

    @ManyToOne //Muitos endereços podem estar associados a um paciente
    @JoinColumn(name = "paciente_id", nullable = false) //Define a coluna de chave estrangeira para o relacionamento com a entidade Paciente
    private PacienteEntity paciente;

    public EnderecoEntity(){} //Construtor vazio

    public EnderecoEntity(Long id, String logradouro, String cidade, String estado, Integer numero, boolean isPrincipal, PacienteEntity paciente){ //Construtor com todos os atributos
        this.id = id;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.isPrincipal = isPrincipal;
        this.paciente = paciente;
    }

    //Metodos getters e setters, permitem acessar e modificar os atributos
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

    public PacienteEntity getPaciente(){
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente){
        this.paciente = paciente;
    }
}