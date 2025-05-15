package org.example.service;

import org.example.entity.ConsultaEntity;
import org.example.repository.ConsultaRepository;
import java.util.List;

public class RelatorioPacienteMedicoService extends RelatorioService {

    private String CRM;

    public RelatorioPacienteMedicoService(String CRM, ConsultaRepository consultaRepository) {
        this.CRM = CRM;
        this.consultaRepository = new ConsultaRepository();
    }

    @Override
    public void gerarRelatorio() {
        System.out.println("Gerando relatorio de PACIENTES POR MEDICOS pelo CRM: " + CRM);

        List<ConsultaEntity> pacientePorMedico = consultaRepository.buscarPacientesPorMedico(CRM);

        if(pacientePorMedico.isEmpty()){
            System.out.println("Nenhum paciente encontrado para este medico.");
        } else{
            for(ConsultaEntity consulta : pacientePorMedico) {
                System.out.println("Paciente: " + consulta.getPaciente().getNomeCompleto());
                System.out.println("CPF: " + consulta.getPaciente().getCpf());
                System.out.println("Data da consulta: " + consulta.getLocalDateTime());
                System.out.println("------------------------");
            }
        }
    }
}
