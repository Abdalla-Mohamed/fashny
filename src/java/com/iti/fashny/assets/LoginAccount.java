/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.UserAccount;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abdalla
 */
public class LoginAccount {

    private Role role;
    private UserAccount account;

    public LoginAccount(UserAccount userAccount) {
        this.account = userAccount;
        spcifyRoleOfAccount(account);
    }


    private void spcifyRoleOfAccount(UserAccount account) {
        if (account instanceof Client) {
            role = Role.Client;
        } else if (account instanceof Company) {
            role = Role.Company;

        } else if (account instanceof Partener) {
            role = Role.Partner;
        }

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Client getClient(){
        Client client=null;
        if(role == Role.Client) {
            client = (Client) account;
        }
        return client;
    }

    public Company getCompany(){
        Company company=null;
        if(role == Role.Company) {
            company = (Company) account;
        }
        return company;
    }

    public Partener getPartener(){
        Partener partener=null;
        if(role == Role.Partner) {
            partener = (Partener) account;
        }
        return partener;
    }
}
