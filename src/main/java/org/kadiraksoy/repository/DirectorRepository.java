package org.kadiraksoy.repository;



import com.mysql.cj.log.Log;
import org.hibernate.Session;
import org.kadiraksoy.model.Director;
import org.kadiraksoy.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DirectorRepository {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    private Session session = null;
    public void save(Director director){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(director);
            session.getTransaction().commit();
            LOG.info("Director successfully saved.");
        }catch (Exception e){
            session.getTransaction().rollback();
            LOG.info(e.getMessage());

        }
    }
    public void getAll(){
        List<Object[]> list = null;

        try{
            EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
            String query = "select d.directorName, a.awardName, f.filmName  from director as d\n" +
                    "inner join award as a\n" +
                    "on d.award_id = a.id\n" +
                    "inner join director_film as df\n" +
                    "on d.id = df.Director_id\n" +
                    "inner join film as f\n" +
                    "on df.films_id = f.id\n" +
                    "where award_id is not null";
            list = entityManager.createNativeQuery(query).getResultList();
            for(Object[] item:list){
                System.out.println(
                        "Director name: " + item[0] + ", " +
                                "Award name: " + item[1] + ", " +
                                "Film name: " + item[2]
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void deleteById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Director director = session.load(Director.class,id);
            session.delete(director);
            session.getTransaction().commit();
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
    }

    public void update(Director director){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(director);
            session.getTransaction().commit();
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
    }
}