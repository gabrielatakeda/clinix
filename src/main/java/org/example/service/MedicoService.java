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
        //Verifica se já existe um médico com o mesmo CRM (ignorando maiúsculas/minúsculas)
        List<MedicoEntity> lista = buscarTodos();
        for(MedicoEntity m : lista){
            if(m.getCrm().equalsIgnoreCase(medico.getCrm())){
                //Caso o CRM já esteja cadastrado, informa e retorna o médico existente
                System.out.println("CRM já cadastrado! Médico existente: " + m.getNomeCompleto());
                return m;
            }
        }

        medicoRepository.salvar(medico); //Se não encontrar duplicata, salva o novo médico

        //Verifica novamente após salvar
        lista = buscarTodos();
        for(MedicoEntity m : lista){
            if(m.getCrm().equalsIgnoreCase(medico.getCrm())){
                return m; //Retorna o médico recém-salvo
            }
        }

        return new MedicoEntity(); //Caso algo dê errado, retorna um objeto vazio
    }

    public List<MedicoEntity> buscarTodos(){ //Retorna todos os médicos do banco de dados
        return medicoRepository.buscarTodos();
    }

    public MedicoEntity buscarPorId(Long id){ //Busca um médico pelo id
        return medicoRepository.buscarPorId(id);
    }

    public void removerMedico(Long id){ //Remove um médico do banco de dados, se ele existir
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if(medico != null){ //Verifica se o médico existe
            medicoRepository.remover(medico); //Remove o médico
            System.out.println("Médico removido com sucesso!");
        }else{
            System.out.println("Médico com ID " + id + " não encontrado."); //Se o médico não for encontrado, informa ao usuário
        }
    }

    public void atualizarMedico(Long id, String novoNome){ //Atualiza o nome completo de um médico, dado seu id
        MedicoEntity medico = medicoRepository.buscarPorId(id);
        if(medico != null){ //Verifica se encontrou o médico
            medico.setNomeCompleto(novoNome); //Define o novo nome
            medicoRepository.atualizar(medico); //Atualiza no banco
            System.out.println("Médico atualizado com sucesso!");
        }else{ //Caso o médico não seja encontrado
            System.out.println("Médico com ID " + id + " não encontrado.");
        }
    }

    public List<MedicoEntity> listarMedicos(){ //Lista todos os médicos
        return medicoRepository.buscarTodos();
    }

    public MedicoEntity buscarPorCrm(String crm){
        return medicoRepository.buscarPorCrm(crm);
    }

}

