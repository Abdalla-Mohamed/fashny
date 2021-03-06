/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.businessbeans.ClientBusiness;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Resouce;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "adminClient")
@SessionScoped
public class AdminClientManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Attribute">
    ClientBusiness clientBusiness;
    private List<Client> items = null;
    private Client selected;
    private List<Client> filteredItems;
    private Resouce resouce;
    private UploadedFile file;
    private UploadImage uploadImage;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    public ClientBusiness getClientBusiness() {
        return clientBusiness;
    }

    public void setClientBusiness(ClientBusiness clientBusiness) {
        this.clientBusiness = clientBusiness;
    }

    public List<Client> getItems() {
        if (items == null) {
            try {
                items = clientBusiness.view();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return items;
    }

    public void setItems(List<Client> items) {
        this.items = items;
    }

    public Client getSelected() {
        return selected;
    }

    public void setSelected(Client selected) {
        this.selected = selected;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Client> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Client> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public Client getClient(java.lang.Integer id) {
        return clientBusiness.showSpecificInfo(id);
    }

    public UploadImage getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

//</editor-fold>
    //-----------constructor
    public AdminClientManagedBean() {
        clientBusiness = new ClientBusiness();
        selected = new Client();
    }

    public List<Client> viewItems() {
        List<Client> clients = null;
        if (clients == null) {
            try {
                items = clientBusiness.view();
                clients=clientBusiness.view();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clients;
    }

    public Client prepareCreate() {
        selected = new Client();
        return selected;
    }

    public String create() {
        String next = null;
        if (getSelected() != null) {
            try {
                selected.setLastSeen(new Timestamp(System.currentTimeMillis()));
                clientBusiness.add(selected);
                next = "adminClient";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return next;
    }

    public String update() {
        String next = null;
        if (selected != null) {
            try {
                clientBusiness.update(selected);
                next = "adminClient";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return next;
    }

    public List<Client> getItemsAvailableSelectMany() {
        List<Client> clientList = null;
        try {
            clientList = clientBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientList;
    }

    public List<Client> getItemsAvailableSelectOne() {
        List<Client> clientList = null;
        try {
            clientList = clientBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientList;
    }

    public String goToViewClient(int id) {
        selected = clientBusiness.showSpecificInfo(id);
        return "ViewClient";
    }

    public String goToCreateClient() {
        selected = new Client();
        return "CreateClient";
    }

    public void getFileData(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void vMailUniqe(FacesContext context, UIComponent comp, Object value) {

        FacesMessage message = new FacesMessage("in valid mail");
        context.addMessage(comp.getClientId(context), message);

    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        clientBusiness.addImageToClient(event.getFile(), selected);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("picForm");
        context.execute("PF('uploadImage').hide()");

        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //<editor-fold defaultstate="collapsed" desc="update in table">
    public void onRowEdit(RowEditEvent event) {
        selected = (Client) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Client Edited", ((Client) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Client) event.getObject()).getName());
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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="converter">
    @FacesConverter(forClass = Client.class)
    public static class ClientControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdminClientManagedBean controller = (AdminClientManagedBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "clientController");
            return controller.getClient(getKey(value));
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
            if (object instanceof Client) {
                Client o = (Client) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Client.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
}
