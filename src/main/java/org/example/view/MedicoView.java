package org.example.view;

import org.example.model.entity.Medico;
import org.example.controller.MedicoController;

import java.util.List;
import java.util.Scanner;

public class MedicoView {

    MedicoController medicoController = new MedicoController();
    Scanner sc = new Scanner(System.in);

    public void printMenu() {
        var medicos = medicoController.listarMedicos();
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

                    Medico medico = new Medico(null, nome, crm, esp, tel);
                    medicoController.salvarMedico(medico);
                    break;
                case "2":
                    var lista = medicoController.listarMedicos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado.");
                    } else {
                        for (Medico m : lista) {
                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
                        }
                    }
                    break;
                case "3":
                    if (medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado.");
                    } else {
                        for (Medico m : medicos) {
                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
                        }
                    }
                    System.out.print("Digite o CRM do médico para atualizar: ");
                    String crmAtualizar = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    medicoController.atualizarMedicoCRM(crmAtualizar, novoNome);
                    break;
                case "4":
                    System.out.print("Digite o CRM do médico para remover: ");
                    String crmRemover = sc.nextLine();
                    medicoController.removerMedicoCRM(crmRemover);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public Medico selecionarMedico() {
        List<Medico> medicos = medicoController.listarMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado para seleção.");
            return null;
        }
        System.out.println("\n=== SELECIONAR MÉDICO ===");
        for (int i = 0; i < medicos.size(); i++) {
            Medico m = medicos.get(i);
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

}
