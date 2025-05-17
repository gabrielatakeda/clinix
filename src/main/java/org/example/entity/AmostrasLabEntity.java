package org.example.entity;


import org.example.enums.StatusAmostraLab;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "amostralab")
public class AmostrasLabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_amostralab;

    @Column(name = "datacoleta")
    private LocalDateTime dataColeta;

    @Column(name = "tipo")
    private String tipoExame;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "status")
    private StatusAmostraLab status;

    @ManyToOne
    @JoinColumn(name = "id_medico") // nome da coluna da FK no banco
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;


    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaEntity consulta;

    public AmostrasLabEntity(){}

    public AmostrasLabEntity(Long id_amostralab, LocalDateTime dataColeta, String tipoExame, String resultado, StatusAmostraLab status, MedicoEntity medico, PacienteEntity paciente, ConsultaEntity consulta) {
        this.id_amostralab = id_amostralab;
        this.dataColeta = dataColeta;
        this.tipoExame = tipoExame;
        this.resultado = resultado;
        this.status = status;
        this.medico = medico;
        this.paciente = paciente;
        this.consulta = consulta;
    }

    public Long getId_amostralab() {
        return id_amostralab;
    }

    public void setID_Amostra(Long ID_Amostra) {
        this.id_amostralab = ID_Amostra;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public ConsultaEntity getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaEntity consulta) {
        this.consulta = consulta;
    }


    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public StatusAmostraLab getStatus() {
        return status;
    }

    public void setStatus(StatusAmostraLab status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id: " + id_amostralab +
                "\nNome Paciente: "  + paciente +
                "\nMedico: " + medico +
                "\nData: " + dataColeta;
    }
}
