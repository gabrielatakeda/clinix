package org.example;

import org.example.Entity.PacienteEntity;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.PacienteRepository;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        PacienteRepository pacienteRepository = new PacienteRepository(em);

        PacienteEntity paciente = pacienteRepository.findByID(5L);

        if (paciente != null) {
            System.out.println(paciente.getId());
            System.out.println(paciente.getNome());
        } else {
            System.out.println("Paciente not found or returned as null");
        }



    }
}