package org.example;


import org.example.entity.EnderecoEntity;
import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;
import org.example.service.PacienteService;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.entity.*;
import org.example.service.PacienteService;
import org.example.service.MedicoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        PacienteService pacienteService = new PacienteService();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== MENU DE INSERÇÃO DE PACIENTE ===");

        System.out.print("ID do paciente: ");
        long pacienteId = sc.nextLong();
        sc.nextLine(); // limpa o buffer

        System.out.print("Nome completo: ");
        String nomeCompleto = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataString = sc.nextLine();
        LocalDate dataNascimento = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
            return; // encerra o programa ou volta ao menu
        }

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        var pacienteTeste = new PacienteEntity(
                null,
                nomeCompleto,
                cpf,
                dataNascimento,
                telefone, // telefone de exemplo
                new ArrayList<>() // lista de endereços vazia
        );

        var listaEndereco = new ArrayList<EnderecoEntity>();
        String opcao;

        do {
            System.out.println("\n=== INSERÇÃO DE ENDEREÇO ===");

            System.out.print("Logradouro: ");
            String logradouro = sc.nextLine();

            System.out.print("Cidade: ");
            String cidade = sc.nextLine();

            System.out.print("Estado: ");
            String estado = sc.nextLine();

            System.out.print("Número: ");
            int numero = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            System.out.print("Esse é o endereço principal? (s/n): ");
            String principalInput = sc.nextLine();
            boolean isPrincipal = principalInput.equalsIgnoreCase("s");

            listaEndereco.add(new EnderecoEntity(
                    null, logradouro, cidade, estado, numero, isPrincipal, pacienteTeste
            ));

            System.out.print("Deseja adicionar outro endereço? (s/n): ");
            opcao = sc.nextLine();

        } while(opcao.equalsIgnoreCase("s"));

        var salvo = pacienteService.salvarPaciente(pacienteTeste, listaEndereco);

        if (salvo.getId() != null) {
            System.out.println("Paciente salvo com sucesso! ID: " + salvo.getId());
        } else {
            System.out.println("Paciente não foi salvo. CPF já existe!");
        }

        pacienteService.salvarPaciente(pacienteTeste, listaEndereco);

        sc.close();

        Scanner sc = new Scanner(System.in);
        PacienteService pacienteService = new PacienteService();
        MedicoService medicoService = new MedicoService();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Pacientes");
            System.out.println("2 - Gerenciar Médicos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    menuPaciente(sc, pacienteService);
                    break;
                case "2":
                    menuMedico(sc, medicoService);
                    break;
                case "0":
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuPaciente(Scanner sc, PacienteService service) {
        while (true) {
            System.out.println("\n=== MENU PACIENTE ===");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar pacientes");
            System.out.println("3 - Atualizar paciente");
            System.out.println("4 - Remover paciente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarPaciente(sc, service);
                    break;
                case "2":
                    var lista = service.listarPacientes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum paciente encontrado.");
                    } else {
                        for (PacienteEntity p : lista) {
                            System.out.println("ID: " + p.getId() + ", Nome: " + p.getNomeCompleto());
                        }
                    }
                    break;
                case "3":
                    System.out.print("Digite o ID do paciente para atualizar: ");
                    Long idAtualizar = Long.parseLong(sc.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    service.atualizarPaciente(idAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o ID do paciente para remover: ");
                    Long idRemover = Long.parseLong(sc.nextLine());
                    service.removerPaciente(idRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarPaciente(Scanner sc, PacienteService service) {
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNasc = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        PacienteEntity paciente = new PacienteEntity(null, nome, cpf, dataNasc, telefone, new ArrayList<>());

        List<EnderecoEntity> enderecos = new ArrayList<>();
        while (true) {
            System.out.print("Logradouro: ");
            String logradouro = sc.nextLine();
            System.out.print("Cidade: ");
            String cidade = sc.nextLine();
            System.out.print("Estado: ");
            String estado = sc.nextLine();
            System.out.print("Número: ");
            Integer numero = Integer.parseInt(sc.nextLine());
            System.out.print("É o endereço principal? (s/n): ");
            boolean isPrincipal = sc.nextLine().equalsIgnoreCase("s");

            enderecos.add(new EnderecoEntity(null, logradouro, cidade, estado, numero, isPrincipal, paciente));

            System.out.print("Deseja adicionar outro endereço? (s/n): ");
            if (!sc.nextLine().equalsIgnoreCase("s")) break;
        }

        PacienteEntity salvo = service.salvarPaciente(paciente, enderecos);
        System.out.println(salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");
    }

    private static void menuMedico(Scanner sc, MedicoService service) {
        while (true) {
            System.out.println("\n=== MENU MÉDICO ===");
            System.out.println("1 - Cadastrar médico");
            System.out.println("2 - Listar médicos");
            System.out.println("3 - Atualizar médico");
            System.out.println("4 - Remover médico");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome completo: ");
                    String nome = sc.nextLine();
                    System.out.print("CRM: ");
                    String crm = sc.nextLine();
                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    MedicoEntity medico = new MedicoEntity(null, nome, crm, esp, tel, new ArrayList<>());
                    service.salvarMedico(medico);
                    break;
                case "2":
                    var medicos = service.listarMedicos();
                    if (medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado.");
                    } else {
                        for (MedicoEntity m : medicos) {
                            System.out.println("ID: " + m.getId() + ", Nome: " + m.getNomeCompleto());
                        }
                    }
                    break;
                case "3":
                    System.out.print("Digite o ID do médico para atualizar: ");
                    Long idAtualizar = Long.parseLong(sc.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    service.atualizarMedico(idAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o ID do médico para remover: ");
                    Long idRemover = Long.parseLong(sc.nextLine());
                    service.removerMedico(idRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}