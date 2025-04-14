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


import org.example.repository.*;
import org.example.service.ProdutoServices;
import org.example.service.UsuarioServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.entity.RelatorioEntity;
import org.example.service.RelatorioService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.security.auth.login.Configuration;
import java.time.LocalDate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializa Hibernate
        //talvez isso nao possa ficar aqui, vai dar conflito com o customizerfactor usado pelos outros

        //joao vitor
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        UsuarioRepository usuarioRepository = new UsuarioRepository(session);
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);
      
        ProdutoRepository produtoRepository = new ProdutoRepository(session);
        ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

        //gabriela takeda
        EntityManager em = CustomizerFactory.getEntityManager();
        RelatorioRepository relatorioRepository = new RelatorioRepository(em);
        ConsultaRepository consultaRepository = new ConsultaRepository(em);
        
  
        //polyana
        PacienteService pacienteService = new PacienteService();

        while (executando) {

            System.out.println("\n=== Sistema de Login ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();


            if (!entrada.matches("\\d+")) {
                System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");

                continue;
            }

            int opcao = Integer.parseInt(entrada);
            switch (opcao) {
                case 1:
                    usuarioServices.cadastrarUsuario(); // Retorna para o cadastro
                    break;

                case 2:
                    System.out.print("\nDigite seu e-mail ou CPF: ");
                    String loginOuCpf = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (usuarioServices.autenticarUsuario(loginOuCpf, senha)) {
                        System.out.println("\nLogin bem-sucedido!");
                        M
                        return;
                    } else {
                        System.out.println("\nLogin falhou. Verifique suas credenciais.");
                    }
                    break;

                case 3:
                    System.out.println("\nSaindo...");

                    scanner.close();
                    session.close();
                    factory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;


       

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

    }
}