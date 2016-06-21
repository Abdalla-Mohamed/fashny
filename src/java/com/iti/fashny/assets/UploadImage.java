/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bakar M.M.R
 */
public class UploadImage implements Serializable {

    private UploadedFile file;
    private String folderId;
    String folderName = "C:" + File.separator + "uploaded";

    public UploadImage() {
    }

    /**
     * Creates a new instance of FileUploadMB
     */
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private String getFolderId() {
        return folderId;
    }

    private void setFolderId(String folderId) {
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
    FileUploadEvent event;

    public String handleFileUpload() {
//        forClient("5");
        folderName += File.separator + folderId;
        String newFileName = "";

        try {
            boolean mkdirs = new File(folderName).mkdirs();
            int count = new File(folderName).list().length;
            newFileName = folderName + File.separator+ count + file.getFileName();

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
//            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    public void handleFileUploadEvent(FileUploadEvent event) {
        file = event.getFile();
        handleFileUpload();
        
        
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
