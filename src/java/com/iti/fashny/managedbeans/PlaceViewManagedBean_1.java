/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import java.util.*;
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
import org.primefaces.model.StreamedContent;
import java.awt.image.BufferedImage;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultStreamedContent;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.faces.context.ExternalContext;
import javax.imageio.ImageIO;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "placeView_1")
@ViewScoped
public class PlaceViewManagedBean_1 implements Serializable{

    PlaceBusiness placeBusiness;
    private List<Place> items = null;
    private Place selected;
    private List<Place> filteredItems;
    private List<StreamedContent> imagesList;
    private StreamedContent im;

    public StreamedContent getIm() {
        InputStream input = null;
        try {
            File f = new File("C:\\images\\pic.jpg");
            input = new FileInputStream(f);
            im = new DefaultStreamedContent(input, "image/jpeg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return im;

    }

    public void setIm(StreamedContent im) {
        this.im = im;
    }

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
        selected = (Place) event.getObject();
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

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<StreamedContent> getImagesList() {
        imagesList = new ArrayList();
        StreamedContent imageRes;
        try {
            System.out.println(selected.getName());

            for (Resouce resouce : selected.getResouceList()) {
                File f = new File(resouce.getPath());
                InputStream input = new FileInputStream(f);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                imageRes = new DefaultStreamedContent(input, externalContext.getMimeType(f.getName()), f.getName());

                imageRes = new DefaultStreamedContent(input, "image/jpeg");
                imagesList.add(imageRes);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagesList;
    }

    public void setImagesList(List<StreamedContent> imagesList) {
        this.imagesList = imagesList;
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
