package org.example.entity;

import org.example.enums.TypeUser;

import javax.persistence.*;

@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cpf; // Para pacientes

    @Column(unique = true)
    private String crm; // Para médicos

    @Column(unique = true)
    private String usuario; // Para recepção e laboratório

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String login; // Identificador principal para login (CPF, CRM ou usuário)

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeUser tipo;

    // Construtor vazio exigido pelo JPA
    public UsuarioEntity() {}

    // Construtor com campos principais (pode ser ajustado conforme necessidade)
    public UsuarioEntity(String cpf, String crm, String usuario, String nomeCompleto, String email, String login, String senha, TypeUser tipo) {
        this.cpf = cpf;
        this.crm = crm;
        this.usuario = usuario;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TypeUser getTipo() {
        return tipo;
    }

    public void setTipo(TypeUser tipo) {
        this.tipo = tipo;
    }
}
