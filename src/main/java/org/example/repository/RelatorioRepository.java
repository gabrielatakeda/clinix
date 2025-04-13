package org.example.repository;

import org.example.entity.RelatorioEntity;

import javax.persistence.EntityManager;

public class RelatorioRepository {

    private EntityManager em;

    public RelatorioRepository(EntityManager em) {
        this.em = em;
    }

    public RelatorioRepository(){}

    public RelatorioEntity buscarId(Long id){
        return em.find(RelatorioEntity.class, id);
    }
}
