/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
//import javax.faces.view.ViewScoped;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "placeView_1")
@ViewScoped
public class PlaceViewManagedBean_1 {

    PlaceBusiness placeBusiness;
    private List<Place> items = null;
    private Place selected;
    private List<Place> filteredItems;

    public List<Place> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Place> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public PlaceViewManagedBean_1() {
        placeBusiness = new PlaceBusiness();
    }

    public PlaceBusiness getPlaceBusiness() {
        return placeBusiness;
    }

    public void setPlaceBusiness(PlaceBusiness placeBusiness) {
        this.placeBusiness = placeBusiness;
    }

    public List<Place> getItems() {
        if (items == null) {
            try {
                items = placeBusiness.view();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return items;
    }

    public void setItems(List<Place> items) {
        this.items = items;
    }

    public Place getSelected() {
        return selected;
    }

    public void setSelected(Place selected) {
        this.selected = selected;
    }

    public Place prepareCreate() {
        selected = new Place();
//        initializeEmbeddableKey();
        return selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void create() {
        if (getSelected() != null) {
            try {
                placeBusiness.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update() {
        if (selected != null) {
            try {
                placeBusiness.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void destroy() {
        if (selected != null) {
            try {
                selected.setActive(Boolean.FALSE);
                placeBusiness.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Place getPlace(java.lang.Integer id) {
        return placeBusiness.showSpecificInfo(id);
    }

    public List<Place> getItemsAvailableSelectMany() {
        List<Place> placesList = null;
        try {
            placesList = placeBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return placesList;
    }

    public List<Place> getItemsAvailableSelectOne() {
        List<Place> placesList = null;
        try {
            placesList = placeBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return placesList;
    }
    
    
     public void onRowEdit(RowEditEvent event) {
         selected=(Place) event.getObject();
         update();
        FacesMessage msg = new FacesMessage("Place Edited", ((Place) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Place) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    @FacesConverter(forClass = Place.class)
    public static class PlaceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlaceViewManagedBean_1 controller = (PlaceViewManagedBean_1) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "placeView_1");
            return controller.getPlace(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Place) {
                Place o = (Place) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Place.class.getName()});
                return null;
            }
        }

    }
}
