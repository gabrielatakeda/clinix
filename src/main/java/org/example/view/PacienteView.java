package org.example.view;

import org.example.model.entity.Endereco;
import org.example.model.entity.Paciente;
import org.example.controller.PacienteController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacienteView {

    PacienteController pacienteController = new PacienteController();
    Scanner sc = new Scanner(System.in);

    public void printMenu() {
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
                    cadastrarPaciente();
                    break;
                case "2":
                    var lista = pacienteController.listarPacientes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum paciente encontrado.");
                    } else {
                        for (Paciente p : lista) {
                            System.out.println("CPF: " + p.getCpf() + ", Nome: " + p.getNome());
                        }
                    }
                    break;
                case "3":
                    System.out.print("Digite o CPF do paciente para atualizar: ");
                    String cpfAtualizar = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    pacienteController.atualizarPacienteCPF(cpfAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o CPF do paciente para remover: ");
                    String cpfRemover = sc.nextLine();
                    pacienteController.removerPacienteCPF(cpfRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarPaciente() {
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNasc = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Paciente paciente = new Paciente(null, nome, cpf, dataNasc, telefone, new ArrayList<>());

        List<Endereco> enderecos = new ArrayList<>();
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

            enderecos.add(new Endereco(null, logradouro, cidade, estado, numero, isPrincipal, paciente));

            System.out.print("Deseja adicionar outro endereço? (s/n): ");
            if (!sc.nextLine().equalsIgnoreCase("s")) break;
        }

        Paciente salvo = pacienteController.salvarPaciente(paciente, enderecos);
        System.out.println(salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");
    }

}
