package org.example.controller;

import org.example.model.entity.Exame;
import org.example.model.entity.Consulta;
import org.example.DAO.AmostrasLabRepository;
import org.example.DAO.CustomizerFactory;
import org.example.model.entity.Prontuario;
import org.example.model.enums.StatusAmostraLab;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class ExameController {

    EntityManager em = CustomizerFactory.getEntityManager();
    AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);

    // Adicione o formatter na parte superior da classe para usar em todo o código
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public void cadastrarExame(Scanner sc, Prontuario id) {

        System.out.println("Digite o tipo do exame: ");
        String tipoExame = sc.nextLine();

        Exame exameExistente = amostraRepository.buscarPorTipo(tipoExame);

        if (exameExistente != null) {
            System.out.println("Exame já cadastrado:");
            System.out.println("Tipo: " + exameExistente.getTipoExame());
            System.out.println("Status: " + exameExistente.getStatus());
            if (exameExistente.getStatus() == StatusAmostraLab.CONCLUIDO) {
                System.out.println("Resultado: " + exameExistente.getResultado());
            } else {
                System.out.println("Resultado ainda não disponível, exame está em andamento.");
            }


        } else {
            Exame novaAmostra = new Exame();
            novaAmostra.setTipoExame(tipoExame);
            novaAmostra.setProntuario(id);
            novaAmostra.setStatus(StatusAmostraLab.PENDENTE);
            Consulta consulta = new Consulta();
            System.out.println(consulta.getID_Consulta());
            System.out.println("Exame solicitado: " + tipoExame);
            System.out.println("Status: " + StatusAmostraLab.PENDENTE);
            System.out.println("Resultado: Aguardando resultado do exame.");

            // Salva a nova amostra no banco
            amostraRepository.salvar(novaAmostra);

            System.out.println("Exame cadastrado com status aguardando resultado.");

        }
    }

    public void atualizarResultadoExame(Scanner sc, Exame amostra) {
        System.out.println("Digite o resultado do exame: ");
        sc.nextLine();
        String resultado = sc.nextLine();

        amostra.setResultado(resultado);
        amostra.setStatus(StatusAmostraLab.CONCLUIDO);

        amostraRepository.atualizar(amostra);

        System.out.println("Resultado do exame atualizado e status alterado para CONCLUIDO.");

    }

//    public AmostrasLabEntity salvarAmostra(AmostrasLabEntity amostra) {
//        List<AmostrasLabEntity> todasAmostras = amostraRepository.findAll();
//
//        for (AmostrasLabEntity c : todasAmostras) {
//            if (c.getDataColeta().equals(amostra.getDataColeta())) {
//                System.out.println("\n\tConsulta já existente nessa data! ");
//                return c; // retorna a consulta existente
//            }
//        }
//        return amostraRepository.salvar(amostra);
//    }

    public List<Exame> listarAmostra() {
        return amostraRepository.findAll();
    }

    public void exibirAmostras() {
        List<Exame> amostras = amostraRepository.findAll();

        if (amostras.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (Exame c : amostras) {
                System.out.println("\nID: " + c.getId_amostralab());
                System.out.println("Tipo do Exame: " + c.getTipoExame());
                System.out.println("Resultado: " + c.getResultado());
                System.out.println("------------------------------");
            }
        }
    }

    public void removerPorId(Long id) {
        Exame consulta = amostraRepository.buscarPorId(id);
        if (consulta != null) {
            amostraRepository.remover(consulta);
            System.out.println("Consulta removida com sucesso.");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }




    public void listarPorStatus(StatusAmostraLab status) {
        List<Exame> amostras = amostraRepository.buscarPorStatus(status);

        if (amostras.isEmpty()) {
            System.out.println("Nenhuma amostra com status " + status + " encontrada.");
            return;
        }

        System.out.println("\n=== Amostras com status: " + status + " ===");
        for (Exame a : amostras) {
            System.out.println("ID: " + a.getId_amostralab());
            System.out.println("Paciente: " + a.getProntuario().getNome());
            System.out.println("Medico: " + a.getProntuario().getMedico());
            System.out.println("Tipo do Exame: " + a.getTipoExame());
            if (a.getResultado() != null && !a.getResultado().trim().isEmpty()) {
                System.out.println("Resultado: " + a.getResultado());
            }else{
                System.out.println("Resultado: Aguardando resultado...");
            }
            System.out.println("Status: " + a.getStatus());
            System.out.println("----------------------------");
        }

    }


    public void atualizarStatus(StatusAmostraLab status,Scanner sc) {
        listarPorStatus(status);
        Exame amostra = new Exame();
        AmostrasLabRepository repository = new AmostrasLabRepository(em);


        System.out.println("Digite o ID do exame que quer atualizar: ");
        Long id = sc.nextLong();

        amostra = repository.buscarPorId(id);

        if(amostra != null){
            System.out.println("Status atual: " + amostra.getStatus());
            System.out.println("Escolha o novo status:");
            System.out.println("1. Concluido");
            System.out.println("2. Inconclusivel");
            System.out.print("Escolha uma opção: ");
            var opcao = sc.nextInt();
            StatusAmostraLab novoStatus  = null;

            switch (opcao) {
                case 1:
                    System.out.println("Qual o resultado do exame? ");
                    sc.nextLine();
                    String resul = sc.nextLine();
                    amostra.setResultado(resul);
                    novoStatus = StatusAmostraLab.CONCLUIDO;
                    break;
                case 2:
                    novoStatus = StatusAmostraLab.INCONCLUSIVO;
                    break;
                default:
                    System.out.println("Opçao invalida!");
                    break;
            }

            try {
                if (novoStatus != null) {
                    amostra.setStatus(novoStatus);
                    amostraRepository.atualizar(amostra);
                    System.out.println("Status atualizado com sucesso!");
                } else {
                    System.out.println("Erro ao atualizar: status inválido.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido.");
            }

        }else{
            System.out.println("Id nao encontrado.");
        }
    }

    public void deletarAmostra(Scanner sc){
        listarAmostra();
        System.out.println("Digite o ID do exame a ser excluido ");
        Long opcao = sc.nextLong();
        sc.nextLine();
        removerPorId(opcao);
    }

}