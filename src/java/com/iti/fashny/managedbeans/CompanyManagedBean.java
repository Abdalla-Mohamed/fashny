/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
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
@ManagedBean(name = "CompanyMB")
@SessionScoped
public class CompanyManagedBean implements Serializable {

    CompanyController companyController;
    private List<Company> items = null;
    private Company selected;
    UploadImage uploadImage;

    /**
     * Creates a new instance of CompanyManagedBean
     */
    public CompanyManagedBean() {
        companyController = new CompanyController();
        selected = new Company();
        uploadImage = new UploadImage();
    }

//_________________________  setter and getter _______________________________//
    public CompanyController getCompanyController() {
        return companyController;
    }

    public List<Company> getItems() {

        try {
            items = companyController.view();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }

    public Company getSelected() {
        return selected;
    }

    public UploadImage getUploadImage() {
        return uploadImage;
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

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }
//_________________________  managed Bean Functions _________________________//

    public void signup() {
        if (getSelected() != null) {
            selected.setLastSeen(new Timestamp(System.currentTimeMillis()));
            selected.setValidated(false);
            selected.setActive(true);
            try {
                companyController.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //new guestImpl().signUp(c);
            uploadImage.forCompany(selected.getId()+"");
            uploadImage.handleFileUpload();
        }
    }

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
            System.out.println("=====update===");
        }
    }

    public void destroy() {
        if (selected != null) {
            try {
                selected.setActive(Boolean.FALSE);
                companyController.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Company getCompany(java.lang.Integer id) {
        return companyController.showSpecificInfo(id);
    }

    // --------------------------- for page --------------------------------//
    public void onRowEdit(RowEditEvent event) {
        selected = (Company) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Company Edited", ((Company) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Company) event.getObject()).getName());
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

    public void prepareViewCompany(int id) {
        selected = companyController.showSpecificInfo(id);
        try {
            selected = companyController.gitAllCompanyLists(selected);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //_________________  for button in admin pages   ________________________//
    public String goToViewCompany(int id) {
        // prepareViewCompany(id);
        selected = companyController.showSpecificInfo(id);
        try {
            selected = companyController.gitAllCompanyLists(selected);
        } catch (Exception ex) {
            Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "viewCompany";
    }

    public String goToCreateCompany() {
        selected = new Company();
        return "createCompany";
    }

    public String save() {
        create();
        items = getItems();
        selected = new Company();
        return "compaies";
    }

    public String goToCompanies() {
        return "compaies";
    }
    //_________________  for button in client pages   ________________________//

    public String goToViewCompanyForClient(int id) {
        prepareViewCompany(id);
        return "viewCompanyClient";
    }

    //___________________________ for client _______________________________//
    public String saveForClient() {
        signup();
        items = getItems();
        selected = new Company();
        return "login";
    }

    public String cancelForClient() {
        selected = new Company();
        return "home";
    }
    
    public String editCompany()
    {
        update();
        System.out.println("------ edit------");
        return "compaies";
    }
}
