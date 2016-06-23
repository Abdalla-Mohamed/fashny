/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Hosam
 */
public class TripFacade extends AbstractFacade<Trip> {

    TripFacade(EntityManager em) {
        super(Trip.class, em);
    }

    @Override
    protected void addAssociationExample(Criteria c, Trip mainExample) {

        List<Tag> tags = mainExample.getTagList();

        if (tags != null && !tags.isEmpty()) {
            addTagConditionOnExample(c, tags, "tagList");
        }
//        

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

    //_________________find validated trips____________________
    public List<Trip> getValidTrips() {

        List<Trip> trips = new ArrayList();

        trips = getEntityManager().createNamedQuery("Trip.findByValidated").setParameter("validated", true).getResultList();

        return trips;
    }

    //_________________find validated trips____________________
    public List<Trip> search(Trip trip) {

        List<Trip> findAll = getValidTrips();
        for (Trip trip1 : findAll) {
            trip.getCompanyId().getId();
            trip.getPlaceList().size();
            trip.getTagList().size();
            
             for (Trip trip2 : findAll) {
                System.out.println(trip2.getName());
                List<Resouce> resouceList = trip2.getResouceList();
                for (Resouce resouceList1 : resouceList) {
                    System.out.println(resouceList1.getPath());
                }
            }
            
           

        }
        return findAll;
    }

    //_________________find validated trips____________________
}
