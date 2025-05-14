package org.example.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prontuario")
public class ProntuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", updatable = false, insertable = false)
    private String nome;

    @Column(name = "idade", updatable = false, insertable = false)
    private Integer idade;

    @Column(name = "medico", updatable = false, insertable = false)
    private String medico;

    @Column(name = "datamarcada", updatable = false, insertable = false)
    private LocalDateTime dataMarcada;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "prescricao")
    private String prescricao;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "status")
    private String status;

    @Column(name = "datarealizada")
    private LocalDateTime dataRealizada;

    public ProntuarioEntity(Long id, String nome, Integer idade, String medico, LocalDateTime dataMarcada,
                            String motivo, String prescricao, String observacoes, String status,
                            LocalDateTime dataRealizada) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.medico = medico;
        this.dataMarcada = dataMarcada;
        this.motivo = motivo;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
        this.status = status;
        this.dataRealizada = dataRealizada;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public Integer getIdade() {
        return idade;
    }

    public String getMedico() {
        return medico;
    }

    public LocalDateTime getDataMarcada() {
        return dataMarcada;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataRealizada() {
        return dataRealizada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public void setDataMarcada(LocalDateTime dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataRealizada(LocalDateTime dataRealizada) {
        this.dataRealizada = dataRealizada;
    }
}
