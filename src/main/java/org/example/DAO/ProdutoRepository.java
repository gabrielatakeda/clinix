package org.example.DAO;

import org.example.model.entity.Produto;

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

    public List<Produto> buscarTodos(){
        verificarEntityManager();
        return em.createQuery("SELECT p FROM ProdutoEntity p", Produto.class).getResultList();
    }

    public Optional<Produto> buscarPorId(Long id){
        verificarEntityManager();
        return Optional.ofNullable(em.find(Produto.class, id));
    }

    public void salvarProduto(Produto produto){
        verificarEntityManager();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    public void atualizarQuantidade(Long id, int novaQuantidade){
        verificarEntityManager();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            produto.setQuantidade(novaQuantidade);
            em.merge(produto);
            em.getTransaction().commit();
        }
    }

    public void removerProduto(Long id){
        verificarEntityManager();
        Produto produto = em.find(Produto.class, id);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }
}