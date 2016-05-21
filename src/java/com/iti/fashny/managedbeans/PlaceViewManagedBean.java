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
import java.util.Locale;
import javax.faces.model.DataModel;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name="placeView")
@RequestScoped
public class PlaceViewManagedBean {

    @ManagedProperty(value = "#{placeManagedBean}")
    private PlaceManagedBean placeManagedBean;

    DataModel<Place> filterPlaces;

    public DataModel<Place> getFilterPlaces() {
        return filterPlaces;
    }

    public void setFilterPlaces(DataModel<Place> filterPlaces) {
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
    
    public boolean filterBy(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
    
}
