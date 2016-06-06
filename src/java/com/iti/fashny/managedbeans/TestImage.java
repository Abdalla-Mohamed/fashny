/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean(name = "fileUploadView")
@SessionScoped
public class TestImage {

    /**
     * Creates a new instance of TestImage
     */
    public TestImage() {
    }

    String fileName;
    String path = "C:\\images\\";
    Resouce resouce;

    public void handleFileUpload(FileUploadEvent event) {
        resouce = new Resouce();
        try {
            fileName = event.getFile().getFileName();
            InputStream inputstream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(path + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputstream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            resouce.setDescription(fileName);
            resouce.setPath(path + fileName);
            resouce.setType(1);

            inputstream.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public Resouce getResouce() {
        return resouce;
    }

    public void setResouce(Resouce resouce) {
        this.resouce = resouce;
    }
    

}
