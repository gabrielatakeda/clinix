package org.example.Entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "amostralab", schema = "consultorio")
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


    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaEntity consulta;

    public AmostrasLabEntity(){}

    public AmostrasLabEntity(Long id_amostralab, String tipoExame, String resultado, LocalDateTime dataColeta) {
        this.id_amostralab = id_amostralab;
        this.tipoExame = tipoExame;
        this.resultado = resultado;
        this.dataColeta = dataColeta;
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


}
