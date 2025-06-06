package org.example.view;

import org.example.controller.ConsultaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ConsultaView {

    Scanner sc = new Scanner(System.in);
    ConsultaController consultaController = new ConsultaController();
//
//
//    public void printMenu() {
//
//        List<Consulta> ListaConsultasEntity = new ArrayList<>();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//
//        System.out.println("\n==== MENU CONSULTAS ====");
//        System.out.println("1. Agendar uma consulta");
//        System.out.println("2. Editar consulta");
//        System.out.println("3. Listar todas as consultas");
//        System.out.println("4. Cancelar consulta");
//        System.out.println("5. Retornar ao menu anterior");
//        System.out.print("Escolha uma opção: ");
//        int opcao = sc.nextInt();
//        sc.nextLine();
//
//        switch (opcao) {
//            case 1:
//                Consulta novaConsulta = new Consulta();
//                Exame novaAmostra = new Exame();
//                Paciente pacienteSelecionado;
//
//                try {
//                    System.out.println("AGENDAMENTO DE CONSULTA");
//                    System.out.print("Insira o CPF do paciente: ");
//                    String cpf = sc.nextLine().trim();
//
//                    PacienteController pacienteController = new PacienteController();
//                    pacienteSelecionado = pacienteController.buscarPorCpf(cpf);
//
//                    if (pacienteSelecionado == null) {
//                        System.out.println("\nPaciente não encontrado! Cadastre o paciente primeiro.");
//                        return;
//                    }
//                    MedicoView medicoView = new MedicoView();
//                    var medicoSelecionado = medicoView.selecionarMedico();
//                    System.out.println("Data da consulta (dd/MM/yyyy HH:mm): ");
//                    String dataConsultaStr = sc.nextLine().trim();
//                    if (dataConsultaStr == null || dataConsultaStr.trim().isEmpty()) {
//                        System.out.println("Data inválida. Digite no formato dd/MM/yyyy.");
//                        return; // ou pedir novamente
//                    } else {
//                        LocalDateTime dataConsulta = LocalDateTime.parse(dataConsultaStr, formatter);
//
//                        novaConsulta.setMedico(medicoSelecionado);
//                        novaConsulta.setPaciente(pacienteSelecionado);
//                        novaConsulta.setLocalDateTime(dataConsulta);
//                        novaConsulta.setStatus(StatusConsulta.AGENDADA);
//
//                        Consulta salvo = consultaController.salvarConsulta(novaConsulta);
//                        System.out.println(salvo.getID_Consulta() != null ? "Consulta agendada com sucesso!" : "Erro ao salvar.");
//                    }
//                } catch (Exception e) {
//                    System.out.println("\nErro: Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
//                    return;
//                }
//                break;
//            case 2:
//                System.out.println("Editar consulta.");
//                consultaController.exibirConsultas();
//                if (ListaConsultasEntity.isEmpty()) {
//                    System.out.println("Nenhuma consulta encontrada.");
//                    return;
//                } else {
//                    System.out.print("Digite a Data da consulta: ");
//                    String dataConsulta = sc.nextLine();
//                }
//                break;
//            case 3:
//                consultaController.exibirConsultas();
//                break;
//            case 4:
//                System.out.print("\nDigite o Data da consulta que deseja deletar: ");
//                String data = sc.nextLine();
//                sc.nextLine(); // limpar buffer
//                consultaController.removerPorData(data);
//                break;
//            case 5:
//                System.out.println("Saindo...");
//                return;
//            default:
//                System.out.println("Opção inválida.");
//                break;
//        }
//    }

        public void printMenu() {
            JFrame frame = new JFrame("MENU CONSULTA");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400); // Tamanho fixo ideal
            frame.setLocationRelativeTo(null); // Centraliza na tela
            frame.setLayout(new BorderLayout()); // Centraliza conteúdo vertical e horizontal

            // Painel com os botões e título
            JPanel painel = new JPanel();
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            painel.setBackground(Color.WHITE);

            JLabel titulo = new JLabel("MENU CONSULTA", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 22));
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

            painel.add(titulo);
            painel.add(Box.createRigidArea(new Dimension(0, 20)));


            String[] nomes = {"Agendar consulta", "Editar consulta", "Listar consultas", "Cancelar consulta", "Voltar"};
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
            botoes[0].addActionListener(e -> { frame.setVisible(false); agendarConsulta();});
            botoes[1].addActionListener(e -> { frame.setVisible(false); editarConsulta();});
            botoes[2].addActionListener(e -> { frame.setVisible(false); listarConsulta();});
            botoes[3].addActionListener(e -> { frame.setVisible(false); cancelarConsulta();});
            botoes[4].addActionListener(e -> frame.dispose());

            frame.add(painel);

            frame.setVisible(true);
        }


        private void agendarConsulta(){
                JFrame frame = new JFrame();
                frame.setSize(437, 247);
                frame.getContentPane().setLayout(null);
                 frame.setLocationRelativeTo(null); // Centraliza na tela

                JLabel cpfLabel = new JLabel("Insira o CPF do paciente: ");
                cpfLabel.setBounds(10, 40, 153, 14);
                frame.getContentPane().add(cpfLabel);

                JTextField cpfField = new JTextField();
                cpfField.setBounds(193, 37, 211, 20);
                frame.getContentPane().add(cpfField);
                cpfField.setColumns(10);


                JLabel lblNewLabel_1 = new JLabel("CRM Medico:");
                lblNewLabel_1.setBounds(10, 71, 124, 14);
                 frame.getContentPane().add(lblNewLabel_1);

                JTextField crmField = new JTextField();
                crmField.setBounds(193, 68, 211, 20);
                frame.getContentPane().add(crmField);
                crmField.setColumns(10);


                JLabel dataLabel = new JLabel("Data da consulta (dd/mm/aaaa):");
                dataLabel.setBounds(10, 108, 190, 14);
                frame.getContentPane().add(dataLabel);

                JTextField dataField = new JTextField();
                dataField.setBounds(193, 105, 211, 20);
                frame.getContentPane().add(dataField);
                dataField.setColumns(10);


                JButton btnNewButton = new JButton("Voltar");
                btnNewButton.setBounds(10, 159, 99, 23);
                frame.getContentPane().add(btnNewButton);
                btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                        printMenu();
                    }
                });

                JLabel lblNewLabel_3 = new JLabel("AGENDAMENTO DE CONSULTA");
                lblNewLabel_3.setBounds(132, 11, 217, 14);
                frame.getContentPane().add(lblNewLabel_3);

                JButton btnNewButton_1 = new JButton("Confirmar");
                btnNewButton_1.setBounds(294, 159, 110, 23);
                frame.getContentPane().add(btnNewButton_1);
                btnNewButton_1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (cpfField.getText().trim().isEmpty() || crmField.getText().trim().isEmpty() || dataField.getText().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Campos obrigatórios", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Se os campos estiverem preenchidos:
                        JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
                        frame.setVisible(false);
                        printMenu();
                    }
                });


                frame.setVisible(true);

        }
    private void editarConsulta(){


    }
    private void listarConsulta(){


    }
    private void cancelarConsulta(){


    }

}
