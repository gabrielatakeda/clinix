package org.example.repository;

import org.example.entity.MedicoEntity;
import javax.persistence.EntityManager;

public class MedicoRepository {

    private EntityManager em;

    public MedicoRepository(EntityManager em) {
        this.em = em;
    }

    public MedicoEntity findMedicoById(Long id){
        return em.find(MedicoEntity.class,id);
    }

    public MedicoEntity findByCrm(String crm){
        return em.find(MedicoEntity.class, crm);
    }

    public MedicoEntity salvarNovoMedico(MedicoEntity medicoEntity){
        em.getTransactional();
        em.merge(medicoEntity);
        em.commit();
    }
}
