package org.example.repository;

import org.hibernate.SessionFactory;
<<<<<<< HEAD

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
=======
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
>>>>>>> 749756b (Atualizações das classes)

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

<<<<<<< HEAD
    public static void  fechar(){
        emf.close();
    }
}
=======
    public static void fechar(){
        emf.close();
    }
}
>>>>>>> 749756b (Atualizações das classes)
