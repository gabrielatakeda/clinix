package org.example.view;

import org.example.DAO.UsuarioRepository;
import org.example.model.enums.TypeUser;
import org.example.controller.UsuarioController;

import java.util.Scanner;

public class LoginView {

    public void iniciar(Scanner sc) {
        boolean executando = true;
        UsuarioController usuarioController = new UsuarioController();
        while (executando) {
            System.out.println("---BEM VINDO AO CLINIX---");
            System.out.println("\n===CADASTRO / LOGIN ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = sc.nextLine();

            if (!entrada.matches("\\d+")) {
                System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                continue;
            }

            int opcao = Integer.parseInt(entrada);
            switch (opcao) {
                case 1:
                    usuarioController.cadastrarUsuario();
                    break;
                case 2:
                    System.out.println("\n=== LOGIN ===");
                    System.out.print("Informe o tipo de usuário para login (ADMIN, PACIENTE, MEDICO, LAB, RECEPCAO): ");
                    String tipoLoginInput = sc.nextLine().toUpperCase();

                    TypeUser tipoLogin;
                    try {
                        tipoLogin = TypeUser.valueOf(tipoLoginInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de usuário inválido. Tente novamente.");
                        break;
                    }

                    String login = null;

                    switch (tipoLogin) {
                        case ADMIN:
                        case LAB:
                        case RECEPCAO:
                            System.out.print("Digite seu nome de usuário: ");
                            login = sc.nextLine();
                            if (login == null || login.trim().isEmpty()) {
                                System.out.println("Usuário inválido.");
                                break;
                            }
                            break;
                        case PACIENTE:
                            System.out.print("Digite seu CPF (somente números): ");
                            login = sc.nextLine();
                            if (!login.matches("\\d{11}")) {
                                System.out.println("CPF inválido.");
                                break;
                            }
                            break;
                        case MEDICO:
                            System.out.print("Digite seu CRM: ");
                            login = sc.nextLine();
                            if (login == null || login.trim().isEmpty()) {
                                System.out.println("CRM inválido.");
                                break;
                            }
                            break;
                    }

                    if (login == null || login.trim().isEmpty()) {
                        System.out.println("Login inválido. Tente novamente.");
                        break;
                    }
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    if (usuarioController.autenticarUsuario(tipoLogin, login, senha)) {
                        MenuView menuView = new MenuView();
                        menuView.abrirMenu();
                    } else {
                        System.out.println("Falha no login.");
                    }
                    break;
                case 3:
                    System.out.println("\nSaindo...");
                    sc.close();
//                    em.close();
//                    entityManagerFactory.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nOpção inválida! Digite um número entre 1 e 3.");
                    break;
            }
        }
    }
}

