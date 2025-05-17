package org.example.service;

import org.example.Entity.MedicoEntity;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.MedicoRepository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class MedicoService {

    Scanner sc = new Scanner(System.in);
    private final EntityManager em = CustomizerFactory.getEntityManager();
    private final MedicoRepository medicoRepository = new MedicoRepository(em);

    public MedicoEntity salvarMedico(MedicoEntity medico) {
        //Verifica se já existe um médico com o mesmo CRM
        List<MedicoEntity> lista = buscarTodos();
        for (MedicoEntity m : lista) {
            if (m.getCrm().equalsIgnoreCase(medico.getCrm())) {
                System.out.println("CRM já cadastrado! Médico existente: " + m.getNomeCompleto());
                return m;
            }
        }

        medicoRepository.salvar(medico);

        //Verifica novamente após salvar
        lista = buscarTodos();
        for (MedicoEntity m : lista) {
            if (m.getCrm().equalsIgnoreCase(medico.getCrm())) {
                return m;
            }
        }

        return new MedicoEntity(); // fallback
    }

    public List<MedicoEntity> buscarTodos() {
        return medicoRepository.buscarTodos();
    }

//    public MedicoEntity buscarPorId(Long id) {
//        return medicoRepository.buscarPorId(id);
//    }

//    public MedicoEntity buscarPorCrm(String crm){
//        return medicoRepository.buscarPorCrm(crm);
//    }

    // public void removerMedico(Long id) {
    //     MedicoEntity medico = medicoRepository.buscarPorId(id);
    //     if (medico != null) {
    //         medicoRepository.remover(medico);
    //         System.out.println("Médico removido com sucesso!");
    //     } else {
    //         System.out.println("Médico com ID " + id + " não encontrado.");
    //     }
    // }

    public MedicoEntity selecionarMedico() {
        List<MedicoEntity> medicos = listarMedicos();

        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado para seleção.");
            return null;
        }

        System.out.println("\n=== SELECIONAR MÉDICO ===");
        for (int i = 0; i < medicos.size(); i++) {
            MedicoEntity m = medicos.get(i);
            System.out.println((i + 1) + " - " + m.getNomeCompleto() + " (CRM: " + m.getCrm() + ")");
        }

        int escolha = -1;
        while (true) {
            System.out.print("Digite o número do médico: ");
            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                sc.nextLine();
                if (escolha >= 1 && escolha <= medicos.size()) {
                    return medicos.get(escolha - 1);
                } else {
                    System.out.println("Número inválido. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número!");
                sc.next();
            }
        }
    }

    public void removerMedicoCRM(String crm) {
        MedicoEntity medico = medicoRepository.buscarPorCrm(crm);
        if (medico != null) {
            medicoRepository.remover(medico);
            System.out.println("Médico removido com sucesso!");
        } else {
            System.out.println("Médico com CRM " + crm + " não encontrado.");
        }
    }

    // public void atualizarMedico(Long id, String novoNome) {
    //     MedicoEntity medico = medicoRepository.buscarPorId(id);
    //     if (medico != null) {
    //         medico.setNomeCompleto(novoNome);
    //         medicoRepository.atualizar(medico);
    //         System.out.println("Médico atualizado com sucesso!");
    //     } else {
    //         System.out.println("Médico com ID " + id + " não encontrado.");
    //     }
    // }

    public void atualizarMedicoCRM(String crm, String novoNome) {
        MedicoEntity medico = medicoRepository.buscarPorCrm(crm);
        if (medico != null) {
            medico.setNomeCompleto(novoNome);
            medicoRepository.atualizar(medico);
            System.out.println("Médico atualizado com sucesso!");
        } else {
            System.out.println("Médico com CRM " + crm + " não encontrado.");
        }
    }

    public List<MedicoEntity> listarMedicos() {
        return medicoRepository.buscarTodos();
    }

    public void printMenu(Scanner sc, MedicoService service) {
        var medicos = service.listarMedicos();
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

                    MedicoEntity medico = new MedicoEntity(null, nome, crm, esp, tel);
                    service.salvarMedico(medico);
                    break;
                case "2":
                    if (medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado.");
                    } else {
                        for (MedicoEntity m : medicos) {
                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
                        }
                    }
                    break;
                case "3":
                    if (medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado.");
                    } else {
                        for (MedicoEntity m : medicos) {
                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
                        }
                    }
                    System.out.print("Digite o CRM do médico para atualizar: ");
                    String crmAtualizar = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    service.atualizarMedicoCRM(crmAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o CRM do médico para remover: ");
                    String crmRemover = sc.nextLine();
                    service.removerMedicoCRM(crmRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}