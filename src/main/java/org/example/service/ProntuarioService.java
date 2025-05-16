package org.example.service;

import org.example.Entity.ProntuarioEntity;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.ProntuarioRepository;
import org.example.service.ProntuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProntuarioService {

    Scanner sc = new Scanner(System.in);

    ProntuarioRepository repository = new ProntuarioRepository(
            CustomizerFactory.getEntityManager()
    );

    List<ProntuarioEntity> lista = new ArrayList<>();

    //função de printar todas as consultas procuradas de um usuario
    public void exibirProntuarios (List<ProntuarioEntity> lista){

        //função de for each percorrendo a lista do prontuario
        for (ProntuarioEntity entidade : lista){
            System.out.println("Consulta número "+ entidade.getId() +"\n"
            +"Data: "+ entidade.getData()+"\n"
            +"Nome: "+ entidade.getNome()+"\n"
            +"Idade: "+ entidade.getIdade()+"\n"
            +"Cpf: "+ entidade.getCpf()+"\n"
            +"Médico: "+ entidade.getMedico()+"\n"
            +"Status da Consulta: "+ entidade.getStatus());

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
    public void exibirProntuario (ProntuarioEntity entidade) {

        System.out.println("Consulta número " + entidade.getId() + "\n"
                + "Data: " + entidade.getData() + "\n"
                + "Nome: " + entidade.getNome() + "\n"
                + "Idade: " + entidade.getIdade() + "\n"
                + "Cpf: " + entidade.getCpf() + "\n"
                + "Médico: " + entidade.getMedico() + "\n"
                + "Status da Consulta: " + entidade.getStatus());

        //printa so se a consulta ja foi realizada
        if (entidade.getStatus().equals("realizada")) {
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
    public void atualizacaoProntuario(ProntuarioEntity entidade){

        System.out.println("Qual foi o motivo da consulta? : ");
        entidade.setMotivo(sc.nextLine());

        System.out.println("Qual o diagnóstico levantado? : ");
        entidade.setDiagnostico(sc.nextLine());

        System.out.println("Será necessário algum exame? (sim/não): ");
        entidade.setExame_necessario(sc.nextLine());

        //percorrido caso seja necessário algum exame
        if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                || entidade.getExame_necessario().equalsIgnoreCase("s")) {

            System.out.println("Que exame será necessário? : ");
            entidade.setExame(sc.nextLine());
            entidade.setStatus_exame("aguardando");
            //salva no banco de dados
            repository.atualizar(entidade);

        }else{
            //colocando que não foi necessário nenhum exame só de precaução
            entidade.setExame_necessario("não");
            //salva no banco de dados
            repository.atualizar(entidade);

        }
    }

    public void exibirExames (List<ProntuarioEntity> lista){

        for (ProntuarioEntity entidade : lista){
            System.out.println("Numero da Consulta: "+ entidade.getId()+"\n"
            +"Paciente: "+ entidade.getNome()+"\n"
            +"Medico: "+ entidade.getMedico()+"\n"
            +"Exame Necessário: "+ entidade.getExame()+"\n"
            +"----------------------------------------------------");
        }

    }

    public void menuPronutuario (String cpf){

        //declaração da lista de prontuarios de que será procurada por CPF
        List<ProntuarioEntity> lista = new ArrayList<>();

        //buscando os prontuarios de um paciente e os listando em "lista"
        lista = repository.buscarCpfProntuario(cpf);

        //declarando a classe Service para as funções
        ProntuarioService service = new ProntuarioService();

        //variavel para o switch case
        int op;

        do {

            //exibe toda a lista de consultas de "lista"
            service.exibirProntuarios(lista);

            System.out.println("O que deseja fazer ?" + "\n"
                    + "-(1) alterar uma consulta" + "\n"
                    + "-(2) exculuir uma consulta" + "\n"
                    + "-(3) sair");

            //declarando o prontuario que foi selecionado para ser alterado
            ProntuarioEntity consulta = new ProntuarioEntity();

            op = sc.nextInt();

            switch (op) {
                case 1: {
                    while (true) {
                        System.out.println("\nSelecione o número da consulta que deseja editar : ");

                        //selecionando um prontuario expecifico para alteração pelo seu numero
                        consulta = repository.buscarPorId(sc.nextLong());

                        //faz sua exibição
                        service.exibirProntuario(consulta);

                        System.out.println("\nEsta é a consulta que deseja editar?" + "\n"
                                + "-(1) SIM" + "\n"
                                + "-(2) NÃO" + "\n"
                                + "-(3) SAIR" + "\n");
                        int op2;

                        op2 = sc.nextInt();

                        if (op2 == 1) {
                            //aciona a função de alteração do prontuario de Service
                            service.atualizacaoProntuario(consulta);
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

                        consulta = repository.buscarPorId(sc.nextLong());

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
