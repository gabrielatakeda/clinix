package org.example.controller;

import org.example.model.entity.Consulta;
import org.example.DAO.ConsultaRepository;

import java.util.List;

public class RelatorioPacienteMedicoController {

    public void gerarRelatorio(String CRM) {
        System.out.println("Gerando relatorio de PACIENTES POR MEDICOS pelo CRM: " + CRM);

        ConsultaRepository consultaRepository = new ConsultaRepository();
        List<Consulta> pacientePorMedico = consultaRepository.buscarPacientesPorMedico(CRM);

        if(pacientePorMedico.isEmpty()){
            System.out.println("Nenhum paciente encontrado para este medico.");
        } else{
            for(Consulta consulta : pacientePorMedico) {
                System.out.println("Paciente: " + consulta.getPaciente().getNome());
                System.out.println("CPF: " + consulta.getPaciente().getCpf());
                System.out.println("Data da consulta: " + consulta.getLocalDateTime());
                System.out.println("------------------------");
            }
        }
    }
}




