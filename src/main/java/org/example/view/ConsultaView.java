package org.example.view;

import org.example.controller.ConsultaController;
import org.example.controller.MedicoController;
import org.example.controller.PacienteController;
import org.example.model.entity.Consulta;
import org.example.model.entity.Exame;
import org.example.model.entity.Paciente;
import org.example.model.enums.StatusConsulta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaView {

    Scanner sc = new Scanner(System.in);
    ConsultaController consultaController = new ConsultaController();


    public void printMenu() {

        List<Consulta> ListaConsultasEntity = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n==== MENU CONSULTAS ====");
        System.out.println("1. Agendar uma consulta");
        System.out.println("2. Editar consulta");
        System.out.println("3. Listar todas as consultas");
        System.out.println("4. Cancelar consulta");
        System.out.println("5. Retornar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                Consulta novaConsulta = new Consulta();
                Exame novaAmostra = new Exame();
                Paciente pacienteSelecionado;

                try {
                    System.out.println("AGENDAMENTO DE CONSULTA");
                    System.out.print("Insira o CPF do paciente: ");
                    String cpf = sc.nextLine().trim();

                    PacienteController pacienteController = new PacienteController();
                    pacienteSelecionado = pacienteController.buscarPorCpf(cpf);

                    if (pacienteSelecionado == null) {
                        System.out.println("\nPaciente não encontrado! Cadastre o paciente primeiro.");
                        return;
                    }
                    MedicoView medicoView = new MedicoView();
                    var medicoSelecionado = medicoView.selecionarMedico();
                    System.out.println("Data da consulta (dd/MM/yyyy HH:mm): ");
                    String dataConsultaStr = sc.nextLine().trim();
                    if (dataConsultaStr == null || dataConsultaStr.trim().isEmpty()) {
                        System.out.println("Data inválida. Digite no formato dd/MM/yyyy.");
                        return; // ou pedir novamente
                    } else {
                        LocalDateTime dataConsulta = LocalDateTime.parse(dataConsultaStr, formatter);

                        novaConsulta.setMedico(medicoSelecionado);
                        novaConsulta.setPaciente(pacienteSelecionado);
                        novaConsulta.setLocalDateTime(dataConsulta);
                        novaConsulta.setStatus(StatusConsulta.AGENDADA);

                        Consulta salvo = consultaController.salvarConsulta(novaConsulta);
                        System.out.println(salvo.getID_Consulta() != null ? "Consulta agendada com sucesso!" : "Erro ao salvar.");
                    }
                } catch (Exception e) {
                    System.out.println("\nErro: Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
                    return;
                }
                break;
            case 2:
                System.out.println("Editar consulta.");
                consultaController.exibirConsultas();
                if (ListaConsultasEntity.isEmpty()) {
                    System.out.println("Nenhuma consulta encontrada.");
                    return;
                } else {
                    System.out.print("Digite a Data da consulta: ");
                    String dataConsulta = sc.nextLine();
                }
                break;
            case 3:
                consultaController.exibirConsultas();
                break;
            case 4:
                System.out.print("\nDigite o Data da consulta que deseja deletar: ");
                String data = sc.nextLine();
                sc.nextLine(); // limpar buffer
                consultaController.removerPorData(data);
                break;
            case 5:
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
