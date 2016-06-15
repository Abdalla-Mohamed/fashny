/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.JoinTripFacade;
import com.iti.fashny.entities.JoinTrip;
import javax.persistence.criteria.Join;

/**
 *
 * @author Hosam
 */
public class JoinTripBuisinesss {
    DaoFactory daoFactory = new DaoFactory();
    JoinTripFacade joinTripFacade = daoFactory.getJoinTripDoa();
    
    public void rate(JoinTrip joinTrip){
        daoFactory.beginTransaction();
        joinTripFacade.edit(joinTrip);
        daoFactory.commitTransaction();
    }
}
