package org.kadiraksoy.repository;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.kadiraksoy.model.Actor;
import org.kadiraksoy.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ActorRepository {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private Session session = null;

    public void save(Actor actor){
        try {
            //Bu, Hibernate tarafından sağlanan bir oturum nesnesidir
            // ve veritabanı işlemlerini gerçekleştirmek için kullanılır.
            session = HibernateUtil.getSessionFactory().openSession();
            //çağrısı ile bir veritabanı işlemi başlatılır.
            session.beginTransaction();
            // çağrısı ile Actor nesnesi veritabanına kaydedilir.
            session.save(actor);
            //çağrısı ile veritabanı işlemi onaylanır (commit edilir).
            session.getTransaction().commit();
            LOG.info("Actor successfully saved");

        }catch (Exception e){
            //çağrısı ile yapılan değişiklikler geri alınır (rollback).
            // Ardından, hata mesajı LOG.info(e.getMessage()) ile kaydedilir.
            session.getTransaction().rollback();
            LOG.info(e.getMessage());
        }
    }

    public void getAll(){
        //List objesi oluşturuldu.
        List<Object[]> list = null;
        try{
            // çağrısı ile HibernateUtil sınıfındaki getSessionFactory() metodu çağrılır
            // ve bir EntityManager nesnesi alınır.
            // Bu, JPA (Java Persistence API) tarafından sağlanan bir yönetici nesnesidir
            // ve veritabanı sorgularını yönetmek için kullanılır.
            EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
            //veritabanından çekilecek verilerin sorgu ifadesini içerir
            String query = "select ac.actorName, f.filmName, aw.awardName  from actor_award as aa\n" +
                    "inner join actor as ac\n" +
                    "on aa.Actor_id = ac.id\n" +
                    "inner join award as aw\n" +
                    "on aa.awards_id = aw.id\n" +
                    "inner join actor_film af\n" +
                    "on aa.Actor_id = af.Actor_id \n" +
                    "inner join film f\n" +
                    "on af.films_id = f.id";
            // çağrısı ile veritabanında belirtilen sorgu çalıştırılır
            // ve sonuç bir List<Object[]> olarak alınır
            list = entityManager.createNativeQuery(query).getResultList();
            for(Object[] item:list){
                System.out.println(
                        "Actor Name: " + item[0] + ", " +
                                "Film Name: " + item[1] + ", " +
                                "Award Name: " + item[2]
                );
            }
        }catch (Exception e){
            LOG.info(e.getMessage());

        }
    }

    public void deleteById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            //çağrısı ile belirtilen id değerine sahip Actor nesnesi yüklenir.
            // Bu, veritabanından Actor nesnesini getirir ancak veritabanı sorgusu henüz gerçekleştirilmez.
            Actor actor = session.load(Actor.class,id);
            // çağrısı ile yüklenen Actor nesnesi veritabanından silinir.
            session.delete(actor);
            //çağrısı ile veritabanı işlemi onaylanır (commit edilir) ve değişiklikler kalıcı hale getirilir.
            session.getTransaction().commit();
        }catch (Exception e){
            if(session.getTransaction() != null){
                //çağrısı ile yapılan değişiklikler geri alınır (rollback).
                session.getTransaction().rollback();
            }
        }
    }

    public void update(Actor actor){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // çağrısı ile actor nesnesi güncellenir.
            session.update(actor);
            session.getTransaction().commit();
        }catch (Exception e){
            if(session.getTransaction() != null){
                //çağrısı ile yapılan değişiklikler geri alınır (rollback).
                session.getTransaction().rollback();
            }
        }
    }
}
