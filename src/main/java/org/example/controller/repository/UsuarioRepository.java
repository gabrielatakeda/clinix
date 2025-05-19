package org.example.controller.repository;

import org.example.model.entity.UsuarioEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {
    private final EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<UsuarioEntity> buscarPorCpf(String cpf) {
        try {
            List<UsuarioEntity> results = em.createQuery(
                            "SELECT u FROM UsuarioEntity u WHERE u.cpf = :cpf", UsuarioEntity.class)
                    .setParameter("cpf", cpf)
                    .getResultList();

            if (results.isEmpty()) return Optional.empty();
            return Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por CPF: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<UsuarioEntity> buscarPorCrm(String crm) {
        try {
            List<UsuarioEntity> results = em.createQuery(
                            "SELECT u FROM UsuarioEntity u WHERE u.crm = :crm", UsuarioEntity.class)
                    .setParameter("crm", crm)
                    .getResultList();

            if (results.isEmpty()) return Optional.empty();
            return Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por CRM: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<UsuarioEntity> buscarPorUsuario(String usuario) {
        try {
            List<UsuarioEntity> results = em.createQuery(
                            "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario", UsuarioEntity.class)
                    .setParameter("usuario", usuario)
                    .getResultList();

            if (results.isEmpty()) return Optional.empty();
            return Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por nome de usuário: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<UsuarioEntity> buscarPorLogin(String login) {
        try {
            List<UsuarioEntity> results = em.createQuery(
                            "SELECT u FROM UsuarioEntity u WHERE u.login = :login", UsuarioEntity.class)
                    .setParameter("login", login)
                    .getResultList();

            if (results.isEmpty()) return Optional.empty();
            return Optional.of(results.get(0));
        } catch (Exception e) {
            System.out.println("Erro ao buscar por login: " + e.getMessage());
            return Optional.empty();
        }
    }

    public void salvar(UsuarioEntity usuario) {
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
