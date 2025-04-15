package org.example.service;

import org.example.entity.MedicoEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.MedicoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class MedicoService{

    private final EntityManager em = CustomizerFactory.getEntityManager();
    private final MedicoRepository medicoRepository = new MedicoRepository(em);

    public MedicoEntity salvarMedico(MedicoEntity medico){
        //Verifica se já existe um médico com o mesmo CRM
        List<MedicoEntity> lista = buscarTodos();
        for(MedicoEntity m : lista){
            if(m.getCrm().equalsIgnoreCase(medico.getCrm())){
                System.out.println("CRM já cadastrado! Médico existente: " + m.getNomeCompleto());
                return m;
            }
        }

        medicoRepository.salvar(medico);

        //Verifica novamente após salvar
        lista = buscarTodos();
        for(MedicoEntity m : lista){
            if(m.getCrm().equalsIgnoreCase(medico.getCrm())){
                return m;
            }
        }

        return new MedicoEntity(); // fallback
    }

    public List<MedicoEntity> buscarTodos() {
        return medicoRepository.buscarTodos();
    }

    public MedicoEntity buscarPorId(Long id) {
        return medicoRepository.buscarPorId(id);
    }

    public void removerMedico(Long id){
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if(medico != null){
            medicoRepository.remover(medico);
            System.out.println("Médico removido com sucesso!");
        }else{
            System.out.println("Médico com ID " + id + " não encontrado.");
        }
    }

    public void atualizarMedico(Long id, String novoNome){
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if(medico != null){
            medico.setNomeCompleto(novoNome);
            medicoRepository.atualizar(medico);
            System.out.println("Médico atualizado com sucesso!");
        }else{
            System.out.println("Médico com ID " + id + " não encontrado.");
        }
    }

    public List<MedicoEntity> listarMedicos() {
        return medicoRepository.buscarTodos();
    }
}


