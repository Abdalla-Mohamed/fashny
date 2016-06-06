/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientBusiness implements Commens<Client>{

    @Override
    public Client login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Client t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Client t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> view() throws Exception {
          DaoFactory daoFactory = new DaoFactory();
        List<Client> clientResults = new ArrayList<>();
        try {
            ClientFacade clientFacade = daoFactory.getClientDoa();
            clientResults = clientFacade.findAll();
            for (Client client:clientResults) {
                System.out.println(client.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
