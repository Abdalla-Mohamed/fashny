/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Abdalla
 */
@ManagedBean
@RequestScoped
public class AdminLogin {

    private String username;
    private String password;

    /**
     * Creates a new instance of AdminLogin
     */
    public AdminLogin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {

            context.addMessage(null, new FacesMessage("Login failed."));
            return "";
        }
        return "adminHome";
    }

//    public void logout() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        try {
//            request.logout();
//        } catch (ServletException e) {
//
//            context.addMessage(null, new FacesMessage("Logout failed."));
//        }
//    }
//    
    public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "";
}
}
