package org.example.view.service;

import org.example.model.entity.AmostrasLabEntity;
import org.example.model.entity.ConsultaEntity;

import org.example.model.entity.PacienteEntity;
import org.example.controller.repository.ConsultaRepository;
import org.example.controller.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.enums.StatusConsulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);
    MedicoService medicoService = new MedicoService();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void exibirConsultas() {
        List<ConsultaEntity> consultas = consultaRepository.listarTodos();

        if (consultas.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (ConsultaEntity c : consultas) {
                System.out.println("==Paciente==\n" + c.getPaciente().getNome());
                System.out.println("CPF: " + c.getPaciente().getCpf());
                System.out.println("==Médico==\n" + c.getMedico().getNomeCompleto());
                System.out.println("Especialidade: " + c.getMedico().getEspecialidade());
                System.out.println("\nData Consulta: " + c.getLocalDateTime().format(formatter));
                System.out.println("Status: " + c.getStatus());
                System.out.println("------------------------------");
            }
        }
    }

    public ConsultaEntity salvarConsulta(ConsultaEntity consulta) {
        List<ConsultaEntity> todasConsultas = consultaRepository.listarTodos();

        for (ConsultaEntity c : todasConsultas) {
            if (c.getLocalDateTime().equals(consulta.getLocalDateTime())) {
                System.out.println("\n\tConsulta já existente nessa data!\n\tPor favor, escolha outra data.");
                return c; // retorna a consulta existente
            }
        }
        consultaRepository.salvar(consulta);
        return consulta;
    }

    public List<ConsultaEntity> listarConsultas() {
        return consultaRepository.listarTodos();
    }

    public void removerPorData(String data) {
        try {
            LocalDateTime dataConsulta = LocalDateTime.parse(data, formatter);

            ConsultaEntity consulta = consultaRepository.buscarConsultaPorData(dataConsulta);

            if (consulta != null) {
                consultaRepository.remover(consulta);
                System.out.println("Consulta removida com sucesso.");
            } else {
                System.out.println("Consulta na data: " + data + " não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
        }
    }


    public void printMenu(Scanner sc, ConsultaService consultaService) {
        // Formato que o usuário deve digitar
        List<ConsultaEntity> ListaConsultasEntity = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.println("\n==== MENU CONSULTAS ====");
        System.out.println("1. Agendar uma consulta");
        System.out.println("2. Editar consulta");
        System.out.println("3. Listar todas as consultas");
        System.out.println("4. Cancelar consulta");
        System.out.println("5. Retornar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // limpar buffer

        switch (opcao) {
            case 1:
                ConsultaEntity novaConsulta = new ConsultaEntity();
                AmostrasLabEntity novaAmostra = new AmostrasLabEntity();
                PacienteEntity pacienteSelecionado;

                try {
                    System.out.println("AGENDAMENTO DE CONSULTA");
                    System.out.print("Insira o CPF do paciente: ");
                    String cpf = sc.nextLine().trim();

                    // VERIFICA O PACIENTE CADASTRADO POR CPF
                    PacienteService pacienteService = new PacienteService();
                    pacienteSelecionado = pacienteService.buscarPorCpf(cpf);

                    if (pacienteSelecionado == null) {
                        System.out.println("\nPaciente não encontrado! Cadastre o paciente primeiro.");
                        return;
                    }
                    var medicoSelecionado = medicoService.selecionarMedico();
                    System.out.println("Data da consulta (dd/MM/yyyy HH:mm): ");
                    String dataConsultaStr = sc.nextLine().trim();
                    if (dataConsultaStr == null || dataConsultaStr.trim().isEmpty()) {
                        System.out.println("Data inválida. Digite no formato dd/MM/yyyy.");
                        return; // ou pedir novamente
                    }else{
                        LocalDateTime dataConsulta = LocalDateTime.parse(dataConsultaStr, formatter);

                        novaConsulta.setMedico(medicoSelecionado);
                        novaConsulta.setPaciente(pacienteSelecionado);
                        novaConsulta.setLocalDateTime(dataConsulta);
                        novaConsulta.setStatus(StatusConsulta.AGENDADA);

                        ConsultaEntity salvo = consultaService.salvarConsulta(novaConsulta);
                        System.out.println(salvo.getID_Consulta() != null ? "Consulta agendada com sucesso!" : "Erro ao salvar.");
                    }
                } catch (Exception e) {
                    System.out.println("\nErro: Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
                    return;
                }
                break;
            case 2:
                System.out.println("Editar consulta.");
                consultaService.exibirConsultas();
                if (ListaConsultasEntity.isEmpty()) {
                    System.out.println("Nenhuma consulta encontrada.");
                    return;
                } else {
                    System.out.print("Digite a Data da consulta: ");
                    String dataConsulta = sc.nextLine();
                    //aqui ele encontrara a consulta de acordo com a data

                }
                break;
            case 3:
                consultaService.exibirConsultas();
                break;
            case 4:
                System.out.print("\nDigite o Data da consulta que deseja deletar: ");
                String data = sc.nextLine();
                sc.nextLine(); // limpar buffer
                consultaService.removerPorData(data);
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