package org.example.service;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);

    boolean executando = true;
    while (executando) {
        System.out.println("\n======= MENU =======");
        System.out.println("1- Cadastrar Paciente");
        System.out.println("2- Cadastrar Medico");
        System.out.println("3- Agendar consulta.");
        System.out.println("4- Prontuarios.");
        System.out.println("5- Relatorios.");
        System.out.println("6- Sair ");
        System.out.print("Escolha uma opção: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Cadastrar Paciente");
                break;

            case 2:
                System.out.println("Cadastrar Medico");
                break;

            case 3:
                System.out.println("Agendar Consulta");
                break;

            case 4:
                System.out.println("Prontuarios");
                break;

            case 5:
                System.out.println("Relatorios: ");
                System.out.println("2 - Relatório de Pacientes por Médico");
                relatorioService.gerarRelatorioPacientesPorMedico(crm2);
                System.out.println("5 - Relatório de Consultas por Paciente");
                relatorioService.gerarRelatorioConsultasPorPaciente(cpf);

                break;

            case 6:
                System.out.println("\nSaindo...");
                sc.close();
//                session.close();
//                factory.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
