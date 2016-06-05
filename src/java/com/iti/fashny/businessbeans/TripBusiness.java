/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amira Anis
 */
public class TripBusiness implements Commens<Trip> {

    @Override
    public Trip login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Trip t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        TripFacade tripFacade = daoFactory.getTripDoa();
        try {

            daoFactory.beginTransaction();
            tripFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Trip t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        TripFacade tripFacade = daoFactory.getTripDoa();

        try {

            daoFactory.beginTransaction();
            tripFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Trip> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> tripResults = new ArrayList<>();
        try {
            TripFacade tripFacade = daoFactory.getTripDoa();
            tripResults = tripFacade.findAll();
            for (Trip trip : tripResults) {
                System.out.println(trip.getName());
                List<Resouce> resouceList = trip.getResouceList();
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
        return tripResults;
    }

    @Override
    public List<Trip> searchByExample(Trip t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trip showSpecificInfo(int id) {
        Trip trip = new Trip();
        DaoFactory dao = new DaoFactory();
        TripFacade p = dao.getTripDoa();
        trip = p.find(id);
        return trip;
    }

}
