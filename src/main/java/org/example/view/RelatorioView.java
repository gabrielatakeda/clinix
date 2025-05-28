package org.example.view;

import org.example.controller.RelatorioConsultaPacienteController;
import org.example.controller.RelatorioPacienteMedicoController;

import java.util.Scanner;

public class RelatorioView {

    Scanner sc = new Scanner(System.in);

    public void printMenu() {
        boolean saida = true;
        while (saida) {
            System.out.println("\n=== Relatorios. ===");
            System.out.println("1 - Relatório de Pacientes por Médico");
            System.out.println("2 - Relatório de Consultas por Paciente");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Relatório de Pacientes por Médico");
                    System.out.print("Digite o CRM do médico para gerar o relatório: ");
                    String crm = sc.nextLine();
                    RelatorioPacienteMedicoController relatorioPacienteMedicoController = new RelatorioPacienteMedicoController();
                    relatorioPacienteMedicoController.gerarRelatorio(crm);
                    break;
                case 2:
                    System.out.println("Relatório de Consultas por Paciente");
                    System.out.print("Digite o CPF do paciente para gerar o relatório: ");
                    String cpf = sc.nextLine();
                    RelatorioConsultaPacienteController relatorioConsultaPacienteController = new RelatorioConsultaPacienteController();
                    relatorioConsultaPacienteController.gerarRelatorio(cpf);
                    break;
                case 3:
                    System.out.println("Voltando...");
                    saida = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
