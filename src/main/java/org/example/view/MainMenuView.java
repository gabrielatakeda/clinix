package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView {

    public static void showMainMenu() {
        JFrame mainFrame = new JFrame("Clinix - Bem-vindo!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Bem-vindo ao CLINIX!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton registerButton = new JButton("Cadastrar");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Sair");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                UsuarioView.showCadastroScreen();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                LoginView.loginRequest();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(welcomeLabel);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(exitButton);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }
}