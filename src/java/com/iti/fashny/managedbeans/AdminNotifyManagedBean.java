/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdminManager;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.*;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "adminNotify")
@RequestScoped
public class AdminNotifyManagedBean {

    private AdminManager adminManager;
    private List<Company> companiesList;
    private List<Place> placesList;
    private List<Trip> tripsList;
    private List<Tag> tagsList;

    public AdminNotifyManagedBean() {
        adminManager = new AdminManager();
        companiesList = new ArrayList<>();
        placesList = new ArrayList<>();
        tripsList = new ArrayList<>();
        tagsList = new ArrayList<>();
    }
//<editor-fold defaultstate="collapsed" desc="Getter & Setter">

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    public List<Company> getCompaniesList() {
        companiesList = getAdminManager().findAllUncofirmCompany();
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    public List<Place> getPlacesList() {
        placesList = getAdminManager().findAllUncofirmPlaces();
        return placesList;
    }

    public void setPlacesList(List<Place> placesList) {
        this.placesList = placesList;
    }

    public List<Trip> getTripsList() {
        tripsList = getAdminManager().findAllUncofirmTrips();
        return tripsList;
    }

    public void setTripsList(List<Trip> tripsList) {
        this.tripsList = tripsList;
    }

    public List<Tag> getTagsList() {
        tagsList = getAdminManager().findAllUncofirmTags();
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }

//</editor-fold>
    public String notifyCount() {
        int size = getPlacesList().size()+ getCompaniesList().size() + getTagsList().size() + getTripsList().size();
        return size + "";
    }
}
