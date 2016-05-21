/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.entities.Place;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean
@RequestScoped
public class PlaceManagedBean {

    /**
     * Creates a new instance of PlaceManagedBean
     */
    public PlaceManagedBean() {

    }

//    List<Place> placeResults;
            
    public List<Place> getPlaces() {

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
}

