package org.example.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consulta",schema = "consultorio")
public class ConsultaEntity {


    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AmostrasLabEntity> amostras = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Consulta;

    @Column(name = "data_consulta")
    private LocalDateTime data_consulta;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "status")
    private String status;

    @Column(name = "prescricao")
    private String prescricao;

    @Column(name = "observacoes")
    private String observacoes;



    public ConsultaEntity (){

    }

    public ConsultaEntity(List<AmostrasLabEntity> amostras, Long ID_Consulta, LocalDateTime data_consulta, String motivo, String status, String prescricao, String observacoes) {
        this.amostras = amostras;
        this.ID_Consulta = ID_Consulta;
        this.data_consulta = data_consulta;
        this.motivo = motivo;
        this.status = status;
        this.prescricao = prescricao;
        this.observacoes = observacoes;
    }

    public Long getID_Consulta() {
        return ID_Consulta;
    }

    public void setID_Consulta(Long ID_Consulta) {
        this.ID_Consulta = ID_Consulta;
    }

    public LocalDateTime getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(LocalDateTime data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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