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

        Scanner sc = new Scanner(System.in);

        List<ProntuarioEntity> lista = new ArrayList<>();

        ProntuarioEntity prontSingular = new ProntuarioEntity();

        ProntuarioService service = new ProntuarioService();

        ProntuarioRepository repository = new ProntuarioRepository(
                CustomizerFactory.getEntityManager()
        );

        lista = repository.buscarCpfProntuario("132.218.199-35");

        service.exibirProntuarios(lista);

        System.out.println("qual ID do prontuario que deseja modificar ?");

        prontSingular = repository.buscarPorId(sc.nextLong());

        service.exibirProntuario(prontSingular);

    }
}