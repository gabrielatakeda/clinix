package org.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = 'consulta')
@Table(name = 'consulta')
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'id_consulta')
    private Long id_consulta;

    @ManyToOne
    @JoinColumn(name = 'id_paciente')
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = 'id_medico')
    private MedicoEntity medico;

    @Column(name = 'data_hora')
    private LocalDateTime dataHora;

    @Column(name = 'status')
    private String status;

    @Column(name = 'motivo')
    private String motivo;

    public ConsultaEntity(Long id_consulta, PacienteEntity paciente, MedicoEntity medico, LocalDateTime dataHora, String status, String motivo) {
        this.id_consulta = id_consulta;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.status = status;
        this.motivo = motivo;
    }

    public ConsultaEntity(){}

    public Long getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(Long id_consulta) {
        this.id_consulta = id_consulta;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime data_hora) {
        this.dataHora = data_hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
