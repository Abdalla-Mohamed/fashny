/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Abdalla
 */
@ManagedBean
@ViewScoped
public class FileUploadMB implements Serializable {

    /**
     * Creates a new instance of FileUploadMB
     */
    public FileUploadMB() {
    }

    UploadedFile file;
    String fileName;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void forClient(String id) {
        fileName = "clients" + File.separator + id;
    }

    public void forCompany(String id) {
        fileName = "companies" + File.separator + id;
    }

    public void forPartner(String id) {
        fileName = "partners" + File.separator + id;
    }

    public void forPlace(String id) {
        fileName = "places" + File.separator + id;
    }

    public void forTrip(String id) {
        fileName = "Trips" + File.separator + id;
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        String folderName = "C:" + File.separator + "uploaded";
        String newFileName = folderName + File.separator + fileName + File.separator + file.getFileName();

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
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
