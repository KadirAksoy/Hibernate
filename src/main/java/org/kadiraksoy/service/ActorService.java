package org.kadiraksoy.service;

import org.kadiraksoy.model.*;
import org.kadiraksoy.repository.ActorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Service
public class ActorService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static ActorRepository actorRepository;



    public void save(){
        try{
            Film film = new Film("java");
            Award award = new Award("Best choice award");

            Actor actor1 = new Actor("kadir", Arrays.asList(film),Arrays.asList(award));
            actorRepository.save(actor1);
        }catch (Exception e){
            LOG.info(e.getMessage());
        }
    }
    public void getAll(){
        try{
            actorRepository.getAll();
        }catch (Exception e){
            LOG.info(e.getMessage());
        }
    }

    public void delete(int id){
        try {
            actorRepository.deleteById(id);
        }catch (Exception e){
            LOG.info(e.getMessage());
        }
    }

    public void update(){
        try {
            Director director17 = new Director("Director17");
            Award award17 = new Award("Award17");
            Film film17 = new Film("17.FİLM");
            Actor actor17 = new Actor(4,"Trakya",Arrays.asList(film17),Arrays.asList(award17));
            actorRepository.update(actor17);

        }catch (Exception e){
            LOG.info(e.getMessage());
        }
    }

}

