
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.InterestsBusiness;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Tag;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "interests")
@ViewScoped
public class InterestsManagedBean implements Serializable {

    InterestsBusiness interests;
    private List<Tag> tagList;
    List<Tag> tagTarget;
    private DualListModel<Tag> tags;
    private Tag tag;
    
    @ManagedProperty(value = "#{login}")
    private LoginManagedBean loginManagedBean;

    public LoginManagedBean getLoginManagedBean() {
        return loginManagedBean;
    }

    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }
    
    
    //  private String selectedItem;
    //  Client c;

//    public InterestsManagedBean() {
//        try {
//            interests = new InterestsBusiness();
//            tagList = interests.view();
//        } catch (Exception ex) {
//            Logger.getLogger(InterestsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @PostConstruct
    public void init() {

        try {
            interests = new InterestsBusiness();
            List<Tag>allTags = interests.view();
            tagList = new ArrayList();
            tagTarget=getClientInterests(loginManagedBean.getLoginAccount().getClient());
            
             for (Tag tag1 : allTags) {
                if(!tagTarget.contains(tag1)){
                    tagList.add(tag1);
                }
                
            }      

            tags = new DualListModel<Tag>(tagList, tagTarget);
        } catch (Exception ex) {
            Logger.getLogger(InterestsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTagTarget(List<Tag> tagTarget) {
        this.tagTarget = tagTarget;
    }

    public List<Tag> getTagTarget() {
        return tagTarget;
    }

    public void setInterests(InterestsBusiness interests) {
        this.interests = interests;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void setTags(DualListModel<Tag> tags) {
        this.tags = tags;
    }

    public InterestsBusiness getInterests() {
        return interests;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public DualListModel<Tag> getTags() {
        return tags;
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            Tag tg = (Tag) item;
            builder.append(tg.getName()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public void clientInterest(Client c) {
        System.out.println("clientInterest started");
        interests.addInterests(c, tags.getTarget());
    }
    
    
    
    public List<Tag> getClientInterests(Client client){
        
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
                           
            Client client1 = new Client();
            client1 = clientFacade.refreshObj(client);
            
            return client1.getTagList();
        
    }
    
    
}
