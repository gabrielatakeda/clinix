package org.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.security.auth.login.Configuration;

public class CustomizerFactory {

    private static final EntityManagerFactory emf;

    static {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        emf = sessionFactory.unwrap(EntityManagerFactory.class);
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void fechar(){
        emf.close();
    }

}
