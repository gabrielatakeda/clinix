package org.example.repository;

import org.example.entity.EnderecoEntity;
import javax.persistence.EntityManager;

public class EnderecoRepository {

    private EntityManager em;

    public EnderecoRepository(EntityManager em) {
        this.em = em;
    }

    public EnderecoEntity buscarPorId(Long id) {
        return em.find(EnderecoEntity.class, id);
    }

    public void salvar(EnderecoEntity endereco) {
        em.getTransaction().begin();
        em.persist(endereco);
        em.getTransaction().commit();
    }
}