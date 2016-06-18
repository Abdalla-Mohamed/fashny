/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ClientJoinTripBusiness;
import com.iti.fashny.businessbeans.JoinTripBuisinesss;
import com.iti.fashny.businessbeans.TripBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.JoinTrip;
import com.iti.fashny.entities.JoinTripPK;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Trip;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
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
    private JoinTrip clientJoinTrip;
    private JoinTripPK joinTripPK;
    private JoinTripBuisinesss joinTripBuisinesss;
    //_______________________________________________________________________//

    public TripManagedBean() {
        tripBusiness = new TripBusiness();
        selected = new Trip();
        clientJoinTrip = new JoinTrip();
        joinTripPK=new JoinTripPK();
        joinTripBuisinesss = new JoinTripBuisinesss();
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

    public void setTripBusiness(TripBusiness tripBusiness) {
        this.tripBusiness = tripBusiness;
    }

    public void setItems(List<Trip> items) {
        this.items = items;
    }

    public void setSelected(Trip selected) {
        this.selected = selected;
    }

    public JoinTrip getClientJoinTrip() {
        return clientJoinTrip;
    }

    public void setClientJoinTrip(JoinTrip clientJoinTrip) {
        this.clientJoinTrip = clientJoinTrip;
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

    public void joinTrip(Client client) {
        System.out.println("jointrip method");
        if (selected != null) {
            
            joinTripPK.setClientId(client.getId());
            joinTripPK.setTripid(selected.getId());

            clientJoinTrip.setJoinTripPK(joinTripPK);

            ClientJoinTripBusiness clientJoinTripBusiness = new ClientJoinTripBusiness();
            clientJoinTripBusiness.joinTrip(clientJoinTrip,selected);

            clientJoinTrip = new JoinTrip();
            joinTripPK=new JoinTripPK();
           
        }
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

    public void creatByCompany(Company company) {
        selected.setCompanyId(company);
        save();
    }

    public String cansel() {
        selected = new Trip();
        return "trips";
    }
    
    
    //_______________view trip rate__________________//
    
    public int getRate(Trip trip){
       
        return joinTripBuisinesss.tripRate(trip);
    }
}
