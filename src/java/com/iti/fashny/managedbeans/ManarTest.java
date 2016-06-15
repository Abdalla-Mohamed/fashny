/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.guestImpl;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PartnTypeFacade;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;
import javax.persistence.NamedQuery;

/**
 *
 * @author MANAR ADEL
 */
public class ManarTest 
{
    public static void main(String[] args) {
//        Partener p = new Partener();
//        
//        p.setName("fs4wf");
//        p.setPassword("4fsfdsaf");
//        p.setEmail("faddff");
//        p.setContactEmail("dfdfadfa");
//        p.setPhone("32423");
//        p.setMobile1("234234");
//        p.setMobile2("4234");
//        p.setType(new PartnType(1));
        
        
//        new guestImpl().signUp(p);
//     
//        new SignUpCreatePartenerBean().registerNewPartener();
        
        
//        
//        DaoFactory daoFactory1 = new DaoFactory();
//        PartnTypeFacade partnTypeFacade = daoFactory1.getPartnTypeDoa();
//        daoFactory1.beginTransaction();
//        List<PartnType> findAll = partnTypeFacade.findAll();
//        
//        for(PartnType pt : findAll)
//        {
//            System.out.println(pt.getId());
//            System.out.println(pt.getName());
//            System.out.println("---------------");
//        }
//        
//        
//        daoFactory1.commitTransaction();
//        daoFactory1.close();
        
        
        
          
    DaoFactory daoFactory2 = new DaoFactory();
    PartnTypeFacade partnTypeFacade2 = daoFactory2.getPartnTypeDoa();
    daoFactory2.beginTransaction();
    
        List<Partener> allofpartenerOfOneCat = partnTypeFacade2.getAllpartenerOfOneCat(1);
    
    for(Partener prtn : allofpartenerOfOneCat)
    {
        System.out.println(prtn.getName());
    }
    
    daoFactory2.commitTransaction();
    daoFactory2.close();
        
        
        
        
        
        
        
        
        
        
    }
    
    //@NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    //@NamedQuery(name = "Client.findByContactEmail", query = "SELECT c FROM Client c WHERE c.contactEmail = :contactEmail");
    
    //List resultList = getEntityManager().createNamedQuery("Client.findByEmail").setParameter("email", mail).getResultList();
    
//    public boolean validMail(String mail) {
//
//        boolean valid = false;
//
//        List resultList = getEntityManager().createNamedQuery("Client.findByEmail").setParameter("email", mail).getResultList();
//        
//        
//        if(resultList.size()>=1)
//            valid=true;
//        
//        return valid;
//    }
    
    
    
 
    
    
    
    
}
