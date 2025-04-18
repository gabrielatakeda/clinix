package org.example;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;
import org.example.service.AmostrasLabService;
import org.example.service.ConsultaService;
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
        AmostrasLabService amostraService = new AmostrasLabService();

        List<ConsultaEntity> ListaConsultasEntity = service.listarConsultas();

        List<AmostrasLabEntity> listaAmostraEntity = amostraService.listarAmostra();

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Menu Consultas");
            System.out.println("2. Menu Amostras");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    menuConsulta(opcao, scanner, service, ListaConsultasEntity, amostraService);
                    break;
                case 2:
                    menuAmostra(opcao, scanner, amostraService, listaAmostraEntity, service);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);


    }

    private static void menuConsulta(int opcao, Scanner scanner, ConsultaService service, List<ConsultaEntity> ListaConsultasEntity, AmostrasLabService amostrasLabService) {
        do {
            System.out.println("\n==== MENU CONSULTAS ====");
            System.out.println("1. Cadastrar nova consulta");
            System.out.println("2. Atualizar consulta");
            System.out.println("3. Listar todas as consultas");
            System.out.println("4. Cancelar/Deletar consulta");
            System.out.println("5. Retornar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarConsulta(opcao, scanner, service, amostrasLabService);
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

        } while (opcao != 5);
    }

    private static void cadastrarConsulta(int opcao, Scanner scanner, ConsultaService service, AmostrasLabService amostrasLabService) {
        ConsultaEntity novaConsulta = new ConsultaEntity();
        AmostrasLabEntity novaAmostra = new AmostrasLabEntity();


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

        System.out.println("\n\tDejesa encaminhar algum exame?(s/n) ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {


            novaAmostra.setDataColeta(salvo.getData_consulta()); // pega a mesma data da consulta
            System.out.print("Data da amostra : " + novaAmostra.getDataColeta());

            System.out.print("\nTipo: ");
            novaAmostra.setTipoExame(scanner.nextLine());

            System.out.print("Resultado: ");
            novaAmostra.setResultado(scanner.nextLine());

            novaAmostra.setConsulta(salvo); // associa a amostra à consulta

            AmostrasLabEntity amostraSalva = amostrasLabService.salvarAmostra(novaAmostra);

            System.out.println(amostraSalva.getId_amostralab() != null ? "Amostra cadastrada com sucesso!" : "Erro ao salvar a amostra.");
        }else if(!resposta.equalsIgnoreCase("n")){
            System.out.println("\n\tOpcao invalida!");

        }
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

    //AMOSTRAS
    private static void menuAmostra(int opcao, Scanner scanner, AmostrasLabService amostrasLabService, List<AmostrasLabEntity> listaAmostraEntity, ConsultaService service) {
        do {
            System.out.println("\n==== MENU AMOSTRAS ====");
            System.out.println("1. Atualizar amostra");
            System.out.println("2. Listar todas as amostras");
            System.out.println("3. Cancelar/Deletar amostras");
            System.out.println("4. Retornar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    break;
                case 2:
                    amostrasLabService.exibirAmostras();
                    break;
                case 3:
                    DeletarAmostras(scanner,amostrasLabService);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 4);
    }

    private static void DeletarAmostras(Scanner scanner, AmostrasLabService amostrasLabService) {

        System.out.print("\nDigite o ID da consulta que deseja deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // limpar buffer

        amostrasLabService.removerPorId(id);
    }
}



