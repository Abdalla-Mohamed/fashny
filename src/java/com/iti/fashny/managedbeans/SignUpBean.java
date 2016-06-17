/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.businessbeans.AdditionalFns;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
        new guestImpl().signUp(c);
        return "/info";
    }

    public String registerNewClientTest() {
        Client client = new Client(null, "omr", "abdo@g.com", "123", "alex", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2");
        client.setPic(c.getPic());
        new guestImpl().signUp(client);
        return "/info";
    }

/*    String fileName;
    String logo;
    String path = "C:\\Users\\";

    public String handleFileUpload(FileUploadEvent event) {

        fileName = event.getFile().getFileName();
        logo = event.getFile().getFileName();
        System.out.println("Logo=" + logo);
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return logo;

    }

    private void copyFile(String fileName, InputStream inputstream) {

        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(path + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputstream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            Resouce r = new Resouce();
            r.setId(c.getId());
            r.setDescription(logo);
            r.setPath(path + fileName);
            r.setType(1);

            DaoFactory daoFactory = new DaoFactory();
            ResouceFacade resouceDoa = daoFactory.getResouceDoa();
            daoFactory.beginTransaction();
            resouceDoa.create(r);
            //resouceDoa.
            daoFactory.commitTransaction();
            daoFactory.close();

            inputstream.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
*/
    
}
