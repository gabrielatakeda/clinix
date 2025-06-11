package org.example.view;

import org.example.controller.ProntuarioController;
import org.example.model.entity.Prontuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ProntuarioView extends JFrame {

    ProntuarioController prontuarioController = new ProntuarioController();

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textMotivo;
    private JTextField textDiagnostico;
    private JTextField cpfField;


    public void exibirMenu() {
        setTitle("CLINIX - Menu Prontuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null); // centraliza a janela

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titulo = new JLabel("Menu de Prontuários");
        titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
        titulo.setBounds(120, 20, 250, 30);
        contentPane.add(titulo);

        JButton btnBuscarPorCPF = new JButton("Buscar por CPF");
        btnBuscarPorCPF.setBounds(125, 70, 200, 35);
        contentPane.add(btnBuscarPorCPF);
        btnBuscarPorCPF.addActionListener(e -> prontuarioCPF());

        JButton btnListarProntuarios = new JButton("Listar Prontuários");
        btnListarProntuarios.setBounds(125, 120, 200, 35);
        contentPane.add(btnListarProntuarios);
        btnListarProntuarios.addActionListener(e -> listarProntuario());

        JButton btnEditarProntuario = new JButton("Editar Prontuário");
        btnEditarProntuario.setBounds(125, 170, 200, 35);
        contentPane.add(btnEditarProntuario);
        btnEditarProntuario.addActionListener(e -> editarProntuario());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 10, 80, 20);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            dispose(); // aqui você pode voltar ao menu principal do sistema geral
        });

        setVisible(true);
    }

    public void prontuarioCPF(){
        setTitle("Prontuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                listarProntuario();

            }
        });
        confirmButton.setBounds(173, 120, 103, 23);
        contentPane.add(confirmButton);

        cpfField = new JTextField();
        cpfField.setBounds(90, 81, 258, 20);
        contentPane.add(cpfField);
        cpfField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Insira o CPF do paciente");
        lblNewLabel.setBounds(155, 56, 150, 14);
        contentPane.add(lblNewLabel);

        JButton returnButCPF = new JButton("voltar");
        returnButCPF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        returnButCPF.setBounds(10, 7, 74, 14);
        contentPane.add(returnButCPF);
    }


    public void editarProntuario () {
        setTitle("Prontuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        textMotivo = new JTextField();
        textMotivo.setBounds(10, 28, 214, 20);
        contentPane.add(textMotivo);
        textMotivo.setColumns(10);

        JLabel lblNewLabel = new JLabel("Motivo da consulta");
        lblNewLabel.setBounds(10, 11, 141, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Diagnóstioco\r\n");
        lblNewLabel_1.setBounds(10, 57, 141, 14);
        contentPane.add(lblNewLabel_1);

        textDiagnostico = new JTextField();
        textDiagnostico.setBounds(10, 73, 214, 20);
        contentPane.add(textDiagnostico);
        textDiagnostico.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Será necessário exame?");
        lblNewLabel_2.setBounds(10, 104, 160, 14);
        contentPane.add(lblNewLabel_2);

        JRadioButton examButtonSim = new JRadioButton("Sim");
        examButtonSim.setBounds(10, 124, 64, 23);
        contentPane.add(examButtonSim);

        JRadioButton examButtonNao = new JRadioButton("Não");
        examButtonNao.setBounds(76, 124, 75, 23);
        contentPane.add(examButtonNao);

        ButtonGroup RadioGroup = new ButtonGroup();
        RadioGroup.add(examButtonSim);
        RadioGroup.add(examButtonNao);

        JLabel lblNewLabel_3 = new JLabel("Caso seja, qual Exame?");
        lblNewLabel_3.setBounds(10, 156, 141, 14);
        contentPane.add(lblNewLabel_3);

        JComboBox ExameNecess = new JComboBox();
        ExameNecess.setBounds(10, 174, 141, 22);
        contentPane.add(ExameNecess);

        JButton confirmEditionButton = new JButton("Confirmar");
        confirmEditionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
//                ProntuarioLista frame = new ProntuarioLista();
//                frame.setVisible(true);
                listarProntuario();
            }
        });
        confirmEditionButton.setBounds(321, 227, 103, 23);
        contentPane.add(confirmEditionButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                listarProntuario();
            }
        });
        cancelButton.setBounds(208, 227, 103, 23);
        contentPane.add(cancelButton);


    }

    public void listarProntuario(){
        setTitle("Prontuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JList list = new JList();
        list.setBounds(10, 28, 414, 192);
        contentPane.add(list);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                editarProntuario();
            }
        });
        confirmButton.setBounds(165, 227, 103, 23);
        contentPane.add(confirmButton);

        JButton returnButCPF = new JButton("voltar");
        returnButCPF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                prontuarioCPF();
            }
        });
        returnButCPF.setBounds(10, 7, 74, 14);
        contentPane.add(returnButCPF);
    }

}
