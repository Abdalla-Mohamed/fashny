/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.entities.Place;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean
@ApplicationScoped
public class PlaceManagedBean {

    List<Place> places;
    
    public PlaceManagedBean() {
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
