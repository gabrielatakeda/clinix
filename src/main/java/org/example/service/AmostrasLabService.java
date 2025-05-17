package org.example.service;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;
import org.example.entity.PacienteEntity;
import org.example.Repository.AmostrasLabRepository;
import org.example.Repository.ConsultaRepository;
import org.example.Repository.CustomizerFactory;
import org.example.enums.StatusAmostraLab;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class AmostrasLabService {

    EntityManager em = CustomizerFactory.getEntityManager();
    AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);

    // Adicione o formatter na parte superior da classe para usar em todo o código
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public void cadastrarExame(Scanner sc) {
        System.out.println("\n===== CADASTRAR NOVO EXAME =====");

        System.out.print("Insira o CPF do paciente: ");
        String cpf = sc.nextLine().trim();

        // VERIFICA O PACIENTE CADASTRADO POR CPF
        PacienteService pacienteService = new PacienteService();
        PacienteEntity pacienteSelecionado = pacienteService.buscarPorCpf(cpf);

        if (pacienteSelecionado == null) {
            System.out.println("\nPaciente não encontrado! Cadastre o paciente primeiro.");
            return;
        }

        // Busca a consulta mais recente associada ao paciente
        ConsultaRepository consultaRepository = new ConsultaRepository(em);
        ConsultaEntity consulta = consultaRepository.buscarConsultaPorPaciente(pacienteSelecionado);

        // Busca a consulta associada à amostra
        AmostrasLabEntity novaAmostra = new AmostrasLabEntity();

        if (consulta == null) {
            System.out.println("Consulta não encontrada ou inválida.");
            return;
        }
        novaAmostra.setConsulta(consulta);

        System.out.print("Tipo do Exame: ");
        String tipoExame = sc.nextLine();
        novaAmostra.setTipoExame(tipoExame);

//        System.out.print("Resultado do Exame: ");
//        String resultado = sc.nextLine();
//        novaAmostra.setResultado(resultado);

        // Solicita a data da coleta
        System.out.print("Data da Coleta (formato: dd/MM/yyyy HH:mm): ");
        String dataStr = sc.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataColeta = LocalDateTime.parse(dataStr, formatter);

            // Verifica se a data da consulta está disponível
            LocalDateTime dataConsulta = consulta.getData_consulta(); // Supondo que seja LocalDateTime
            if (dataColeta.isBefore(dataConsulta)) {
                System.out.println("Erro: A data da coleta não pode ser anterior à data da consulta (" +
                        dataConsulta.format(formatter) + ").");
                return;
            }

            novaAmostra.setDataColeta(dataColeta);
        } catch (DateTimeParseException e) {
            System.out.println("Data em formato inválido! Use o formato dd/MM/yyyy HH:mm.");
            return;
        }

        novaAmostra.setStatus(StatusAmostraLab.PENDENTE);

        AmostrasLabEntity salvo = salvarAmostra(novaAmostra);
        if (salvo.getId_amostralab() != null) {
            System.out.println("Amostra cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao salvar a amostra.");
        }
    }

    public AmostrasLabEntity salvarAmostra(AmostrasLabEntity amostra) {
        List<AmostrasLabEntity> todasAmostras = amostraRepository.findAll();

        for (AmostrasLabEntity c : todasAmostras) {
            if (c.getDataColeta().equals(amostra.getDataColeta())) {
                System.out.println("\n\tConsulta já existente nessa data! ");
                return c; // retorna a consulta existente
            }
        }
        return amostraRepository.salvar(amostra);
    }

    public List<AmostrasLabEntity> listarAmostra() {
        return amostraRepository.findAll();
    }

    public void exibirAmostras() {
        List<AmostrasLabEntity> amostras = amostraRepository.findAll();

        if (amostras.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (AmostrasLabEntity c : amostras) {
                System.out.println("\nID: " + c.getId_amostralab());
                System.out.println("ID Consulta: " + c.getConsulta().getID_Consulta());
                System.out.println("Tipo do Exame: " + c.getTipoExame());
                System.out.println("Data da Coleta: " + c.getDataColeta().format(formatter));
                System.out.println("Resultado: " + c.getResultado());
                System.out.println("------------------------------");
            }
        }
    }

    public void removerPorId(Long id) {
        AmostrasLabEntity consulta = amostraRepository.buscarPorId(id);
        if (consulta != null) {
            amostraRepository.remover(consulta);
            System.out.println("Consulta removida com sucesso.");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }

    public void printMenu(Scanner sc, AmostrasLabService amostrasLabService) {

        int opcao;
        System.out.println("\n==== MENU AMOSTRAS ====");
        System.out.println("1. Atualizar amostra");
        System.out.println("2. Listar todas as amostras");
        System.out.println("3. Cancelar/Deletar amostras");
        System.out.println("4. Retornar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        opcao = sc.nextInt();
        sc.nextLine(); // limpar buffer

        switch (opcao) {
            case 1:
                break;
            case 2:
                amostrasLabService.exibirAmostras();
                break;
            case 3:
                System.out.print("\nDigite o ID da consulta que deseja deletar: ");
                Long id = sc.nextLong();
                sc.nextLine(); // limpar buffer
                amostrasLabService.removerPorId(id);
                break;
            case 4:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
