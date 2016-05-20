/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.interfaces.SearchEngine;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Abdalla
 */
public class SearchManager implements SearchEngine {

    @Override
    public List<Client> searchByExample(Client client) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Client> clientsResults = new ArrayList<>();

        try {

            ClientFacade clientFacade = daoFactory.getClientDoa();
            clientsResults = clientFacade.findByExample(client);

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return clientsResults;

    }

    @Override
    public List<Company> searchByExample(Company company) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> searchByExample(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Partener> searchByExample(Partener partener) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> searchByExample(Tag tag) throws Exception {
         DaoFactory daoFactory = new DaoFactory();
        List<Tag> tagResults = new ArrayList<>();

        try {

            TagFacade tagFacade = daoFactory.getTagDoa();

            tagResults = tagFacade.findByExample(tag);

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return tagResults;

    }

    @Override
    public List<Place> searchByExample(Place place) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trip> searchByExample(Trip trip) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
