package org.example;

import org.example.entity.RelatorioEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.RelatorioRepository;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        RelatorioRepository relatorioRepository = new RelatorioRepository(em);

        RelatorioEntity relatorio = relatorioRepository.buscarId(1L);

    }
}
