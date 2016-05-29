/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean
@ApplicationScoped

public class PlaceManagedBean {

    DataModel<Place> places;
  
    public PlaceManagedBean() {
        try {
            PlaceBusiness placeBusiness = new PlaceBusiness();
            places = new ListDataModel<>(placeBusiness.view());
        } catch (Exception ex) {
            Logger.getLogger(PlaceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataModel<Place> getPlaces() {
        return places;
    }

    public void setPlaces(DataModel<Place> places) {
        this.places = places;
    }

    public void viewPlace(){
    }
    
    public void updatePlace(){}
    public void deactivePlace(){}

}
