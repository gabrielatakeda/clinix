package org.example.service;

import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;
import org.example.entity.EnderecoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteService {

    EntityManager em = CustomizerFactory.getEntityManager();
    PacienteRepository pacienteRepository = new PacienteRepository(em);

    public PacienteEntity salvarPaciente(PacienteEntity paciente, List<EnderecoEntity> endereços){
        PacienteEntity existente = pacienteRepository.buscarPorCpf(paciente.getCpf());
        if (existente != null) {
            System.out.println("CPF já cadastrado! Paciente existente: " + existente.getNomeCompleto());
            return existente;
        }

        for(EnderecoEntity x : endereços){
            x.setPaciente(paciente);
            paciente.getEnderecos().add(x);
        }
        pacienteRepository.salvar(paciente);
        var lista = pacienteRepository.buscarPorNomeInicial(paciente.getNomeCompleto());
        for(PacienteEntity x : lista){
            if(x.getCpf().equalsIgnoreCase(paciente.getCpf())){
                return x;
            }
        }
        return new PacienteEntity();
    }

    public List<PacienteEntity> buscarTodos() {
        return pacienteRepository.buscarTodos();
    }

    public PacienteEntity buscarPorCpf(String cpf) {
        return pacienteRepository.buscarPorCpf(cpf);
    }

    public void atualizarPaciente(Long id, String novoNome) {
        PacienteEntity paciente = pacienteRepository.buscarPorId(id);
        if (paciente != null) {
            paciente.setNomeCompleto(novoNome);
            pacienteRepository.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso!");
        } else {
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    public void removerPaciente(Long id) {
        PacienteEntity paciente = pacienteRepository.buscarPorId(id);
        if (paciente != null) {
            pacienteRepository.remover(paciente);
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    public List<PacienteEntity> listarPacientes() {
        return pacienteRepository.buscarTodos();
    }
}