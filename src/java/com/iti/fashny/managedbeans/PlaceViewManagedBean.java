/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.entities.Place;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name="placeView")
@RequestScoped
public class PlaceViewManagedBean {

    @ManagedProperty(value = "#{placeManagedBean}")
    private PlaceManagedBean placeManagedBean;

    private List<Place> filterPlaces;

    public List<Place> getFilterPlaces() {
        return filterPlaces;
    }

    public void setFilterPlaces(List<Place> filterPlaces) {
        this.filterPlaces = filterPlaces;
    }

    
    public PlaceManagedBean getPlaceManagedBean() {
        return placeManagedBean;
    }

    public void setPlaceManagedBean(PlaceManagedBean placeManagedBean) {
        this.placeManagedBean = placeManagedBean;
    }
    
    public PlaceViewManagedBean() {
    }
    
}
