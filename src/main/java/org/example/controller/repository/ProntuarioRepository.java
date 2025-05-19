package org.example.controller.repository;

import org.example.model.entity.ProntuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProntuarioRepository {

    private EntityManager em;


    public ProntuarioRepository(EntityManager em) {
        this.em = em;
    }

    //Para buscar todas as consultas feitas por um certo paciente por CPF
    public List<ProntuarioEntity> buscarCpfProntuario (String cpf ) {
        String jpql = "SELECT p FROM prontuario p WHERE p.cpf = :cpf";
        TypedQuery<ProntuarioEntity> query = em.createQuery(jpql, ProntuarioEntity.class);
        query.setParameter("cpf", cpf);
        return query.getResultList();
    }

    //Para selecionar uma consulta expecifica à partir do id dela
    public ProntuarioEntity buscarPorId(Long id){
        return em.find(ProntuarioEntity.class,id);
    }

    //salva um novo prontuario mas não será usado pois os prontuarios são criados altomaticamente
    public void salvar(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.persist(prontuario);
        em.getTransaction().commit();
    }

    //atualiza um prontuario
    public void atualizar(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.merge(prontuario);
        em.getTransaction().commit();
    }

    //pode remover um prontuario
    public void remover(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.remove(em.contains(prontuario) ? prontuario : em.merge(prontuario));
        em.getTransaction().commit();
    }

    //PARTE FEITA APENAS PARA EXAME E NAO NECESSARIAMENTE UTILIZADA NO FINAL

    public List<ProntuarioEntity> buscarPorExameNecessario() {
        String jpql = "SELECT p FROM prontuario p WHERE p.exame_necessario = :exame";
        TypedQuery<ProntuarioEntity> query = em.createQuery(jpql, ProntuarioEntity.class);
        query.setParameter("exame", "sim");
        return query.getResultList();
    }

    public ProntuarioEntity buscarPorIdConsulta(Long idConsulta) {
        String jpql = "SELECT p FROM ProntuarioEntity p WHERE p.consulta.id = :idConsulta";
        return em.createQuery(jpql, ProntuarioEntity.class)
                .setParameter("idConsulta", idConsulta)
                .getSingleResult();
    }

}