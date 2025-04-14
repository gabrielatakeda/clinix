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


    public PacienteEntity exibirConsulta(PacienteEntity pacienteEntity){

        System.out.println("\nid: " + pacienteEntity.getId() +
                "\nnome: " + pacienteEntity.getNome() +
                "\nidade: " + pacienteEntity.getIdade() +
                "\ndata: " + pacienteEntity.getData() +
                "\nmedico: " + pacienteEntity.getMedico());

        return pacienteEntity;
    }

}

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
}

