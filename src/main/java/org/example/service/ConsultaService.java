package org.example.service;

import org.example.entity.ConsultaEntity;

import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;
import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;

import org.example.entity.PacienteEntity;
import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);

    // Adicione o formatter na parte superior da classe para usar em todo o código
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ConsultaEntity buscarPorId(Long id){
        return consultaRepository.buscarPorId(id);
    }

    public List<ConsultaEntity> findByNome(String nome){
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

    public void exibirConsultas() {
        List<ConsultaEntity> consultas = consultaRepository.findAll();

        if (consultas.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (ConsultaEntity c : consultas) {
                System.out.print("\nID: " + c.getID_Consulta());
                System.out.println("\nData: " + c.getData_consulta().format(formatter));
                System.out.println("Motivo: " + c.getMotivo());
                System.out.println("Status: " + c.getStatus());
                System.out.println("Prescrição: " + c.getPrescricao());
                System.out.println("Observações: " + c.getObservacoes());
                System.out.println("------------------------------");
            }
        }
    }


    public ConsultaEntity salvarConsulta(ConsultaEntity consulta) {
        List<ConsultaEntity> todasConsultas = consultaRepository.findAll();

        for (ConsultaEntity c : todasConsultas) {
            if (c.getData_consulta().equals(consulta.getData_consulta())) {
                System.out.println("\n\tConsulta já existente nessa data! Motivo: " + c.getMotivo());
                return c; // retorna a consulta existente
            }
        }
        return consultaRepository.salvar(consulta);
    }

    public List<ConsultaEntity> listarConsultas() {
        return consultaRepository.findAll();
    }

    public void atualizarMotivoConsulta(Long id, String novoMotivo){
        ConsultaEntity consulta = consultaRepository.buscarPorId(id);
        if(consulta != null){
            consulta.setMotivo(novoMotivo);
            consultaRepository.atualizar(consulta);
            System.out.println("Motivo da consulta atualizado com sucesso!");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }

    public void removerPorId(Long id) {
        ConsultaEntity consulta = consultaRepository.buscarPorId(id);
        if (consulta != null) {
            consultaRepository.remover(consulta);
            System.out.println("Consulta removida com sucesso.");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }


    public void printMenu(Scanner sc, ConsultaService consultaService){

    }


}


    public List<ConsultaEntity> findByNome(String nome){
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public ConsultaEntity exibirConsultas(ConsultaEntity consultaEntity){

        System.out.println("\nid: " + consultaEntity.getID_Consulta() +
                "\nnome: " + consultaEntity.getNome() +
                "\ndata: " + consultaEntity.getLocalDateTime() +
                "\nmotivo: " + consultaEntity.getMotivo() +
                "\nobservações: " + consultaEntity.getObservacoes() +
                "\nprescrição: " + consultaEntity.getPrescricao());

        return consultaEntity;
    }

    public void printMenu(Scanner sc, ConsultaService consultaService){

    }

    public void printMenu(Scanner sc, PacienteService pacienteService, MedicoService medicoService){

    }

}

