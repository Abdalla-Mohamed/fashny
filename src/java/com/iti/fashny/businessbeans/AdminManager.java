/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.ClientReviewPlace;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.exceptions.AlreadyExsist;
import com.iti.fashny.interfaces.AdminInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amira Anis
 */
public class AdminManager implements AdminInterface {

    @Override
    public void addAdmin(Admin admin) throws AlreadyExsist {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAdmin(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deactiveAdmin(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> FinAllAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmPlace(Place place) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deactivateTag(Tag tag) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmTag(Tag tag) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmTrip(Trip trip) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ClientReviewPlace> FindAllComment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Place> findAllUncofirmPlaces() {
        DaoFactory daoFactory = new DaoFactory();
        List<Place> unconfirmPlaces = new ArrayList<>();

        try {
            
            // get doas
            PlaceFacade palceFacade = daoFactory.getPlaceDoa();
            unconfirmPlaces=palceFacade.getUnconcirmPlaces();

            for (Place placerslt : unconfirmPlaces) {
                System.out.println(placerslt.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        
        return unconfirmPlaces;
    }

    @Override
    public List<Trip> findAllUncofirmTrips() {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> unconfirmTrips = new ArrayList<>();

        try {
            
            // get doas
            TripFacade tripFacade = daoFactory.getTripDoa();
            unconfirmTrips = tripFacade.getUnconcirmTrips();
//
//            for (Trip triprslt : unconfirmTrips) {
//                System.out.println(triprslt.getName());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        
        return unconfirmTrips;
    }

    @Override
    public List<Tag> findAllUncofirmTags() {
        DaoFactory daoFactory = new DaoFactory();
        List<Tag> unconfirmTags = new ArrayList<>();
        try {
            
            TagFacade tagFacade = daoFactory.getTagDoa();
            unconfirmTags = tagFacade.getUnconcirmTags();
//
//            for (Trip triprslt : unconfirmTrips) {
//                System.out.println(triprslt.getName());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return unconfirmTags;
    }

}
