/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.SearchManager;
import com.iti.fashny.businessbeans.TagsController;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.SearchEngine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abdalla
 */
@ManagedBean(name = "searchMB")
@ViewScoped() 
public class SearchMB implements Serializable {

    SearchType selectType;

    TagsController tagsController;
    CompanyController companyController;
    PlaceBusiness placeBusiness;
    
    List<String> governorate;
    String address;

    List<Tag> selectedTags;
    List<Company> selectedCompanies;
   
    String nameSearch;

    SearchEngine searchEngine = new SearchManager();

    List<Place> placesResult;

    List<Trip> tripsResult;
    
    Company selectedCompany;
    
    List<Place> selectedPlaces;
    String anyChars = "%";
   
    /**
     * Creates a new instance of SearchMB
     */
    public SearchMB() {
        tagsController = new TagsController();
        companyController = new CompanyController();
        placeBusiness = new PlaceBusiness();
        governorate = new ArrayList<>();
        selectedCompany = new Company();
        selectType = SearchType.Place;
        address="";
    }

    public List<Place> getSelectedPlaces() {
        return selectedPlaces;
    }

    public void setSelectedPlaces(List<Place> selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }
        
    
    public List<Trip> getTripsResult() {
        return tripsResult;
    }

    public void setTripsResult(List<Trip> tripsResult) {
        this.tripsResult = tripsResult;
    }

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    
    
    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SearchType[] getSelectTypes() {
        return SearchType.values();
    }

    public SearchType getSelectType() {
        return selectType;
    }

    public void setSelectType(SearchType selectType) {
        this.selectType = selectType;
    }

    public List<Tag> getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(List<Tag> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        try {
            tags = tagsController.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tags;
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>(0);
        try {
            companies = this.companyController.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companies;
    }
    
    public List<Place> getPlaces(){
        List<Place> places = new ArrayList();
        
        try {
            places = this.placeBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(SearchMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return places;
    }

    public List<String> getGovernorate() {
        if (governorate.isEmpty()) {
            this.fillbndlGovernorateList();
        }
        return governorate;
    }

    public void setGovernorate(List<String> governorate) {
        this.governorate = governorate;
    }

    private void fillbndlGovernorateList() {

        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("governoratesProb", locale, loader);

//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Locale locale = facesContext.getViewRoot().getLocale();
//        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "bndlGovernorate");
        System.out.println("enum");
        Enumeration<String> enumeration = bundle.getKeys();
        while (enumeration.hasMoreElements()) {
            governorate.add(bundle.getString(enumeration.nextElement()));

        }
    }

    public void setSelectedCompanies(List<Company> selectedCompanies) {
        this.selectedCompanies = selectedCompanies;
    }

    public List<Company> getSelectedCompanies() {
        return selectedCompanies;
    }

    public void search() {
    

//        return null;
        switch (selectType) {
            case Company:
                break;
            case Place:
                searchForPlaces();
                break;
            case Trip:
                searchForTrips();
                break;
            case Tag:
                break;
        }
    }

    private void searchForPlaces() {
        Place placeExample = new Place();
        placeExample.setActive(true);
        placeExample.setValidated(true);
        placeExample.setName(nameSearch.isEmpty()?null:anyChars+nameSearch+anyChars);
        System.out.println("th address is"+address);
        placeExample.setAddress(address.isEmpty()?null:address);
        placeExample.setTagList(selectedTags);
        try {
            placesResult = getSearchEngine().searchByExample(placeExample);
            System.out.println(placesResult.size()+"*************************************");
        } catch (Exception ex) {
            Logger.getLogger(SearchMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchForTrips() {
        Trip tripExample = new Trip();
        tripExample.setValidated(true);
        tripExample.setName(nameSearch.isEmpty()?null:anyChars+nameSearch+anyChars);
        tripExample.setCompanyId(selectedCompany);
        tripExample.setTagList(selectedTags);
        tripExample.setPlaceList(selectedPlaces);
        
       // System.out.println("ddddd"+selectedCompany.getId());
       // tripExample.setPlaceList(selectedPlaces);
        //tripExample.setTagList(selectedTags);
        System.out.println("hiiii"+nameSearch);
        System.out.println(selectedPlaces.size());
        System.out.println(selectedTags.size());
        System.out.println(selectedCompany.getId());
        try {
            tripsResult = getSearchEngine().searchByExample(tripExample);
            System.out.println(tripsResult.size()+"\n*************************************");
        } catch (Exception ex) {
            Logger.getLogger(SearchMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Trip trip : tripsResult) {
            System.out.println(trip.getName());
        }
    }

    public SearchEngine getSearchEngine() {
        return searchEngine;
    }

    public boolean renderForTrip() {
        return  selectType == SearchType.Trip;
    }
    public boolean renderForPlace() {
        return  selectType == SearchType.Place;
    }
    public boolean renderForCompany() {
        return  selectType == SearchType.Company;
    }

    public List<Place> getPlacesResult() {
        return placesResult;
    }

    public void setPlacesResult(List<Place> placesResult) {
        this.placesResult = placesResult;
    }
   
    
   
    
    
}
