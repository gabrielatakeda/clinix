package org.example.view;

import org.example.DAO.AmostrasLabRepository;
import org.example.model.entity.Consulta;
import org.example.model.entity.Exame;
import org.example.model.enums.StatusAmostraLab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExameView {

   // ExameController exameController = new ExameController();
 //   Scanner sc = new Scanner(System.in);

//    public void printMenu() {
//        int opcao = -1;
//        StatusAmostraLab status = null;
//
//        while (opcao != 0) {
//            System.out.println("\n==== MENU AMOSTRAS ====");
//            System.out.println("1. Listar amostras por status");
//            System.out.println("2. Atualizar status da amostra");
//            System.out.println("3. Deletar amostra");
//            System.out.println("0. Voltar");
//            System.out.print("Escolha uma opção: ");
//            opcao = sc.nextInt();
//            sc.nextLine(); // limpar buffer
//
//            switch (opcao) {
//                case 1:
//                    printMenuListagem();
//                    break;
//                case 2:
//                    status = StatusAmostraLab.PENDENTE;
//                    exameController.atualizarStatus(status,sc);
//                    break;
//                case 3:
//                    exameController.deletarAmostra(sc);
//                    break;
//                case 0:
//                    System.out.println("Voltando ao menu anterior...");
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//                    break;
//            }
//        }
//    }
//
//    public void printMenuListagem() {
//        int opcao;
//        System.out.println("\n==== MENU LISTAGEM AMOSTRAS ====");
//        System.out.println("--Lista de exames por status--");
//        System.out.println("1. Concluídos");
//        System.out.println("2. Pendentes");
//        System.out.println("3. Inconclusivo");
//        System.out.println("0. Voltar");
//        System.out.print("Escolha uma opção: ");
//        opcao = sc.nextInt();
//        sc.nextLine();
//
//        switch (opcao) {
//            case 1:
//                exameController.listarPorStatus(StatusAmostraLab.CONCLUIDO);
//                break;
//            case 2:
//                exameController.listarPorStatus(StatusAmostraLab.PENDENTE);
//                break;
//            case 3:
//                exameController.listarPorStatus(StatusAmostraLab.INCONCLUSIVO);
//                break;
//            case 0:
//                System.out.println("Saindo...");
//                break;
//            default:
//                System.out.println("Opção inválida.");
//        }
//    }

    public  void printMenu(){
        JFrame frame = new JFrame("MENU AMOSTRAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 300); // Tamanho fixo ideal
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setLayout(new BorderLayout()); // Centraliza conteúdo vertical e horizontal

        // Painel com os botões e título
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("MENU AMOSTRAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));


        String[] nomes = {"Listar amostras por status", "Atualizar status da amostra", "Deletar amostra", "Voltar"};
        JButton[] botoes = new JButton[nomes.length];

        for (int i = 0; i < nomes.length; i++) {
            botoes[i] = new JButton(nomes[i]);
            botoes[i].setMaximumSize(new Dimension(200, 30));
            botoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            botoes[i].setFocusPainted(false);
            painel.add(botoes[i]);
            painel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Ações
        botoes[0].addActionListener(e -> { frame.setVisible(false); printMenuListagem();});
        botoes[1].addActionListener(e -> { frame.setVisible(false); atualizarExame();});
        botoes[2].addActionListener(e -> { frame.setVisible(false); deletarExame();});
        botoes[3].addActionListener(e -> frame.dispose());

        frame.add(painel);

        frame.setVisible(true);
    }

    private void cadastroExame(){

        AmostrasLabRepository amostrasLabRepository = new AmostrasLabRepository();
        JFrame frame = new JFrame();
        frame.setSize(285, 154);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        JFrame frameExibeCadastro = new JFrame("SUCESSO DO CADASTRO");
        frameExibeCadastro.setSize(285, 154);
        frameExibeCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha só essa janela
        frameExibeCadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printMenu();
            }
        });
        frameExibeCadastro.getContentPane().setLayout(null);
        frameExibeCadastro.setLocationRelativeTo(null); // Centraliza na tela
        frameExibeCadastro.setVisible(false);

        JLabel atualizarLabel = new JLabel("CADASTRAR EXAME");
        atualizarLabel.setBounds(87, 11, 124, 14);
        frame.getContentPane().add(atualizarLabel);

        JLabel tipoLabel = new JLabel("Tipo do exame: ");
        tipoLabel.setBounds(22, 36, 124, 14);
        frame.getContentPane().add(tipoLabel);

        JTextField tipoField = new JTextField();
        tipoField.setBounds(112, 36, 137, 20);
        frame.getContentPane().add(tipoField);
        tipoField.setColumns(10);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBounds(10, 10, 250, 100);
        frameExibeCadastro.getContentPane().add(infoArea);

        JLabel cadastroLabel = new JLabel("Exame cadastrado! Status: aguardando resultado.");
        cadastroLabel.setBounds(87, 11, 124, 14);
        frameExibeCadastro.getContentPane().add(cadastroLabel);

        JButton voltarMenuBtn = new JButton("Voltar ao menu");
        voltarMenuBtn.setBounds(75, 110, 130, 23);
        voltarMenuBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameExibeCadastro.setVisible(false);
                printMenu();
            }
        });
        frameExibeCadastro.getContentPane().add(voltarMenuBtn);



        JButton confimarButton = new JButton("Confirmar");
        confimarButton.setBounds(147, 67, 102, 23);
        frame.getContentPane().add(confimarButton);
        confimarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tipoField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, informe o tipo do exame.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Exame exameExistente = amostrasLabRepository.buscarPorTipo(tipoField.getText().trim());

                if (exameExistente != null) {
                    JOptionPane.showMessageDialog(frame, "Esse exame já foi cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                    Exame novaAmostra = new Exame();
                    novaAmostra.setTipoExame(tipoField.getText().trim());
                    novaAmostra.setStatus(StatusAmostraLab.PENDENTE);
                // Salva a nova amostra no banco
                amostrasLabRepository.salvar(novaAmostra);
                    Consulta consulta = new Consulta();


                    infoArea.setText(
                            "Exame cadastrado com sucesso!\n\n" +
                                    "Exame solicitado: " + tipoField.getText().trim() + "\n" +
                                    "Status: " + StatusAmostraLab.PENDENTE + "\n" +
                                    "Resultado: Aguardando resultado do exame."
                    );


                frame.setVisible(false);
                frameExibeCadastro.setVisible(true);
                }

        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 67, 89, 23);
        frame.getContentPane().add(voltarButton);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                printMenu();
            }
        });

        frame.setVisible(true);

    }

    private void printMenuListagem() {
        JFrame frame = new JFrame("MENU LISTAGEM AMOSTRAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300); // Tamanho fixo ideal
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setLayout(new BorderLayout()); // Centraliza conteúdo vertical e horizontal

        // Painel com os botões e título
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("LISTA AMOSTRAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblNewLabel = new JLabel("Filtro status: ");
        lblNewLabel.setBounds(21, 11, 70, 14);
        frame.getContentPane().add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Selecione uma opção", "CONCLUÍDO", "PENDENTE ", "INCONCLUSIVO"}));
        comboBox.setBounds(107, 7, 149, 22);
        frame.getContentPane().add(comboBox);


        JTable table = new JTable();
        table.setBounds(21, 59, 235, 0);
        frame.getContentPane().add(table);
        frame.setVisible(true);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 80, 89, 23);
        frame.getContentPane().add(voltarButton);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                printMenu();
            }
        });

        frame.add(painel);

        frame.setVisible(true);
    }

    private void atualizarExame(){
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        JLabel atualizarLabel = new JLabel("ATUALIZAR EXAME");
        atualizarLabel.setBounds(82, 11, 124, 14);
        frame.getContentPane().add(atualizarLabel);

        JLabel idLabel = new JLabel("ID do exame: ");
        idLabel.setBounds(22, 36, 150, 14);
        frame.getContentPane().add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(112, 36, 150, 20);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        JLabel nvStatusLabel = new JLabel("Novo status:");
        nvStatusLabel.setBounds(22, 72, 102, 14);
        frame.getContentPane().add(nvStatusLabel);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione uma opção", "CONCLUÍDO", "INCONCLUSIVO"}));
        comboBox.setBounds(112, 68, 150, 22);
        frame.getContentPane().add(comboBox);


        JButton confimarButton = new JButton("Confirmar");
        confimarButton.setBounds(160, 114, 102, 23);
        frame.getContentPane().add(confimarButton);

        confimarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if  (idField.getText().trim().isEmpty() || comboBox.getSelectedItem().equals("Selecione uma opção")) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione e preencha todos os campos!", "Campo obrigatório", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Se o campo estiver selecionado:
                JOptionPane.showMessageDialog(null, "Exame atualizado com sucesso!");
                frame.setVisible(false);
                printMenu();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(22, 114, 89, 23);
        frame.getContentPane().add(voltarButton);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                printMenu();
            }
        });
        frame.setVisible(true);

    }

    private void deletarExame(){
        JFrame frame = new JFrame();
        frame.setSize(285, 154);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null); // Centraliza na tela

        JLabel atualizarLabel = new JLabel("DELETAR EXAME");
        atualizarLabel.setBounds(87, 11, 124, 14);
        frame.getContentPane().add(atualizarLabel);

        JLabel idLabel = new JLabel("ID do exame: ");
        idLabel.setBounds(22, 36, 124, 14);
        frame.getContentPane().add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(112, 36, 137, 20);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        JButton confimarButton = new JButton("Confirmar");
        confimarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Campos obrigatórios", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Se os campos estiverem preenchidos:
                JOptionPane.showMessageDialog(null, "Exame deletado com sucesso!");
                frame.setVisible(false);
                printMenu();
            }
        });
        confimarButton.setBounds(147, 67, 102, 23);
        frame.getContentPane().add(confimarButton);



        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBounds(10, 67, 89, 23);
        frame.getContentPane().add(voltarButton);
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                printMenu();
            }
        });


        frame.setVisible(true);
    }
}
