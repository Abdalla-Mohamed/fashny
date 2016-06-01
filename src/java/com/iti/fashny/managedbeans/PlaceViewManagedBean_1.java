/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

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
    private MapModel draggableModel;
    LatLng latLng;

    private Marker marker;
    private double lat;

    private double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void count() {
        
        int size = draggableModel.getMarkers().size();
        System.out.println("---->> " + size);
    }

    public PlaceViewManagedBean_1() {
        placeBusiness = new PlaceBusiness();
        draggableModel = new DefaultMapModel();
        
//       marker = new LatLng 
        
       
    }

    public void onPointSelect(PointSelectEvent event) {
        System.out.println("#####################");

        latLng = event.getLatLng();
        System.out.println(latLng);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Lat:" + latLng.getLat() + ", Lng:" + latLng.getLng(),"Point Selected"));
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void onMarkerDrag(MarkerDragEvent event) {

        marker = event.getMarker();
        LatLng coord1 = new LatLng(marker.getLatlng().getLat(), marker.getLatlng().getLng());
        //if ((draggableModel.getMarkers()).size()==0)
        draggableModel.addOverlay(new Marker(coord1, ""));
        for (Marker premarker : draggableModel.getMarkers()) {
            premarker.setDraggable(true);
        }
//        else 
//        {
//            for (Marker mark : draggableModel.getMarkers()) {
//                mark.setVisible(false);
//                mark=null;
//            }
//            draggableModel.addOverlay(new Marker(coord1, ""));
//        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
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
        //selected = new Place(88, "zoo", "giza","first street" , 12.2, 22.2, Boolean.FALSE);

        if (getSelected() != null) {
            try {
                System.out.println("&&& --->> " + selected.getName());
                placeBusiness.add(selected);
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
                setEmbeddableKeys();
                placeBusiness.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println(" --- yyyyy ----");
        }
    }

    public void update(Place sel) {
        selected = sel;
        if (selected != null) {
            try {
                setEmbeddableKeys();
                placeBusiness.update(selected);
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
                setEmbeddableKeys();
                selected.setActive(Boolean.FALSE);
                System.out.println("  @@ -->> destrooooy");
                placeBusiness.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void destroy(Place p) {
        selected = p;
        if (selected != null) {
            try {
                setEmbeddableKeys();
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
