package org.example.repository;

import org.example.entity.ConsultaEntity;
import javax.persistence.EntityManager;
import java.util.List;

public class ConsultaRepository {

    private EntityManager em;

    public  ConsultaRepository(){}

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

    public List<ConsultaEntity> buscarTodos() {
        return em.createQuery("SELECT c FROM ConsultaEntity c", ConsultaEntity.class).getResultList();
    }

    public List<ConsultaEntity>buscarPorNomeInicial(String nome){
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.nome LIKE :nome",ConsultaEntity.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

    public List<ConsultaEntity> buscarPacientesPorMedico(String crm) {
        String jpql = "SELECT p FROM ConsultaEntity p WHERE p.id_medico.crm = :crm";
        return em.createQuery(jpql, ConsultaEntity.class)
                .setParameter("crm", crm)
                .getResultList();
    }

    public List<ConsultaEntity> buscarConsultasPorPaciente(String cpf) {
        return em.createQuery(
                        "SELECT c FROM ConsultaEntity c WHERE c.id_paciente.cpf = :cpf", ConsultaEntity.class)
                .setParameter("cpf", cpf)
                .getResultList();
    }

    public void atualizar(ConsultaEntity consulta){
        em.getTransaction().begin();
        em.merge(consulta);
        em.getTransaction().commit();
    }

    public void remover(ConsultaEntity consulta){
        em.getTransaction().begin();
        em.remove(em.contains(consulta) ? consulta : em.merge(consulta));
        em.getTransaction().commit();
    }
}