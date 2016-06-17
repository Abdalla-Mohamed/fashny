/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.Role;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import com.iti.fashny.interfaces.UserAccount;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class LoginBusiness implements Serializable {

    public LoginBusiness() {
    }

    public UserAccount login(String email, String pass, Role role) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException, Fasa7nyException {
        switch (role) {
            case Client:
                return loginClient(email, pass);
            case Company:
                return loginCompany(email, pass);
            case Partner:
                return loginPartener(email, pass);
            default:
                throw new Fasa7nyException();
        }
    }

    public Client loginClient(String email, String pass) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException {

        DaoFactory factory = new DaoFactory();
        ClientFacade cf = factory.getClientDoa();
        Client c;
        try {
            c = cf.login(email, pass);

        } finally {
            factory.close();
        }
        return c;
    }

    public Company loginCompany(String email, String pass) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException {

        DaoFactory factory = new DaoFactory();
        CompanyFacade companyFacade = factory.getCompanyDoa();
        Company c;
        try {
            c = companyFacade.login(email, pass);

        } finally {
            factory.close();
        }
        return c;
    }

    public Partener loginPartener(String email, String pass) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException {

        DaoFactory factory = new DaoFactory();
        PartenerFacade partenerFacade = factory.getPartenerDoa();
        Partener partener;
        try {
            partener = partenerFacade.login(email, pass);

        } finally {
            factory.close();
        }

        return partener;
    }
}
