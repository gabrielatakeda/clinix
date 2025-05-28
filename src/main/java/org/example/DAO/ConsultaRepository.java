package org.example.DAO;

import org.example.model.entity.Consulta;
import org.example.model.entity.Paciente;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaRepository {

    private EntityManager em;

    public  ConsultaRepository(){}

    public ConsultaRepository(EntityManager em) {
        this.em = em;
    }

    public Consulta buscarPorId(Long ID_Consulta){
        return em.find(Consulta.class,ID_Consulta);
    }


    public void salvar(Consulta consulta){
        em.getTransaction().begin();
        em.persist(consulta);
        em.getTransaction().commit();
    }

    public void atualizar(Consulta consulta) {
        em.getTransaction().begin();
        em.merge(consulta);
        em.getTransaction().commit();
    }

    public void remover(Consulta consulta) {
        em.getTransaction().begin();
        em.remove(em.contains(consulta) ? consulta : em.merge(consulta));
        em.getTransaction().commit();
    }

    public Consulta buscarPorHorario(LocalDate data_consulta) {
        try {
            return em.createQuery(
                            "SELECT c FROM ConsultaEntity c WHERE c.data_consulta = :horario",
                            Consulta.class)
                    .setParameter("horario", data_consulta)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Nenhuma consulta encontrada nesse horário
        }
    }

    public Consulta buscarConsultaPorPaciente(Paciente paciente) {
        try {
            return em.createQuery(
                            "SELECT c FROM ConsultaEntity c WHERE c.paciente = :paciente ORDER BY c.data_consulta DESC",
                            Consulta.class)
                    .setParameter("paciente", paciente)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Consulta> buscarPacientesPorMedico(String crm) {
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.medico.crm = :crm", Consulta.class)
                .setParameter("crm", crm)
                .getResultList();
    }

    public List<Consulta> buscarConsultasPorPaciente(String cpf) {
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.paciente.cpf = :cpf", Consulta.class)
                .setParameter("cpf", cpf)
                .getResultList();
    }

    public List<Consulta> listarTodos() {
        return em.createQuery("SELECT c FROM ConsultaEntity c", Consulta.class).getResultList();
    }

    public List<Consulta>buscarPorNomeInicial(String nome){
        return em.createQuery("SELECT c FROM ConsultaEntity c WHERE c.nome LIKE :nome", Consulta.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

    public Consulta buscarConsultaPorData(LocalDateTime dataConsulta) {
        try {
            return em.createQuery(
                            "SELECT c FROM ConsultaEntity c WHERE c.data_consulta = :dataConsulta",
                            Consulta.class)
                    .setParameter("dataConsulta", dataConsulta)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}