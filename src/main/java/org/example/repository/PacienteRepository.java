package org.example.repository;

import org.example.entity.PacienteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteRepository{

    private EntityManager em;

    public PacienteRepository(EntityManager em){
        this.em = em;
    }

    public PacienteEntity buscarPorId(Long id){
        return em.find(PacienteEntity.class, id);
    }

    public void salvar(PacienteEntity paciente){
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
    }

    public List<PacienteEntity> buscarTodos(){
        return em.createQuery("SELECT p FROM paciente p", PacienteEntity.class).getResultList();
    }

    public void atualizar(PacienteEntity paciente){
        em.getTransaction().begin();
        em.merge(paciente);
        em.getTransaction().commit();
    }

    public void remover(PacienteEntity paciente){
        em.getTransaction().begin();
        em.remove(em.contains(paciente) ? paciente : em.merge(paciente));
        em.getTransaction().commit();
    }

    public List<PacienteEntity> buscarPorNomeInicial(String prefixo){
        return em.createQuery("SELECT p FROM paciente p WHERE p.nomeCompleto LIKE :prefixo", PacienteEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public PacienteEntity buscarPorCpf(String cpf){
        try{
            return em.createQuery("SELECT p FROM paciente p WHERE p.cpf = :cpf", PacienteEntity.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
        }catch(Exception e){
            return null; //se não encontrar ninguém, retorna null
        }
    }
}