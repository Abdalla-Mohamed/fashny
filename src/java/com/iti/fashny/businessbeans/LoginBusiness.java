/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.exceptions.Fasa7nyException;

/**
 *
 * @author Administrator
 */
public class LoginBusiness {

    public LoginBusiness() {
    }

    public Client login(String email, String pass) throws Fasa7nyException {

        DaoFactory factory = new DaoFactory();
        ClientFacade cf = factory.getClientDoa();
        try {
            Client c = cf.login(email, pass);
            return c;

        } finally {
            factory.close();
        }
    }
}
