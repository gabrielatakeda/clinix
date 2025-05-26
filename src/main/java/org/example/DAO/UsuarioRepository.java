package org.example.DAO;

import org.example.model.entity.Usuario;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository() {}

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        try {
            List<Usuario> results = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.cpf = :cpf", Usuario.class)
                    .setParameter("cpf", cpf)
                    .getResultList();

            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por CPF: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Usuario> buscarPorCrm(String crm) {
        try {
            List<Usuario> results = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.crm = :crm", Usuario.class)
                    .setParameter("crm", crm)
                    .getResultList();

            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por CRM: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Usuario> buscarPorUsuario(String usuario) {
        try {
            List<Usuario> results = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class)
                    .setParameter("usuario", usuario)
                    .getResultList();

            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por nome de usuário: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Usuario> buscarPorLogin(String login) {
        try {
            List<Usuario> results = em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
                    .setParameter("login", login)
                    .getResultList();

            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por login: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void salvar(Usuario usuario) {
        if (!em.isOpen()) {
            throw new IllegalStateException("Erro: EntityManager está fechado.");
        }

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
