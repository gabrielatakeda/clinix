package org.example;

import org.example.entity.ConsultaEntity;
import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        ConsultaEntity consulta = consultaRepository.buscarPorId(1L);

        if (consulta == null) {
            System.out.println("ID inexixtente");
        }
    }
}