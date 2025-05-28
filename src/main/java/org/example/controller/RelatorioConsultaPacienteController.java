package org.example.controller;

import org.example.model.entity.Consulta;
import org.example.DAO.ConsultaRepository;

import java.util.List;

public class RelatorioConsultaPacienteController{

    public void gerarRelatorio(String cpf) {
        System.out.println("Gerando relatorio de CONSULTAS POR PACIENTE pelo CPF: " + cpf);

        ConsultaRepository consultaRepository = new ConsultaRepository();
        List<Consulta> consultasPorPaciente = consultaRepository.buscarConsultasPorPaciente(cpf);

        System.out.println("teste 01");
        if(consultasPorPaciente.isEmpty()){
            System.out.println("Nenhuma consulta encontrada para este paciente.");
        }else{
            for (Consulta consulta : consultasPorPaciente) {
                System.out.println("Consulta :");
                System.out.println("Data/Hora: " + consulta.getLocalDateTime());
                System.out.println("Medico: " + consulta.getMedico().getNomeCompleto());
                System.out.println("Especialidade:" + consulta.getMedico().getEspecialidade());
                System.out.println("Status: " + consulta.getStatus());
                System.out.println("------------------------");
            }
        }
    }
}
