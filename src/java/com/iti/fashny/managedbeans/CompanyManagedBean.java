/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name="CompanyMB")
@ViewScoped
public class CompanyManagedBean {

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
            try {
                companyController.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println(" --- xxxxx ----");
        }
    }

    public void update() {
        if (selected != null) {
            try {
//                setEmbeddableKeys();
                companyController.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println(" --- yyyyy ----");
        }
    }

    public void destroy() {
        if (selected != null) {
            try {
//                setEmbeddableKeys();
                selected.setActive(Boolean.FALSE);
                companyController.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Company getPlace(java.lang.Integer id) {
        return companyController.showSpecificInfo(id);
    } 
}