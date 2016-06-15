/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.JoinTripFacade;
import com.iti.fashny.entities.JoinTrip;

/**
 *
 * @author Bakar M.M.R
 */
public class ClientJoinTripBusiness {
    
     public void joinTrip(JoinTrip t){
         DaoFactory daoFactory = new DaoFactory();
         JoinTripFacade joinTripFacade = daoFactory.getJoinTripDoa();
        try {

            daoFactory.beginTransaction();
            joinTripFacade.create(t);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }
}
