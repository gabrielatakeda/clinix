package org.example;

import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        PacienteRepository pacienteRepository = new PacienteRepository(em);

//        PacienteEntity paciente = pacienteRepository.buscarPorId(3);
        List<PacienteEntity> pacientes = pacienteRepository.buscarPorCpf("129");

//        System.out.println(paciente.getID());
//        System.out.println(paciente.getNomeCompleto());
        //System.out.println(paciente.getCPF());
//        System.out.println(paciente.getDataNascimento());
//        System.out.println(paciente.getEndereco());
//        System.out.println(paciente.getTelefone());

        for (PacienteEntity paciente : pacientes) {
            System.out.println(paciente.getCpf());
        }

    }
}
