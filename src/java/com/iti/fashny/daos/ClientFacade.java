/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Client;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author Hosam
 */
public class ClientFacade extends AbstractFacade<Client> {

    private static final String GET_CLIENT_TRIPS = "select j.trip  from JoinTrip j where j. = :partenerId";
    private static final String HQL_LOGIN = "FROM Client c WHERE c.email =:email AND c.password =:password ";
    Client client;

    ClientFacade(EntityManager em) {
        super(Client.class, em);
    }

    public boolean validMail(String mail) {

        boolean valid = false;

        List resultList = getEntityManager().createNamedQuery("Client.findByEmail").setParameter("email", mail).getResultList();

        if (resultList.size() >= 1) {
            valid = true;
        }

        return valid;
    }

    public Client login(String email, String pass) throws InvalidLoginDataException, DeletedAccountException {
        Client client;
        System.out.println("=========================");
        System.out.println(email + "," + pass);
        System.out.println("=========================");
        List result = getEntityManager().createQuery(HQL_LOGIN)
                .setParameter("email", email).setParameter("password", pass).getResultList();

        if (result == null || result.isEmpty()) {
            throw new InvalidLoginDataException();
        } else {
            client = (Client) result.get(0);
            if (client.getActive() == false) {
                throw new DeletedAccountException();

            }
        }

        return client;
    }

}
