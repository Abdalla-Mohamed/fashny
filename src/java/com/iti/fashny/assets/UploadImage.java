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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bakar M.M.R
 */
public class UploadImage {

    UploadedFile file;
    String folderName = "C:" + File.separator + "uploaded"+File.separator;


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void forClient(String id) {
        folderName += "clients" + File.separator + id;
    }

    public void forCompany(String id) {
        folderName += "companies" + File.separator + id;
    }

    public void forPartner(String id) {
        folderName += "partners" + File.separator + id;
    }

    public void forPlace(String id) {
        folderName += "places" + File.separator + id;
    }

    public void forTrip(String id) {
        folderName += "Trips" + File.separator + id;
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
    }

    public void copyFile() {

        String newFileName = this.folderName + File.separator + file.getFileName();
        try {
            new File(folderName).mkdirs();
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
