
package org.example.repository;

import org.example.entity.ConsultaEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

public class ConsultaRepository {

    private EntityManager em;

    public ConsultaRepository(){}

    public ConsultaRepository(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ConsultaEntity buscarPorId(Long ID_Consulta){
        return em.find(ConsultaEntity.class,ID_Consulta);
    }

    public void salvar(ConsultaEntity consulta){
        em.getTransaction().begin();
        em.persist(consulta);
        em.getTransaction().commit();
    }

    public void atualizar(ConsultaEntity consulta) {
        em.getTransaction().begin();
        em.merge(consulta);
        em.getTransaction().commit();
    }

    public void remover(ConsultaEntity consulta) {
        em.getTransaction().begin();
        em.remove(em.contains(consulta) ? consulta : em.merge(consulta));
        em.getTransaction().commit();
    }

    public ConsultaEntity buscarPorHorario(LocalDate data_consulta) {
        try {
            return em.createQuery(
                            "SELECT c FROM ConsultaEntity c WHERE c.data_consulta = :horario",
                            ConsultaEntity.class)
                    .setParameter("horario", data_consulta)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Nenhuma consulta encontrada nesse horário
        }
    }


    public List<ConsultaEntity> buscarPacientesPorMedico(String crm) {
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.medico.crm = :crm", ConsultaEntity.class)
                .setParameter("crm", crm)
                .getResultList();
    }

    public List<ConsultaEntity> buscarConsultasPorPaciente(String cpf) {
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.paciente.cpf = :cpf", ConsultaEntity.class)
                .setParameter("cpf", cpf)
                .getResultList();
    }

    public List<ConsultaEntity> buscarTodos() {
        return em.createQuery("SELECT c FROM ConsultaEntity c", ConsultaEntity.class).getResultList();
    }

    public List<ConsultaEntity>buscarPorNomeInicial(String nome){
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.nome LIKE :nome",ConsultaEntity.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

}

