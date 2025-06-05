package org.example.controller;

import org.example.model.entity.Medico;
import org.example.DAO.CustomizerFactory;
import org.example.DAO.MedicoRepository;
import javax.persistence.EntityManager;
import java.util.List;

public class MedicoController {

    private final EntityManager em = CustomizerFactory.getEntityManager();
    private final MedicoRepository medicoRepository = new MedicoRepository(em);

    public Medico salvarMedico(Medico medico) {
        //Verifica se já existe um médico com o mesmo CRM
        List<Medico> lista = buscarTodos();
        for (Medico m : lista) {
            if (m.getCrm().equalsIgnoreCase(medico.getCrm())) {
                System.out.println("CRM já cadastrado! Médico existente: " + m.getNomeCompleto());
                return m;
            }
        }
        medicoRepository.salvar(medico);
        lista = buscarTodos();
        for (Medico m : lista) {
            if (m.getCrm().equalsIgnoreCase(medico.getCrm())) {
                return m;
            }
        }
        return new Medico(); // fallback
    }

    public List<Medico> buscarTodos() {
        return medicoRepository.buscarTodos();
    }

    public boolean removerMedicoCRM(String crm) {
        Medico medico = medicoRepository.buscarPorCrm(crm);
        if (medico != null) {
            medicoRepository.remover(medico);
            System.out.println("Médico removido com sucesso!");
            return true;
        } else {
            System.out.println("Médico com CRM " + crm + " não encontrado.");
            return false;
        }
    }

    public void atualizarMedicoCRM(String crm, String novoNome) {
        Medico medico = medicoRepository.buscarPorCrm(crm);
        if (medico != null) {
            medico.setNomeCompleto(novoNome);
            medicoRepository.atualizar(medico);
            System.out.println("Médico atualizado com sucesso!");
        } else {
            System.out.println("Médico com CRM " + crm + " não encontrado.");
        }
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.buscarTodos();
    }

}