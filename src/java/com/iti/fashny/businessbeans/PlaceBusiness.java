/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.entities.Place;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
public class PlaceBusiness implements Commens<Place>{

    @Override
    public Place login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Place t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Place t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeFacade=daoFactory.getPlaceDoa();
        daoFactory.beginTransaction();
        placeFacade.edit(t);
        daoFactory.commitTransaction();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return placeResults;
    }

    @Override
    public List<Place> searchByExample(Place t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Place showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
