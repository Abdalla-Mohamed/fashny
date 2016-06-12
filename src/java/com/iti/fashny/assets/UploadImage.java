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
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Bakar M.M.R
 */
public class UploadImage {

    private String fileName;
    private String path = "C:\\images\\";
    private Resouce resouce;

    
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Resouce getResouce() {
        return resouce;
    }

    public void setResouce(Resouce resouce) {
        this.resouce = resouce;
    }
    
}
