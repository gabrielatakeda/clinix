package org.example.service;

import org.example.Entity.ProntuarioEntity;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.ProntuarioRepository;
import org.example.service.ProntuarioService;

import java.util.ArrayList;
import java.util.List;


public class ProntuarioService {

    List<ProntuarioEntity> lista = new ArrayList<>();

    public void exibirProntuarios (List<ProntuarioEntity> lista){

        for (ProntuarioEntity entidade : lista){
            System.out.println("Id da consulta: "+ entidade.getId() +"\n"
            +"Nome: "+ entidade.getNome()+"\n"
            +"Idade: "+ entidade.getIdade()+"\n"
            +"Cpf: "+ entidade.getCpf()+"\n"
            +"Médico: "+ entidade.getMedico()+"\n"
            +"Status da Consulta: "+ entidade.getStatus()+"\n"
            +"------------------------------------------------");
        }

    }

    public void exibirProntuario (ProntuarioEntity entidade){

            System.out.println("Id da consulta: "+ entidade.getId() +"\n"
                    +"Nome: "+ entidade.getNome()+"\n"
                    +"Idade: "+ entidade.getIdade()+"\n"
                    +"Cpf: "+ entidade.getCpf()+"\n"
                    +"Médico: "+ entidade.getMedico()+"\n"
                    +"Status da Consulta: "+ entidade.getStatus()+"\n");

    }

}
