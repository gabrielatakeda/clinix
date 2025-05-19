package org.example.model.entity;

import org.example.enums.StatusConsulta;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_consulta;

    @Column(name = "data_hora")
    private LocalDateTime localDateTime;


    @ManyToOne
    @JoinColumn(name = "id_medico") // nome da coluna da FK no banco
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusConsulta status;

    public ConsultaEntity (){
    }

    public ConsultaEntity(Long id_consulta, LocalDateTime localDateTime, MedicoEntity medico, PacienteEntity paciente, StatusConsulta status) {
        this.id_consulta = id_consulta;
        this.localDateTime = localDateTime;
        this.medico = medico;
        this.paciente = paciente;
        this.status = status;
    }

    public Long getID_Consulta() {
        return id_consulta;
    }

    public void setID_Consulta(Long ID_Consulta) {
        this.id_consulta = ID_Consulta;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Consulta: " +
                "\nId: " + id_consulta +
                "\nPaciente: " + paciente.getNome() +
                "\nMédico: " + medico.getNomeCompleto() +
                "\nEspecialidade: " + medico.getEspecialidade() +
                "\nData: " + localDateTime +
                "\nStatus: " + status +
                "===============================";
    }
}
