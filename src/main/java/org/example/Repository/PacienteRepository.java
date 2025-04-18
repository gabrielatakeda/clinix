package org.example.Repository;

import org.example.Entity.PacienteEntity;

import java.util.List;

public class PacienteRepository {

    private javax.persistence.EntityManager em;

    public PacienteRepository(javax.persistence.EntityManager em) {
        this.em = em;
    }

    public PacienteEntity findByID(Long id){
        return em.find(PacienteEntity.class,id);
    }

    public List<PacienteEntity> findByFk_usuario(Long fk_usuario){
        return em.createQuery("SELECT p FROM PacienteEntity p WHERE p.fk_usuario = :fk_usuario", PacienteEntity.class)
                .setParameter("fk_usuario", fk_usuario)
                .getResultList();
    }

}