package org.example.DAO;

import org.example.model.entity.Medico;

import javax.persistence.EntityManager;
import java.util.List;

public class MedicoRepository {
    private EntityManager em;

    public MedicoRepository(EntityManager em) {
        this.em = em;
    }

    public Medico buscarPorId(Long id) {
        return em.find(Medico.class, id);
    }

    public void salvar(Medico medico) {
        em.getTransaction().begin();
        em.persist(medico);
        em.getTransaction().commit();
    }

    public void atualizar(Medico medico) {
        em.getTransaction().begin();
        em.merge(medico);
        em.getTransaction().commit();
    }

    public void remover(Medico medico) {
        em.getTransaction().begin();
        em.remove(em.contains(medico) ? medico : em.merge(medico));
        em.getTransaction().commit();
    }

    public List<Medico> buscarTodos() {
        return em.createQuery("SELECT m FROM medico m", Medico.class).getResultList();
    }

    public Medico buscarPorCrm(String crm){
        List<Medico> resultado = em.createQuery(
                        "SELECT m FROM medico m WHERE LOWER(m.crm) = LOWER(:crm)", Medico.class)
                .setParameter("crm", crm)
                .getResultList();

        if(resultado.isEmpty()){
            return null;
        }

        return resultado.get(0);
    }

}
