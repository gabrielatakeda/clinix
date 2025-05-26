package org.example.DAO;

import org.example.model.entity.Relatorio;

import javax.persistence.EntityManager;

public class RelatorioRepository {

    private EntityManager em;

    public RelatorioRepository(){

    }

    public RelatorioRepository(EntityManager em) {
        this.em = em;
    }

    public Relatorio buscarId(Long id){
        return em.find(Relatorio.class, id);
    }

    public void salvar(Relatorio relatorio) {
        em.getTransaction().begin();
        em.persist(relatorio);
        em.getTransaction().commit();
    }

}
