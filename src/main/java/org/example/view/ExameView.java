package org.example.view;

import org.example.controller.ExameController;
import org.example.model.enums.StatusAmostraLab;

import java.util.Scanner;

public class ExameView {

    ExameController exameController = new ExameController();
    Scanner sc = new Scanner(System.in);

    public void printMenu() {
        int opcao = -1;
        StatusAmostraLab status = null;

        while (opcao != 0) {
            System.out.println("\n==== MENU AMOSTRAS ====");
            System.out.println("1. Listar amostras por status");
            System.out.println("2. Atualizar status da amostra");
            System.out.println("3. Deletar amostra");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    printMenuListagem();
                    break;
                case 2:
                    status = StatusAmostraLab.PENDENTE;
                    exameController.atualizarStatus(status,sc);
                    break;
                case 3:
                    exameController.deletarAmostra(sc);
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public void printMenuListagem() {
        int opcao;
        System.out.println("\n==== MENU LISTAGEM AMOSTRAS ====");
        System.out.println("--Lista de exames por status--");
        System.out.println("1. Concluídos");
        System.out.println("2. Pendentes");
        System.out.println("3. Inconclusivo");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                exameController.listarPorStatus(StatusAmostraLab.CONCLUIDO);
                break;
            case 2:
                exameController.listarPorStatus(StatusAmostraLab.PENDENTE);
                break;
            case 3:
                exameController.listarPorStatus(StatusAmostraLab.INCONCLUSIVO);
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
