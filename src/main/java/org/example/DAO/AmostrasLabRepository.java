package org.example.DAO;

import org.example.model.entity.Exame;
import org.example.model.enums.StatusAmostraLab;

import javax.persistence.EntityManager;
import java.util.List;

public class AmostrasLabRepository {

    private EntityManager em;

    public AmostrasLabRepository(){

    }

    public AmostrasLabRepository(EntityManager em) {
        this.em = em;
    }

    public Exame buscarPorId(Long ID_Amostra){
        return em.find(Exame.class,ID_Amostra);
    }


    public List<Exame> findAll(){
        return em.createQuery("SELECT c FROM AmostrasLabEntity c", Exame.class).getResultList();
    }

    public List<Exame> buscarPorStatus(StatusAmostraLab status) {
        return em.createQuery("SELECT a FROM AmostrasLabEntity a WHERE a.status = :status", Exame.class)
                .setParameter("status", status)
                .getResultList();
    }


    public Exame buscarPorTipo(String tipoExame) {
        try {
            return em.createQuery("SELECT a FROM AmostrasLabEntity a WHERE a.tipoExame = :tipo", Exame.class)
                    .setParameter("tipo", tipoExame)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null; // não encontrou nenhum exame com esse tipo
        }
    }
    public Exame salvar(Exame amostra){
        em.getTransaction().begin();
        em.persist(amostra);
        em.getTransaction().commit();
        return amostra;
    }

    public void atualizar (Exame amostra){
        em.getTransaction().begin();
        em.merge(amostra);
        em.getTransaction().commit();
    }

    public void remover (Exame amostra){
        em.getTransaction().begin();
        em.remove(em.contains(amostra)? amostra : em.merge(amostra));
        em.getTransaction().commit();
    }


}