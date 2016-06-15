/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ServicesBusiness;
import com.iti.fashny.entities.Service;
import com.iti.fashny.entities.ServiceCategorey;
import static com.iti.fashny.entities.Service_.categoreyServiceid;
import com.iti.fashny.entities.Trip;
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
@ManagedBean(name = "serviceBean")
@SessionScoped

public class ServiceManagedBean {

    ServicesBusiness serviceBusiness;
    private List<Service> serviceList = null;
    private List<Service> filteredItems;
    private Service service;
    ServiceCategoryMB serviceCategory;
    List<ServiceCategorey> catList;

    public ServiceManagedBean() {
        serviceBusiness = new ServicesBusiness();
        serviceCategory = new ServiceCategoryMB();
        prepareCreate();
    }

    public List<ServiceCategorey> getCategories() {
        catList = serviceCategory.getItems();
        return catList;
    }

    public void setCatList(List<ServiceCategorey> catList) {
        this.catList = catList;
    }

    public void setCategories(List<ServiceCategorey> catList) {
        this.catList = catList;
    }

    //Setters
    public void setServiceBusiness(ServicesBusiness serviceBusiness) {
        this.serviceBusiness = serviceBusiness;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public void setFilteredItems(List<Service> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    //Getters
    public ServicesBusiness getServiceBusiness() {
        return serviceBusiness;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public List<Service> getFilteredItems() {
        return filteredItems;
    }

    public List<Service> getItems() {

        try {
            serviceList = serviceBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(ServiceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return serviceList;
    }

    public void setItems(List<Service> services) {
        this.serviceList = services;
    }

    private void prepareCreate() {
        service = new Service();
        service.setCategoreyServiceid(new ServiceCategorey());
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void create() {
        if (getService() != null) {
            try {
                serviceBusiness.add(service);
                prepareCreate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String save() {
        System.out.println("===================");
        create();
        serviceList = getItems();
        service = new Service();
        return "Services";
    }

    public void update() {
        if (service != null) {
            try {
                serviceBusiness.update(service);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
//
//    public Service getService(java.lang.Integer id) {
//        return serviceBusiness.showSpecificInfo(id);
//    }

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
        service = (Service) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Tag Edited", ((Service) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Service) event.getObject()).getName());
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
