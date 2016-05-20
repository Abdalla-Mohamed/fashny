/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */

public class CompanyFacade extends AbstractFacade<Company> {

   

     CompanyFacade(EntityManager em) {
        super(Company.class,em);
    }
 
    
     public boolean validMail(String mail){
        
        boolean valid = false;
        
        List resultList = getEntityManager().createNamedQuery("Company.findByEmail").setParameter("email", mail).getResultList();
        
        
        if(resultList.size()>1)
            valid=true;
        
        return valid;
    }
    
    
}
