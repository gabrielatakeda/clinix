package org.example.repository;

import org.example.entity.AmostrasLabEntity;

import javax.persistence.EntityManager;

public class AmostrasLabRepository {

    private EntityManager em;

    public AmostrasLabRepository(EntityManager em) {
    }

    public AmostrasLabEntity buscarPorId(Long ID_Amostra){
        return em.find(AmostrasLabEntity.class,ID_Amostra);
    }

}
