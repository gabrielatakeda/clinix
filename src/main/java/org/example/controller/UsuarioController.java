package org.example.controller;

import org.example.DAO.CustomizerFactory;
import org.example.DAO.UsuarioRepository;
import org.example.model.entity.Usuario;
import org.example.model.enums.TypeUser;

import javax.persistence.EntityManager;
import java.util.Optional;

public class UsuarioController {

    private final EntityManager em = CustomizerFactory.getEntityManager();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository(em);

    public boolean cadastrarUsuario(TypeUser tipo, String nomeCompleto, String email, String identificador, String senha) {
        switch (tipo) {
            case PACIENTE:
                if (!identificador.matches("\\d{11}")) return false;
                if (usuarioRepository.buscarPorCpf(identificador).isPresent()) return false;
                break;
            case MEDICO:
                if (identificador.trim().isEmpty()) return false;
                if (usuarioRepository.buscarPorCrm(identificador).isPresent()) return false;
                break;
            case LAB:
            case RECEPCAO:
            case ADMIN:
                if (identificador.trim().isEmpty()) return false;
                if (usuarioRepository.buscarPorUsuario(identificador).isPresent()) return false;
                break;
            default:
                return false;
        }

        Usuario u = new Usuario();
        u.setTipo(tipo);
        u.setNomeCompleto(nomeCompleto);
        u.setEmail(email);
        u.setSenha(senha);

        switch (tipo) {
            case PACIENTE:
                u.setCpf(identificador);
                u.setLogin(email);
                break;
            case MEDICO:
                u.setCrm(identificador);
                u.setLogin(identificador);
                break;
            default: // LAB, RECEPCAO, ADMIN
                u.setUsuario(identificador);
                u.setLogin(identificador);
                break;
        }

        try {
            usuarioRepository.salvar(u);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
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
                return false;
        }

        if (!usuarioOpt.isPresent()) {
            return false;
        }

        Usuario u = usuarioOpt.get();
        return u.getSenha().equals(senha);
    }
}