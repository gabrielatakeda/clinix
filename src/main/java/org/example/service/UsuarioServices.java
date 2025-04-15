package org.example.service;

import org.example.entity.UsuarioEntity;
import org.example.repository.UsuarioRepository;

import java.util.Optional;
import java.util.Scanner;

public class UsuarioServices {
    private UsuarioRepository usuarioRepository;
    private Scanner scanner = new Scanner(System.in);

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario() {
        while (true) { // Loop para voltar ao cadastro se houver erro
            System.out.print("CPF (11 dígitos): ");
            String cpf = scanner.nextLine();

            // Verifica se o CPF tem exatamente 11 números
            if (!cpf.matches("\\d{11}")) {
                System.out.println("ERRO: CPF inválido! Digite exatamente 11 números.");
                continue; // Volta para o início do loop
            }

            // Verifica se já existe um usuário com o mesmo CPF
            if (usuarioRepository.buscarPorCpf(cpf).isPresent()) {
                System.out.println("ERRO: CPF já cadastrado! Use outro CPF.");
                continue;
            }

            System.out.print("Login (email ou nome de usuário): ");
            String login = scanner.nextLine();

            // Verifica se já existe um usuário com o mesmo login
            if (usuarioRepository.buscarPorLogin(login).isPresent()) {
                System.out.println("ERRO: Login já cadastrado! Escolha outro.");
                continue;
            }

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            UsuarioEntity novoUsuario = new UsuarioEntity(cpf, login, senha);
            usuarioRepository.salvar(novoUsuario);

            System.out.println("Cadastro realizado com sucesso!");
            break; // Sai do loop se o cadastro for bem sucedido
        }
    }

    public boolean autenticarUsuario(String loginOuCpf, String senha) {
        Optional<UsuarioEntity> usuarioOpt = (loginOuCpf.matches("\\d{11}") ?
                usuarioRepository.buscarPorCpf(loginOuCpf) :
                usuarioRepository.buscarPorLogin(loginOuCpf));

        if (usuarioOpt.isEmpty()) {
            System.out.println("Usuário não encontrado! Verifique suas credenciais.");
            return false;
        }

        boolean autenticado = usuarioOpt.get().getSenha().equals(senha);

        if (!autenticado) {
            System.out.println("Senha incorreta! Tente novamente.");
        }

        return autenticado;
    }

}