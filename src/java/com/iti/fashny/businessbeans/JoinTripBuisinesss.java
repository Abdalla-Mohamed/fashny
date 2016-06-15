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
import javax.persistence.criteria.Join;

/**
 *
 * @author Hosam
 */
public class JoinTripBuisinesss {
    DaoFactory daoFactory = new DaoFactory();
    JoinTripFacade joinTripFacade = daoFactory.getJoinTripDoa();
    TripFacade tripFacade = daoFactory.getTripDoa();
    public void rate(JoinTrip joinTrip){
        daoFactory.beginTransaction();
        joinTripFacade.edit(joinTrip);
        daoFactory.commitTransaction();
    }
    
    public int tripRate(Trip trip){
        
     int rate =0;
     int voters = 0;
     trip = tripFacade.find(trip.getId());
     
        for(JoinTrip joinTrip :trip.getJoinTripList()){
            if(joinTrip.getRate()>0){
            rate+=joinTrip.getRate();
            voters++;
            }
        }
        
        if(voters >0)
            rate = rate/voters;
            System.out.println(rate);
            
        return rate;
    }
    
}
