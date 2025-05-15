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
                    +"Status do Exame: "+ entidade.getStatus_exame()+"\n");

                    //printa apenas se o exame ja foi concluido
                    if (!entidade.getStatus_exame().equals("aguardando")){
                        System.out.println("Resultado do Exame: "+ entidade.getResultado_exame());
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
                + "Status da Consulta: " + entidade.getStatus() + "\n");

        //printa so se a consulta ja foi realizada
        if (entidade.getStatus().equals("realizada")) {
            System.out.print("Motivo da consulta: " + entidade.getMotivo() + "\n"
                    + "Diagnóstico levantado: " + entidade.getDiagnostico() + "\n"
                    + "Exame Requerido? : " + entidade.getExame_necessario());

            //printa apenas se foi necessário algum exame
            if (entidade.getExame_necessario().equalsIgnoreCase("sim")
                    || entidade.getExame_necessario().equalsIgnoreCase("s")) {
                System.out.println("Exame Solicitado: " + entidade.getExame() + "\n"
                        + "Status do Exame: " + entidade.getStatus_exame() + "\n");

                //printa apenas se o exame ja foi concluido
                if (!entidade.getStatus_exame().equals("aguardando")) {
                    System.out.print("Resultado do Exame: " + entidade.getResultado_exame());
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

}
