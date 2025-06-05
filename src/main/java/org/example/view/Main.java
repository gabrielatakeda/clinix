package org.example.view;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = entityManagerFactory.createEntityManager();

        if (em == null) {
            throw new IllegalStateException("Erro crítico: EntityManager está nulo. Verifique a inicialização.");
        }

        try {
            MainMenuView.showMainMenu();
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o Sistema.");
            em.close();
        }
    }
}