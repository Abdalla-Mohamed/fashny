/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
public class PlaceBusiness implements Commens<Place> {

    @Override
    public Place login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Place t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeFacade = daoFactory.getPlaceDoa();
        try {

            daoFactory.beginTransaction();
            placeFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Place t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeFacade = daoFactory.getPlaceDoa();
        try {

            daoFactory.beginTransaction();
            placeFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Place> view() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Place> placeResults = new ArrayList<>();
        try {
            // get doas
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            // search/read/select 
            placeResults = placeFacade.findAll();
            for (Place place : placeResults) {
                System.out.println(place.getName());
                List<Resouce> resouceList = place.getResouceList();
                for (Resouce resouceList1 : resouceList) {
                    System.out.println(resouceList1.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return placeResults;
    }

        public void getPlaceResoures(Place place) {
        DaoFactory daoFactory = new DaoFactory();

        try {
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            daoFactory.beginTransaction();
            placeFacade.refresh(place);
            place.getResouceList();
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.rollbackTransaction();
            e.printStackTrace();
        }

    }
    @Override
    public List<Place> searchByExample(Place t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Place showSpecificInfo(int id) {
        Place place = new Place();
        DaoFactory dao = new DaoFactory();
        PlaceFacade p = dao.getPlaceDoa();
        place = p.find(id);
        return place;
    }

}
