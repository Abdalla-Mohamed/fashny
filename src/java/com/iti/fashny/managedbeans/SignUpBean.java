/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.businessbeans.ClientBusiness;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.businessbeans.guestImpl;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MANAR ADEL
 */
@ManagedBean(name = "SignUpBean")
@SessionScoped
public class SignUpBean {

    UploadImage uploadImage;
    
    Client c = new Client();
    private Client newClient;
    private boolean signUpDone;
    private boolean picUploaded;

    public SignUpBean() {
        uploadImage =new UploadImage();
    }

    
    public UploadImage getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public void getAllInfo() {
        System.out.println(c.getName());
        System.out.println(c.getPassword());
        System.out.println(c.getEmail());
        System.out.println(c.getContactEmail());
        System.out.println(c.getAddress());
        System.out.println(c.getMobile1());
        System.out.println(c.getMobile2());
        System.out.println(c.getBirthDate());
        System.out.println(c.getMaritalStatus());
        System.out.println(c.getLastSeen());
        System.out.println(c.getActive());
        System.out.println(c.getGender());
        System.out.println(c.getId());

    }

    public String registerNewClient() {
        c.setActive(Boolean.TRUE);
        c.setLastSeen(new Date());
        
        new guestImpl().signUp(c);
        newClient = c;
        c=new Client();
        
        RequestContext context = RequestContext.getCurrentInstance();

        context.scrollTo("uploadGrid");
        signUpDone = true;
        
        return "";
    }

    public String registerNewClientTest() {
        Client client = new Client(null, "omr", "abdo4@g.com", "123", "alex", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2");
        client.setPic(c.getPic());
        new guestImpl().signUp(client);
        return "/info";
    }

 

    public void handleFileUpload(FileUploadEvent event) {
        ClientBusiness clientBusiness = new ClientBusiness();
         clientBusiness.addImageToClient(event.getFile(), newClient);

        picUploaded = true;
        RequestContext context = RequestContext.getCurrentInstance();
        

//        context.execute("PF('uploadImage').hide()");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean isPicUploaded() {
        return picUploaded;
    }

    public boolean isSignUpDone() {
        return signUpDone;
    }

    public void setPicUploaded(boolean picUploaded) {
        this.picUploaded = picUploaded;
    }

    public void setSignUpDone(boolean signUpDone) {
        this.signUpDone = signUpDone;
    }

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }
   
 public String goToLoginPage(){
        picUploaded =false;
        signUpDone = true;
        newClient = new Client();
        return "login";
    }
    
    
}
