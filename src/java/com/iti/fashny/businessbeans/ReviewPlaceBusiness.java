/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientReviewPlaceFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.ClientReviewPlace;

/**
 *
 * @author Bakar M.M.R
 */
public class ReviewPlaceBusiness {
    
    public void review(ClientReviewPlace t){
    DaoFactory daoFactory = new DaoFactory();
        ClientReviewPlaceFacade clientReviewPlaceFacade = daoFactory.getClientReviewPlaceFacade();
        try {

            daoFactory.beginTransaction();
            clientReviewPlaceFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }
    
}
