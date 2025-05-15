package org.example.repository;

import org.example.entity.ProdutoEntity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    private EntityManager em;

    public ProdutoRepository(EntityManager em){
        if (em == null) {
            throw new IllegalArgumentException("Erro: EntityManager não pode ser nulo!");
        }
        this.em = em;
    }

    public ProdutoRepository(){

    }

    private void verificarEntityManager() {
        if (em == null || !em.isOpen()) {
            throw new IllegalStateException("EntityManager não inicializado ou fechado.");
        }
    }

    public List<ProdutoEntity> buscarTodos(){
        verificarEntityManager();
        return em.createQuery("SELECT p FROM ProdutoEntity p", ProdutoEntity.class).getResultList();
    }

    public Optional<ProdutoEntity> buscarPorId(Long id){
        verificarEntityManager();
        return Optional.ofNullable(em.find(ProdutoEntity.class, id));
    }

    public void salvarProduto(ProdutoEntity produto){
        verificarEntityManager();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public void atualizarQuantidade(Long id, int novaQuantidade){
        verificarEntityManager();
        ProdutoEntity produto = em.find(ProdutoEntity.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            produto.setQuantidade(novaQuantidade);
            em.merge(produto);
            em.getTransaction().commit();
        }
    }

    public void removerProduto(Long id){
        verificarEntityManager();
        ProdutoEntity produto = em.find(ProdutoEntity.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }
}