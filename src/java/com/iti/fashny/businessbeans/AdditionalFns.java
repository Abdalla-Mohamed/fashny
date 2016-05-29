/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.entities.Client;

/**
 *
 * @author MANAR ADEL
 */
public class AdditionalFns 
{
    public Boolean isMailExist(String email)
    {
        boolean valid = true;
        
         DaoFactory daoFactory = new DaoFactory();
          ClientFacade clientFacade = daoFactory.getClientDoa();
          CompanyFacade companyFacade = daoFactory.getCompanyDoa();
          PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
          AdminFacade adminFacade = daoFactory.getAdminDoa();
          
          daoFactory.beginTransaction();
          if(!clientFacade.validMail(email)||companyFacade.validMail(email)||partenerFacade.validMail(email)||adminFacade.validMail(email))
          {
              System.out.println("Mail Already Exist");
              valid = false;
              System.out.println(valid);
          }
                  
          daoFactory.commitTransaction();
          daoFactory.close();
          
          return valid;
    }
    
    public Client getClientObject()
    {
        Client c ;
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        daoFactory.beginTransaction();
        c = clientFacade.find(7);
        daoFactory.commitTransaction();
        daoFactory.close();
        
        return c;
    }
    
      public Client getClientObject(int id)
    {
        Client c ;
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        daoFactory.beginTransaction();
        c = clientFacade.find(id);
        daoFactory.commitTransaction();
        daoFactory.close();
        
        return c;
    }
    
    
    public Client getCurrentClientObjectFromSession()
    {
        Client c = new Client();
        
        return c;
    }
     
     
      /*
      
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
        
     daoFactory.beginTransaction();
     clientFacade.create(c);
      //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
      daoFactory.commitTransaction();
      daoFactory.close();
      
      */
    
    
    public static void main(String[] args)
    {
        new AdditionalFns().isMailExist("Reham@yahoo.com");
        
    }
      
}
