package org.example.service;

import org.example.entity.MedicoEntity;
import org.example.repository.MedicoRepository;

public class MedicoService {


    private MedicoRepository medicoRepository = new Medicorepository(CustomizerFactory.getEntityManager());

    public MedicoEntity salvarNovomedico(MedicoEntity novoMedico){
        var isMedicoExist = medicoRepository.findByCrm(nomoMedico.getCrm());
        if(isMedicoExist.getId !=null ){
            throw new Exception();
        }
        novoMedico.setId(null);
         return medicoRepository.salvarNovoMedico(novoMedico);
    }

    public MedicoEntity atualizarMedico(MedicoEntity updateMedico){
        var isMedicoExist = medicoRepository.findByCrm(updateMedico.getCrm());
        if(isMedicoExist == null){
            return new Exception();
        }

        //validações
        if(updateMedico.getNome != null){
            isMedicoExist.setNome(updateMedico.getNome);

        }

        return medicoRepository.salvarNovoMedico(isMedicoExist);
    }


}
