package org.example.view;

import org.example.controller.ProntuarioController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class MenuView {


    public void exibirMenu() {
        JFrame frame = new JFrame();
        frame.setTitle("CLINIX - Menu Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 480, 360);

        JPanel painel = new JPanel();
        painel.setBorder(new EmptyBorder(5, 5, 5, 5));
        painel.setLayout(null);
        frame.setContentPane(painel);

        JLabel titulo = new JLabel("CLINIX - MENU PRINCIPAL");
        titulo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        titulo.setBounds(120, 30, 250, 30);
        painel.add(titulo);

        JButton btnCadastrarMedico = new JButton("Cadastrar Médicos");
        btnCadastrarMedico.setBounds(20, 80, 200, 35);
        painel.add(btnCadastrarMedico);
        btnCadastrarMedico.addActionListener((ActionEvent e) -> {
            MedicoView medico = new MedicoView();
            medico.exibirMenu();
        });

        JButton btnCadastrarPaciente = new JButton("Cadastrar Pacientes");
        btnCadastrarPaciente.setBounds(20, 130, 200, 35);
        painel.add(btnCadastrarPaciente);
        btnCadastrarPaciente.addActionListener((ActionEvent e) -> {
            PacienteView paciente = new PacienteView();
            paciente.exibirMenu();
        });

        JButton btnAgendarConsulta = new JButton("Agendar Consultas");
        btnAgendarConsulta.setBounds(20, 180, 200, 35);
        painel.add(btnAgendarConsulta);
        btnAgendarConsulta.addActionListener((ActionEvent e) -> {
            ConsultaView consulta = new ConsultaView();
            consulta.exibirMenu();
        });

        JButton btnProntuario = new JButton("Prontuários");
        btnProntuario.setBounds(240, 180, 200, 35);
        painel.add(btnProntuario);
        btnProntuario.addActionListener((ActionEvent e) -> {
            ProntuarioView prontuario = new ProntuarioView();
            prontuario.exibirMenu();
        });

        JButton btnExame = new JButton("Exames");
        btnExame.setBounds(240, 130, 200, 35);
        painel.add(btnExame);
        btnExame.addActionListener((ActionEvent e) -> {
            ExameView exame = new ExameView();
            exame.exibirMenu();
        });

        JButton btnEstoque = new JButton("Estoque");
        btnEstoque.setBounds(240, 80, 200, 35);
        painel.add(btnEstoque);
        btnEstoque.addActionListener((ActionEvent e) -> {
//            ProdutoView estoque = new ProdutoView();
//            estoque.exibirMenu();

        });

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(350, 260, 80, 30);
        painel.add(btnSair);
        btnSair.addActionListener((ActionEvent e) -> {
            frame.dispose();
            LoginView loginView = new LoginView();
            loginView.exibirMenu();
        });

        JToolBar toolBar = new JToolBar();
        toolBar.setRollover(true);
        toolBar.setBounds(0, 0, 460, 20);
        painel.add(toolBar);

        frame.setVisible(true);
    }


//    public void abrirMenu() {
//        Scanner sc = new Scanner(System.in);
//        boolean executando = true;
//        while (executando) {
//            System.out.println("\n======= MENU =======");
//            System.out.println("1- Gerenciar Paciente");
//            System.out.println("2- Gerenciar Medico");
//            System.out.println("3- Agendar consulta.");
//            System.out.println("4- Prontuarios.");
//            System.out.println("5- Estoque de insumos.");
//            System.out.println("6- Exames");
////            System.out.println("7- Relatorios.");
//            System.out.println("7- Sair ");
//            System.out.print("Escolha uma opção: ");
//
//            int opcao = sc.nextInt();
//            sc.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    PacienteView pacienteView = new PacienteView();
//                    pacienteView.exibirMenu();
//                    break;
//                case 2:
//                    MedicoView medicoView = new MedicoView();
//                    medicoView.exibirMenu();
//                    break;
//                case 3:
//                    ConsultaView consultaView = new ConsultaView();
//                    consultaView.exibirMenu();
//                    break;
//                case 4:
//                    ProntuarioView prontuarioView = new ProntuarioView();
//                    prontuarioView.printMenu();
//                    break;
//                case 5:
//                    ProdutoView produtoView = new ProdutoView();
//                    produtoView.printMenu();
//                    break;
//                case 6:
//                    ExameView exameView = new ExameView();
//                    exameView.printMenu();
//                    break;
////                case 7:
////                    RelatorioView relatorioView = new RelatorioView();
////                    relatorioView.printMenu();
////                    break;
//                case 7:
//                    System.out.println("\nSaindo...");
//                    sc.close();
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Opção inválida. Tente novamente.");
//                    break;
//            }
//        }
//    }
}