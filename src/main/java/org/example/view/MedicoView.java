package org.example.view;

import org.example.model.entity.Medico;
import org.example.controller.MedicoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class MedicoView {
    MedicoController medicoController = new MedicoController();

    public void exibirMenu() {
        JFrame frame = new JFrame("Menu Médico");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400); // Tamanho fixo ideal
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setLayout(new GridBagLayout()); // Centraliza conteúdo vertical e horizontal

        // Painel com os botões e título
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painelConteudo.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("MENU MÉDICO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelConteudo.add(titulo);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botões
        String[] nomes = {"Cadastrar Médico", "Listar Médicos", "Atualizar Médico", "Remover Médico", "Sair"};
        JButton[] botoes = new JButton[nomes.length];

        for (int i = 0; i < nomes.length; i++) {
            botoes[i] = new JButton(nomes[i]);
            botoes[i].setMaximumSize(new Dimension(200, 30));
            botoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            botoes[i].setFocusPainted(false);
            painelConteudo.add(botoes[i]);
            painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Ações
        botoes[0].addActionListener(e -> cadastrarMedico());
        botoes[1].addActionListener(e -> listarMedicos());
        botoes[2].addActionListener(e -> atualizarMedico());
        botoes[3].addActionListener(e -> removerMedico());
        botoes[4].addActionListener(e -> frame.dispose());

        // Centraliza o painel no frame
        frame.add(painelConteudo, new GridBagConstraints());

        frame.setVisible(true);
    }

    private void cadastrarMedico() {
        JTextField nomeField = new JTextField();
        JTextField crmField = new JTextField();
        JTextField espField = new JTextField();
        JTextField telField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome completo:"));
        panel.add(nomeField);
        panel.add(new JLabel("CRM:"));
        panel.add(crmField);
        panel.add(new JLabel("Especialidade:"));
        panel.add(espField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Médico", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Medico medico = new Medico(
                        null,
                        nomeField.getText(),
                        crmField.getText(),
                        espField.getText(),
                        telField.getText()
                );
                medicoController.salvarMedico(medico);
                JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar médico: " + ex.getMessage());
            }
        }
    }

    private void listarMedicos() {
        List<Medico> medicos = medicoController.listarMedicos();

        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum médico cadastrado.");
            return;
        }

        String[] colunas = {"CRM", "Nome", "Especialidade", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Medico m : medicos) {
            model.addRow(new Object[]{m.getCrm(), m.getNomeCompleto(), m.getEspecialidade(), m.getTelefone()});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Médicos", JOptionPane.PLAIN_MESSAGE);
    }

    private void atualizarMedico() {
        List<Medico> medicos = medicoController.listarMedicos();
        if (medicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum médico cadastrado.");
            return;
        }

        Medico selecionado = selecionarMedico(medicos);
        if (selecionado == null) return;

        String novoNome = JOptionPane.showInputDialog("Digite o novo nome para o médico:", selecionado.getNomeCompleto());
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            medicoController.atualizarMedicoCRM(selecionado.getCrm(), novoNome);
            JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");
        }
    }

    private void removerMedico() {
        String crm = JOptionPane.showInputDialog("Digite o CRM do médico para remover:");
        if (crm != null && !crm.trim().isEmpty()) {
            boolean removido = medicoController.removerMedicoCRM(crm);
            if (removido) {
                JOptionPane.showMessageDialog(null, "Médico removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "CRM não encontrado. Nenhum médico foi removido.");
            }
        }
    }

    public Medico selecionarMedico(List<Medico> medicos) {
        String[] opcoes = new String[medicos.size()];
        for (int i = 0; i < medicos.size(); i++) {
            Medico m = medicos.get(i);
            opcoes[i] = m.getNomeCompleto() + " (CRM: " + m.getCrm() + ")";
        }

        String escolha = (String) JOptionPane.showInputDialog(null,
                "Selecione um médico:", "Selecionar Médico",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha != null) {
            int index = java.util.Arrays.asList(opcoes).indexOf(escolha);
            return medicos.get(index);
        }

        return null;
    }



//    MedicoController medicoController = new MedicoController();
//    Scanner sc = new Scanner(System.in);
//
//    public void printMenu() {
//        var medicos = medicoController.listarMedicos();
//        while (true) {
//            System.out.println("\n=== MENU MÉDICO ===");
//            System.out.println("1 - Cadastrar médico");
//            System.out.println("2 - Listar médicos");
//            System.out.println("3 - Atualizar médico");
//            System.out.println("4 - Remover médico");
//            System.out.println("0 - Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            String opcao = sc.nextLine();
//
//            switch (opcao) {
//                case "1":
//                    System.out.print("Nome completo: ");
//                    String nome = sc.nextLine();
//                    System.out.print("CRM: ");
//                    String crm = sc.nextLine();
//                    System.out.print("Especialidade: ");
//                    String esp = sc.nextLine();
//                    System.out.print("Telefone: ");
//                    String tel = sc.nextLine();
//
//                    Medico medico = new Medico(null, nome, crm, esp, tel);
//                    medicoController.salvarMedico(medico);
//                    break;
//                case "2":
//                    var lista = medicoController.listarMedicos();
//                    if (lista.isEmpty()) {
//                        System.out.println("Nenhum médico cadastrado.");
//                    } else {
//                        for (Medico m : lista) {
//                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
//                        }
//                    }
//                    break;
//                case "3":
//                    if (medicos.isEmpty()) {
//                        System.out.println("Nenhum médico cadastrado.");
//                    } else {
//                        for (Medico m : medicos) {
//                            System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNomeCompleto());
//                        }
//                    }
//                    System.out.print("Digite o CRM do médico para atualizar: ");
//                    String crmAtualizar = sc.nextLine();
//                    System.out.print("Novo nome: ");
//                    String novoNome = sc.nextLine();
//                    medicoController.atualizarMedicoCRM(crmAtualizar, novoNome);
//                    break;
//                case "4":
//                    System.out.print("Digite o CRM do médico para remover: ");
//                    String crmRemover = sc.nextLine();
//                    medicoController.removerMedicoCRM(crmRemover);
//                    break;
//                case "0":
//                    return;
//                default:
//                    System.out.println("Opção inválida!");
//            }
//        }
//    }
//
//    public Medico selecionarMedico() {
//        List<Medico> medicos = medicoController.listarMedicos();
//        if (medicos.isEmpty()) {
//            System.out.println("Nenhum médico cadastrado para seleção.");
//            return null;
//        }
//        System.out.println("\n=== SELECIONAR MÉDICO ===");
//        for (int i = 0; i < medicos.size(); i++) {
//            Medico m = medicos.get(i);
//            System.out.println((i + 1) + " - " + m.getNomeCompleto() + " (CRM: " + m.getCrm() + ")");
//        }
//        int escolha = -1;
//        while (true) {
//            System.out.print("Digite o número do médico: ");
//            if (sc.hasNextInt()) {
//                escolha = sc.nextInt();
//                sc.nextLine();
//                if (escolha >= 1 && escolha <= medicos.size()) {
//                    return medicos.get(escolha - 1);
//                } else {
//                    System.out.println("Número inválido. Tente novamente.");
//                }
//            } else {
//                System.out.println("Entrada inválida. Digite um número!");
//                sc.next();
//            }
//        }
//    }
}