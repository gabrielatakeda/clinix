package org.example.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuario")
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public ProntuarioEntity(Long id, String nome, LocalDateTime localDateTime, String motivo, String prescricao, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.localDateTime = localDateTime;
        this.motivo = motivo;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

}
