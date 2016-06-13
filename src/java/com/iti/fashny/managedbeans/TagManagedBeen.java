/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.TagsController;
import com.iti.fashny.entities.Tag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean(name = "tagBean") 
@SessionScoped
public class TagManagedBeen implements Serializable{
    TagsController tagsController;
    private List<Tag> items = null;

    /**
     * Creates a new instance of TagManagedBeen
     */
    public TagManagedBeen() {
        tagsController = new TagsController();
        items = new ArrayList<>();
    }

    public void setTagsController(TagsController tagsController) {
        this.tagsController = tagsController;
    }
        
    public void setItems(List<Tag> items) {
        this.items = items;
    }

    public TagsController getTagsController() {
        return tagsController;
    }

    public List<Tag> getItems() {
//        //if (items == null) {
//        items = new ArrayList<>();
//            try {
//                items = tagsController.view();
//                for (Tag item : items) {
//                    System.out.println("!!!!!!!!!!!!!!!!!!!1" + item.getName());
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//       // }
//        return items;
        List<Tag> tags = new ArrayList<>();
        try {
            tags = tagsController.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tags;
    }
}
