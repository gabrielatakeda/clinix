package org.example.view;

import org.example.DAO.UsuarioRepository;
import org.example.controller.UsuarioController;
import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        EntityManagerFactory entityManagerFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//
//        EntityManager em = entityManagerFactory.createEntityManager();
//
//        if (em == null) {
//            throw new IllegalStateException("Erro crítico: EntityManager está nulo. Verifique a inicialização.");
//        }
//            em.close();
//        }
        ConsultaView consultaView = new ConsultaView();
        consultaView.printMenu();

    }
}
//
//        try (Scanner sc = new Scanner(System.in)) {
//            LoginView loginView = new LoginView();
//            loginView.iniciar(sc);
//        } catch (Exception e){
//            System.out.println("Erro para iniciar o Sistema.");