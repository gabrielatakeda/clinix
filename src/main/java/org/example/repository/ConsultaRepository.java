    package org.example.repository;

    import org.example.entity.AmostrasLabEntity;
    import org.example.entity.ConsultaEntity;

    import javax.persistence.EntityManager;
    import javax.persistence.NoResultException;
    import java.time.LocalDate;
    import java.util.List;

    public class ConsultaRepository {

        private EntityManager em;

        public ConsultaRepository(EntityManager em) {
            this.em = em;
        }

        public ConsultaEntity buscarPorId(Long ID_Consulta){
            return em.find(ConsultaEntity.class,ID_Consulta);
        }

        public List<ConsultaEntity> findAll(){
        return em.createQuery("SELECT c FROM ConsultaEntity c", ConsultaEntity.class).getResultList();
        }

        public ConsultaEntity buscarPorHorario(LocalDate data_consulta) {
            try {
                return em.createQuery(
                                "SELECT c FROM ConsultaEntity c WHERE c.data_consulta = :horario",
                                ConsultaEntity.class)
                        .setParameter("horario", data_consulta)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null; // Nenhuma consulta encontrada nesse horário
            }
        }


        public ConsultaEntity salvar(ConsultaEntity consulta){
            em.getTransaction().begin();
            consulta = em.merge(consulta);
            em.getTransaction().commit();
            return consulta;
        }

        public void atualizar (ConsultaEntity consulta){
            em.getTransaction().begin();
            em.merge(consulta);
            em.getTransaction().commit();
        }

        public void remover (ConsultaEntity consulta){
            em.getTransaction().begin();
            em.remove(em.contains(consulta)? consulta : em.merge(consulta));
            em.getTransaction().commit();
        }



    }
