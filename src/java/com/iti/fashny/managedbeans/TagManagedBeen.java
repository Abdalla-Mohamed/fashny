/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.TagBusiness;
import com.iti.fashny.entities.Tag;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name = "tagBean") 
@ViewScoped
public class TagManagedBeen implements Serializable{
    TagBusiness tagBusiness;
    private List<Tag> items = null;

    /**
     * Creates a new instance of TagManagedBeen
     */
    public TagManagedBeen() {
        tagBusiness = new TagBusiness();
    }
    
    public void setTagBusiness(TagBusiness tagBusiness) {
        this.tagBusiness = tagBusiness;
    }

    public void setItems(List<Tag> items) {
        this.items = items;
    }

    public TagBusiness getTagBusiness() {
        return tagBusiness;
    }

    public List<Tag> getItems() {
        if (items == null) {
            try {
                items = tagBusiness.view();
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return items;
    }
    public static void main(String[] args) {
        TagManagedBeen been =new TagManagedBeen();
        List<Tag> items1 = been.getItems();
        for (Tag tag : items1) {
            System.out.println(tag.getName());
        }
    }
    
   
}
