package org.example.controller;

import org.example.DAO.CustomizerFactory;
import org.example.model.entity.Usuario;
import org.example.model.enums.TypeUser;
import org.example.DAO.UsuarioRepository;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Scanner;

public class UsuarioController {

//    private final UsuarioRepository usuarioRepository;
    Scanner sc = new Scanner(System.in);

//    public UsuarioController(){}
//
//    public UsuarioController(UsuarioRepository usuarioRepository) {
//        this.usuarioRepository = usuarioRepository;
//    }

//    private final EntityManager em = CustomizerFactory.getEntityManager();
//    private final UsuarioRepository usuarioRepository = new UsuarioRepository(em);
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void cadastrarUsuario() {
        while (true) {
            System.out.print("Digite o tipo de usuário (ADMIN, PACIENTE, MEDICO, LAB, RECEPCAO): ");
            String tipoInput = sc.nextLine().toUpperCase();

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
                    cpf = sc.nextLine();
                    if (!cpf.matches("\\d{11}")) {
                        System.out.println("CPF inválido!");
                        continue;
                    }
                    if (usuarioRepository.buscarPorCpf(cpf).isPresent()) {
                        System.out.println("CPF já cadastrado!");
                        continue;
                    }

                    System.out.print("Nome completo: ");
                    nomeCompleto = sc.nextLine();

                    System.out.print("Email: ");
                    email = sc.nextLine();

                    System.out.print("Senha: ");
                    senha = sc.nextLine();

                    login = email;
                    break;

                case MEDICO:
                    System.out.print("CRM: ");
                    crm = sc.nextLine();
                    if (crm.isEmpty()) {
                        System.out.println("CRM inválido!");
                        continue;
                    }
                    if (usuarioRepository.buscarPorCrm(crm).isPresent()) {
                        System.out.println("CRM já cadastrado!");
                        continue;
                    }

                    System.out.print("Nome completo: ");
                    nomeCompleto = sc.nextLine();

                    System.out.print("Email: ");
                    email = sc.nextLine();

                    System.out.print("Senha: ");
                    senha = sc.nextLine();

                    login = crm;
                    break;

                case RECEPCAO:
                case LAB:
                    System.out.print("Nome completo: ");
                    nomeCompleto = sc.nextLine();

                    System.out.print("Email: ");
                    email = sc.nextLine();

                    System.out.print("Nome de usuário: ");
                    usuario = sc.nextLine();
                    if (usuarioRepository.buscarPorUsuario(usuario).isPresent()) {
                        System.out.println("Usuário já cadastrado!");
                        continue;
                    }

                    System.out.print("Senha: ");
                    senha = sc.nextLine();

                    login = usuario;
                    break;

                case ADMIN:
                    System.out.print("Nome completo: ");
                    nomeCompleto = sc.nextLine();

                    System.out.print("Email: ");
                    email = sc.nextLine();

                    System.out.print("Nome de usuário (para login): ");
                    usuario = sc.nextLine();
                    if (usuarioRepository.buscarPorUsuario(usuario).isPresent()) {
                        System.out.println("Usuário já cadastrado!");
                        continue;
                    }

                    System.out.print("Senha: ");
                    senha = sc.nextLine();

                    login = usuario;
                    break;

                default:
                    System.out.println("Tipo não implementado.");
                    continue;
            }


            Usuario novoUsuario = new Usuario();
            novoUsuario.setTipo(tipo);
            novoUsuario.setLogin(login);
            novoUsuario.setSenha(senha);

            // Setar os campos extras
            novoUsuario.setCpf(cpf);
            novoUsuario.setCrm(crm);
            novoUsuario.setUsuario(usuario);
            novoUsuario.setNomeCompleto(nomeCompleto);
            novoUsuario.setEmail(email);

            try {
                usuarioRepository.salvar(novoUsuario);
                System.out.println("Cadastro realizado com sucesso!");
            } catch (Exception e) {
                System.out.println("Cadastro falhou. Verifique os dados e tente novamente.");
            }

            break;
        }
    }

    public boolean autenticarUsuario(TypeUser tipo, String identificador, String senha) {
        Optional<Usuario> usuarioOpt;

        switch (tipo) {
            case PACIENTE:
                usuarioOpt = usuarioRepository.buscarPorCpf(identificador);
                break;
            case MEDICO:
                usuarioOpt = usuarioRepository.buscarPorCrm(identificador);
                break;
            case LAB:
            case RECEPCAO:
            case ADMIN:
                usuarioOpt = usuarioRepository.buscarPorUsuario(identificador);
                break;
            default:
                System.out.println("Tipo de usuário inválido para login.");
                return false;
        }

        if (!usuarioOpt.isPresent()) {
            System.out.println("Usuário não encontrado.");
            return false;
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getSenha().equals(senha)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        System.out.println("Login bem-sucedido! Tipo de usuário: " + usuario.getTipo());
        return true;
    }
}