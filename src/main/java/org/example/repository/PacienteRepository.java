package org.example.repository;

import org.example.entity.PacienteEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteRepository {

    private EntityManager em;

    public PacienteRepository(){

    }

    public PacienteRepository(EntityManager em){
        this.em = em;
    }

    public void criarPaciente(PacienteEntity paciente){
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
    }

    public List<PacienteEntity> buscarPaciente(){
        return em.createQuery("SELECT pacientes FROM paciente pacientes", PacienteEntity.class).getResultList();
    }

    public PacienteEntity buscarPorId(int ID){
        return em.find(PacienteEntity.class, ID);
    }

    public List<PacienteEntity> buscarPorCpf(String prefixo){
        return em.createQuery("SELECT pacientes FROM paciente pacientes WHERE paciente.cpf LIKE :prefixo", PacienteEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public void atualizarPaciente(PacienteEntity paciente){
        em.getTransaction().begin();
        em.merge(paciente);
        em.getTransaction().commit();
    }

    public void removerPaciente(PacienteEntity paciente){
        em.getTransaction().begin();
        em.remove(em.contains(paciente) ? paciente : em.merge(paciente));
        em.getTransaction().commit();
    }
}
