package org.example;

import org.example.Entity.ProntuarioEntity;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.ProntuarioRepository;
import org.example.service.ProntuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //scanner
        Scanner sc = new Scanner(System.in);

        //declaração da lista de prontuarios de que será procurada por CPF
        List<ProntuarioEntity> lista = new ArrayList<>();

        //declarando o prontuario que foi selecionado para ser alterado
        ProntuarioEntity prontSingular = new ProntuarioEntity();

        //declarando a classe Service para as funções
        ProntuarioService service = new ProntuarioService();

        //declarando a classe Repository para suas funções
        ProntuarioRepository repository = new ProntuarioRepository(
                CustomizerFactory.getEntityManager()
        );

        //buscando os prontuarios de um paciente e os listando em "lista"
        lista = repository.buscarCpfProntuario("132.218.199-35");

        //exibe toda a lista de consultas de "lista"
        service.exibirProntuarios(lista);

        System.out.println("Selecione o número da consulta que deseja alterar : ");

        //selecionando um prontuario expecifico para alteração pelo seu numero
        prontSingular = repository.buscarPorId(sc.nextLong());

        //faz sua exibição
        service.exibirProntuario(prontSingular);

        //aciona a função de alteração do prontuario de Service
        service.atualizacaoProntuario(prontSingular);

    }
}