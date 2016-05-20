/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Admin;
import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author Hosam
 */

public class AdminFacade extends AbstractFacade<Admin> {

  

     AdminFacade(EntityManager em) {
        super(Admin.class,em);
    
    }
    
    
    
    
    
    public boolean validMail(String mail){
        
        boolean valid = false;
        
        List resultList = getEntityManager().createNamedQuery("Admin.findByEmail").setParameter("email", mail).getResultList();
        
        
        if(resultList.size()>1)
            valid=true;
        
        return valid;
    }
    
}
