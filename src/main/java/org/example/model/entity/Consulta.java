package org.example.model.entity;

import org.example.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_consulta;

    @Column(name = "data_hora")
    private LocalDateTime localDateTime;


    @ManyToOne
    @JoinColumn(name = "id_medico") // nome da coluna da FK no banco
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusConsulta status;

    public Consulta(){
    }

    public Consulta(Long id_consulta, LocalDateTime localDateTime, Medico medico, Paciente paciente, StatusConsulta status) {
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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
