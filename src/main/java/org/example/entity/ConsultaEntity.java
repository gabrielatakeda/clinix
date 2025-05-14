package org.example.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
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


    //NOME DO QUE???????????
    @Column(name = "nome")
    private String nome;

    @Column(name = "data_hora")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "id_medico") // nome da coluna da FK no banco
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity paciente;

    // @Column(name = "motivo")
    // private String motivo;

    @Column(name = "status")
    private String status;

    // @Column(name = "prescricao")
    // private String prescricao;

    // @Column(name = "observacoes")
    // private String observacoes;


    public ConsultaEntity (){
    }

    public ConsultaEntity(Long ID_Consulta, LocalDateTime data_consulta, String nome, LocalDateTime localDateTime, MedicoEntity medico, PacienteEntity paciente, String status) {
        this.ID_Consulta = ID_Consulta;
        this.data_consulta = data_consulta;
        this.nome = nome;
        this.localDateTime = localDateTime;
        this.medico = medico;
        this.paciente = paciente;
        this.status = status;
    }




    public List<AmostrasLabEntity> getAmostras() {
        return amostras;
    }

    public void setAmostras(List<AmostrasLabEntity> amostras) {
        this.amostras = amostras;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}