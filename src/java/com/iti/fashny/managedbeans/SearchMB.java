/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.TagsController;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Tag;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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
public class SearchMB {

    String selectType;
    
    TagsController tagsController;
    CompanyController companyController;
    
    List<String> governorate;

    List<Tag> selectedTags;
    Company selectedCompany;
     /**
     * Creates a new instance of SearchMB
     */
    public SearchMB() {
        tagsController = new TagsController();
        companyController = new CompanyController();
        governorate = new ArrayList<>();

    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
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
        List<Company> companies = new ArrayList<>();
        try {
            companies = companyController.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return companies;
    }

    public List<String> getGovernorate() {
        if (governorate.isEmpty()) {
            fillbndlGovernorateList();
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

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }
    
    
    

}
