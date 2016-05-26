/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Tag;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hosam
 */
public class TagFacade extends AbstractFacade<Tag> {

    TagFacade(EntityManager em) {
        super(Tag.class, em);
    }

    public List<Tag> getUnconcirmTags() {
        List<Tag> unconfirmTags = new ArrayList<>();
        try {

            unconfirmTags = getEntityManager().createNamedQuery("Tag.findByVaidated").setParameter("vaidated", false).getResultList();

//            for (Place placerslt : unconfirmPlaces) {
//                System.out.println(placerslt.getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unconfirmTags;
    }

}
