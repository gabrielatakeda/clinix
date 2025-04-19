package org.example.service;

import org.example.entity.PacienteEntity;
import org.example.repository.CustomizerFactory;
import org.example.repository.PacienteRepository;
import org.example.entity.EnderecoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacienteService{

    EntityManager em = CustomizerFactory.getEntityManager();
    PacienteRepository pacienteRepository = new PacienteRepository(em);

    //Metodo responsável por salvar um novo paciente junto com seus endereços
    public PacienteEntity salvarPaciente(PacienteEntity paciente, List<EnderecoEntity> endereços){
        //Verifica se já existe um paciente com o mesmo CPF
        PacienteEntity existente = pacienteRepository.buscarPorCpf(paciente.getCpf());
        if(existente != null){
            //Se já existe, imprime mensagem e retorna o paciente já existente
            System.out.println("CPF já cadastrado! Paciente existente: " + existente.getNomeCompleto());
            return existente;
        }

        //Associa os endereços ao paciente antes de salvar
        for(EnderecoEntity x : endereços){
            x.setPaciente(paciente); //Define a relação no endereço
            paciente.getEnderecos().add(x); //Adiciona o endereço à lista do paciente
        }
        pacienteRepository.salvar(paciente); //Salva o paciente (e os endereços por cascata)

        //Busca pacientes com nome semelhante para tentar retornar o recém-criado
        var lista = pacienteRepository.buscarPorNomeInicial(paciente.getNomeCompleto());
        for(PacienteEntity x : lista){
            if(x.getCpf().equalsIgnoreCase(paciente.getCpf())){
                return x; //Retorna o paciente recém-cadastrado
            }
        }
        return new PacienteEntity(); //Retorna um paciente vazio se não encontrou
    }

    public List<PacienteEntity> buscarTodos(){ //Retorna todos os pacientes cadastrados
        return pacienteRepository.buscarTodos();
    }

    public PacienteEntity buscarPorCpf(String cpf){ //Busca um paciente com base no CPF
        return pacienteRepository.buscarPorCpf(cpf);
    }

    public void atualizarPaciente(Long id, String novoNome){ //Atualiza o nome de um paciente com base no id
        PacienteEntity paciente = pacienteRepository.buscarPorId(id);
        if(paciente != null){
            //Se encontrado, altera o nome e atualiza no banco
            paciente.setNomeCompleto(novoNome);
            pacienteRepository.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso!");
        }else{ //Caso contrário, informa que o paciente não foi encontrado
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    public void removerPaciente(Long id){ //Remove um paciente com base no id
        PacienteEntity paciente = pacienteRepository.buscarPorId(id);
        if(paciente != null){ //Se encontrado, remove
            pacienteRepository.remover(paciente);
            System.out.println("Paciente removido com sucesso!");
        }else{ //Caso contrário, informa que o paciente não foi encontrado
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    public List<PacienteEntity> listarPacientes(){ //Lista todos os pacientes
        return pacienteRepository.buscarTodos();
    }
}