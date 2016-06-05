/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name="CompanyMB" )
@SessionScoped
public class CompanyManagedBean implements Serializable{

    CompanyController companyController;
    private List<Company> items = null;
    private Company selected;
    /**
     * Creates a new instance of CompanyManagedBean
     */
    public CompanyManagedBean() {
        companyController = new CompanyController();
        selected = new Company();
    }
    
//_________________________  setter and getter _______________________________//
    
    public CompanyController getCompanyController() {
        return companyController;
    }

    public List<Company> getItems() {
        if (items == null) {
            try {
                items = companyController.view();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return items;
    }

    public Company getSelected() {
        return selected;
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
    
//_________________________  managed Bean Functions _________________________//
    
   public void create() {
        if (getSelected() != null) {
            selected.setLastSeen(new Timestamp(System.currentTimeMillis()));
            try {
                companyController.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
    }

    public void update() {
        if (selected != null) {
            try {
                companyController.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void destroy() {
        if (selected != null) {
            try {
                selected.setActive(Boolean.FALSE);
                companyController.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Company getCompany(java.lang.Integer id) {
        return companyController.showSpecificInfo(id);
    }
    
    // --------------------------- for page --------------------------------//
    public String goToViewCompany(int id) {
        selected = companyController.showSpecificInfo(id);
        try {
            companyController.gitTripsOfCompany(selected);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewCompany";
    }

    public String goToCreateCompany() {
        selected = new Company();
        return "createCompany";
    }
    public String goToCompanies(){
        return "compaies";
    }
}
