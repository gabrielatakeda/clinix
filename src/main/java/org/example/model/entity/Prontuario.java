package org.example.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity (name = "prontuario")
@Table(name = "prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "medico")
    private String medico;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "exame")
    private String exame;

    @Column(name = "status_exame")
    private String status_exame;

    @Column(name = "resultado_exame")
    private String resultado_exame;

    @Column(name = "exame_necessario")
    private String exame_necessario;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_exame")
    private Exame exames;


    //--------------------CONSTRUTORES----------------------------

    public Prontuario(){

    }

    public Prontuario(Long id, String nome, String cpf, String medico, LocalDateTime data, String motivo, String diagnostico, String exame, String status_exame, String resultado_exame, String exame_necessario, String status, Exame exames) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.medico = medico;
        this.data = data;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.exame = exame;
        this.status_exame = status_exame;
        this.resultado_exame = resultado_exame;
        this.exame_necessario = exame_necessario;
        this.status = status;
        this.exames = exames;
    }

    //--------------------GETTERS---------------------------------

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getMedico() {
        return medico;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getExame() {
        return exame;
    }

    public String getStatus_exame() {
        return status_exame;
    }

    public String getResultado_exame() {
        return resultado_exame;
    }

    public String getExame_necessario() {
        return exame_necessario;
    }

    //------------------------SETTERS---------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setExame(String exame) {
        this.exame = exame;
    }

    public void setStatus_exame(String status_exame) {
        this.status_exame = status_exame;
    }

    public void setResultado_exame(String resultado_exame) {
        this.resultado_exame = resultado_exame;
    }

    public void setExame_necessario(String exame_necessario) {
        this.exame_necessario = exame_necessario;
    }

    public Exame getExames() {
        return exames;
    }

    public void setExames(Exame exames) {
        this.exames = exames;
    }
}