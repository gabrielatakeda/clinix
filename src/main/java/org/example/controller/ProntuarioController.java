package org.example.controller;

import org.example.model.entity.Consulta;
import org.example.DAO.CustomizerFactory;
import org.example.DAO.ProntuarioRepository;
import org.example.model.entity.Prontuario;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProntuarioController {

    Scanner sc = new Scanner(System.in);
    EntityManager em = CustomizerFactory.getEntityManager();

    ProntuarioRepository repository = new ProntuarioRepository(
            CustomizerFactory.getEntityManager()
    );

    List<Prontuario> lista = new ArrayList<>();

    //função de printar todas as consultas procuradas de um usuario
    public void exibirProntuarios (List<Prontuario> lista){

        //função de for each percorrendo a lista do prontuario
        for (Prontuario entidade : lista){
            Consulta consulta = new Consulta();
            System.out.println("Consulta número "+ entidade.getId() +"\n"
                    +"Data: "+ entidade.getData()+"\n"
                    +"Nome: "+ entidade.getNome()+"\n"
                    +"Cpf: "+ entidade.getCpf()+"\n"
                    +"Médico: "+ entidade.getMedico()+"\n"
                    +"Status da Consulta: "+ consulta.getStatus());

            //ser printado apenas se a consulta ja foi realizada
            if (entidade.getStatus().equals("realizada")){
                System.out.println("Motivo da consulta: "+ entidade.getMotivo()+"\n"
                        +"Diagnóstico levantado: "+ entidade.getDiagnostico()+"\n"
                        +"Exame Requerido? : "+ entidade.getExame_necessario());

                //printa apenas se precisa de algum exame
                if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                        || entidade.getExame_necessario().equalsIgnoreCase("s")) {
                    System.out.println("Exame Solicitado: "+ entidade.getExame()+"\n"
                            +"Status do Exame: "+ entidade.getStatus_exame());

                    //printa apenas se o exame ja foi concluido
                    if (!entidade.getStatus_exame().equals("aguardando")){
                        System.out.println("Resultado do Exame: "+ entidade.getResultado_exame()+"\n");
                    }

                }

            }

            System.out.print("-------------------------------------------------------------\n");

        }

    }

    //função de printar um prontuario expecifico selecionado
    public void exibirProntuario (Prontuario entidade) {
        Consulta consulta = new Consulta();
        System.out.println("Consulta número " + entidade.getId() + "\n"
                + "Data: " + entidade.getData() + "\n"
                + "Nome: " + entidade.getNome() + "\n"
                + "Cpf: " + entidade.getCpf() + "\n"
                + "Médico: " + entidade.getMedico() + "\n"
                + "Status da Consulta: " + consulta.getStatus());

        String statusNovo = String.valueOf(consulta.getStatus());

        //printa so se a consulta ja foi realizada
        if (statusNovo.equals("REALIZADA")) {
            System.out.print("Motivo da consulta: " + entidade.getMotivo() + "\n"
                    + "Diagnóstico levantado: " + entidade.getDiagnostico() + "\n"
                    + "Exame Requerido? : " + entidade.getExame_necessario()+"\n");

            //printa apenas se foi necessário algum exame
            if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                    || entidade.getExame_necessario().equalsIgnoreCase("s")) {
                System.out.println("Exame Solicitado: " + entidade.getExame() + "\n"
                        + "Status do Exame: " + entidade.getStatus_exame());

                //printa apenas se o exame ja foi concluido
                if (!entidade.getStatus_exame().equals("aguardando")) {
                    System.out.print("Resultado do Exame: " + entidade.getResultado_exame()+"\n");
                }

            }

        }

    }

    //função que vai atualizar a parte de realização da consulta
    public void atualizacaoProntuario(Prontuario entidade){

        System.out.println("Qual foi o motivo da consulta? : ");
        entidade.setMotivo(sc.nextLine());

        System.out.println("Qual o diagnóstico levantado? : ");
        entidade.setDiagnostico(sc.nextLine());

        System.out.println("Será necessário algum exame? (sim/não): ");
        entidade.setExame_necessario(sc.nextLine());
        if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                || entidade.getExame_necessario().equalsIgnoreCase("s")){

            ExameController amostraService = new ExameController();

            amostraService.cadastrarExame(sc, entidade);

            if (entidade.getStatus().equalsIgnoreCase("concluido")) {
                System.out.println("Resultado do exame: " + entidade.getResultado_exame());
            }else{
                System.out.println("Aguardando resultado do exame.");
            }
            repository.atualizar(entidade);
        }

        //percorrido caso seja necessário algum exame

        if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                || entidade.getExame_necessario().equalsIgnoreCase("s")) {


            //salva no banco de dados
            repository.atualizar(entidade);
        }
