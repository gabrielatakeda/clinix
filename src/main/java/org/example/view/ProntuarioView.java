package org.example.view;

import org.example.controller.ProntuarioController;
import org.example.model.entity.Prontuario;

import java.util.Scanner;

public class ProntuarioView {

    ProntuarioController prontuarioController = new ProntuarioController();
    Scanner sc = new Scanner(System.in);

    public void printMenu(){
        System.out.println("Digite o CPF do paciente: ");
        String cpf;
        cpf = sc.nextLine();

        prontuarioController.menuPronutuario(cpf);
    }

}
