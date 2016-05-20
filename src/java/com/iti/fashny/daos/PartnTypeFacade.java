/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.PartnType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */

public class PartnTypeFacade extends AbstractFacade<PartnType> {

     PartnTypeFacade(EntityManager em) {
        super(PartnType.class,em);
    }
    
}
