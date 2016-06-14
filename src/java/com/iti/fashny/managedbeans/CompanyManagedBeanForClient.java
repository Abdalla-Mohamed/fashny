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
    /**
     * Creates a new instance of CompanyManagedBean
     */
    public CompanyManagedBeanForClient() {
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
