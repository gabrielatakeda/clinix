package org.example.service;

import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;
import org.example.entity.EnderecoEntity;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacienteService {

    EntityManager em = CustomizerFactory.getEntityManager();
    PacienteRepository pacienteRepository = new PacienteRepository(em);

    public PacienteEntity salvarPaciente(PacienteEntity paciente, List<EnderecoEntity> endereços) {
        PacienteEntity existente = pacienteRepository.buscarPorCpf(paciente.getCpf());
        if (existente != null) {
            System.out.println("CPF já cadastrado! Paciente existente: " + existente.getNomeCompleto());
            return existente;
        }

        for (EnderecoEntity x : endereços) {
            x.setPaciente(paciente);
            paciente.getEnderecos().add(x);
        }
        pacienteRepository.salvar(paciente);
        var lista = pacienteRepository.buscarPorNomeInicial(paciente.getNomeCompleto());
        for (PacienteEntity x : lista) {
            if (x.getCpf().equalsIgnoreCase(paciente.getCpf())) {
                return x;
            }
        }
        return new PacienteEntity();
    }

    public List<PacienteEntity> buscarTodos() {
        return pacienteRepository.buscarTodos();
    }

    public PacienteEntity buscarPorCpf(String cpf) {
        return pacienteRepository.buscarPorCpf(cpf);
    }

    public void atualizarPacienteCPF(String cpf, String novoNome) {
        PacienteEntity paciente = pacienteRepository.buscarPorCpf(cpf);
        if (paciente != null) {
            paciente.setNomeCompleto(novoNome);
            pacienteRepository.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso!");
        } else {
            System.out.println("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    public void removerPacienteCPF(String cpf) {
        PacienteEntity paciente = pacienteRepository.buscarPorCpf(cpf);
        if (paciente != null) {
            pacienteRepository.remover(paciente);
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<PacienteEntity> listarPacientes() {
        return pacienteRepository.buscarTodos();
    }

    public void printMenu(Scanner sc, PacienteService pacienteService) {
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
                    cadastrarPaciente(sc, pacienteService);
                    break;
                case "2":
                    var lista = pacienteService.listarPacientes();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum paciente encontrado.");
                    } else {
                        for (PacienteEntity p : lista) {
                            System.out.println("ID: " + p.getId() + ", Nome: " + p.getNomeCompleto());
                        }
                    }
                    break;
                case "3":
                    System.out.print("Digite o CPF do paciente para atualizar: ");
                    String cpfAtualizar = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    pacienteService.atualizarPacienteCPF(cpfAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o CPF do paciente para remover: ");
                    String cpfRemover = sc.nextLine();
                    pacienteService.removerPacienteCPF(cpfRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public List<PacienteEntity> buscarPorNomeInicial(String prefixo){
        return em.createQuery("SELECT p FROM paciente p WHERE p.nomeCompleto LIKE :prefixo", PacienteEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    private void cadastrarPaciente(Scanner sc, PacienteService service) {
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
}
