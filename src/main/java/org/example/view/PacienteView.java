package org.example.view;

import org.example.model.entity.Endereco;
import org.example.model.entity.Paciente;
import org.example.controller.PacienteController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PacienteView {

    private final PacienteController pacienteController = new PacienteController();

    public void exibirMenu() {
        JFrame frame = new JFrame("Menu Paciente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        // Painel de fundo (cor diferente para destacar o "quadrado branco")
        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(new Color(240, 240, 240)); // cinza claro

        // Painel branco centralizado com conteúdo
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBackground(Color.WHITE);
        painelConteudo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1), // borda fina cinza
                BorderFactory.createEmptyBorder(20, 50, 20, 50) // margem interna
        ));

        JLabel titulo = new JLabel("MENU PACIENTE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelConteudo.add(titulo);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] nomes = {"Cadastrar Paciente", "Listar Pacientes", "Atualizar Paciente", "Remover Paciente", "Sair"};
        JButton[] botoes = new JButton[nomes.length];

        for (int i = 0; i < nomes.length; i++) {
            botoes[i] = new JButton(nomes[i]);
            botoes[i].setMaximumSize(new Dimension(200, 30));
            botoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            botoes[i].setFocusPainted(false);
            painelConteudo.add(botoes[i]);
            painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Ações dos botões
        botoes[0].addActionListener(e -> cadastrarPaciente());
        botoes[1].addActionListener(e -> listarPacientes());
        botoes[2].addActionListener(e -> atualizarPaciente());
        botoes[3].addActionListener(e -> removerPaciente());
        botoes[4].addActionListener(e -> frame.dispose());

        // Adiciona o painel branco ao painel de fundo
        painelFundo.add(painelConteudo, new GridBagConstraints());

        // Define o painel de fundo como conteúdo principal da janela
        frame.setContentPane(painelFundo);
        frame.setVisible(true);
    }

    private void cadastrarPaciente() {
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataNascField = new JTextField();
        JTextField telefoneField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome completo:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Data de nascimento (dd/MM/yyyy):"));
        panel.add(dataNascField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastrar Paciente", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                LocalDate dataNasc = LocalDate.parse(dataNascField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String telefone = telefoneField.getText();

                Paciente paciente = new Paciente(null, nome, cpf, dataNasc, telefone, new ArrayList<>());

                List<Endereco> enderecos = new ArrayList<>();
                boolean adicionarMais = true;

                while (adicionarMais) {
                    JTextField logradouro = new JTextField();
                    JTextField cidade = new JTextField();
                    JTextField numero = new JTextField();
                    JCheckBox principal = new JCheckBox("É o endereço principal?");

                    String[] estados = {
                            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                            "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
                    };
                    JComboBox<String> estadoCombo = new JComboBox<>(estados);

                    JPanel enderecoPanel = new JPanel(new GridLayout(0, 1));
                    enderecoPanel.add(new JLabel("Logradouro:"));
                    enderecoPanel.add(logradouro);
                    enderecoPanel.add(new JLabel("Cidade:"));
                    enderecoPanel.add(cidade);
                    enderecoPanel.add(new JLabel("Estado:"));
                    enderecoPanel.add(estadoCombo);
                    enderecoPanel.add(new JLabel("Número:"));
                    enderecoPanel.add(numero);
                    enderecoPanel.add(principal);

                    int enderecoResult = JOptionPane.showConfirmDialog(null, enderecoPanel, "Endereço", JOptionPane.OK_CANCEL_OPTION);
                    if (enderecoResult == JOptionPane.OK_OPTION) {
                        enderecos.add(new Endereco(
                                null,
                                logradouro.getText(),
                                cidade.getText(),
                                estadoCombo.getSelectedItem().toString(),
                                Integer.parseInt(numero.getText()),
                                principal.isSelected(),
                                paciente
                        ));
                        int addMais = JOptionPane.showConfirmDialog(null, "Deseja adicionar outro endereço?", "Endereço", JOptionPane.YES_NO_OPTION);
                        adicionarMais = (addMais == JOptionPane.YES_OPTION);
                    } else {
                        break;
                    }
                }

                Paciente salvo = pacienteController.salvarPaciente(paciente, enderecos);
                JOptionPane.showMessageDialog(null, salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro nos dados informados: " + ex.getMessage());
            }
        }
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteController.listarPacientes();

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum paciente encontrado.");
            return;
        }

        String[] colunas = {"CPF", "Nome"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        for (Paciente p : pacientes) {
            tableModel.addRow(new Object[]{p.getCpf(), p.getNome()});
        }

        JTable tabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Pacientes", JOptionPane.PLAIN_MESSAGE);
    }

    private void atualizarPaciente() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do paciente:");
        String novoNome = JOptionPane.showInputDialog("Digite o novo nome:");

        if (cpf != null && novoNome != null) {
            pacienteController.atualizarPacienteCPF(cpf, novoNome);
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
        }
    }

    private void removerPaciente() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do paciente para remover:");
        if (cpf != null) {
            boolean removido = pacienteController.removerPacienteCPF(cpf);
            if (removido) {
                JOptionPane.showMessageDialog(null, "Paciente removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Paciente não encontrado com esse CPF.");
            }
    }
}

}

