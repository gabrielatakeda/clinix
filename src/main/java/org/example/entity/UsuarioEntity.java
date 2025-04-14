package org.example.entity;

import javax.persistence.*;

@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;  // CPF único para cada usuário

    @Column(nullable = false, unique = true)
    private String login;  // Pode ser email ou nome de usuário

    @Column(nullable = false)
    private String senha;

    public UsuarioEntity() {

    }

    public UsuarioEntity(String cpf, String login, String senha){
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
    }

    public Long getId(){
        return id; }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

}