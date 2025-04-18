package org.example.entity;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "amostralab")
public class AmostrasLabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Amostra;

    @Column(name = "dataColeta")
    private LocalDateTime dataColeta;

    @Column(name = "tipoExame")
    private String tipoExame;


    @Column(name = "resultado")
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private ConsultaEntity consulta;



    public AmostrasLabEntity(){}

    public AmostrasLabEntity(Long ID_Amostra, String tipoExame, String tipoExame1, String resultado, LocalDateTime dataColeta) {
        this.ID_Amostra = ID_Amostra;
        this.tipoExame = tipoExame1;
        this.resultado = resultado;
        this.dataColeta = dataColeta;
    }



    public Long getID_Amostra() {
        return ID_Amostra;
    }

    public void setID_Amostra(Long ID_Amostra) {
        this.ID_Amostra = ID_Amostra;
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
}
