/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */

public class PartnTypeFacade extends AbstractFacade<PartnType> {

     PartnTypeFacade(EntityManager em) {
        super(PartnType.class,em);
    }
     
     public List<Partener> getAllpartenerOfOneCat(int id)
     {
       
        List<Partener> all2 = new ArrayList<>();
        
        all2 = getEntityManager().createQuery("SELECT p FROM Partener p WHERE p.type.id = :id").setParameter("id", id).getResultList();
        
        
        return all2;
     }
    
}
