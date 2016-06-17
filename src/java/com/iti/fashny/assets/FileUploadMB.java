/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Abdalla
 */
@ManagedBean
@SessionScoped
public class FileUploadMB implements Serializable {

    UploadImage uploadImage;

    private UploadedFile file;
    private String folderId;

    /**
     * Creates a new instance of FileUploadMB
     */
    public FileUploadMB() {
        uploadImage = new UploadImage();
    }

    public String sumbitAndUpload() {
        Place place = new Place(null, "nail", "cairo", "all day", 20.0, 30.0, true);
        DaoFactory daoFactory = new DaoFactory();
        try {
            PlaceFacade placeDoa = daoFactory.getPlaceDoa();

            ResouceFacade resouceDoa = daoFactory.getResouceDoa();

            daoFactory.beginTransaction();
            placeDoa.create(place);

            uploadImage.forPlace("" + place.getId());
            String filePath = uploadImage.handleFileUpload();

            Resouce resouce = new Resouce(null, filePath);
            resouceDoa.create(resouce);
            if(resouce.getPlaceList() == null){
                resouce.setPlaceList(new ArrayList<>(1));
            }
            resouce.getPlaceList().add(place);

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            exception.printStackTrace();
        }

        return "";
    }

    public UploadImage getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(UploadImage uploadImage) {
        this.uploadImage = uploadImage;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public void forClient(String id) {
        folderId = "clients" + File.separator + id;
    }

    public void forCompany(String id) {
        folderId = "companies" + File.separator + id;
    }

    public void forPartner(String id) {
        folderId = "partners" + File.separator + id;
    }

    public void forPlace(String id) {
        folderId = "places" + File.separator + id;
    }

    public void forTrip(String id) {
        folderId = "Trips" + File.separator + id;
    }

    public void handleFileUpload() {
        forClient("5");
        String folderName = "C:" + File.separator + "uploaded" + File.separator + folderId;
        String newFileName = folderName + File.separator + file.getFileName();

        try {
            boolean mkdirs = new File(folderName).mkdirs();
            FileOutputStream fos = new FileOutputStream(new File(newFileName));
            InputStream is = file.getInputstream();
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            int a;
            while (true) {
                a = is.read(buffer);
                if (a < 0) {
                    break;
                }
                fos.write(buffer, 0, a);
                fos.flush();
            }
            fos.close();
            is.close();
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
