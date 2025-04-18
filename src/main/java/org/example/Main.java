package org.example;

import org.example.entity.ConsultaEntity;
import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;
import org.example.service.ConsultaService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<ConsultaEntity> listaConsultaEntity = new ArrayList<>(); // simula o banco

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        ConsultaService service = new ConsultaService();
        EntityManager em = CustomizerFactory.getEntityManager();

        ConsultaRepository consultaRepository = new ConsultaRepository(em);
        ConsultaEntity consultaEntity = new ConsultaEntity();

        System.out.println(consultaRepository.buscarPorId(2L));

        do {
            System.out.println("\n==== MENU CONSULTAS ====");
            System.out.println("1. Cadastrar nova consulta");
            System.out.println("2. Listar todas as consultas");
            System.out.println("3. Buscar consulta por data");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarConsulta(scanner, service);
                    break;
                case 2:
                    listarConsultas();
                    break;
                case 3:
                    buscarPorData(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarConsulta(Scanner scanner, ConsultaService service) {
        ConsultaEntity novaConsulta = new ConsultaEntity();

        System.out.print("Data da consulta (AAAA-MM-DD): ");
        novaConsulta.setData_consulta(LocalDate.parse(scanner.nextLine()));

        System.out.print("Motivo: ");
        novaConsulta.setMotivo(scanner.nextLine());

        System.out.print("Status: ");
        novaConsulta.setStatus(scanner.nextLine());

        System.out.print("Prescrição: ");
        novaConsulta.setPrescricao(scanner.nextLine());

        System.out.print("Observações: ");
        novaConsulta.setObservacoes(scanner.nextLine());


        ConsultaEntity salvo = service.salvarConsulta(novaConsulta);
        System.out.println(salvo.getID_Consulta() != null ? "Consulta agendada com sucesso!" : "Erro ao salvar.");


        System.out.println("✅ Consulta cadastrada com sucesso!");
    }

    private static void listarConsultas() {
        if (listaConsultaEntity.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        }

        for (ConsultaEntity c : listaConsultaEntity) {
            System.out.println("\nID: " + c.getID_Consulta());
            System.out.println("Data: " + c.getData_consulta());
            System.out.println("Motivo: " + c.getMotivo());
            System.out.println("Status: " + c.getStatus());
            System.out.println("Prescrição: " + c.getPrescricao());
            System.out.println("Observações: " + c.getObservacoes());
        }
    }

    private static void buscarPorData(Scanner scanner) {
        System.out.print("Digite a data da consulta (AAAA-MM-DD): ");
        LocalDate dataBusca = LocalDate.parse(scanner.nextLine());
        boolean encontrado = false;

        for (ConsultaEntity c : listaConsultaEntity) {
            if (c.getData_consulta().equals(dataBusca)) {
                System.out.println("\nConsulta encontrada:");
                System.out.println("ID: " + c.getID_Consulta());
                System.out.println("Motivo: " + c.getMotivo());
                System.out.println("Status: " + c.getStatus());
                System.out.println("Prescrição: " + c.getPrescricao());
                System.out.println("Observações: " + c.getObservacoes());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(" Nenhuma consulta encontrada para essa data.");
        }
    }
}
