package org.example.view;

import org.example.controller.UsuarioController;
import org.example.model.enums.TypeUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LoginView {

    public static void loginRequest() {
        JFrame telaLogin = new JFrame("Login");
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setSize(400, 350);
        telaLogin.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(230, 230, 250));

        GridBagConstraints gbc;

        JLabel typeLabel = new JLabel("Tipo de Usuário:");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(typeLabel, gbc);

        String[] userTypes = {"ADMIN", "PACIENTE", "MEDICO", "LAB", "RECEPCAO"};
        JComboBox<String> typeComboBox = new JComboBox<>(userTypes);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(typeComboBox, gbc);

        JLabel userLabel = new JLabel("Tipo de Usuário:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(userField, gbc);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(loginButton, gbc);

        telaLogin.getContentPane().add(panel);
        telaLogin.setVisible(true);

        typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                switch (selectedType) {
                    case "ADMIN":
                    case "LAB":
                    case "RECEPCAO":
                        userLabel.setText("Nome de Usuário:");
                        break;
                    case "PACIENTE":
                        userLabel.setText("CPF:");
                        break;
                    case "MEDICO":
                        userLabel.setText("CRM:");
                        break;
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoLoginInput = (String) typeComboBox.getSelectedItem();
                TypeUser tipoLogin = TypeUser.valueOf(tipoLoginInput);

                String login = userField.getText();
                String senha = new String(passwordField.getPassword());

                UsuarioController usuarioController = new UsuarioController();

                if (usuarioController.autenticarUsuario(tipoLogin, login, senha)) {
                    telaLogin.setVisible(false);
                    MenuView menuView = new MenuView();
                    menuView.abrirMenu();
                } else {
                    JOptionPane.showMessageDialog(telaLogin, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

//public class LoginView {
//
//    public void iniciar(Scanner sc) {
//        boolean executando = true;
//        UsuarioController usuarioController = new UsuarioController();
//        while (executando) {
//            System.out.println("---BEM VINDO AO CLINIX---");
//            System.out.println("\n===CADASTRO / LOGIN ===");
//            System.out.println("1 - Cadastrar");
//            System.out.println("2 - Login");
//            System.out.println("3 - Sair");
//            System.out.print("Escolha uma opção: ");
//
//            String entrada = sc.nextLine();
//
//            if (!entrada.matches("\\d+")) {
//                System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
//                continue;
//            }
//
//            int opcao = Integer.parseInt(entrada);
//            switch (opcao) {
//                case 1:
//                    usuarioController.cadastrarUsuario();
//                    break;
//                case 2:
//                    System.out.println("\n=== LOGIN ===");
//                    System.out.print("Informe o tipo de usuário para login (ADMIN, PACIENTE, MEDICO, LAB, RECEPCAO): ");
//                    String tipoLoginInput = sc.nextLine().toUpperCase();
//
//                    TypeUser tipoLogin;
//                    try {
//                        tipoLogin = TypeUser.valueOf(tipoLoginInput);
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Tipo de usuário inválido. Tente novamente.");
//                        break;
//                    }
//
//                    String login = null;
//
//                    switch (tipoLogin) {
//                        case ADMIN:
//                        case LAB:
//                        case RECEPCAO:
//                            System.out.print("Digite seu nome de usuário: ");
//                            login = sc.nextLine();
//                            if (login == null || login.trim().isEmpty()) {
//                                System.out.println("Usuário inválido.");
//                                break;
//                            }
//                            break;
//                        case PACIENTE:
//                            System.out.print("Digite seu CPF (somente números): ");
//                            login = sc.nextLine();
//                            if (!login.matches("\\d{11}")) {
//                                System.out.println("CPF inválido.");
//                                break;
//                            }
//                            break;
//                        case MEDICO:
//                            System.out.print("Digite seu CRM: ");
//                            login = sc.nextLine();
//                            if (login == null || login.trim().isEmpty()) {
//                                System.out.println("CRM inválido.");
//                                break;
//                            }
//                            break;
//                    }
//
//                    if (login == null || login.trim().isEmpty()) {
//                        System.out.println("Login inválido. Tente novamente.");
//                        break;
//                    }
//                    System.out.print("Senha: ");
//                    String senha = sc.nextLine();
//
//                    if (usuarioController.autenticarUsuario(tipoLogin, login, senha)) {
//                        MenuView menuView = new MenuView();
//                        menuView.abrirMenu();
//                    } else {
//                        System.out.println("Falha no login.");
//                    }
//                    break;
//                case 3:
//                    System.out.println("\nSaindo...");
//                    sc.close();
//                    em.close();
//                    entityManagerFactory.close();
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
//                    break;
//            }
//        }
//    }
//}

