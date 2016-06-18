/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.TagBusiness;
import com.iti.fashny.entities.Tag;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "tagAdminBean")
@SessionScoped
public class TagManagedBean {

    TagBusiness tagBusiness;
    private List<Tag> tags = null;
    private List<Tag> filteredItems;
    private Tag selectedTag;

//****************Setters & Getters*****************
    public List<Tag> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Tag> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public TagManagedBean() {
        tagBusiness = new TagBusiness();
    }

    public TagBusiness getTagBusiness() {
        return tagBusiness;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }

    public void setTagBusiness(TagBusiness tagBusiness) {
        this.tagBusiness = tagBusiness;
    }

    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag = selectedTag;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Tag> getItems() {

        try {
            tags = tagBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(TagManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tags;
    }

    public void setItems(List<Tag> tags) {
        this.tags = tags;
    }

    public Tag prepareCreate() {
        selectedTag = new Tag();
        return selectedTag;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void create() {
        if (getSelectedTag() != null) {
            try {
                tagBusiness.add(selectedTag);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update() {
        if (selectedTag != null) {
            try {
                tagBusiness.update(selectedTag);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Tag getTag(java.lang.Integer id) {
        return tagBusiness.showSpecificInfo(id);
    }

    public List<Tag> getItemsAvailableSelectMany() {
        List<Tag> tagsList = null;
        try {
            tagsList = tagBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(TagManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tagsList;
    }

    public List< Tag> getItemsAvailableSelectOne() {
        List< Tag> tagsList = null;
        try {
            tagsList = tagBusiness.view();
        } catch (Exception ex) {
            Logger.getLogger(TagManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tagsList;
    }

    public void onRowEdit(RowEditEvent event) {
        selectedTag = (Tag) event.getObject();
        update();
        FacesMessage msg = new FacesMessage("Tag Edited", ((Tag) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Tag) event.getObject()).getName());
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

    @FacesConverter(forClass = Tag.class)
    public static class TagControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TagManagedBean controller = (TagManagedBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tagAdminBean");
            return controller.getTag(getKey(value));
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
            if (object instanceof Tag) {
                Tag t = (Tag) object;
                return getStringKey(t.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tag.class.getName()});
                return null;
            }
        }

    }
}
