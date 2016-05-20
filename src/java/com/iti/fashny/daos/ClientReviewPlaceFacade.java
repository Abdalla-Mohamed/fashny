/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.ClientReviewPlace;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */

public class ClientReviewPlaceFacade extends AbstractFacade<ClientReviewPlace> {

   
     ClientReviewPlaceFacade(EntityManager em) {
        super(ClientReviewPlace.class,em);
    }
    
}
