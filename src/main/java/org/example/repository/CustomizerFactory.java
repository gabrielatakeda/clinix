package org.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustomizerFactory{

    private static final EntityManagerFactory emf; //Cria conexões com o banco

    static{
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml") //Configurações do hibernate
                .buildSessionFactory();

        emf = sessionFactory.unwrap(EntityManagerFactory.class);
    }

    public static EntityManager getEntityManager(){ //Executa operações no banco
        return emf.createEntityManager();
    }

    public static void fechar(){ //Fecha o EntityManager
        emf.close();
    }
}