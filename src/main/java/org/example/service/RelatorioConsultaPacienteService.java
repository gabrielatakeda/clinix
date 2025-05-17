package org.example.service;

import org.example.entity.ConsultaEntity;
import org.example.Repository.ConsultaRepository;

import java.util.List;

public class RelatorioConsultaPacienteService extends RelatorioService{

    private String cpf;

    public RelatorioConsultaPacienteService(String cpf, ConsultaRepository consultaRepository) {
        this.cpf = cpf;
        this.consultaRepository = consultaRepository;
    }

    public RelatorioConsultaPacienteService(){}

    @Override
    public void gerarRelatorio() {
        System.out.println("Gerando relatorio de CONSULTAS POR PACIENTE pelo CPF: " + cpf);

        List<ConsultaEntity> consultasPorPaciente = consultaRepository.buscarConsultasPorPaciente(cpf);

        if(consultasPorPaciente.isEmpty()){
            System.out.println("Nenhuma consulta encontrada para este paciente.");
        }else{
            for (ConsultaEntity consulta : consultasPorPaciente) {
                System.out.println("Data/Hora: " + consulta.getLocalDateTime());
                System.out.println("Medico: " + consulta.getMedico().getNomeCompleto());
                System.out.println("Especialidade:" + consulta.getMedico().getEspecialidade());
                System.out.println("Status: " + consulta.getStatus());
                System.out.println("------------------------");
            }
        }
    }
}
