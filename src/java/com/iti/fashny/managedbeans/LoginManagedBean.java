/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.LoginAccount;
import com.iti.fashny.assets.Role;
import com.iti.fashny.businessbeans.LoginBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "login", eager = true)
@SessionScoped
public class LoginManagedBean implements Serializable {

    final static String clientHomePageOutcome = "loginClient";
    final static String companyHomePageOutcome = "loginCompaney";
    final static String partnerHomePageOutcome = "loginPartner";

    Role roles;
    private String selectedRole;
    private boolean isLogged = false;
    private String mail;
    private String password;
    LoginBusiness LoginBusiness;
    private LoginAccount loginAccount;

    public LoginManagedBean() {
        roles = Role.Guest;
        LoginBusiness = new LoginBusiness();
    }

    public LoginAccount getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginAccount loginAccount) {
        this.loginAccount = loginAccount;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public String getPassword() {

        return password;
    }

    public String login() {
        String destination = null;
        try {
            loginAccount = new LoginAccount(LoginBusiness.login(mail, password, Role.valueOf(selectedRole)));
            isLogged = true;
            roles = loginAccount.getRole();
            switch (roles) {
                case Client:
                    destination = clientHomePageOutcome;
                    break;
                case Company:
                    destination = companyHomePageOutcome;
                    break;
                case Partner:
                    destination = partnerHomePageOutcome;
                    break;
            }

        } catch (Fasa7nyException ex) {
            showMessages("not login role");
        } catch (NotConfirmAccountException | DeletedAccountException | InvalidLoginDataException loginException) {
            showMessages(loginException.getMessage());
        }
        return destination;
    }

    private void showMessages(String msg) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public String logOut() {
        isLogged = false;
        loginAccount = null;
        return "login";
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
}
