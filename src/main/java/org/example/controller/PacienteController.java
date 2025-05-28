package org.example.controller;

import org.example.model.entity.Paciente;
import org.example.DAO.CustomizerFactory;
import org.example.DAO.PacienteRepository;
import org.example.model.entity.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteController {

    EntityManager em = CustomizerFactory.getEntityManager();
    PacienteRepository pacienteRepository = new PacienteRepository(em);

    public Paciente salvarPaciente(Paciente paciente, List<Endereco> endereços) {
        Paciente existente = pacienteRepository.buscarPorCpf(paciente.getCpf());
        if (existente != null) {
            System.out.println("CPF já cadastrado! Paciente existente: " + existente.getNome());
            return existente;
        }

        for (Endereco x : endereços) {
            x.setPaciente(paciente);
            paciente.getEnderecos().add(x);
        }
        pacienteRepository.salvar(paciente);
        var lista = pacienteRepository.buscarPorNomeInicial(paciente.getNome());
        for (Paciente x : lista) {
            if (x.getCpf().equalsIgnoreCase(paciente.getCpf())) {
                return x;
            }
        }
        return new Paciente();
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.buscarTodos();
    }

    public Paciente buscarPorCpf(String cpf) {
        return pacienteRepository.buscarPorCpf(cpf);
    }

    public void atualizarPacienteCPF(String cpf, String novoNome) {
        Paciente paciente = pacienteRepository.buscarPorCpf(cpf);
        if (paciente != null) {
            paciente.setNomeCompleto(novoNome);
            pacienteRepository.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso!");
        } else {
            System.out.println("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    public void removerPacienteCPF(String cpf) {
        Paciente paciente = pacienteRepository.buscarPorCpf(cpf);
        if (paciente != null) {
            pacienteRepository.remover(paciente);
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.buscarTodos();
    }


//    public List<Paciente> buscarPorNomeInicial(String prefixo){
//        return em.createQuery("SELECT p FROM paciente p WHERE p.nomeCompleto LIKE :prefixo", Paciente.class)
//                .setParameter("prefixo", prefixo + "%")
//                .getResultList();
//    }

}
