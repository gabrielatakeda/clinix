package org.example.repository;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class AmostrasLabRepository {

    private EntityManager em;


    public AmostrasLabRepository(EntityManager em) {
        this.em = em;
    }

    public AmostrasLabEntity buscarPorId(Long ID_Amostra){
        return em.find(AmostrasLabEntity.class,ID_Amostra);
    }


    public List<AmostrasLabEntity> findAll(){
        return em.createQuery("SELECT c FROM AmostrasLabEntity c", AmostrasLabEntity.class).getResultList();
    }

    public AmostrasLabEntity salvar(AmostrasLabEntity amostra){
        em.getTransaction().begin();
        em.persist(amostra);
        em.getTransaction().commit();
        return amostra;
    }

    public void atualizar (AmostrasLabEntity amostra){
        em.getTransaction().begin();
        em.merge(amostra);
        em.getTransaction().commit();
    }

    public void remover (AmostrasLabEntity amostra){
        em.getTransaction().begin();
        em.remove(em.contains(amostra)? amostra : em.merge(amostra));
        em.getTransaction().commit();
    }


}
