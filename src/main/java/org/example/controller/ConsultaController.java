package org.example.controller;

import org.example.model.entity.Consulta;

import org.example.DAO.ConsultaRepository;
import org.example.DAO.CustomizerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;


public class ConsultaController {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);
    MedicoController medicoController = new MedicoController();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void exibirConsultas() {
        List<Consulta> consultas = consultaRepository.listarTodos();

        if (consultas.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (Consulta c : consultas) {
                System.out.println("==Paciente==\n" + c.getPaciente().getNome());
                System.out.println("CPF: " + c.getPaciente().getCpf());
                System.out.println("==Médico==\n" + c.getMedico().getNomeCompleto());
                System.out.println("Especialidade: " + c.getMedico().getEspecialidade());
                System.out.println("\nData Consulta: " + c.getLocalDateTime().format(formatter));
                System.out.println("Status: " + c.getStatus());
                System.out.println("------------------------------");
            }
        }
    }

    public Consulta salvarConsulta(Consulta consulta) {
        List<Consulta> todasConsultas = consultaRepository.listarTodos();

        for (Consulta c : todasConsultas) {
            if (c.getLocalDateTime().equals(consulta.getLocalDateTime())) {
                System.out.println("\n\tConsulta já existente nessa data!\n\tPor favor, escolha outra data.");
                return c; // retorna a consulta existente
            }
        }
        consultaRepository.salvar(consulta);
        return consulta;
    }

    public List<Consulta> listarConsultas() {
        return consultaRepository.listarTodos();
    }

    public void removerPorData(String data) {
        try {
            LocalDateTime dataConsulta = LocalDateTime.parse(data, formatter);

            Consulta consulta = consultaRepository.buscarConsultaPorData(dataConsulta);

            if (consulta != null) {
                consultaRepository.remover(consulta);
                System.out.println("Consulta removida com sucesso.");
            } else {
                System.out.println("Consulta na data: " + data + " não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy HH:mm.");
        }
    }

}