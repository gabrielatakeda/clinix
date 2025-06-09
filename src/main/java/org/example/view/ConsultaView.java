package org.example.view;

import org.example.controller.ConsultaController;
import org.example.controller.MedicoController;
import org.example.controller.PacienteController;
import org.example.model.entity.Consulta;
import org.example.model.entity.Exame;
import org.example.model.entity.Paciente;
import org.example.model.enums.StatusConsulta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaView {

    ConsultaController consultaController = new ConsultaController();

    public void exibirMenu() {
        JFrame frame = new JFrame("Menu de Consultas");
        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblTitulo = new JLabel("MENU DE CONSULTAS");
        lblTitulo.setBounds(140, 20, 200, 30);
        frame.add(lblTitulo);

        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.setBounds(100, 60, 200, 30);
        btnAgendar.addActionListener(e -> agendarConsulta());
        frame.add(btnAgendar);

        JButton btnRemover = new JButton("Remover Consulta");
        btnRemover.setBounds(100, 100, 200, 30);
        btnRemover.addActionListener(e -> cancelarConsulta());
        frame.add(btnRemover);

        JButton btnEditar = new JButton("Editar Consulta");
        btnEditar.setBounds(100, 140, 200, 30);
        btnEditar.addActionListener(e -> editarConsulta());
        frame.add(btnEditar);

        JButton btnListar = new JButton("Listar Consultas");
        btnListar.setBounds(100, 180, 200, 30);
        btnListar.addActionListener(e -> listarConsulta());
        frame.add(btnListar);

        frame.setVisible(true);
    }

    public void agendarConsulta() {
        JFrame frame = new JFrame("Agendar Consulta");
        frame.setSize(420, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("AGENDAMENTO DE CONSULTA");
        lblTitulo.setBounds(110, 10, 250, 20);
        frame.add(lblTitulo);

        JLabel lblCpf = new JLabel("CPF do Paciente:");
        lblCpf.setBounds(10, 40, 150, 20);
        frame.add(lblCpf);

        JTextField txtCpf = new JTextField();
        txtCpf.setBounds(170, 40, 220, 20);
        frame.add(txtCpf);

        JLabel lblCrm = new JLabel("CRM do Médico:");
        lblCrm.setBounds(10, 70, 150, 20);
        frame.add(lblCrm);

        JTextField txtCrm = new JTextField();
        txtCrm.setBounds(170, 70, 220, 20);
        frame.add(txtCrm);

        JLabel lblData = new JLabel("Data da Consulta (dd/mm/aaaa):");
        lblData.setBounds(10, 100, 200, 20);
        frame.add(lblData);

        JTextField txtData = new JTextField();
        txtData.setBounds(220, 100, 170, 20);
        frame.add(txtData);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 150, 90, 25);
        btnVoltar.addActionListener((ActionEvent e) -> frame.dispose());
        frame.add(btnVoltar);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(300, 150, 90, 25);
        // btnConfirmar.addActionListener(e -> ...); // chamada futura
        frame.add(btnConfirmar);

        frame.setVisible(true);
    }

    public void cancelarConsulta() {
        JFrame frame = new JFrame("Cancelar Consulta");
        frame.setSize(360, 200);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("CANCELAMENTO DE CONSULTA");
        lblTitulo.setBounds(70, 10, 250, 20);
        frame.add(lblTitulo);

        JLabel lblData = new JLabel("Data da consulta (dd/mm/aaaa):");
        lblData.setBounds(20, 50, 250, 20);
        frame.add(lblData);

        JTextField txtData = new JTextField("(dd/mm/aaaa)");
        txtData.setBounds(20, 75, 300, 20);
        frame.add(txtData);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 110, 90, 25);
        btnVoltar.addActionListener((ActionEvent e) -> frame.dispose());
        frame.add(btnVoltar);

        JButton btnConcluir = new JButton("Concluir");
        btnConcluir.setBounds(230, 110, 90, 25);
        // btnConcluir.addActionListener(e -> ...);
        frame.add(btnConcluir);

        frame.setVisible(true);
    }

    public void editarConsulta() {
        JFrame frame = new JFrame("Editar Consulta");
        frame.setSize(400, 220);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("EDITAR CONSULTA");
        lblTitulo.setBounds(130, 10, 200, 20);
        frame.add(lblTitulo);

        JLabel lblId = new JLabel("ID da Consulta:");
        lblId.setBounds(20, 50, 120, 20);
        frame.add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(160, 50, 200, 20);
        frame.add(txtId);

        JLabel lblNovaData = new JLabel("Nova Data (dd/mm/aaaa):");
        lblNovaData.setBounds(20, 85, 160, 20);
        frame.add(lblNovaData);

        JTextField txtNovaData = new JTextField();
        txtNovaData.setBounds(190, 85, 170, 20);
        frame.add(txtNovaData);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(270, 130, 90, 25);
        // btnConfirmar.addActionListener(e -> ...);
        frame.add(btnConfirmar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 130, 90, 25);
        btnVoltar.addActionListener((ActionEvent e) -> frame.dispose());
        frame.add(btnVoltar);

        frame.setVisible(true);
    }

    public void listarConsulta() {
        JFrame frame = new JFrame("Lista de Consultas");
        frame.setSize(450, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitulo = new JLabel("LISTAGEM DE CONSULTAS");
        lblTitulo.setBounds(130, 10, 200, 20);
        frame.add(lblTitulo);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 40, 390, 220);
        textArea.setEditable(false);
        textArea.setText("Consulta 1...\nConsulta 2...\nConsulta 3..."); // Simulação
        frame.add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 40, 390, 220);
        frame.add(scrollPane);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(170, 275, 100, 25);
        btnFechar.addActionListener(e -> frame.dispose());
        frame.add(btnFechar);

        frame.setVisible(true);
    }



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
}
