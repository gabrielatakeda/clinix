package org.example.DAO;

import org.example.model.entity.Paciente;

import javax.persistence.EntityManager;

import java.util.List;

public class PacienteRepository {

    private EntityManager em;

    public PacienteRepository(EntityManager em) {
        this.em = em;
    }

    public Paciente buscarPorId(Long id){ //Busca um paciente pelo id
        return em.find(Paciente.class, id); //Chame primária é o id
    }

    public void salvar(Paciente paciente){ //Cria um novo paciente no banco de dados
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
    }

    public List<Paciente> buscarTodos(){ //Retorna todos os pacientes cadastrados
        return em.createQuery("SELECT p FROM paciente p", Paciente.class).getResultList();
    }

    public void atualizar(Paciente paciente){ //Atualiza um paciente existente no banco
        em.getTransaction().begin();
        em.merge(paciente);
        em.getTransaction().commit();
    }

    public void remover(Paciente paciente){ //Remove um paciente cadastrado no banco
        em.getTransaction().begin();
        em.remove(em.contains(paciente) ? paciente : em.merge(paciente));
        em.getTransaction().commit();
    }

    public List<Paciente> buscarPorNomeInicial(String prefixo){ //Busca pacientes cujo nome completo começa com um determinado prefixo
        return em.createQuery("SELECT p FROM paciente p WHERE p.nome LIKE :prefixo", Paciente.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public Paciente buscarPorCpf(String cpf){ //Busca um paciente específico pelo CPF
        try{
            return em.createQuery("SELECT p FROM paciente p WHERE p.cpf = :cpf", Paciente.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult(); //Espera encontrar exatamente um resultado
        }catch(Exception e){
            return null; //Se não encontrar ninguém, retorna null
        }
    }

//    public List<Paciente> findByFk_usuario(Long fk_usuario){
//        return em.createQuery("SELECT p FROM paciente p WHERE p.fk_usuario = :fk_usuario", Paciente.class)
//                .setParameter("fk_usuario", fk_usuario)
//                .getResultList();
//    }
}