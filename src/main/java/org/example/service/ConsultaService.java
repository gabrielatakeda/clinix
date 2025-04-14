package org.example.service;

import org.example.Entity.ConsultaEntity;

import org.example.Entity.PacienteEntity;
import org.example.Repository.ConsultaRepository;
import org.example.Repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);

    public List<ConsultaEntity> findByNome(String nome){
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public ConsultaEntity exibirConsultas(ConsultaEntity consultaEntity){

        System.out.println("\nid: " + consultaEntity.getId() +
                "\nnome: " + consultaEntity.getNome() +
                "\ndata: " + consultaEntity.getLocalDateTime() +
                "\nmotivo: " + consultaEntity.getMotivo() +
                "\nobservações: " + consultaEntity.getObservacoes() +
                "\nprescrição: " + consultaEntity.getPrescricao());

        return consultaEntity;
    }

}
