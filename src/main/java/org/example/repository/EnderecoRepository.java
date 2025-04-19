package org.example.repository;

import org.example.entity.EnderecoEntity;
import javax.persistence.EntityManager;

public class EnderecoRepository{

    private EntityManager em;

    public EnderecoRepository(EntityManager em){ //Armazena o parametro para uso interno
        this.em = em;
    }

    public EnderecoEntity buscarPorId(Long id){ //Metodo para buscar um endereço no banco de dados usando o id
        return em.find(EnderecoEntity.class, id); //Retorna o endereco com o id fornecido
    }

    public void salvar(EnderecoEntity endereco){ //Metodo para criar um novo endereço no banco de dados
        em.getTransaction().begin(); //Inicia uma transação
        em.persist(endereco); //Salva o objeto no banco de dados
        em.getTransaction().commit(); //Finaliza a transação e salva
    }

    public void atualizar(EnderecoEntity endereco){ //Metodo para atualizar um endereço já existente no banco de dados
        em.getTransaction().begin();
        em.merge(endereco); //Atualiza o endereço no banco
        em.getTransaction().commit();
    }

    public void remover(EnderecoEntity endereco){ //Metodo para remover um endereço do banco de dados
        em.getTransaction().begin();
        //Verifica se o endereço está sendo gerenciado, se sim, remove direto
        //Se não, faz merge para gerenciar antes de remover
        em.remove(em.contains(endereco) ? endereco : em.merge(endereco));
        em.getTransaction().commit();
    }
}