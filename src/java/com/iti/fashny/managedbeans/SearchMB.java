/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.SearchManager;
import com.iti.fashny.businessbeans.TagsController;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abdalla
 */
@ManagedBean(name = "searchMB")
@ViewScoped
public class SearchMB implements Serializable {

    SearchType selectType;

    TagsController tagsController;
    CompanyController companyController;

    List<String> governorate;
    String selectdGovernorat;

    List<Tag> selectedTags;
    List<Company> selectedCompanies;

    String nameSearch;

    SearchEngine searchEngine = new SearchManager();

    List<Place> placesResult;

    /**
     * Creates a new instance of SearchMB
     */
    public SearchMB() {
        tagsController = new TagsController();
        companyController = new CompanyController();
        governorate = new ArrayList<>();
        selectType = SearchType.Place;

    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getSelectdGovernorat() {
        return selectdGovernorat;
    }

    public void setSelectdGovernorat(String selectdGovernorat) {
        this.selectdGovernorat = selectdGovernorat;
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
                break;
            case Tag:
                break;
        }
    }

    private void searchForPlaces() {
        Place placeExample = new Place();
        placeExample.setName(nameSearch.isEmpty()?null:nameSearch);
        placeExample.setAddress(selectdGovernorat.isEmpty()?null:selectdGovernorat);
        placeExample.setTagList(selectedTags);
        try {
            placesResult = getSearchEngine().searchByExample(placeExample);
            System.out.println(placesResult.size()+"*************************************");
        } catch (Exception ex) {
            Logger.getLogger(SearchMB.class.getName()).log(Level.SEVERE, null, ex);
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