//        else{
//            //colocando que não foi necessário nenhum exame só de precaução
//            entidade.setExame_necessario("não");
//            //salva no banco de dados
//            repository.atualizar(entidade);
//
//        }
    }

    public void exibirExames (List<Prontuario> lista){

        for (Prontuario entidade : lista){
            System.out.println("Numero da Consulta: "+ entidade.getId()+"\n"
                    +"Paciente: "+ entidade.getNome()+"\n"
                    +"Medico: "+ entidade.getMedico()+"\n"
                    +"Exame Necessário: "+ entidade.getExame()+"\n"
                    +"----------------------------------------------------");
        }

    }

    public void menuPronutuario (String cpf){

        lista = repository.buscarCpfProntuario(cpf);

        ProntuarioController service = new ProntuarioController();

        int op;

        do {

            //exibe toda a lista de consultas de "lista"
            service.exibirProntuarios(lista);

            System.out.println("teste 5");

            System.out.println("O que deseja fazer ?" + "\n"
                    + "-(1) alterar uma consulta" + "\n"
                    + "-(2) excluir uma consulta" + "\n"
                    + "-(3) sair");

            //declarando o prontuario que foi selecionado para ser alterado
            Prontuario consulta = new Prontuario();

            op = sc.nextInt();

            switch (op) {
                case 1: {
                    while (true) {

                        System.out.println("\nSelecione o número da consulta que deseja editar : ");

                        Long idConsulta = sc.nextLong();

                        Prontuario prontuario = repository.buscarId(idConsulta);

                        if (consulta == null) {
                            System.out.println("Consulta não encontrada com o número informado.");
                            continue;
                        }
                        //faz sua exibição
                        service.exibirProntuario(prontuario);

                        System.out.println("\nEsta é a consulta que deseja editar?" + "\n"
                                + "-(1) SIM" + "\n"
                                + "-(2) NÃO" + "\n"
                                + "-(3) SAIR" + "\n");
                        int op2;

                        op2 = sc.nextInt();

                        if (op2 == 1) {
                            //aciona a função de alteração do prontuario de Service
                            service.atualizacaoProntuario(prontuario);
                            break;

                        } else if (op2 == 2) {
                            continue;

                        } else if (op2 == 3) {
                            break;

                        } else {
                            System.out.println("Por favor selecione uma opção válida");
                            continue;

                        }
                    }
                    break;
                }
                case 2: {
                    int op2;

                    while (true) {
                        System.out.println("\nSelecione o número da cunsulta que deseja excluir : ");

                        consulta = repository.buscarId(sc.nextLong());

                        service.exibirProntuario(consulta);

                        System.out.println("\nEsta é a consulta que deseja excluir?" + "\n"
                                + "-(1) SIM" + "\n"
                                + "-(2) NÃO" + "\n"
                                + "-(3) SAIR" + "\n");

                        op2 = sc.nextInt();

                        if (op2 == 1) {
                            repository.remover(consulta);

                            System.out.println("Consulta removida com sucesso");
                            break;

                        } else if (op2 == 2) {
                            continue;

                        } else if (op2 == 3) {
                            break;

                        } else {
                            System.out.println("Por favor selecione uma opção válida");
                            continue;
                        }
                    }
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    System.out.println("Por favor selecione uma opção válida");
                    break;
                }
            }
        }while(op != 3);
    }
}