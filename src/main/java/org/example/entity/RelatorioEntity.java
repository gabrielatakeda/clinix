package org.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "relatorio")
public class RelatorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'id_relatorio')
    private Long id;

    @Column(name = "data_relatorio")
    private LocalDateTime dataRelatorio = LocalDateTime.now();

    @Column(name = "descricao")
    private String descricao;

    public RelatorioEntity(){}

    public RelatorioEntity(Long id, LocalDateTime dataRelatorio, String descricao) {
        this.id = id;
        this.dataRelatorio = dataRelatorio;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(LocalDateTime dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
