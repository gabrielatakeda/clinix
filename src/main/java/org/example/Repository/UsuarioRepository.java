package org.example.Repository;

import org.example.Entity.UsuarioEntity;
import javax.persistence.EntityManager;
import java.util.Optional;

public class UsuarioRepository {
    private EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<UsuarioEntity> buscarPorLogin(String login) {
        try {
            return Optional.ofNullable(em.createQuery(
                            "SELECT u FROM usuario u WHERE u.login = :login", UsuarioEntity.class)
                    .setParameter("login", login)
                    .getSingleResult());
        } catch (javax.persistence.NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por login: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<UsuarioEntity> buscarPorCpf(String cpf) {
        try {
            return Optional.ofNullable(em.createQuery(
                            "SELECT u FROM usuario u WHERE u.cpf = :cpf", UsuarioEntity.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult());
        } catch (javax.persistence.NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por CPF: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void salvar(UsuarioEntity usuario) {
        if (!em.isOpen()) {
            throw new IllegalStateException("Falha ao salvar usuário: conexão com o banco fechada!");
        }

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}