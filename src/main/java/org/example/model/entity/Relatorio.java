package org.example.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_relatorio;

    @Column(name = "data_relatorio")
    private LocalDateTime dataRelatorio = LocalDateTime.now();

    @Column(name = "tipo")
    private String tipo;

    public Relatorio(){}

    public Relatorio(Long id_relatorio, LocalDateTime dataRelatorio, String tipo) {
        this.id_relatorio = id_relatorio;
        this.dataRelatorio = dataRelatorio;
        this.tipo = tipo;
    }

    public Long getId_relatorio() {
        return id_relatorio;
    }

    public void setId_relatorio(Long id_relatorio) {
        this.id_relatorio = id_relatorio;
    }

    public LocalDateTime getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(LocalDateTime dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
