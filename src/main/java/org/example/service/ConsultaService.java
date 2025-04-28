package org.example.service;

import org.example.entity.AmostrasLabEntity;
import org.example.entity.ConsultaEntity;

import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);

    // Adicione o formatter na parte superior da classe para usar em todo o código
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ConsultaEntity buscarPorId(Long id) {
        return consultaRepository.buscarPorId(id);
    }

    public List<ConsultaEntity> findByNome(String nome) {
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

    public void exibirConsultas() {
        List<ConsultaEntity> consultas = consultaRepository.findAll();

        if (consultas.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (ConsultaEntity c : consultas) {
                System.out.print("\nID: " + c.getID_Consulta());
                System.out.println("\nData: " + c.getData_consulta().format(formatter));
                System.out.println("Motivo: " + c.getMotivo());
                System.out.println("Status: " + c.getStatus());
                System.out.println("Prescrição: " + c.getPrescricao());
                System.out.println("Observações: " + c.getObservacoes());
                System.out.println("------------------------------");
            }
        }
    }


    public ConsultaEntity salvarConsulta(ConsultaEntity consulta) {
        List<ConsultaEntity> todasConsultas = consultaRepository.findAll();

        for (ConsultaEntity c : todasConsultas) {
            if (c.getData_consulta().equals(consulta.getData_consulta())) {
                System.out.println("\n\tConsulta já existente nessa data! Motivo: " + c.getMotivo());
                return c; // retorna a consulta existente
            }
        }
        return consulta;
        //precisa mesmo retornar? sabendo que a funcao salvar e void????
        //return consultaRepository.salvar(consulta);
    }

    public List<ConsultaEntity> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void atualizarMotivoConsulta(Long id, String novoMotivo) {
        ConsultaEntity consulta = consultaRepository.buscarPorId(id);
        if (consulta != null) {
            consulta.setMotivo(novoMotivo);
            consultaRepository.atualizar(consulta);
            System.out.println("Motivo da consulta atualizado com sucesso!");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }

    public void removerPorId(Long id) {
        ConsultaEntity consulta = consultaRepository.buscarPorId(id);
        if (consulta != null) {
            consultaRepository.remover(consulta);
            System.out.println("Consulta removida com sucesso.");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }


    public void printMenu(Scanner sc, ConsultaService consultaService, AmostrasLabService amostrasLabService) {
        // Formato que o usuário deve digitar
        List<ConsultaEntity> ListaConsultasEntity = new ArrayList<>();
        List<AmostrasLabService> amostrasLab = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.println("\n==== MENU CONSULTAS ====");
        System.out.println("1. Cadastrar nova consulta");
        System.out.println("2. Atualizar consulta");
        System.out.println("3. Listar todas as consultas");
        System.out.println("4. Cancelar/Deletar consulta");
        System.out.println("5. Retornar ao menu anterior");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // limpar buffer

        switch (opcao) {
            case 1:
                ConsultaEntity novaConsulta = new ConsultaEntity();
                AmostrasLabEntity novaAmostra = new AmostrasLabEntity();

                try {
                    System.out.print("Data da consulta (dd/MM/yyyy HH:mm): ");
                    String dataConsultaStr = sc.nextLine();

                    LocalDateTime dataConsulta = LocalDateTime.parse(dataConsultaStr, formatter);

                    novaConsulta.setData_consulta(dataConsulta);
                } catch (Exception e) {
                    System.out.println("\nErro: Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
                    return;
                }

                System.out.print("Motivo: ");
                novaConsulta.setMotivo(sc.nextLine());

                System.out.print("Status: ");
                novaConsulta.setStatus(sc.nextLine());

                System.out.print("Prescrição: ");
                novaConsulta.setPrescricao(sc.nextLine());

                System.out.print("Observações: ");
                novaConsulta.setObservacoes(sc.nextLine());

                ConsultaEntity salvo = consultaService.salvarConsulta(novaConsulta);

                System.out.println("\n\tDejesa encaminhar algum exame?(s/n) ");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("s")) {


                    novaAmostra.setDataColeta(salvo.getData_consulta()); // pega a mesma data da consulta
                    System.out.print("Data da amostra : " + novaAmostra.getDataColeta().format(formatter));


                    System.out.print("\nTipo: ");
                    novaAmostra.setTipoExame(sc.nextLine());

                    System.out.print("Resultado: ");
                    novaAmostra.setResultado(sc.nextLine());

                    novaAmostra.setConsulta(salvo); // associa a amostra à consulta

                    AmostrasLabEntity amostraSalva = amostrasLabService.salvarAmostra(novaAmostra);

                    System.out.println(amostraSalva.getId_amostralab() != null ? "Amostra cadastrada com sucesso!" : "Erro ao salvar a amostra.");
                } else if (!resposta.equalsIgnoreCase("n")) {
                    System.out.println("\n\tOpcao invalida!");

                }
                System.out.println(salvo.getID_Consulta() != null ? "Consulta agendada com sucesso!" : "Erro ao salvar.");
                break;
            case 2:

                if (ListaConsultasEntity.isEmpty()) {
                    System.out.println("Nenhuma consulta encontrada.");
                    return;
                } else {
                    System.out.print("Digite o ID da consulta: ");
                    Long id = sc.nextLong();
                    sc.nextLine(); // Consumir quebra de linha

                    System.out.print("Digite o novo motivo: ");
                    String novoMotivo = sc.nextLine();

                    consultaService.atualizarMotivoConsulta(id, novoMotivo);
                }
                break;
            case 3:
                consultaService.exibirConsultas();
                break;
            case 4:
                System.out.print("\nDigite o ID da consulta que deseja deletar: ");
                Long id = sc.nextLong();
                sc.nextLine(); // limpar buffer
                consultaService.removerPorId(id);
                break;
            case 5:
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}