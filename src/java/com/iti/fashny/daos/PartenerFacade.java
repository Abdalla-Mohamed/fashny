/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Partener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.iti.fashny.entities.Service;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import java.util.ArrayList;

/**
 *
 * @author Hosam
 */

public class PartenerFacade extends AbstractFacade<Partener> {

    private static final String GET_PARTENER_SERVICES= "select s.serviceList  from ServiceCategorey s where s.partenersId = :partenerId";
    private static final String HQL_LOGIN = "FROM Partener p WHERE p.email =:email AND p.password =:password ";
    private static final String GET_ALL_ACTIVE_PARTENERS = "FROM Partener p where p.validated = :validated and p.active = :active";


  
     PartenerFacade(EntityManager em) {
        super(Partener.class,em);
    }
    
     public boolean validMail(String mail){
        
        boolean valid = false;
        
        List resultList = getEntityManager().createNamedQuery("Partener.findByEmail").setParameter("email", mail).getResultList();
        
        
        if(resultList.size()>=1)
            valid=true;
        
        return valid;
    }
     
     public List<Partener> findAllActive(){
        
        List<Partener>parteners = new ArrayList();
        
         parteners = getEntityManager().createQuery(GET_ALL_ACTIVE_PARTENERS)
                                             .setParameter("validated", true).setParameter("active", true).getResultList();
                     
        return parteners;
    }
     
     public List<Service> getPartenerServices(Partener partener){
         
       List<Service> services;
         
       services = getEntityManager().createQuery(GET_PARTENER_SERVICES).setParameter("partenerId", partener.getId()).getResultList();
        
             
        return services;
     }

    public Partener login(String email, String pass) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException{
        Partener partener;
        List result = getEntityManager().createQuery(HQL_LOGIN)
                .setParameter("email", email).setParameter("password", pass).getResultList();
        if (result == null || result.isEmpty()) {
            throw new InvalidLoginDataException();
        } else {
            partener = (Partener) result.get(0);
            if (partener.getValidated() == false) {
                throw new NotConfirmAccountException();
            } else if (partener.getActive()== false) {
                throw new DeletedAccountException();
                  
            }
        }
        return partener;

    }
     
    public List<Partener> getUnconcirmPartener()
    {
        List<Partener> unconfirmPartener = new ArrayList<>();
        try {
            unconfirmPartener = getEntityManager().createNamedQuery("Partener.findByValidated").setParameter("validated", false).getResultList();           
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return unconfirmPartener;
    }
}
