/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author MANAR ADEL
 */
@ManagedBean(name = "EditClientProfileInfoBean")
@SessionScoped
public class EditClientProfileInfoBean 
{
    Client c = new AdditionalFns().getClientObject() ;
    
    // to get object from session

   // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("yourKey", yourObject);
//ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//Map<String, Object> sessionMap = externalContext.getSessionMap();
//sessionMap.put("somekey", yourVariable);
    //----
    //SomeObject yourVariable = (SomeObject) sessionMap.get("somekey");
    

    

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }
    
    public String editProfile()
    {
     c.setId(new AdditionalFns().getClientObject().getId());
//        System.out.println(c.getName()+c.getEmail()+c.getId());
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
     daoFactory.beginTransaction();
     
     clientFacade.edit(c);
     daoFactory.commitTransaction();
     daoFactory.close();
     
     return"index";
        
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
