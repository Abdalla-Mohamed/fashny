/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.businessbeans.guestImpl;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name="SignUpBean")
@SessionScoped
public class SignUpBean 
{
    
    
    Client c = new Client();

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }
    
    public void getAllInfo()
    {
        System.out.println(c.getName());
        System.out.println(c.getPassword());
        System.out.println(c.getEmail());
        System.out.println(c.getContactEmail());
        System.out.println(c.getAddress());
        System.out.println(c.getMobile1());
        System.out.println(c.getMobile2());
        System.out.println(c.getBirthDate());
        System.out.println(c.getMaritalStatus());
        System.out.println(c.getLastSeen());
        System.out.println(c.getActive());
        System.out.println(c.getGender());
        System.out.println(c.getId());
        
    }
    
    public String registerNewClient()
    {
//        DaoFactory daoFactory = new DaoFactory();
//        ClientFacade clientFacade = daoFactory.getClientDoa();
//        
//      daoFactory.beginTransaction();
//      clientFacade.create(c);
//      //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
//      daoFactory.commitTransaction();
//      daoFactory.close();
        
        new guestImpl().signUp(c);
      
      return "index";
    }
    
      public void uploadedPicture()
    {
        
    }
      
//    daoFactory.beginTransaction();
//    clientFacade.create(new Client(c));
//    daoFactory.commitTransaction();
//     daoFactory.close();
 
    public void checkIfMailExitsAnywhere()
    {
        
    }
    
     public void validateMail(String mail)
    {
        
    }
     
  
    
    
    
    /*
     //Common
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
        
     daoFactory.beginTransaction();
     clientFacade.create(c);
     //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
     daoFactory.commitTransaction();
     daoFactory.close();
     */
    
    
}
