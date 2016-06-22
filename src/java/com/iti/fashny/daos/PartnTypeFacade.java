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
import javax.persistence.Query;

/**
 *
 * @author Hosam
 */

public class PartnTypeFacade extends AbstractFacade<PartnType> {

    
    private static final String HQL_HOTELS_Count = "select count(p) from Partener p WHERE p.type.id = 1";
    private static final String HQL_RESTAURANT_Count = "select count(p) from Partener p WHERE p.type.id = 3";
    private static final String HQL_ACTIVE_HOTELS_Count = "select  count(p) FROM Partener p WHERE p.type.id = 1 and p.validated = true and p.active = true";
    private static final String HQL_ACTIVE_RESTAURANTS_Count = "select  count(p) FROM Partener p WHERE p.type.id = 3 and p.validated = true and p.active = true";
    
    
     PartnTypeFacade(EntityManager em) {
        super(PartnType.class,em);
    }
     
     public List<Partener> getAllpartenerOfOneCat(int id)
     {
       
        List<Partener> all2 = new ArrayList<>();
        
        all2 = getEntityManager().createQuery("SELECT p FROM Partener p WHERE p.type.id = :id and p.validated = true and p.active = true").setParameter("id", id).getResultList();
        
        
        return all2;
     }
 
     
     
      //_____________________________count all and active Hotels_________________
    public int[] getHotelsCount(){
    
         int [] o = new int [2] ;
     try{
      Query q = getEntityManager().createQuery(HQL_HOTELS_Count);
         o[0] =  (int) (long) q.getSingleResult();
         
       q = getEntityManager().createQuery(HQL_ACTIVE_HOTELS_Count);
         o[1] =  (int) (long) q.getSingleResult();
        
                         
         
      }catch(Exception e){
          e.printStackTrace();
      }
       // [0] is all , [1] is the active
       return o;
}
    
    //_____________________________count all and active clients_________________
    
    
      //_____________________________count all and active Hotels_________________
    public int[] getRestaurantsCount(){
    
         int [] o = new int [2] ;
     try{
      Query q = getEntityManager().createQuery(HQL_RESTAURANT_Count);
         o[0] =  (int) (long) q.getSingleResult();
         
       q = getEntityManager().createQuery(HQL_ACTIVE_RESTAURANTS_Count);
         o[1] =  (int) (long) q.getSingleResult();
        
                         
         
      }catch(Exception e){
          e.printStackTrace();
      }
        // [0] is all , [1] is the active
       return o;
}
    
    //_____________________________count all and active clients_________________

}
