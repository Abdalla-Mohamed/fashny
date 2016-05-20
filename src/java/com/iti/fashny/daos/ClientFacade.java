/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Client;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */

public class ClientFacade extends AbstractFacade<Client> {

    private static final String GET_CLIENT_TRIPS= "select j.trip  from JoinTrip j where j. = :partenerId";
    
    
     ClientFacade(EntityManager em) {
        super(Client.class,em);
    }
    
    
     public boolean validMail(String mail){
        
        boolean valid = false;
        
        List resultList = getEntityManager().createNamedQuery("Client.findByEmail").setParameter("email", mail).getResultList();
        
        
        if(resultList.size()>1)
            valid=true;
        
        return valid;
    }
    
       
     
}
