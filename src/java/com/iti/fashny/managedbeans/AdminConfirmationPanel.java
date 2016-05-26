/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdminManager;
import static com.iti.fashny.businessbeans.Example.tst;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.AdminInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name = "confirmBean") 
@RequestScoped
public class AdminConfirmationPanel {

    AdminInterface adminInterface;
    DataModel<Place> placesList;
    DataModel<Trip> tripsList;
    DataModel<Tag> tagsList;
    Place placeToConfirm;
    private List<Place> selectedPlaces;

    public void setSelectedPlaces(List<Place> selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }

    public List<Place> getSelectedPlaces() {
        return selectedPlaces;
    }

    /**
     * Creates a new instance of Confirm
     */
    public AdminConfirmationPanel() {
        adminInterface = new AdminManager();
    }

    public AdminInterface getAdminInterface() {
        return adminInterface;
    }

    public DataModel<Place> getPlacesList() {
        placesList = new ListDataModel<Place>(adminInterface.findAllUncofirmPlaces());
        return placesList;
    }

    public DataModel<Trip> getTripsList() {
        tripsList = new ListDataModel<Trip>(adminInterface.findAllUncofirmTrips());
        return tripsList;
    }

    public DataModel<Tag> getTagsList() {
        tagsList= new ListDataModel<Tag>(adminInterface.findAllUncofirmTags());
        return tagsList;
    }

    public void setAdminInterface(AdminInterface adminInterface) {
        this.adminInterface = adminInterface;
    }

    public void setPlacesList(DataModel<Place> placesList) {
        this.placesList = placesList;
    }

    public void setTripsList(DataModel<Trip> tripsList) {
        this.tripsList = tripsList;
    }

    public void setTagsList(DataModel<Tag> tagsList) {
        this.tagsList = tagsList;
    }
    public void confirmPlace()
    {
//                System.out.println("________confirm____");

        placeToConfirm=placesList.getRowData();
        System.out.println("***********  Name :" + placeToConfirm.getName());
//        for (Place place : placesList) {
//          System.out.println("#### --->  Name :" + place.getName());  
////        }
    }

    public void setPlaceToConfirm(Place placeToConfirm) {
        this.placeToConfirm = placeToConfirm;
    }

    public Place getPlaceToConfirm() {
        return placeToConfirm;
    }
    
     public void onRowSelect(SelectEvent event) {
        System.out.println("________sel____");

         System.out.println("select :::   " +((Place) event.getObject()).getId());
    }
 
    public void onRowUnselect(UnselectEvent event) {
        System.out.println("________un____");
        System.out.println("unselect :::   " +((Place) event.getObject()).getId());
        
    }
    public void check(SelectEvent event) {
    System.out.println("in check");
}
    
}
