/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.JoinTripFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.JoinTrip;
import com.iti.fashny.entities.Trip;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientJoinTripBusiness {

    public void joinTrip(JoinTrip t,Trip trip) {
        DaoFactory daoFactory = new DaoFactory();
        JoinTripFacade joinTripFacade = daoFactory.getJoinTripDoa();
        TripFacade tripFacade = daoFactory.getTripDoa();
        try {

            daoFactory.beginTransaction();
            joinTripFacade.create(t);
            trip.setCountBooking(trip.getCountBooking()-t.getCount());
            tripFacade.edit(trip);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }
}