//    PacienteController pacienteController = new PacienteController();
//    Scanner sc = new Scanner(System.in);
//
//    public void printMenu() {
//        while (true) {
//            System.out.println("\n=== MENU PACIENTE ===");
//            System.out.println("1 - Cadastrar paciente");
//            System.out.println("2 - Listar pacientes");
//            System.out.println("3 - Atualizar paciente");
//            System.out.println("4 - Remover paciente");
//            System.out.println("0 - Voltar ao menu principal");
//            System.out.print("Escolha uma opção: ");
//            String opcao = sc.nextLine();
//
//            switch (opcao) {
//                case "1":
//                    cadastrarPaciente();
//                    break;
//                case "2":
//                    var lista = pacienteController.listarPacientes();
//                    if (lista.isEmpty()) {
//                        System.out.println("Nenhum paciente encontrado.");
//                    } else {
//                        for (Paciente p : lista) {
//                            System.out.println("CPF: " + p.getCpf() + ", Nome: " + p.getNome());
//                        }
//                    }
//                    break;
//                case "3":
//                    System.out.print("Digite o CPF do paciente para atualizar: ");
//                    String cpfAtualizar = sc.nextLine();
//                    System.out.print("Novo nome: ");
//                    String novoNome = sc.nextLine();
//                    pacienteController.atualizarPacienteCPF(cpfAtualizar, novoNome);
//                    break;
//                case "4":
//                    System.out.print("Digite o CPF do paciente para remover: ");
//                    String cpfRemover = sc.nextLine();
//                    pacienteController.removerPacienteCPF(cpfRemover);
//                    break;
//                case "0":
//                    return;
//                default:
//                    System.out.println("Opção inválida!");
//            }
//        }
//    }
//
//    private void cadastrarPaciente() {
//        System.out.print("Nome completo: ");
//        String nome = sc.nextLine();
//        System.out.print("CPF: ");
//        String cpf = sc.nextLine();
//        System.out.print("Data de nascimento (dd/MM/yyyy): ");
//        LocalDate dataNasc = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        System.out.print("Telefone: ");
//        String telefone = sc.nextLine();
//
//        Paciente paciente = new Paciente(null, nome, cpf, dataNasc, telefone, new ArrayList<>());
//
//        List<Endereco> enderecos = new ArrayList<>();
//        while (true) {
//            System.out.print("Logradouro: ");
//            String logradouro = sc.nextLine();
//            System.out.print("Cidade: ");
//            String cidade = sc.nextLine();
//            System.out.print("Estado: ");
//            String estado = sc.nextLine();
//            System.out.print("Número: ");
//            Integer numero = Integer.parseInt(sc.nextLine());
//            System.out.print("É o endereço principal? (s/n): ");
//            boolean isPrincipal = sc.nextLine().equalsIgnoreCase("s");
//
//            enderecos.add(new Endereco(null, logradouro, cidade, estado, numero, isPrincipal, paciente));
//
//            System.out.print("Deseja adicionar outro endereço? (s/n): ");
//            if (!sc.nextLine().equalsIgnoreCase("s")) break;
//        }
//
//        Paciente salvo = pacienteController.salvarPaciente(paciente, enderecos);
//        System.out.println(salvo.getId() != null ? "Paciente salvo com sucesso!" : "Erro ao salvar.");
//    }
