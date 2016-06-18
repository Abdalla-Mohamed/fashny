/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ServiceCategoryBusiness;
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
@ManagedBean(name = "serviceCatBean")
@SessionScoped
public class ServiceCategoryMB {

    ServiceCategoryBusiness catBusiness;
    private ServiceCategorey serviceCat;
    private ServiceCategorey category;
    private List<ServiceCategorey> serviceCatList = null;
    private List<ServiceCategorey> serviceFilteredList;

    public ServiceCategoryMB() {
        catBusiness = new ServiceCategoryBusiness();
    }
//Setters

    public void setCategory(ServiceCategorey category) {
        this.category = category;
    }

    public ServiceCategorey getCategory() {
        return category;
    }

    public void setCatBusiness(ServiceCategoryBusiness catBusiness) {
        this.catBusiness = catBusiness;
    }

    public void setServiceCatList(List<ServiceCategorey> serviceCatList) {
        this.serviceCatList = serviceCatList;
    }

    public void setServiceFilteredList(List<ServiceCategorey> serviceFilteredList) {
        this.serviceFilteredList = serviceFilteredList;
    }
    //Getters

    public ServiceCategoryBusiness getCatBusiness() {
        return catBusiness;
    }

    public void setServiceCat(ServiceCategorey serviceCat) {
        this.serviceCat = serviceCat;
    }

    public ServiceCategorey getServiceCat() {
        return serviceCat;
    }

    public List<ServiceCategorey> getServiceCatList() {
        return serviceCatList;
    }

    public List<ServiceCategorey> getServiceFilteredList() {
        return serviceFilteredList;
    }

    public List<ServiceCategorey> getItems() {
        if (serviceCatList == null) {
            try {
                serviceCatList = catBusiness.view();
            } catch (Exception ex) {
                Logger.getLogger(TagManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return serviceCatList;
    }

    public void setItems(List<ServiceCategorey> services) {
        this.serviceCatList = services;
    }

    public ServiceCategorey prepareCreate() {
        serviceCat = new ServiceCategorey();
        return serviceCat;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void create() {
        if (getCatBusiness() != null) {
            try {
                catBusiness.add(serviceCat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update() {
        if (catBusiness != null) {
            try {
                catBusiness.update(serviceCat);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ServiceCategorey getServiceCategorey(java.lang.Integer id) {
        return catBusiness.showSpecificInfo(id);
    }

//    public List<Service> getItemsAvailableSelectMany() {
//        List<Service> services = null;
//        try {
//            serviceList = serviceBusiness.view();
//        } catch (Exception ex) {
//            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return serviceList;
//    }
//
//    public List<Service> getItemsAvailableSelectOne() {
//        List< Tag> tagsList = null;
//        try {
//            serviceList = serviceBusiness.view();
//        } catch (Exception ex) {
//            Logger.getLogger(TagManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return serviceList;
//    }
    public void onRowEdit(RowEditEvent event) {
        serviceCat = (ServiceCategorey) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Category Edited", ((ServiceCategorey) event.getObject()).getName());
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

//    @FacesConverter(forClass = Service.class)
//    public static class ServiceControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            ServiceManagedBean ManagedBean controller = (ServiceManagedBean) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "tagAdminBean");
//            return controller.getTag(getKey(value));
//        }
//
//        java.lang.Integer getKey(String value) {
//            java.lang.Integer key;
//            key = Integer.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(java.lang.Integer value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Tag) {
//                Tag t = (Tag) object;
//                return getStringKey(t.getId());
//            } else {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tag.class.getName()});
//                return null;
//            }
//        }
//
//    }
}
