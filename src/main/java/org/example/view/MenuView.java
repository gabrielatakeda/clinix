package org.example.view;

import org.example.controller.ProntuarioController;

import java.util.Scanner;

public class MenuView {

    public void abrirMenu() {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;
        while (executando) {
            System.out.println("\n======= MENU =======");
            System.out.println("1- Gerenciar Paciente");
            System.out.println("2- Gerenciar Medico");
            System.out.println("3- Agendar consulta.");
            System.out.println("4- Prontuarios.");
            System.out.println("5- Estoque de insumos.");
            System.out.println("6- Exames");
//            System.out.println("7- Relatorios.");
            System.out.println("7- Sair ");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    PacienteView pacienteView = new PacienteView();
                    pacienteView.exibirMenu();
                    break;
                case 2:
                    MedicoView medicoView = new MedicoView();
                    medicoView.exibirMenu();
                    break;
                case 3:
                    ConsultaView consultaView = new ConsultaView();
                    consultaView.printMenu();
                    break;
                case 4:
                    ProntuarioView prontuarioView = new ProntuarioView();
                    prontuarioView.printMenu();
                    break;
                case 5:
                    ProdutoView produtoView = new ProdutoView();
                    produtoView.printMenu();
                    break;
                case 6:
                    ExameView exameView = new ExameView();
                    exameView.printMenu();
                    break;
//                case 7:
//                    RelatorioView relatorioView = new RelatorioView();
//                    relatorioView.printMenu();
//                    break;
                case 7:
                    System.out.println("\nSaindo...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}