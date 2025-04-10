package org.example.Repository;

import org.example.Entity.PacienteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteRepository {

    private javax.persistence.EntityManager em;

    public PacienteRepository(javax.persistence.EntityManager em) {
        this.em = em;
    }

    public PacienteEntity findByID(Long id){
        return em.find(PacienteEntity.class,id);
    }
}