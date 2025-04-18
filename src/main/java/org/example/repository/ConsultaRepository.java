package org.example.repository;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class ConsultaRepository {

    private EntityManager em;

    public ConsultaRepository(EntityManager em) {
        this.em = em;
    }

    public ConsultaEntity buscarPorId(Long ID_Amostra){
        return em.find(ConsultaEntity.class,ID_Amostra);
    }

    public List<ConsultaEntity> findAll(){
    return em.createQuery("SELECT c FROM consultorio.consulta c", ConsultaEntity.class).getResultList();
    }

    public ConsultaEntity salvar(ConsultaEntity consulta){
        em.getTransaction().begin();
        em.persist(consulta);
        em.getTransaction().commit();
        return consulta;

    }

    public void atualizar (ConsultaEntity consulta){
        em.getTransaction().begin();
        em.merge(consulta);
        em.getTransaction().commit();
    }

    public void remover (ConsultaEntity consulta){
        em.getTransaction().begin();
        em.remove(em.contains(consulta)? consulta : em.merge(consulta));
        em.getTransaction().commit();
    }



}
