package org.example.repository;

import org.example.entity.MedicoEntity;
import org.example.entity.PacienteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class MedicoRepository {
    private EntityManager em;

    public MedicoRepository(EntityManager em){
        this.em = em;
    }

    public MedicoEntity buscarPorId(Long id){
        return em.find(MedicoEntity.class, id);
    }

    public void salvar(MedicoEntity medico) {
        em.getTransaction().begin();
        em.persist(medico);
        em.getTransaction().commit();
    }

    public void atualizar(MedicoEntity medico) {
        em.getTransaction().begin();
        em.merge(medico);
        em.getTransaction().commit();
    }

    public void remover(MedicoEntity medico) {
        em.getTransaction().begin();
        em.remove(em.contains(medico) ? medico : em.merge(medico));
        em.getTransaction().commit();
    }

    public List<MedicoEntity> buscarTodos() {
        return em.createQuery("SELECT m FROM medico m", MedicoEntity.class).getResultList();
    }
}
