package org.example.controller;

import org.example.model.entity.Consulta;
import org.example.DAO.ConsultaRepository;

import java.util.List;

public class RelatorioConsultaPacienteController extends RelatorioController {

    private String cpf;

    public RelatorioConsultaPacienteController(String cpf) {
        this.cpf = cpf;
    }

    public RelatorioConsultaPacienteController(){}

    @Override
    public void gerarRelatorio() {
        System.out.println("Gerando relatorio de CONSULTAS POR PACIENTE pelo CPF: " + cpf);

        ConsultaRepository consultaRepository = new ConsultaRepository();
        List<Consulta> consultasPorPaciente = consultaRepository.buscarConsultasPorPaciente(cpf);

        if(consultasPorPaciente.isEmpty()){
            System.out.println("Nenhuma consulta encontrada para este paciente.");
        }else{
            for (Consulta consulta : consultasPorPaciente) {
                System.out.println("Data/Hora: " + consulta.getLocalDateTime());
                System.out.println("Medico: " + consulta.getMedico().getNomeCompleto());
                System.out.println("Especialidade:" + consulta.getMedico().getEspecialidade());
                System.out.println("Status: " + consulta.getStatus());
                System.out.println("------------------------");
            }
        }
    }
}
