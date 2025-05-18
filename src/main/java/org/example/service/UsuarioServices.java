package org.example.service;

import org.example.entity.UsuarioEntity;
import org.example.enums.TypeUser;
import org.example.repository.UsuarioRepository;

import java.util.Optional;
import java.util.Scanner;

public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final Scanner scanner = new Scanner(System.in);

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario() {
        while (true) {
            System.out.print("Digite o tipo de usuário (ADMIN, PACIENTE, MEDICO, LAB, RECEPCAO): ");
            String tipoInput = scanner.nextLine().toUpperCase();

            TypeUser tipo;
            try {
                tipo = TypeUser.valueOf(tipoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido! Tente novamente.");
                continue;
            }

            String cpf = null;
            String crm = null;
            String usuario = null;
            String login = null;
            String nomeCompleto = null;
            String email = null;
            String senha;

            switch (tipo) {
                case PACIENTE:
                    System.out.print("CPF (11 dígitos): ");
                    cpf = scanner.nextLine();
                    if (!cpf.matches("\\d{11}")) {
                        System.out.println("CPF inválido!");
                        continue;
                    }
                    if (usuarioRepository.buscarPorCpf(cpf).isPresent()) {
                        System.out.println("CPF já cadastrado!");
                        continue;
                    }

                    System.out.print("Nome completo: ");
                    nomeCompleto = scanner.nextLine();

                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    login = email; // login será email para paciente
                    break;

                case MEDICO:
                    System.out.print("CRM: ");
                    crm = scanner.nextLine();
                    if (crm.isEmpty()) {
                        System.out.println("CRM inválido!");
                        continue;
                    }
                    if (usuarioRepository.buscarPorCrm(crm).isPresent()) {
                        System.out.println("CRM já cadastrado!");
                        continue;
                    }

                    System.out.print("Nome completo: ");
                    nomeCompleto = scanner.nextLine();

                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    login = crm; // login será crm para médico
                    break;

                case RECEPCAO:
                case LAB:
                    System.out.print("Nome completo: ");
                    nomeCompleto = scanner.nextLine();

                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("Nome de usuário: ");
                    usuario = scanner.nextLine();
                    if (usuarioRepository.buscarPorUsuario(usuario).isPresent()) {
                        System.out.println("Usuário já cadastrado!");
                        continue;
                    }

                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    login = usuario; // login será nome de usuário
                    break;

                case ADMIN:
                    System.out.print("Email: ");
                    email = scanner.nextLine();

                    System.out.print("Senha: ");
                    senha = scanner.nextLine();

                    login = email;
                    break;

                default:
                    System.out.println("Tipo não implementado.");
                    continue;
            }

            // Criação do novo usuário
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setTipo(tipo);
            novoUsuario.setLogin(login);
            novoUsuario.setSenha(senha);

            // Setar os campos extras
            novoUsuario.setCpf(cpf);
            novoUsuario.setCrm(crm);
            novoUsuario.setUsuario(usuario);
            novoUsuario.setNomeCompleto(nomeCompleto);
            novoUsuario.setEmail(email);

            usuarioRepository.salvar(novoUsuario);

            System.out.println("Cadastro realizado com sucesso!");
            break;
        }
    }

    public boolean autenticarUsuario(TypeUser tipo, String identificador, String senha) {
        Optional<UsuarioEntity> usuarioOpt;

        switch (tipo) {
            case PACIENTE:
                usuarioOpt = usuarioRepository.buscarPorCpf(identificador);
                break;
            case MEDICO:
                usuarioOpt = usuarioRepository.buscarPorCrm(identificador);
                break;
            case LAB:
            case RECEPCAO:
                usuarioOpt = usuarioRepository.buscarPorUsuario(identificador);
                break;
            case ADMIN:
                usuarioOpt = usuarioRepository.buscarPorLogin(identificador);
                break;
            default:
                System.out.println("Tipo de usuário inválido para login.");
                return false;
        }

        if (!usuarioOpt.isPresent()) {
            System.out.println("Usuário não encontrado.");
            return false;
        }

        UsuarioEntity usuario = usuarioOpt.get();

        if (!usuario.getSenha().equals(senha)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        System.out.println("Login bem-sucedido! Tipo de usuário: " + usuario.getTipo());
        return true;
    }
}
