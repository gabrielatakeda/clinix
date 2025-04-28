package org.example.Entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Consulta;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private LocalDateTime localDateTime;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "prescricao")
    private String prescricao;

    @Column(name = "observacoes")
    private String observacoes;


    public ConsultaEntity (){

    }

    public ConsultaEntity(Long ID_Consulta, String nome, LocalDateTime localDateTime, String motivo, String prescricao, String observacoes) {
        this.ID_Consulta = ID_Consulta;
        this.nome = nome;
        this.localDateTime = localDateTime;
        this.motivo = motivo;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
    }


    public Long getId() {
        return ID_Consulta;
    }

    public void setId(Long id) {
        this.ID_Consulta = ID_Consulta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }





}