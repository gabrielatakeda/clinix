package org.example;

import org.example.Entity.ConsultaEntity;
import org.example.Entity.PacienteEntity;
import org.example.Repository.ConsultaRepository;
import org.example.Repository.CustomizerFactory;
import org.example.Repository.PacienteRepository;
import org.example.service.ConsultaService;
import org.example.service.PacienteService;
import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //os nomes sendo utilizados são meramente irrelevantes, devo trocar na implementação como:
        // consulta ao invez de paciente etc...

        EntityManager em = CustomizerFactory.getEntityManager();
        PacienteRepository pacienteRepository = new PacienteRepository(em);

        PacienteService pacienteService = new PacienteService();

        List<PacienteEntity> pacientes = pacienteRepository.findByFk_usuario(1L);

        for (PacienteEntity paciente : pacientes) {
            pacienteService.exibirConsulta(paciente);
        }

        // a partir daqui é a parte de consulta, acima é apenas teste

        ConsultaRepository consultaRepository = new ConsultaRepository(em);

        ConsultaService consultaService = new ConsultaService();

        List<ConsultaEntity> consultas = consultaService.findByNome();

        for (ConsultaEntity consulta : consultas){
            consultaService.exibirConsultas(consulta);
        }

    }
}