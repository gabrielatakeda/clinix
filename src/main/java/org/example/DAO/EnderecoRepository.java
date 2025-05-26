package org.example.DAO;

import org.example.model.entity.Endereco;
import javax.persistence.EntityManager;

public class EnderecoRepository{

    private EntityManager em;

    public EnderecoRepository(EntityManager em){
        this.em = em;
    }

    public Endereco buscarPorId(Long id){
        return em.find(Endereco.class, id);
    }

    public void salvar(Endereco endereco){
        em.getTransaction().begin();
        em.persist(endereco);
        em.getTransaction().commit();
    }

    public void atualizar(Endereco endereco){
        em.getTransaction().begin();
        em.merge(endereco);
        em.getTransaction().commit();
    }

    public void remover(Endereco endereco){
        em.getTransaction().begin();
        em.remove(em.contains(endereco) ? endereco : em.merge(endereco));
        em.getTransaction().commit();
    }
}