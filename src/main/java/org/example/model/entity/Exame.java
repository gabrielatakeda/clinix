package org.example.model.entity;


import org.example.model.enums.StatusAmostraLab;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "amostralab")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_amostralab;

//    @Column(name = "datacoleta")
//    private LocalDateTime dataColeta;

    @Column(name = "tipo")
    private String tipoExame;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "status")
    @Enumerated(EnumType.STRING) // OU ORDINAL
    private StatusAmostraLab status;

//    @ManyToOne
//    @JoinColumn(name = "id_medico") // nome da coluna da FK no banco
//    private MedicoEntity medico;
//
//    @ManyToOne
//    @JoinColumn(name = "id_paciente")
//    private PacienteEntity paciente;


    @ManyToOne
    @JoinColumn(name = "id_prontuario")
    private Prontuario prontuario;

    @ManyToMany
    @JoinTable(
            name = "amostralab_produto",
            joinColumns = @JoinColumn(name = "id_amostralab"),
            inverseJoinColumns = @JoinColumn(name = "id_produto")
    )
    private List<Produto> produtos = new ArrayList<>();


    public Exame() {
    }

    public Exame(Long id_amostralab, String tipoExame, String resultado, StatusAmostraLab status, Prontuario prontuario, List<Produto> produtos) {
        this.id_amostralab = id_amostralab;
        this.tipoExame = tipoExame;
        this.resultado = resultado;
        this.status = status;
        this.prontuario = prontuario;
        this.produtos = produtos;
    }


    public Long getId_amostralab() {
        return id_amostralab;
    }

    public void setId_amostralab(Long id_amostralab) {
        this.id_amostralab = id_amostralab;
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

    public StatusAmostraLab getStatus() {
        return status;
    }

    public void setStatus(StatusAmostraLab status) {
        this.status = status;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Id: " + id_amostralab +
                "\nNome Paciente: "  + prontuario.getNome() +
                "\nMedico: " + prontuario.getMedico();
    }
}