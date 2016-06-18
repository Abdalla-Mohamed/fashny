/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ServiceCategoryBusiness;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Service;
import com.iti.fashny.entities.ServiceCategorey;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryManagedBean {

    ServiceCategoryBusiness categoryBusiness;
    private List<ServiceCategorey> categoryList;
    private List<ServiceCategorey> filteredCategory;
    private List<Partener> partnerList;
    private ServiceCategorey categoryObj;
    private PartnerCRUDSBean partnerBean;

    public CategoryManagedBean() {
        categoryBusiness = new ServiceCategoryBusiness();

    }

    public void setCategoryBusiness(ServiceCategoryBusiness categoryBusiness) {
        this.categoryBusiness = categoryBusiness;
    }

    public void setCategoryList(List<ServiceCategorey> categoryList) {
        this.categoryList = categoryList;
    }

    public void setFilteredCategory(List<ServiceCategorey> filteredCategory) {
        this.filteredCategory = filteredCategory;
    }

    public void setPartnerList(List<Partener> partnerList) {
        this.partnerList = partnerList;
    }

    public void setCategoryObj(ServiceCategorey categoryObj) {
        this.categoryObj = categoryObj;
    }

    public ServiceCategoryBusiness getCategoryBusiness() {
        return categoryBusiness;
    }

    public List<ServiceCategorey> getCategoryList() {
        return categoryList;
    }

    public List<ServiceCategorey> getFilteredCategory() {
        return filteredCategory;
    }

    public List<Partener> getPartnerList() {
        return partnerList;
    }

    public ServiceCategorey getCategoryObj() {
        return categoryObj;
    }

    public List<ServiceCategorey> getItems() {

        try {
            categoryList = categoryBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoryList;
    }

    public void setItems(List<ServiceCategorey> services) {
        this.categoryList = services;
    }

    public List<Partener> getPartners() {
        partnerList = partnerBean.getItems();
        return partnerList;
    }

    public void setPartner(List<Partener> partnerList) {
        this.partnerList = partnerList;
    }

    public void prepareCreate() {
        categoryObj = new ServiceCategorey();
        categoryObj.setPartenersId(new Partener());
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void create() {
        if (getCategoryObj() != null) {
            try {
                categoryBusiness.add(categoryObj);
                prepareCreate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String save() {
        System.out.println("===================");
        create();
        categoryList = getItems();
        categoryObj = new ServiceCategorey();
        return "Services";
    }

    public void update() {
        if (categoryObj != null) {
            try {
                categoryBusiness.update(categoryObj);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void onRowEdit(RowEditEvent event) {
        categoryObj = (ServiceCategorey) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Service Category Edited", ((ServiceCategorey) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((ServiceCategorey) event.getObject()).getName());
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

}
