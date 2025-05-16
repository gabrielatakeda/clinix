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

        //variavel para o switch case
        int op;

        //declaração da variavel que será usada para a busca das consultas
        String cpf;

        //declaração da lista de prontuarios de que será procurada por CPF
        List<ProntuarioEntity> lista = new ArrayList<>();

        //declarando o prontuario que foi selecionado para ser alterado
        ProntuarioEntity consulta = new ProntuarioEntity();

        //declarando a classe Service para as funções
        ProntuarioService service = new ProntuarioService();

        //declarando a classe Repository para suas funções
        ProntuarioRepository repository = new ProntuarioRepository(
                CustomizerFactory.getEntityManager()
        );

        System.out.println("Digite o CPF do paciente: ");
        cpf = sc.nextLine();

        //buscando os prontuarios de um paciente e os listando em "lista"
        lista = repository.buscarCpfProntuario(cpf);

        //exibe toda a lista de consultas de "lista"
        service.exibirProntuarios(lista);

        System.out.println("O que deseja fazer ?"+"\n"
        +"-(1) alterar uma consulta"+"\n"
        +"-(2) exculuir uma consulta"+"\n"
        +"-(3) sair");

        op = sc.nextInt();

        switch(op){
            case 1: {
                System.out.println("Selecione o número da consulta que deseja alterar : ");

                //selecionando um prontuario expecifico para alteração pelo seu numero
                consulta = repository.buscarPorId(sc.nextLong());

                //faz sua exibição
                service.exibirProntuario(consulta);

                //aciona a função de alteração do prontuario de Service
                service.atualizacaoProntuario(consulta);
                break;
            }
            case 2: {
                int op2;

                do {
                    System.out.println("Selecione o número da cunsulta que deseja excluir : ");

                    consulta = repository.buscarPorId(sc.nextLong());

                    service.exibirProntuario(consulta);

                    System.out.println("Esta é a consulta que deseja excluir?" + "\n"
                    + "-(1) SIM" + "\n"
                    + "-(2) NÃO" + "\n"
                    +"-(3) SAIR"+"\n");

                    op2 = sc.nextInt();

                    if (op2 == 1) {
                        repository.remover(consulta);

                        System.out.println("Consulta removida com sucesso");
                        break;
                    }

                }while(op2 != 1 && op2 != 3);

                break;

            }
            case 3: {
                break;
            }
            default:{
                System.out.println("Por favor selecione uma opção válida");
                break;
            }
        }

        //A PARTIR DAQUI É PARTE DE TESTE PARA A PARTE DE EXAME

        System.out.println("PARTE TESTE PARA EXAME\n");

        List<ProntuarioEntity> listaExames = new ArrayList<>();

        listaExames = repository.buscarPorExameNecessario();

        service.exibirExames(listaExames);

        System.out.println("Qual a consulta que deseja realizar o exame? : ");

        ProntuarioEntity exame = new ProntuarioEntity();

        exame = repository.buscarPorId(sc.nextLong());

        System.out.println("Qual o resultado do exame? : ");
        sc.nextLine();
        exame.setResultado_exame(sc.nextLine());
        exame.setStatus_exame("finalizado");
        repository.atualizar(exame);

    }
}