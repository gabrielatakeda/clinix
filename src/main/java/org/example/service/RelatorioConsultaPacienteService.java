package org.example.service;

import org.example.entity.ConsultaEntity;
import org.example.repository.ConsultaRepository;

import java.util.List;

public class RelatorioConsultaPacienteService extends RelatorioService{

    private String cpf;
    private ConsultaRepository consultaRepository;

    public RelatorioConsultaPacienteService(String cpf, ConsultaRepository consultaRepository) {
        this.cpf = cpf;
        this.consultaRepository = consultaRepository;
    }


    @Override
    public void gerarRelatorio() {
        System.out.println("Gerando relatorio de CONSULTAS POR PACIENTE pelo CPF: " + cpf);

        List<ConsultaEntity> consultasPorPaciente = consultaRepository.buscarConsultasPorPaciente(cpf);

        if(consultasPorPaciente.isEmpty()){
            System.out.println("Nenhuma consulta encontrada para este paciente.");
        }else{
            for (ConsultaEntity consulta : consultasPorPaciente) {
                System.out.println("Data/Hora: " + consulta.getDataHora());
                System.out.println("Medico: " + consulta.getMedico());
                System.out.println("Motivo: " + consulta.getMotivo());
                System.out.println("Status: " + consulta.getStatus());
                System.out.println("Médico: " + consulta.getMedico().getNome());
                System.out.println("------------------------");
        }
    }
}
