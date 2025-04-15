package org.example;

import org.example.entity.*;
import org.example.service.MedicoService;

import org.example.Entity.ConsultaEntity;
import org.example.Entity.PacienteEntity;
import org.example.Repository.ConsultaRepository;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.PacienteRepository;
import org.example.service.ConsultaService;
import org.example.service.PacienteService;
import javax.persistence.EntityManager;
import java.util.List;


import org.example.entity.EnderecoEntity;
import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.*;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        PacienteService pacienteService = new PacienteService();
        MedicoService medicoService = new MedicoService();

        while(true){
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Pacientes");
            System.out.println("2 - Gerenciar Médicos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = sc.nextLine();

            switch(escolha){
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

    private static void menuPaciente(Scanner sc, PacienteService service){
        while(true){
            System.out.println("\n=== MENU PACIENTE ===");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Listar pacientes");
            System.out.println("3 - Atualizar paciente");
            System.out.println("4 - Remover paciente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch(opcao){
                case "1":
                    cadastrarPaciente(sc, service);
                    break;
                case "2":
                    var lista = service.listarPacientes();
                    if(lista.isEmpty()){
                        System.out.println("Nenhum paciente encontrado.");
                    }else{
                        for(PacienteEntity p : lista){
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

    private static void cadastrarPaciente(Scanner sc, PacienteService service){
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
        while(true){
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
            if(!sc.nextLine().equalsIgnoreCase("s")) break;
        }

        PacienteEntity salvo = service.salvarPaciente(paciente, enderecos);
        System.out.println(salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");
    }

    private static void menuMedico(Scanner sc, MedicoService service){
        while(true){
            System.out.println("\n=== MENU MÉDICO ===");
            System.out.println("1 - Cadastrar médico");
            System.out.println("2 - Listar médicos");
            System.out.println("3 - Atualizar médico");
            System.out.println("4 - Remover médico");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch(opcao){
                case "1":
                    System.out.print("Nome completo: ");
                    String nome = sc.nextLine();
                    System.out.print("CRM: ");
                    String crm = sc.nextLine();
                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    MedicoEntity medico = new MedicoEntity(null, nome, crm, esp, tel);
                    service.salvarMedico(medico);
                    break;
                case "2":
                    var medicos = service.listarMedicos();
                    if(medicos.isEmpty()){
                        System.out.println("Nenhum médico cadastrado.");
                    }else{
                        for(MedicoEntity m : medicos){
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
        Scanner sc = new Scanner(System.in);
        boolean executando = true;
        //talvez isso nao possa ficar aqui, vai dar conflito com o customizerfactor usado pelos outros

        //joao vitor
        //talvez isso nao possa ficar aqui, vai dar conflito com o customizerfactor usado pelos outros
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

        //joao martinazzo
        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        ConsultaService consultaService = new ConsultaService();

        while (executando) {

            System.out.println("\n=== Sistema de Login ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = sc.nextLine();


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
                    String loginOuCpf = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    if (usuarioServices.autenticarUsuario(loginOuCpf, senha)) {
                        System.out.println("\nLogin bem-sucedido!");


                    } else {
                        System.out.println("\nLogin falhou. Verifique suas credenciais.");
                    }
                    break;

                case 3:
                    System.out.println("\nSaindo...");

                    sc.close();
                    session.close();
                    factory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;


        //os nomes sendo utilizados são meramente irrelevantes, devo trocar na implementação como:
        // consulta ao invez de paciente etc...

        EntityManager em = CustomizerFactory.getEntityManager();
        PacienteRepository pacienteRepository = new PacienteRepository(em);

        PacienteService pacienteService = new PacienteService();

        List<PacienteEntity> pacientes = pacienteRepository.findByFk_usuario(1L);

        for (PacienteEntity paciente : pacientes) {
            pacienteService.exibirConsulta(paciente);
        }

        // a partir daqui é a parte de consulta, acima é apenas teste

        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        ConsultaService consultaService = new ConsultaService();

        List<ConsultaEntity> consultas = consultaService.findByNome();

        for (ConsultaEntity consulta : consultas){
            consultaService.exibirConsultas(consulta);
        }


            }
        }


    }
}
