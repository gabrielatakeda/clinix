package org.example.Repository;
import org.example.Entity.ProntuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProntuarioRepository {

    private EntityManager em;


    public ProntuarioRepository(EntityManager em) {
        this.em = em;
    }

    public List<ProntuarioEntity> buscarCpfProntuario (String cpf ) {
        String jpql = "SELECT p FROM prontuario p WHERE p.cpf = :cpf";
        TypedQuery<ProntuarioEntity> query = em.createQuery(jpql, ProntuarioEntity.class);
        query.setParameter("cpf", cpf);
        return query.getResultList();
    }

    public ProntuarioEntity buscarPorId(Long id){
        return em.find(ProntuarioEntity.class,id);
    }


    public void salvar(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.persist(prontuario);
        em.getTransaction().commit();
    }

    public void atualizar(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.merge(prontuario);
        em.getTransaction().commit();
    }

    public void remover(ProntuarioEntity prontuario){
        em.getTransaction().begin();
        em.remove(em.contains(prontuario) ? prontuario : em.merge(prontuario));
        em.getTransaction().commit();
    }

}
