/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.entities.Place;
import java.util.List;
import javax.faces.bean.ViewScoped;
import java.util.Locale;
import javax.faces.model.DataModel;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "placeView")
@ViewScoped
public class PlaceViewManagedBean {
    
    @ManagedProperty(value = "#{placeManagedBean}")
    private PlaceManagedBean placeManagedBean;
    
    List<Place> filterPlaces;
    private Place placeView;
    
    public void setPlace(Place place) {
        this.placeView = place;
    }
    
    public Place getPlace() {
        return placeView;
    }
    
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
    
    public boolean filterBy(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        
        if (value == null) {
            return false;
        }
        
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

//    public Place getSelectedPlace() {
//
//        place = filterPlaces.getRowData();
//        System.out.println("test DM");
//        return place;
//    }
//    public void onPlaceChosen(SelectEvent event) {
//         place = (Place) event.getObject();
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + place.getId());
//
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
}
