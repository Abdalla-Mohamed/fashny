/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.JoinTripFacade;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.JoinTrip;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Trip;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientJoinTripBusiness implements Serializable{

    public void joinTrip(JoinTrip t, Trip trip) throws Exception{
        DaoFactory daoFactory = new DaoFactory();
        JoinTripFacade joinTripFacade = daoFactory.getJoinTripDoa();
        TripFacade tripFacade = daoFactory.getTripDoa();
        try {

            daoFactory.beginTransaction();
            joinTripFacade.create(t);
            trip.setCountBooking(trip.getCountBooking() + t.getCount());
            tripFacade.edit(trip);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    public Client getJoidTrips(Client client) {

        DaoFactory daoFactory = new DaoFactory();
        try {
            ClientFacade clientFacade = daoFactory.getClientDoa();
            daoFactory.beginTransaction();
            client = clientFacade.refreshObj(client);
            List<JoinTrip> joinTripList = client.getJoinTripList();
            joinTripList.size();
            for (JoinTrip joinTripList1 : joinTripList) {
                
            }
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return client;
    }
}
