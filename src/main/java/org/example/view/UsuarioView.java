package org.example.view;

import org.example.controller.UsuarioController;
import org.example.model.enums.TypeUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UsuarioView {

    public static void showCadastroScreen() {
        JFrame frame = new JFrame("Cadastro de Usuário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20,20,20,20));
        panel.setBackground(new Color(240,240,255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipo = new JLabel("Tipo de Usuário:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        panel.add(lblTipo, gbc);

        String[] tipos = {"ADMIN","PACIENTE","MEDICO","LAB","RECEPCAO"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        gbc.gridy=1;
        panel.add(cbTipo, gbc);

        JLabel lblId = new JLabel("Nome de Usuário:");
        lblId.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy=2;
        panel.add(lblId, gbc);

        JTextField tfId = new JTextField();
        gbc.gridy=3;
        panel.add(tfId, gbc);

        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy=4;
        panel.add(lblNome, gbc);

        JTextField tfNome = new JTextField();
        gbc.gridy=5;
        panel.add(tfNome, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy=6;
        panel.add(lblEmail, gbc);

        JTextField tfEmail = new JTextField();
        gbc.gridy=7;
        panel.add(tfEmail, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy=8;
        panel.add(lblSenha, gbc);

        JPasswordField pfSenha = new JPasswordField();
        gbc.gridy=9;
        panel.add(pfSenha, gbc);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(100,149,237));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy=10; gbc.gridwidth=2;
        panel.add(btnCadastrar, gbc);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        cbTipo.addActionListener((ActionEvent e) -> {
            String sel = (String)cbTipo.getSelectedItem();
            switch (sel) {
                case "PACIENTE": lblId.setText("CPF (11 dígitos):");      break;
                case "MEDICO":   lblId.setText("CRM:");                 break;
                default:         lblId.setText("Nome de Usuário:");     break;
            }
        });

        UsuarioController ctrl = new UsuarioController();
        btnCadastrar.addActionListener((ActionEvent e) -> {
            TypeUser tipo  = TypeUser.valueOf((String)cbTipo.getSelectedItem());
            String id      = tfId.getText().trim();
            String nome    = tfNome.getText().trim();
            String email   = tfEmail.getText().trim();
            String senha   = new String(pfSenha.getPassword());

            boolean ok = ctrl.cadastrarUsuario(tipo, nome, email, id, senha);
            if (ok) {
                JOptionPane.showMessageDialog(frame,
                        "Cadastro efetuado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                MainMenuView.showMainMenu();  // volta ao menu inicial
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Falha no cadastro. Verifique os dados e tente novamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}