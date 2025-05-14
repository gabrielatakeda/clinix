package org.example.service;



import org.example.entity.MedicoEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.MedicoRepository;

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

    public MedicoEntity buscarPorId(Long id) {
        return medicoRepository.buscarPorId(id);
    }

    public void removerMedico(Long id) {
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if (medico != null) {
            medicoRepository.remover(medico);
            System.out.println("Médico removido com sucesso!");
        } else {
            System.out.println("Médico com ID " + id + " não encontrado.");
        }
    }

    public void atualizarMedico(Long id, String novoNome) {
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if (medico != null) {
            medico.setNomeCompleto(novoNome);
            medicoRepository.atualizar(medico);
            System.out.println("Médico atualizado com sucesso!");
        } else {
            System.out.println("Médico com ID " + id + " não encontrado.");
        }
    }

    public List<MedicoEntity> listarMedicos() {
        return medicoRepository.buscarTodos();
    }

    public void printMenu(Scanner sc, MedicoService service) {

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