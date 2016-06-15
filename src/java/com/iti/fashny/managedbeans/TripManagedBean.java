/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.businessbeans.TripBusiness;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Trip;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "tripMB")
@SessionScoped
public class TripManagedBean implements Serializable {

    TripBusiness tripBusiness;
    private List<Trip> items = null;
    private Trip selected;
    UploadImage uploadImage;

    //_______________________________________________________________________//
    public TripManagedBean() {
        tripBusiness = new TripBusiness();
        selected = new Trip();
        uploadImage = new UploadImage();
    }
    //_________________________ setter and getter  __________________________//

    public List<Trip> getItems() {

        try {
            items = tripBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(TripManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }

    public TripBusiness getTripBusiness() {
        return tripBusiness;
    }

    public Trip getSelected() {
        return selected;
    }

    public UploadImage getUploadImage() {
        return uploadImage;
    }

    public void setTripBusiness(TripBusiness tripBusiness) {
        this.tripBusiness = tripBusiness;
    }

    public void setItems(List<Trip> items) {
        this.items = items;
    }

    public void setSelected(Trip selected) {
        this.selected = selected;
    }

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

    //_________________________ functionlity  _____________________________//
    public Trip prepareCreate() {
        selected = new Trip();
        return selected;
    }

    public void create() {
        if (getSelected() != null) {
            try {
                tripBusiness.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            uploadImage.forTrip(selected.getId() + "");
            uploadImage.copyFile();
        }
    }

    public void update() {
        System.err.println("......_____________________________________#####");

        if (selected != null) {
            try {
                tripBusiness.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String tripDetails(int id) {
        selected = tripBusiness.showSpecificInfo(id);
        return "tripDetails";
    }
    // --------------------------- for page --------------------------------//

    public void onRowEdit(RowEditEvent event) {

        selected = (Trip) event.getObject();
        update();
        items = getItems();
        System.err.println("......_____________________________________>" + ((Trip) event.getObject()).getName());
        FacesMessage msg = new FacesMessage("Trip Edited", ((Trip) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Trip) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String goToViewTrip(int id) {
        selected = tripBusiness.showSpecificInfo(id);
        try {
            selected = tripBusiness.gitAllCompanyLists(selected);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewTrip";
    }

    public String goToCreateTrip() {
        selected = new Trip();
        return "createTrip";
    }

    public String goToTrips() {
        return "trips";
    }

    public String save() {
        create();
        items = getItems();
        selected = new Trip();
        return "trips";
    }

    public String cansel() {
        selected = new Trip();
        return "trips";
    }

}
