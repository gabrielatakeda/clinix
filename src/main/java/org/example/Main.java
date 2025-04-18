package org.example;

import org.example.entity.ConsultaEntity;
import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;
import org.example.service.ConsultaService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<ConsultaEntity> listaConsultaEntity = new ArrayList<>(); // simula o banco


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        ConsultaService service = new ConsultaService();

        List<ConsultaEntity> ListaConsultasEntity = service.listarConsultas();

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Menu Consultas");
            System.out.println("2. Menu Amostras");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarConsulta(scanner, service);
                    break;
                case 2:
                    menuConsulta(opcao, scanner,service,ListaConsultasEntity);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);


    }

    private static void menuConsulta ( int opcao, Scanner scanner, ConsultaService service, List<ConsultaEntity> ListaConsultasEntity){
        do {
            System.out.println("\n==== MENU CONSULTAS ====");
            System.out.println("1. Cadastrar nova consulta");
            System.out.println("2. Atualizar consulta");
            System.out.println("3. Listar todas as consultas");
            System.out.println("4. Cancelar/Deletar consulta");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarConsulta(scanner, service);
                    break;
                case 2:
                    atualizarMotivo(scanner, service, ListaConsultasEntity);
                    break;
                case 3:
                    service.exibirConsultas();
                    break;
                case 4:
                    DeletarConsultas(scanner, service);
                    break;
                case 5:
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

    }


    public static void atualizarMotivo(Scanner scanner, ConsultaService service, List<ConsultaEntity> consultas) {

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta encontrada.");
            return;
        } else {
            System.out.print("Digite o ID da consulta: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consumir quebra de linha

            System.out.print("Digite o novo motivo: ");
            String novoMotivo = scanner.nextLine();

            service.atualizarMotivoConsulta(id, novoMotivo);
        }
    }


    private static void DeletarConsultas(Scanner scanner, ConsultaService service) {

        System.out.print("\nDigite o ID da consulta que deseja deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // limpar buffer

        service.removerPorId(id);
    }



}