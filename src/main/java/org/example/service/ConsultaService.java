package org.example.service;

import org.example.entity.ConsultaEntity;

import org.example.repository.ConsultaRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ConsultaService {

    EntityManager em = CustomizerFactory.getEntityManager();
    ConsultaRepository consultaRepository = new ConsultaRepository(em);

    public List<ConsultaEntity> findByNome(String nome){
        return em.createQuery("SELECT p FROM ConsultaEntity p WHERE p.nome = :nome", ConsultaEntity.class)
                .setParameter("nome", nome + "%")
                .getResultList();
    }

    public ConsultaEntity exibirConsultas(ConsultaEntity consultaEntity){

        System.out.println("\nid: " + consultaEntity.getID_Consulta() +
                "\ndata: " + consultaEntity.getData_consulta() +
                "\nmotivo: " + consultaEntity.getMotivo() +
                "\nobservações: " + consultaEntity.getObservacoes() +
                "\nprescrição: " + consultaEntity.getPrescricao());

        return consultaEntity;
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


    public void printMenu(Scanner sc, ConsultaService consultaService){

    }


}