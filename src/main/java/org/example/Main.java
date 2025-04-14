package org.example;

import org.example.entity.AmostrasLabEntity;
import org.example.repository.AmostrasLabRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        AmostrasLabRepository amostraLabRepository = new AmostrasLabRepository(em);

        AmostrasLabEntity amostraLaboratorial = amostraLabRepository.buscarPorId(1L);

        System.out.println(amostraLaboratorial.getTipoExame());


    }
}
