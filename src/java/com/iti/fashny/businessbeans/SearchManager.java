/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.interfaces.SearchEngine;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Abdalla
 */
public class SearchManager implements SearchEngine,Serializable {

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
        DaoFactory daoFactory = new DaoFactory();
        List<Company> CompaniesResult = new ArrayList<>();

        try {

            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            CompaniesResult = companyFacade.findByExample(company);

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return CompaniesResult;
    }

    @Override
    public List<Admin> searchByExample(Admin admin) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Admin> adminsResult = new ArrayList<>();

        try {

            AdminFacade adminFacade = daoFactory.getAdminDoa();
            adminsResult = adminFacade.findByExample(admin);

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return adminsResult;

    }

    @Override
    public List<Partener> searchByExample(Partener partener) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Partener> partenersResult = new ArrayList<>();

        try {

            PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
            partenersResult = partenerFacade.findByExample(partener);

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return partenersResult;
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
        DaoFactory daoFactory = new DaoFactory();
        List<Place> placesResult = new ArrayList<>();

        try {

            PlaceFacade placesFacade = daoFactory.getPlaceDoa();
            placesResult = placesFacade.findByExample(place);
            for (Place placeRslt : placesResult) {
                placeRslt.getResouceList().size();
                
            }

        } catch (Exception e) {

        } finally {
            daoFactory.close();

        }

        return placesResult;
    }

    @Override
    public List<Trip> searchByExample(Trip trip) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> tripsResult = new ArrayList<>();

        try {
            Company companyId = trip.getCompanyId();
            trip.setCompanyId(null);
                    
            TripFacade tripFacade = daoFactory.getTripDoa();
            tripsResult = tripFacade.findByExample(trip);
//
         
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            daoFactory.close();

        }

        return tripsResult;
    }

    private boolean haveAllPlaces(Trip trip, List<Place> placeList) {
        System.out.println("placcce"+placeList.size());
        System.out.println("trip place"+trip.getPlaceList().size());
       return trip.getPlaceList().containsAll(placeList);
    }

}
