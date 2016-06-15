/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.JoinTripBuisinesss;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.JoinTrip;
import com.iti.fashny.entities.Trip;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Hosam
 */
@ManagedBean(name = "joinMB") 
@RequestScoped
public class JoinTipMB {
    
    DaoFactory daoFactory = new DaoFactory();
    ClientFacade cf = daoFactory.getClientDoa();
    Client client ;
    List<JoinTrip>oldTrips;
    List<JoinTrip>comingTrips;
    JoinTripBuisinesss joinBuisiness; 
    public JoinTipMB(){
        oldTrips = new ArrayList();
        comingTrips = new ArrayList();
        joinBuisiness = new JoinTripBuisinesss();
    }
    public List<JoinTrip> getOldTrips(Client c) {
             getClient(c);
        for (JoinTrip trip : client.getJoinTripList()) {
            
            if(trip.getTrip().getDate().getTime() < System.currentTimeMillis())
                oldTrips.add(trip);
                        
        }
        
        return oldTrips;
    }
    
    public List<JoinTrip> getComingTrips(Client c) {
             getClient(c);
        for (JoinTrip trip : client.getJoinTripList()) {
            
            if(trip.getTrip().getDate().getTime() >= System.currentTimeMillis())
                comingTrips.add(trip);                        
        }
        
        return comingTrips;
    }

    public void setOldTrips(List<JoinTrip> oldTrips) {
        this.oldTrips = oldTrips;
    }

   

    public void setComingTrips(List<JoinTrip> comingTrips) {
        this.comingTrips = comingTrips;
    }
    
    public Client getClient(Client c) {
    if (client==null)
        client=new Client();
    
        client = cf.find(c.getId());
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
   
    public void onrate(JoinTrip joinTrip){
      joinBuisiness.rate(joinTrip);
   }
    
    public void oncancle(JoinTrip joinTrip){
        joinBuisiness.rate(joinTrip);
    }
    
}
