package org.example.repository;

import org.example.entity.ProdutoEntity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    private EntityManager em;

    public ProdutoRepository(EntityManager em) {
        this.em = em;
    }

    public List<ProdutoEntity> buscarTodos() {
        return em.createQuery("SELECT p FROM ProdutoEntity p", ProdutoEntity.class).getResultList();
    }

    public Optional<ProdutoEntity> buscarPorId(Long id) {
        return Optional.ofNullable(em.find(ProdutoEntity.class, id));
    }

    public void salvarProduto(ProdutoEntity produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public void atualizarQuantidade(Long id, int novaQuantidade) {
        ProdutoEntity produto = em.find(ProdutoEntity.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            produto.setQuantidade(novaQuantidade);
            em.merge(produto);
            em.getTransaction().commit();
        }
    }

    public void removerProduto(Long id) {
        ProdutoEntity produto = em.find(ProdutoEntity.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }
}