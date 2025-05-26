package org.example.DAO;

import org.example.model.entity.Prontuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProntuarioRepository {

    private EntityManager em;


    public ProntuarioRepository(EntityManager em) {
        this.em = em;
    }

    //Para buscar todas as consultas feitas por um certo paciente por CPF
    public List<Prontuario> buscarCpfProntuario (String cpf ) {
        String jpql = "SELECT p FROM prontuario p WHERE p.cpf = :cpf";
        TypedQuery<Prontuario> query = em.createQuery(jpql, Prontuario.class);
        query.setParameter("cpf", cpf);
        return query.getResultList();
    }

    public Prontuario buscarId (Long id ) {
        String jpql = "SELECT p FROM prontuario p WHERE p.id = :id";
        TypedQuery<Prontuario> query = em.createQuery(jpql, Prontuario.class);
        return em.createQuery(jpql, Prontuario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    //Para selecionar uma consulta expecifica à partir do id dela
    public Prontuario buscarPorId(Long id){
        return em.find(Prontuario.class,id);
    }

    //salva um novo prontuario mas não será usado pois os prontuarios são criados altomaticamente
    public void salvar(Prontuario prontuario){
        em.getTransaction().begin();
        em.persist(prontuario);
        em.getTransaction().commit();
    }

    //atualiza um prontuario
    public void atualizar(Prontuario prontuario){
        em.getTransaction().begin();
        em.merge(prontuario);
        em.getTransaction().commit();
    }

    //pode remover um prontuario
    public void remover(Prontuario prontuario){
        em.getTransaction().begin();
        em.remove(em.contains(prontuario) ? prontuario : em.merge(prontuario));
        em.getTransaction().commit();
    }

    //PARTE FEITA APENAS PARA EXAME E NAO NECESSARIAMENTE UTILIZADA NO FINAL

    public List<Prontuario> buscarPorExameNecessario() {
        String jpql = "SELECT p FROM prontuario p WHERE p.exame_necessario = :exame";
        TypedQuery<Prontuario> query = em.createQuery(jpql, Prontuario.class);
        query.setParameter("exame", "sim");
        return query.getResultList();
    }

    public Prontuario buscarPorIdConsulta(Long idConsulta) {
        String jpql = "SELECT p FROM prontuario p WHERE p.consulta.id = :idConsulta";
        return em.createQuery(jpql, Prontuario.class)
                .setParameter("idConsulta", idConsulta)
                .getSingleResult();
    }

}