/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Wishes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    
    public List<Wishes> getClientsWishes(){
        Place p  = new Place();
        
        List<Wishes> wishes = new ArrayList<>();
        
      String GET_WISHES = "select p.name , size(p.clientList) as l from Place p group by p.name order by l desc";// order by p.clientList.size() 
       
      try{
      Query q = getEntityManager().createQuery(GET_WISHES);
         Iterator it = q.getResultList().iterator();
         while(it.hasNext()){
             Object[] o = (Object[]) it.next();
             Wishes wish = new Wishes();
             wish.setPlace((String) o[0]);
             wish.setPlaceCount((int) o[1]);
             
             wishes.add(wish);
         }
      }catch(Exception e){
          e.printStackTrace();
      }
          
        return wishes;
    }
    
}
