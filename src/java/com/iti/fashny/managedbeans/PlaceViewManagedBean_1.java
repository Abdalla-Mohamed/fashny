/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.ReviewPlaceBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.ClientReviewPlace;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import javax.faces.bean.ManagedBean;
import java.io.*;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
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

    //--------------------attribute
    //<editor-fold defaultstate="collapsed" desc="Attribute">
    PlaceBusiness placeBusiness;
    private List<Place> items = null;
    private Place selected;
    private List<Place> filteredItems;
    private List<String> imagesList;
    private StreamedContent img;
    private MapModel draggableModel;
    private MapModel viewMap;

    private List<Tag> updatedTags ;
    private Resouce selectedPic;
    LatLng latLng;
    private ClientReviewPlace clientReviewPlace;
    private Marker marker;
    private double lat;
    private double lng;
    UploadImage uploadImage;

    //</editor-fold>
    //--------------------getter setter
    //<editor-fold defaultstate="collapsed" desc="Getter&Setter">
    public ClientReviewPlace getClientReviewPlace() {
        return clientReviewPlace;
    }

    public void setClientReviewPlace(ClientReviewPlace clientReviewPlace) {
        this.clientReviewPlace = clientReviewPlace;
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

    public List<Place> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Place> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public MapModel getViewMap() {
        if (selected != null) {
            LatLng coord1 = new LatLng(selected.getAttd(), selected.getLang());
            viewMap.addOverlay(new Marker(coord1, ""));
        } else {
        }
        return viewMap;
    }

    public void setViewMap(MapModel viewMap) {
        this.viewMap = viewMap;
    }

    public List<Place> getItems() {
        try {
            items = placeBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
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

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public PlaceBusiness getPlaceBusiness() {
        return placeBusiness;
    }

    public void setPlaceBusiness(PlaceBusiness placeBusiness) {
        this.placeBusiness = placeBusiness;
    }

    public Place getPlace(java.lang.Integer id) {
        return placeBusiness.showSpecificInfo(id);
    }

    public void setUpdatedTags(List<Tag> updatedTags) {
        this.updatedTags = updatedTags;
    }
    
    public List<Tag> getUpdatedTags() {
        return updatedTags;
    }
    
    
    public List<String> getImagesList() {
        imagesList = new ArrayList<>();
        List<Resouce> resouceList = new ArrayList<>();
        if (selected != null) {

            try {
                resouceList = placeBusiness.getResources(selected).getResouceList();
                for (Resouce resouceList1 : resouceList) {
                    imagesList.add(resouceList1.getPath());
                }
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return imagesList;
    }
  public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public UploadImage getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

    public Resouce getSelectedPic() {
        return selectedPic;
    }

    public void setSelectedPic(Resouce selectedPic) {
        this.selectedPic = selectedPic;
    }

    //</editor-fold>
//--------------------contructor
    public PlaceViewManagedBean_1() {
        placeBusiness = new PlaceBusiness();
        draggableModel = new DefaultMapModel();
        viewMap = new DefaultMapModel();
        selected = new Place();
        clientReviewPlace = new ClientReviewPlace();
        uploadImage = new UploadImage();
        updatedTags = new ArrayList<>();
    }

    public String placeDetails(int id) {
        selected = placeBusiness.showSpecificInfo(id);
       
        try {
            selected = placeBusiness.getComments(selected);
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "PlaceDetails";
    }

    
    public List<ClientReviewPlace> reviewPlaces() {
        List<ClientReviewPlace> clientReviewPlaceList = new ArrayList<>();
        try {
            clientReviewPlaceList = placeBusiness.getComments(selected).getClientReviewPlaceList();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientReviewPlaceList;
    }

    public List<Trip> getTripsList() {
        List<Trip> tripList = new ArrayList<>();
        
            try {
                tripList = placeBusiness.getTrips(selected).getTripList();
            } catch (Exception ex) {
                // Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        
        return tripList;
    }
    
    public void count() {
        int size = draggableModel.getMarkers().size();
        System.out.println("---->> " + size);
    }

    public String goToViewPlace(int id) {
        
        selected = placeBusiness.showSpecificInfo(id);
        updatedTags.clear();
        updatedTags.addAll(selected.getTagList());
        return "ViewPlacePage";

    }

    public String goToCreatePlace() {
        selected = new Place();
        return "CreatePlacePage";
    }

    public void onPointSelect(PointSelectEvent event) {
        latLng = event.getLatLng();
        selected.setAttd(latLng.getLat());
        selected.setLang(latLng.getLng());
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Lat:" + latLng.getLat() + ", Lng:" + latLng.getLng(), "Point Selected"));
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Place prepareCreate() {
        selected = new Place();

        return selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
//
//    public List<Tag> getTagsOfPlace() {
//        List<Tag> tags = new ArrayList<Tag>(0);
//        for (int i = 0; i < tagsIds.size(); i++) {
//
//            //System.out.println(tagsIds.get(i));
//            String s = tagsIds.get(i) + "";
//            int idValue = Integer.parseInt(s);
//            //System.out.println(tagsIds.get(i));
//            tags.add(new Tag((idValue)));
//        }
//        return tags;
//    }

    public void create() {
        if (getSelected() != null) {
            try {
                //           selected.setTagList(getTagsOfPlace());
                placeBusiness.add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            uploadImage.forPlace(selected.getId() + "");
            //uploadImage.handleFileUpload();
        }
    }
    public String update() {
        String next = null;
        if (selected != null) {
            try {
                selected.setTagList(updatedTags);               
                placeBusiness.update(selected);
                items = getItems();
                updatedTags.clear();
                next = "adminPlace_1";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return next;
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

    public void comment(Client client) {
        System.out.println("comment method");
        if (selected != null) {

            clientReviewPlace.setPlaceId(selected);
            clientReviewPlace.setClientId(client);
            ReviewPlaceBusiness reviewPlaceBusiness = new ReviewPlaceBusiness();
            reviewPlaceBusiness.review(clientReviewPlace);
            clientReviewPlace = new ClientReviewPlace();
        }
    }

    //---------------------converter
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

    //______________________________
    public String save() {
        create();
        items = getItems();
        selected = new Place();
        return "adminPlace_1";
    }

    public String cancel() {
        selected = new Place();
        return "adminPlace_1";
    }

    public String goToImages(int id) {
        System.out.println(id);
        selected = placeBusiness.showSpecificInfo(id);
        return "managePlaceImages?faces-redirect=true";

    }

    public void handleFileUpload(FileUploadEvent event) {
        placeBusiness.addImageToPlace(event.getFile(), selected);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("imagTable");
        context.execute("PF('uploadImage').hide()");

        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteRecource() {
        boolean deleteImageFromPlace = placeBusiness.deleteImageFromPlace(selectedPic);
        if (deleteImageFromPlace) {
            selected.getResouceList().remove(selectedPic);
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("imagTable");

            FacesMessage message = new FacesMessage("delete Succesfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getFirstImg(Place place) {
        String path = "0";
        if (place.getResouceList() != null && !place.getResouceList().isEmpty()) {
            path = place.getResouceList().get(0).getPath();
        }
        return path;
    }
   
     public String getFirstImg2(Place place) {
         selected=placeBusiness.showSpecificInfo(selected.getId());
        String path = "0";
        if (place.getResouceList() != null && !place.getResouceList().isEmpty()) {
            path = place.getResouceList().get(0).getPath();
        }
        return path;
    }
    //_________________________get valid and active places_____________________
    
     public List<Place> getActiveItems() {
        try {
            items = placeBusiness.viewActive();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
     
    //_________________________get valid and active places_____________________

}
