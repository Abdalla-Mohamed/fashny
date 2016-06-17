/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ClientBusiness;
import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.TripBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Trip;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name = "clientOfCompanyTrips")
@SessionScoped
public class ClientOfCompanyTrips implements Serializable {

    private Company selected;
    CompanyController companyController;
    private Trip trip = null;
    TripBusiness tripBusiness;
    PlaceBusiness placeBusiness;
    private Place place = null;
    ClientBusiness clientBusiness;
    private Client client = null;
    

    @ManagedProperty(value = "#{login}")
    private LoginManagedBean loginManagedBean;

    /**
     * Creates a new instance of ClientOfCompanyTrips
     */
    public ClientOfCompanyTrips() {
        companyController = new CompanyController();
        selected = new Company();
        tripBusiness = new TripBusiness();
        trip = new Trip();
        placeBusiness = new PlaceBusiness();
        place= new Place();
        clientBusiness = new ClientBusiness();
        client = new Client();
    }

    @PostConstruct
    public void init() {
        selected = loginManagedBean.getLoginAccount().getCompany();
        prepareToViewCompany(selected);

    }

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }

    public LoginManagedBean getLoginManagedBean() {
        return loginManagedBean;
    }

    public CompanyController getCompanyController() {
        return companyController;
    }

    public void setCompanyController(CompanyController companyController) {
        this.companyController = companyController;
    }

    public Trip getTrip() {
        return trip;
    }

    public TripBusiness getTripBusiness() {
        return tripBusiness;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setTripBusiness(TripBusiness tripBusiness) {
        this.tripBusiness = tripBusiness;
    }

    public PlaceBusiness getPlaceBusiness() {
        return placeBusiness;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlaceBusiness(PlaceBusiness placeBusiness) {
        this.placeBusiness = placeBusiness;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public ClientBusiness getClientBusiness() {
        return clientBusiness;
    }

    public Client getClient() {
        return client;
    }

    public void setClientBusiness(ClientBusiness clientBusiness) {
        this.clientBusiness = clientBusiness;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    public Company getSelected() {
        return selected;
    }

    public void setSelected(Company selected) {
        this.selected = selected;
    }
    //_______________________________________________________________________//

    public void prepareToViewCompany(Company company) {
          try {
            selected = companyController.gitAllCompanyLists(company);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String goToViewTrip(int id) {
        trip = tripBusiness.showSpecificInfo(id);
        try {
            trip = tripBusiness.gitAllCompanyLists(trip);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewTrip";
    }
    public String placeDetails(int id) {
        place = placeBusiness.showSpecificInfo(id);
        try {
            place = placeBusiness.getComments(place);
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewPlace";
    }
    public String clientDetails(int id) {
        client = clientBusiness.showSpecificInfo(id);
        return "viewClient";
    }
    public String testRouting(){
        return "viewClient";
    }
    
    
}
