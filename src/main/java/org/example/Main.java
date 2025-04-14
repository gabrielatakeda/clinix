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
    }
}