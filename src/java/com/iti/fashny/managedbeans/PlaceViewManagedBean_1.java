/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.TagBusiness;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class PlaceViewManagedBean_1 implements Serializable {

    PlaceBusiness placeBusiness;
    private List<Place> items = null;
    private Place selected;
    private MapModel draggableModel;
    private MapModel viewMap;
    LatLng latLng;

    private Marker marker;
    private double lat;

    private double lng;

    public String goToViewPlace(int id) {
        System.out.println("==========");
        selected = placeBusiness.showSpecificInfo(id);
        System.out.println("==========" + selected.getName());
        return "ViewPlacePage";
    }
    public String goToCreatePlace() {
        
        return "CreatePlacePage";
    }
    private List<Place> filteredItems;
    private String tst;
    int id;

    public String placeDetails(int id) {
        selected = placeBusiness.showSpecificInfo(id);
        return "Client_Places";
    }

    public void setTst(String tst) {
        this.tst = tst;
    }

    public String getTst() {
        return tst;
    }

    public List<Place> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Place> filteredItems) {
        this.filteredItems = filteredItems;
    }

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
        viewMap = new DefaultMapModel();
        selected = new Place();
    }

    public void onPointSelect(PointSelectEvent event) {
        System.out.println("#####################");
        latLng = event.getLatLng();
        selected.setAttd(latLng.getLat());
        selected.setLang(latLng.getLng());
        System.out.println(latLng);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Lat:" + latLng.getLat() + ", Lng:" + latLng.getLng(), "Point Selected"));
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public MapModel getViewMap() {
       if (selected != null)
       { System.out.println("get map ......."+selected.getAttd()+ selected.getLang());
       LatLng coord1 = new LatLng(selected.getAttd(), selected.getLang());
       viewMap.addOverlay(new Marker(coord1, ""));
       }
       else 
       {System.out.println("N00000000``````");}
        return viewMap;
    }

    public void setViewMap(MapModel viewMap) {
        this.viewMap = viewMap;
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
        if (getSelected() != null) {
            try {
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
//                setEmbeddableKeys();
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
//                setEmbeddableKeys();
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
