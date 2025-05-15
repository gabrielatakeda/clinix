package org.example;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;
import org.example.service.AmostrasLabService;
import org.example.service.ConsultaService;

import org.example.repository.*;
import org.example.service.*;

public class Main{ //Código principal
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in); //Scanner para ler entrada do usuário
        PacienteService pacienteService = new PacienteService(); //Cria instância do serviço de pacientes
        MedicoService medicoService = new MedicoService(); //Cria instância do serviço de médicos

        while(true){ //Laço principal do menu
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Gerenciar Pacientes");
            System.out.println("2 - Gerenciar Médicos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = sc.nextLine(); //Lê a escolha do usuário

            switch(escolha){
                case "1":
                    menuPaciente(sc, pacienteService); //Vai para o submenu de pacientes
                    break;
                case "2":
                    menuMedico(sc, medicoService); //Vai para o submenu de médicos
                    break;
                case "0":
                    System.out.println("Saindo...");
                    sc.close(); //Fecha o scanner
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Scanner;
          
    private static void menuPaciente(Scanner sc, PacienteService service){ //Submenu de paciente
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
                    cadastrarPaciente(sc, service); //Função para cadastrar
                    break;
                case "2":
                    var lista = service.listarPacientes(); //Lista pacientes
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
                    service.atualizarPaciente(idAtualizar, novoNome); //Atualiza nome
                    break;
                case "4":
                    System.out.print("Digite o ID do paciente para remover: ");
                    Long idRemover = Long.parseLong(sc.nextLine());
                    service.removerPaciente(idRemover); //Remove paciente
                    break;
                case "0":
                    return; //Volta ao menu principal
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    //Cadastro de paciente com seus endereços
    private static void cadastrarPaciente(Scanner sc, PacienteService service){
        System.out.print("Nome completo: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNasc = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        //Cria objeto paciente
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

            //Adiciona endereço à lista
            enderecos.add(new EnderecoEntity(null, logradouro, cidade, estado, numero, isPrincipal, paciente));

            System.out.print("Deseja adicionar outro endereço? (s/n): ");
            if(!sc.nextLine().equalsIgnoreCase("s")) break;
        }

        PacienteEntity salvo = service.salvarPaciente(paciente, enderecos); //Salva o paciente
        System.out.println(salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");
    }

    //Submenu de médico
    private static void menuMedico(Scanner sc, MedicoService service){
        while(true){
            System.out.println("\n=== MENU MÉDICO ===");
            System.out.println("1 - Cadastrar médico");
            System.out.println("2 - Listar médicos");
            System.out.println("3 - Atualizar médico");
            System.out.println("4 - Remover médico");
            System.out.println("5 - Buscar médico por CRM");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch(opcao){
                case "1": //Cadastro de médico
                    System.out.print("Nome completo: ");
                    String nome = sc.nextLine();
                    System.out.print("CRM: ");
                    String crm = sc.nextLine();
                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    MedicoEntity medico = new MedicoEntity(null, nome, crm, esp, tel);
                    service.salvarMedico(medico); //Salva o médico

        // Instancia os repositórios passando EntityManager onde necessário
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        UsuarioServices usuarioServices = new UsuarioServices(usuarioRepository);

        ProdutoRepository produtoRepository = new ProdutoRepository(em);
        ProdutoServices produtoServices = new ProdutoServices(produtoRepository);

        AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);
        RelatorioRepository relatorioRepository = new RelatorioRepository(em);
        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        PacienteService pacienteService = new PacienteService();
        ConsultaService consultaService = new ConsultaService();

        // Criando MenuService com EntityManager correto
        MenuService menu = new MenuService(em);

        while (executando) {
            System.out.println("---BEM VINDO AO CLINIX---");
            System.out.println("\n===CADASTRO / LOGIN ===");
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
                    usuarioServices.cadastrarUsuario();
                    break;
                case 2:
                    System.out.print("\nDigite seu e-mail ou CPF: ");
                    String loginOuCpf = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    if (usuarioServices.autenticarUsuario(loginOuCpf, senha)) {
                        System.out.println("\nLogin bem-sucedido!");
                        menu.abrirMenu();
                    } else {
                        System.out.println("\nLogin falhou. Verifique suas credenciais.");
                    }
                    break;
                case "3":
                    System.out.print("Digite o ID do médico para atualizar: ");
                    Long idAtualizar = Long.parseLong(sc.nextLine());
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    service.atualizarMedico(idAtualizar, novoNome); //Atualiza nome
                    break;
                case "4":
                    System.out.print("Digite o ID do médico para remover: ");
                    Long idRemover = Long.parseLong(sc.nextLine());
                    service.removerMedico(idRemover); //Remove médico
                    break;
                case "5":
                    System.out.print("Digite o CRM do médico: ");
                    String crmBusca = sc.nextLine();
                    MedicoEntity encontrado = service.buscarPorCrm(crmBusca);
                    if(encontrado != null){
                        System.out.println("Médico encontrado:");
                        System.out.println("ID: " + encontrado.getId());
                        System.out.println("Nome: " + encontrado.getNomeCompleto());
                        System.out.println("Especialidade: " + encontrado.getEspecialidade());
                        System.out.println("Telefone: " + encontrado.getTelefone());
                    }else{
                        System.out.println("Nenhum médico encontrado com o CRM informado.");
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;
            }
        }
    }
}