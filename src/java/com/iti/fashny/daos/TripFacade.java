/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */
public class TripFacade extends AbstractFacade<Trip> {

    TripFacade(EntityManager em) {
        super(Trip.class, em);
    }

    public List<Trip> getUnconcirmTrips() {
        List<Trip> unconfirmTrips = new ArrayList<>();
        try {

            unconfirmTrips = getEntityManager().createNamedQuery("Trip.findByValidated").setParameter("validated", false).getResultList();

//            for (Place placerslt : unconfirmPlaces) {
//                System.out.println(placerslt.getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unconfirmTrips;
    }

}
