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
    //Client c = new AdditionalFns().getClientObject() ;
    
//    @ManagedProperty("#{loginManagedBean}")
//    private LoginManagedBean loginManagedBean; // +setter (no getter!)
//    Client c =loginManagedBean.c;
 // Client c=loginManagedBean.c;
    
   
    Client c;
    public LoginManagedBean loginManagedBean;
    
   //Client c = new LoginManagedBean().c;
    
//    FacesContext facesContext = FacesContext.getCurrentInstance();
//    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
//    Client c = (Client) session.getAttribute("c");
    
    public EditClientProfileInfoBean()
    {
        loginManagedBean = new LoginManagedBean();
        c=loginManagedBean.c;
    }

//     @ManagedProperty(value = "#{navigationBean}")
//    private NavigationBean navigationBean;
//     
//      @ManagedProperty(value = "#{loginBean}")
//    private LoginBean loginBean;

//    public LoginManagedBean getLoginManagedBean() {
//        return loginManagedBean;
//    }
//
//    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
//        this.loginManagedBean = loginManagedBean;
//    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }
      
      public Boolean isEditable;
    
    // to get object from session

   // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("yourKey", yourObject);
//ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//Map<String, Object> sessionMap = externalContext.getSessionMap();
//sessionMap.put("somekey", yourVariable);
    //----
    //SomeObject yourVariable = (SomeObject) sessionMap.get("somekey");

//    public NavigationBean getNavigationBean() {
//        return navigationBean;
//    }
//
//    public void setNavigationBean(NavigationBean navigationBean) {
//        this.navigationBean = navigationBean;
//    }
//
//    public LoginBean getLoginBean() {
//        return loginBean;
//    }
//
//    public void setLoginBean(LoginBean loginBean) {
//        this.loginBean = loginBean;
//    }
    

    

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }
    
    public String editProfile()
    {
     c.setId(loginManagedBean.c.getId());
//        System.out.println(c.getName()+c.getEmail()+c.getId());
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
     daoFactory.beginTransaction();
     
     clientFacade.edit(c);
     daoFactory.commitTransaction();
     daoFactory.close();
     
//     return navigationBean.toWelcome();
     return"/info";
        
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
