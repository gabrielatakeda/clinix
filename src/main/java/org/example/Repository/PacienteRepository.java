package org.example.Repository;

import org.example.entity.PacienteEntity;
import javax.persistence.EntityManager;

import java.util.List;

public class PacienteRepository {

    private EntityManager em;

    public PacienteRepository(EntityManager em) {
        this.em = em;
    }

    public PacienteEntity buscarPorId(Long id){ //Busca um paciente pelo id
        return em.find(PacienteEntity.class, id); //Chame primária é o id
    }

    public void salvar(PacienteEntity paciente){ //Cria um novo paciente no banco de dados
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
    }

    public List<PacienteEntity> buscarTodos(){ //Retorna todos os pacientes cadastrados
        return em.createQuery("SELECT p FROM PacienteEntity p", PacienteEntity.class).getResultList();
    }

    public void atualizar(PacienteEntity paciente){ //Atualiza um paciente existente no banco
        em.getTransaction().begin();
        em.merge(paciente);
        em.getTransaction().commit();
    }

    public void remover(PacienteEntity paciente){ //Remove um paciente cadastrado no banco
        em.getTransaction().begin();
        em.remove(em.contains(paciente) ? paciente : em.merge(paciente));
        em.getTransaction().commit();
    }

    public List<PacienteEntity> buscarPorNomeInicial(String prefixo){ //Busca pacientes cujo nome completo começa com um determinado prefixo
        return em.createQuery("SELECT p FROM PacienteEntity p WHERE p.nome LIKE :prefixo", PacienteEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public PacienteEntity buscarPorCpf(String cpf){ //Busca um paciente específico pelo CPF
        try{
            return em.createQuery("SELECT p FROM paciente p WHERE p.cpf = :cpf", PacienteEntity.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult(); //Espera encontrar exatamente um resultado
        }catch(Exception e){
            return null; //Se não encontrar ninguém, retorna null
        }
    }

    public List<PacienteEntity> findByFk_usuario(Long fk_usuario){
        return em.createQuery("SELECT p FROM PacienteEntity p WHERE p.fk_usuario = :fk_usuario", PacienteEntity.class)
                .setParameter("fk_usuario", fk_usuario)
                .getResultList();
    }
}