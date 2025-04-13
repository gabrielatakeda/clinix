package org.example.repository;

import org.example.entity.RelatorioEntity;

import javax.persistence.EntityManager;

public class RelatorioRepository {

    private EntityManager em;

    public RelatorioRepository(EntityManager em) {
        this.em = em;
    }

    public RelatorioEntity buscarId(Long id){
        return em.find(RelatorioEntity.class, id);
    }

    public void salvar(RelatorioEntity relatorio) {
        em.getTransaction().begin();
        em.persist(relatorio);
        em.getTransaction().commit();
    }

}
