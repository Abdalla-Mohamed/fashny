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
import static com.sun.faces.facelets.util.Path.context;
import java.util.Enumeration;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MANAR ADEL
 */
@ManagedBean(name = "EditClientProfileInfoBean")
@SessionScoped
public class EditClientProfileInfoBean 
{
   
    Client c;
   
   @ManagedProperty(value = "#{login}")
    private LoginManagedBean loginManagedBean;
    

    public EditClientProfileInfoBean()
    {
        
        
    }

    public LoginManagedBean getLoginManagedBean() {
        return loginManagedBean;
    }

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }


    public Boolean getIsEditable() {
        return isEditable;
    }


    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }
      
      public Boolean isEditable;

    

    public Client getC() {
        c=loginManagedBean.getLoginAccount().getClient();
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }
    
    public String editProfile()
    {
     c.setId(loginManagedBean.getLoginAccount().getClient().getId());
//        System.out.println(c.getName()+c.getEmail()+c.getId());
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
     daoFactory.beginTransaction();
     
     clientFacade.edit(c);
     daoFactory.commitTransaction();
     daoFactory.close();
     
//     return navigationBean.toWelcome();
     return "goHome";
        
    }
    
}
