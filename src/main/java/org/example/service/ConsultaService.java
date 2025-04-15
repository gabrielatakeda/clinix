package org.example.service;

import org.example.entity.ConsultaEntity;

import org.example.entity.PacienteEntity;
import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);

    public List<ConsultaEntity> findByNome(String nome){
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public ConsultaEntity exibirConsultas(ConsultaEntity consultaEntity){

        System.out.println("\nid: " + consultaEntity.getID_Consulta() +
                "\nnome: " + consultaEntity.getNome() +
                "\ndata: " + consultaEntity.getLocalDateTime() +
                "\nmotivo: " + consultaEntity.getMotivo() +
                "\nobservações: " + consultaEntity.getObservacoes() +
                "\nprescrição: " + consultaEntity.getPrescricao());

        return consultaEntity;
    }

    public void printMenu(Scanner sc, ConsultaService consultaService){

    }

    public void printMenu(Scanner sc, PacienteService pacienteService, MedicoService medicoService){

    }

}
