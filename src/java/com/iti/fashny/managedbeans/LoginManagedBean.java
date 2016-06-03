/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.LoginBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.exceptions.Fasa7nyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginManagedBean {

    boolean isLogged = false;
    private String mail;
    private String password;
    LoginBusiness loginB = new LoginBusiness();
    Client c;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String login() {
        String destination = null;
        try {
            c = loginB.login(mail, password);
            isLogged = true;
            destination = "PlaceClient";
        } catch (Fasa7nyException ex) {
            ex.printStackTrace();
        }
        return destination;
    }

    public String logOut() {
        isLogged = false;
        return "login";
    }
}
