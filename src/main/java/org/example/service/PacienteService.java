package org.example.service;

import org.example.Entity.PacienteEntity;
import org.example.Repository.PacienteRepository;
import org.example.Repository.CustomizerFactory;

import javax.persistence.EntityManager;

public class PacienteService {

    EntityManager em = CustomizerFactory.getEntityManager();
    PacienteRepository pacienteRepository = new PacienteRepository(em);

    public PacienteEntity exibirConsulta(PacienteEntity pacienteEntity){

        System.out.println("id: " + pacienteEntity.getId() +
                "\nnome: " + pacienteEntity.getNome() +
                "\nidade: " + pacienteEntity.getIdade() +
                "\ndata: " + pacienteEntity.getData() +
                "\nmedico: " + pacienteEntity.getMedico());

        return pacienteEntity;
    }

}
