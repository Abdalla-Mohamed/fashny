/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.businessbeans.PartnerBusiness;
import com.iti.fashny.businessbeans.guestImpl;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MANAR ADEL
 */
@ManagedBean(name = "SignUpCreatePartenerBean")
@SessionScoped
public class SignUpCreatePartenerBean {

    guestImpl gImpl;
    Partener selected = new Partener();
    List<PartnType> findAll;
    private int partnTybeID;
    AdditionalFns additionalFns;
    PartnerBusiness partnerBusiness;

    private boolean signUpDone;
    private boolean picUploaded;

    public SignUpCreatePartenerBean() {
        gImpl = new guestImpl();
        partnerBusiness = new PartnerBusiness();

        additionalFns = new AdditionalFns();
        findAll = additionalFns.getAllPartnType();
    }

    public guestImpl getgImpl() {
        return gImpl;
    }

    public void setgImpl(guestImpl gImpl) {
        this.gImpl = gImpl;
    }

    public List<PartnType> getFindAll() {
        return findAll;
    }

    public void setFindAll(List<PartnType> findAll) {
        this.findAll = findAll;
    }

    public int getPartnTybeID() {
        return partnTybeID;
    }

    public void setPartnTybeID(int partnTybeID) {
        this.partnTybeID = partnTybeID;
    }

    public AdditionalFns getAdditionalFns() {
        return additionalFns;
    }

    public void setAdditionalFns(AdditionalFns additionalFns) {
        this.additionalFns = additionalFns;
    }

    public Partener getSelected() {
        return selected;
    }

    public void setSelected(Partener selected) {
        this.selected = selected;
    }

    public void getAllInfo() {

    }

    public void registerNewPartener() {
        if (getSelected() != null) {
            try {
                System.out.println(selected.getName());
                gImpl.signUp(selected);
                RequestContext context = RequestContext.getCurrentInstance();

                context.scrollTo("uploadGrid");
                signUpDone = true;
                signUpDone = true;
                //return"/info";

            } catch (Exception ex) {
                ex.printStackTrace();
                // return"";
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        partnerBusiness.addImageToPartner(event.getFile(), selected);

        setPicUploaded(true);
        RequestContext context = RequestContext.getCurrentInstance();

//        context.execute("PF('uploadImage').hide()");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the signUpDone
     */
    public boolean isSignUpDone() {
        return signUpDone;
    }

    /**
     * @param signUpDone the signUpDone to set
     */
    public void setSignUpDone(boolean signUpDone) {
        this.signUpDone = signUpDone;
    }

    /**
     * @return the picUploaded
     */
    public boolean isPicUploaded() {
        return picUploaded;
    }

    /**
     * @param picUploaded the picUploaded to set
     */
    public void setPicUploaded(boolean picUploaded) {
        this.picUploaded = picUploaded;
    }
     public String waitConfirmtion(){
        picUploaded =false;
        signUpDone = true;
        selected = new Partener();
        return "waitConfirmtion";
    }
}
