/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.interfaces.Commens;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientBusiness implements Commens<Client> {

    @Override
    public Client login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Client t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        ResouceFacade resouceFacade = daoFactory.getResouceDoa();
        try {
            daoFactory.beginTransaction();
            resouceFacade.create(t.getProfilePic());
            clientFacade.create(t);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Client t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        try {

            daoFactory.beginTransaction();
            clientFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Client> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Client> clientResults = new ArrayList<>();
        try {
            ClientFacade clientFacade = daoFactory.getClientDoa();
            clientResults = clientFacade.findAll();
            for (Client client : clientResults) {
                System.out.println(client.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            daoFactory.close();
        }
        return clientResults;
    }

    @Override
    public List<Client> searchByExample(Client t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client showSpecificInfo(int id) {
        Client client = new Client();
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        client = clientFacade.find(id);
        return client;
    }

}
