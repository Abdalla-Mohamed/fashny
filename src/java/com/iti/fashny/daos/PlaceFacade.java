/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

/**
 *
 * @author Hosam
 */
public class PlaceFacade extends AbstractFacade<Place> {

    PlaceFacade(EntityManager em) {
        super(Place.class, em);
    }

@Override
    protected void addAssociationExample(Criteria c, Place mainExample) {

        List<Tag> tags = mainExample.getTagList();
        
        if (tags != null && !tags.isEmpty()) {
                addTagConditionOnExample(c, tags, "tagList");
        }

    }
    
    public List<Place> getUnconcirmPlaces()
    {
        List<Place> unconfirmPlaces = new ArrayList<>();
        try {
            
            unconfirmPlaces = getEntityManager().createNamedQuery("Place.findByValidated").setParameter("validated", false).getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        } 
        return unconfirmPlaces;
    }

}
