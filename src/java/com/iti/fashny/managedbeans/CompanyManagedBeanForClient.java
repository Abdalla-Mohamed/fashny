/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.TripBusiness;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Trip;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name="CompanyMBClient" )
@SessionScoped
public class CompanyManagedBeanForClient implements Serializable{

    CompanyController companyController;
    private List<Company> items = null;
    private Company selected;
    private Trip trip = null;
    TripBusiness tripBusiness;
    PlaceBusiness placeBusiness;
    private Place place = null;
    /**
     * Creates a new instance of CompanyManagedBean
     */
    public CompanyManagedBeanForClient() {
        companyController = new CompanyController();
        selected = new Company();
        tripBusiness = new TripBusiness();
        trip = new Trip();
        placeBusiness = new PlaceBusiness();
        place= new Place();
    }
    
//_________________________  setter and getter _______________________________//
    
    public CompanyController getCompanyController() {
        return companyController;
    }

    public List<Company> getItems() {
        if (items == null) {
            try {
                items = companyController.getValidateCompanyForClient();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return items;
    }

    public Company getSelected() {
        return selected;
    }

    public Trip getTrip() {
        return trip;
    }

    public TripBusiness getTripBusiness() {
        return tripBusiness;
    }

    public PlaceBusiness getPlaceBusiness() {
        return placeBusiness;
    }

    public Place getPlace() {
        return place;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setTripBusiness(TripBusiness tripBusiness) {
        this.tripBusiness = tripBusiness;
    }

    public void setPlaceBusiness(PlaceBusiness placeBusiness) {
        this.placeBusiness = placeBusiness;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
    
    public void setCompanyController(CompanyController companyController) {
        this.companyController = companyController;
    }

    public void setItems(List<Company> items) {
        this.items = items;
    }

    public void setSelected(Company selected) {
        this.selected = selected;
    }
//___________________________________________________________________________//

    public void create() {
        if (getSelected() != null) {
            selected.setLastSeen(new Timestamp(System.currentTimeMillis()));
            selected.setValidated(false);
            selected.setActive(true);
            try {
                companyController.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
    }

//_________________________  managed Bean Functions _________________________//
    
  
    public Company getCompany(java.lang.Integer id) {
        return companyController.showSpecificInfo(id);
    }
    
    // --------------------------- for page --------------------------------//

    
    public void prepareViewCompany(int id)
    {
        selected = companyController.showSpecificInfo(id);
        try {
           selected=companyController.gitAllCompanyLists(selected);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBeanForClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    //_________________  for button in admin pages   ________________________//
    public String goToViewTrip(int id) {
        trip = tripBusiness.showSpecificInfo(id);
        try {
            trip = tripBusiness.gitAllCompanyLists(trip);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewTripClient";
    }
    public String placeDetails(int id) {
        place = placeBusiness.showSpecificInfo(id);
        try {
            place = placeBusiness.getComments(place);
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewPlaceClient";
    }
    public String goToCompanies(){
        return "compaies";
    }
    //_________________  for button in client pages   ________________________//
    
    public String goToViewCompanyForClient(int id) {
        prepareViewCompany(id);
        return "viewCompanyClient";
    }
    public String save() {
        create();
        items = getItems();
        selected = new Company();
        return "compaies";
    }
}
