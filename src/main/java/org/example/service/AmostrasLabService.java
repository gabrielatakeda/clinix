package org.example.service;

import org.example.entity.AmostrasLabEntity;
import org.example.repository.AmostrasLabRepository;
import org.example.repository.CustomizerFactory;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AmostrasLabService {

    EntityManager em = CustomizerFactory.getEntityManager();
    AmostrasLabRepository amostraRepository = new AmostrasLabRepository(em);
    // Adicione o formatter na parte superior da classe para usar em todo o código
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public AmostrasLabEntity salvarAmostra(AmostrasLabEntity amostra) {
        List<AmostrasLabEntity> todasAmostras = amostraRepository.findAll();

        for (AmostrasLabEntity c : todasAmostras) {
            if (c.getDataColeta().equals(amostra.getDataColeta())) {
                System.out.println("\n\tConsulta já existente nessa data! ");
                return c; // retorna a consulta existente
            }
        }
        return amostraRepository.salvar(amostra);
    }

    public List<AmostrasLabEntity> listarAmostra() {
        return amostraRepository.findAll();
    }

    public void exibirAmostras() {
        List<AmostrasLabEntity> amostras = amostraRepository.findAll();

        if (amostras.isEmpty()) {
            System.out.println("\nNenhuma consulta encontrada.");
        } else {
            for (AmostrasLabEntity c : amostras) {
                System.out.println("\nID: " + c.getId_amostralab());
                System.out.println("ID Consulta: " + c.getConsulta().getID_Consulta());
                System.out.println("Tipo do Exame: " + c.getTipoExame());
                System.out.println("Data da Coleta: " + c.getDataColeta().format(formatter));
                System.out.println("Resultado: " + c.getResultado());
                System.out.println("------------------------------");
            }
        }
    }

    public void removerPorId(Long id) {
        AmostrasLabEntity consulta = amostraRepository.buscarPorId(id);
        if (consulta != null) {
            amostraRepository.remover(consulta);
            System.out.println("Consulta removida com sucesso.");
        } else {
            System.out.println("Consulta com ID " + id + " não encontrada.");
        }
    }


}
