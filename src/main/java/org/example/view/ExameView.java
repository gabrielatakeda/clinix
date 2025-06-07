package org.example.view;

import org.example.controller.ExameController;
import org.example.model.enums.StatusAmostraLab;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ExameView {

    ExameController exameController = new ExameController();
    Scanner sc = new Scanner(System.in);

//    public void printMenu() {
//        int opcao = -1;
//        StatusAmostraLab status = null;
//
//        while (opcao != 0) {
//            System.out.println("\n==== MENU AMOSTRAS ====");
//            System.out.println("1. Listar amostras por status");
//            System.out.println("2. Atualizar status da amostra");
//            System.out.println("3. Deletar amostra");
//            System.out.println("0. Voltar");
//            System.out.print("Escolha uma opção: ");
//            opcao = sc.nextInt();
//            sc.nextLine(); // limpar buffer
//
//            switch (opcao) {
//                case 1:
//                    printMenuListagem();
//                    break;
//                case 2:
//                    status = StatusAmostraLab.PENDENTE;
//                    exameController.atualizarStatus(status,sc);
//                    break;
//                case 3:
//                    exameController.deletarAmostra(sc);
//                    break;
//                case 0:
//                    System.out.println("Voltando ao menu anterior...");
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//                    break;
//            }
//        }
//    }
//
//    public void printMenuListagem() {
//        int opcao;
//        System.out.println("\n==== MENU LISTAGEM AMOSTRAS ====");
//        System.out.println("--Lista de exames por status--");
//        System.out.println("1. Concluídos");
//        System.out.println("2. Pendentes");
//        System.out.println("3. Inconclusivo");
//        System.out.println("0. Voltar");
//        System.out.print("Escolha uma opção: ");
//        opcao = sc.nextInt();
//        sc.nextLine();
//
//        switch (opcao) {
//            case 1:
//                exameController.listarPorStatus(StatusAmostraLab.CONCLUIDO);
//                break;
//            case 2:
//                exameController.listarPorStatus(StatusAmostraLab.PENDENTE);
//                break;
//            case 3:
//                exameController.listarPorStatus(StatusAmostraLab.INCONCLUSIVO);
//                break;
//            case 0:
//                System.out.println("Saindo...");
//                break;
//            default:
//                System.out.println("Opção inválida.");
//        }
//    }

    public  void printMenu(){
        JFrame frame = new JFrame("MENU AMOSTRAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300); // Tamanho fixo ideal
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setLayout(new BorderLayout()); // Centraliza conteúdo vertical e horizontal

        // Painel com os botões e título
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("MENU AMOSTRAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));


        String[] nomes = {"Listar amostras por status", "Atualizar status da amostra", "Deletar amostra", "Voltar"};
        JButton[] botoes = new JButton[nomes.length];

        for (int i = 0; i < nomes.length; i++) {
            botoes[i] = new JButton(nomes[i]);
            botoes[i].setMaximumSize(new Dimension(200, 30));
            botoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            botoes[i].setFocusPainted(false);
            painel.add(botoes[i]);
            painel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Ações
        botoes[0].addActionListener(e -> { frame.setVisible(false); listagemPorStatus();});
        botoes[1].addActionListener(e -> { frame.setVisible(false); editarConsulta();});
        botoes[2].addActionListener(e -> { frame.setVisible(false); listarConsulta();});
        botoes[3].addActionListener(e -> frame.dispose());

        frame.add(painel);

        frame.setVisible(true);
    }

    private void listagemPorStatus(){

    }


}
