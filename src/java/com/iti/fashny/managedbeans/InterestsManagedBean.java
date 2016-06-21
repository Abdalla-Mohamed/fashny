///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.iti.fashny.managedbeans;
//
//import com.iti.fashny.businessbeans.InterestsBusiness;
//import com.iti.fashny.entities.Client;
//import com.iti.fashny.entities.JoinTrip;
//import com.iti.fashny.entities.Tag;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.TransferEvent;
//import org.primefaces.event.UnselectEvent;
//import org.primefaces.model.DualListModel;
//
///**
// *
// * @author Administrator
// */
//@ManagedBean(name = "interests")
//@SessionScoped
//public class InterestsManagedBean implements Serializable {
//
//    InterestsBusiness interests;
//    private List<Tag> tagList;
//    List<Tag> tagTarget;
//    private DualListModel<Tag> tags;
//    private List<Tag> clientTagList;
//    private List<Tag> tempTag;
//    private Tag tag;
//
//    @ManagedProperty(value = "#{login}")
//    private LoginManagedBean loginManagedBean;
//
//    //  private String selectedItem;
////      Client c;
////    public InterestsManagedBean() {
////        try {
////            interests = new InterestsBusiness();
////            tagList = interests.view();
////        } catch (Exception ex) {
////            Logger.getLogger(InterestsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
//    @PostConstruct
//    public void init() {
//
//        try {
//            interests = new InterestsBusiness();
//            tagList = interests.view();
//            tagTarget = new ArrayList<Tag>();
//            clientTagList = new ArrayList<>();
//
//            tags = new DualListModel<Tag>(tagList, tagTarget);
//        } catch (Exception ex) {
//            Logger.getLogger(InterestsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void setTagTarget(List<Tag> tagTarget) {
//        this.tagTarget = tagTarget;
//    }
//
//    public List<Tag> getTagTarget() {
//        return tagTarget;
//    }
//
//    public void setClientTagList(List<Tag> clientTagList) {
//        this.clientTagList = clientTagList;
//    }
//
//    public void setTempTag(List<Tag> tempTag) {
//        this.tempTag = tempTag;
//    }
//
//    public List<Tag> getTempTag() {
//        return tempTag;
//    }
//
//    public List<Tag> getClientTagList() {
////        boolean find = false;
////        Tag tag1 = new Tag();
////        for (Tag t : tagList) {
////            for (int i = 0; i < tempTag.size(); i++) {
////                tag1 = tempTag.get(i);
////                if (t.equals(tag1)) {
////                    find = true;
////                    break;
////                }
////
////            }
////            if (find == false) {
////                clientTagList.add(t);
////                
////            }
////
////        }
//        return clientTagList;
//    }
//
//    public void setInterests(InterestsBusiness interests) {
//        this.interests = interests;
//    }
//
//    public void setTagList(List<Tag> tagList) {
//        this.tagList = tagList;
//    }
//
//    public void setTags(DualListModel<Tag> tags) {
//        this.tags = tags;
//    }
//
//    public InterestsBusiness getInterests() {
//        return interests;
//    }
//
//    public List<Tag> getTagList() {
//
//        return tagList;
//    }
//
//    public DualListModel<Tag> getTags() {
//        return tags;
//    }
//
//    public List<Tag> interestsTag() {
//        Client client = loginManagedBean.getLoginAccount().getClient();
//        List<Tag> tagList1 = client.getTagList();
//        for (Tag tag1 : tagList1) {
//        }
//        return tagList1;
//    }
//    
//    
//
//    public void onTransfer(TransferEvent event) {
//        StringBuilder builder = new StringBuilder();
//        for (Object item : event.getItems()) {
//            Tag tg = (Tag) item;
//            builder.append(tg.getName()).append("<br />");
//        }
//
//        FacesMessage msg = new FacesMessage();
//        msg.setSeverity(FacesMessage.SEVERITY_INFO);
//        msg.setSummary("Items Transferred");
//        msg.setDetail(builder.toString());
//
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    public void onSelect(SelectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
//    }
//
//    public void onUnselect(UnselectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
//    }
//
//    public void onReorder() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
//    }
//
//    public void clientInterest(Client c) {
//        System.out.println("clientInterest started");
//        interests.addInterests(c, tags.getTarget());
//        for (Tag tag1 : tagList) {
//            System.out.println(c.getTagList());
//        }
//
//    }
//
//    public static void main(String[] args) {
//        InterestsManagedBean interestsManagedBean = new InterestsManagedBean();
//        List<Tag> lt = interestsManagedBean.getTagTarget();
//        try {
//            for (Tag tag : lt) {
//                System.out.println(tag.getName());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(InterestsBusiness.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.InterestsBusiness;
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
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class InterestsManagedBean implements Serializable {

    InterestsBusiness interests;
    private List<Tag> tagList;
    List<Tag> tagTarget;
    private DualListModel<Tag> tags;
    private Tag tag;
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
            tagList = interests.view();
            tagTarget = new ArrayList<Tag>();

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
//    public static void main(String[] args) {
//        InterestsManagedBean interestsManagedBean = new InterestsManagedBean();
//        List<Tag> lt = interestsManagedBean.getTagTarget();
//        try {
//            for (Tag tag : lt) {
//                System.out.println(tag.getName());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(InterestsBusiness.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
