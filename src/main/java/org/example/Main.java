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

        //declarando a classe Service para as funções
        ProntuarioService service = new ProntuarioService();

        //scanner
        Scanner sc = new Scanner(System.in);

        //declarando a classe Repository para suas funções
        ProntuarioRepository repository = new ProntuarioRepository(
                CustomizerFactory.getEntityManager()
        );

        System.out.println("Digite o CPF do paciente: ");

        //declaração da variavel que será usada para a busca das consultas
        String cpf;

        cpf = sc.nextLine();

        //chama toda a parte de menu e funções
        service.menuPronutuario(cpf);


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