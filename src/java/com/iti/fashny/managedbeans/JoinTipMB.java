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
    
    List<JoinTrip>oldTrips;
    List<JoinTrip>comingTrips;
    JoinTripBuisinesss joinBuisiness; 
    public JoinTipMB(){
        oldTrips = new ArrayList();
        comingTrips = new ArrayList();
        joinBuisiness = new JoinTripBuisinesss();
    }
    public List<JoinTrip> getOldTrips(Client c) {
         
        oldTrips = joinBuisiness.getOldTrips(c);
        return oldTrips;
    }
    
    public List<JoinTrip> getComingTrips(Client c) {
       
        comingTrips = joinBuisiness.getComingTrips(c);
        return comingTrips;
    }

    public void setOldTrips(List<JoinTrip> oldTrips) {
        this.oldTrips = oldTrips;
    }

   

    public void setComingTrips(List<JoinTrip> comingTrips) {
        this.comingTrips = comingTrips;
    }
   
    public void onrate(JoinTrip joinTrip){
      joinBuisiness.rate(joinTrip);
   }
    
    public void oncancle(JoinTrip joinTrip){
        joinBuisiness.rate(joinTrip);
    }
    
}
