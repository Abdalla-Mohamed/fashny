/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PartnTypeFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MANAR ADEL
 */
public class AdditionalFns implements Serializable
{

    public Boolean isMailExist(String email) {
        boolean valid = true;

        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        CompanyFacade companyFacade = daoFactory.getCompanyDoa();
        PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
        AdminFacade adminFacade = daoFactory.getAdminDoa();

        daoFactory.beginTransaction();
        if ((clientFacade.validMail(email) || companyFacade.validMail(email) || partenerFacade.validMail(email) || adminFacade.validMail(email))) {
            System.out.println("Mail Already Exist");
            valid = false;
            System.out.println(valid);
        }

        daoFactory.commitTransaction();
        daoFactory.close();

        return valid;
    }

    public Client getClientObject() {
        Client c;
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        daoFactory.beginTransaction();
        c = clientFacade.find(7);
        daoFactory.commitTransaction();
        daoFactory.close();

        return c;
    }

    public Client getClientObject(int id) {
        Client c;
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
        daoFactory.beginTransaction();
        c = clientFacade.find(id);
        daoFactory.commitTransaction();
        daoFactory.close();

        return c;
    }

    public Client getCurrentClientObjectFromSession() {
        Client c = new Client();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        c = (Client) session.getAttribute("c");

        return c;
    }

    String fileName;
    String logo;
    String path = "C:\\Users\\MANAR ADEL\\Downloads\\ITI\\";

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
            r.setId(1);
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

    public void insertProfilePicResourceIntoDB() {

    }

    public List<PartnType> getAllPartnType() {
        List<PartnType> findAll;

        DaoFactory daoFactory1 = new DaoFactory();
        PartnTypeFacade partnTypeFacade = daoFactory1.getPartnTypeDoa();
        daoFactory1.beginTransaction();
        findAll = partnTypeFacade.findAll();

        for (PartnType pt : findAll) {
            System.out.println(pt.getId());
            System.out.println(pt.getName());
            System.out.println("---------------");
        }

        daoFactory1.commitTransaction();
        daoFactory1.close();

        return findAll;
    }

    public Partener getPartenerObject() {
        Partener selected;

        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
        daoFactory.beginTransaction();
        selected = partenerFacade.find(4);
        daoFactory.commitTransaction();
        daoFactory.close();

        return selected;
    }

    public Partener getPartenerObject(int id) {

        Partener selected;

        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
        daoFactory.beginTransaction();
        selected = partenerFacade.find(id);
        daoFactory.commitTransaction();
        daoFactory.close();

        return selected;
    }

    
    /*
      
     DaoFactory daoFactory = new DaoFactory();
     ClientFacade clientFacade = daoFactory.getClientDoa();
        
     daoFactory.beginTransaction();
     clientFacade.create(c);
     //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
     daoFactory.commitTransaction();
     daoFactory.close();
      
     */
//    public static void main(String[] args)
//    {
//        new AdditionalFns().isMailExist("Reham@yahoo.com");
//        System.out.println("------------");
//        new AdditionalFns().isMailExist("Reehaam@yahoo.com");
//        
//        
//        if(false)
//        {
//            System.out.println("if false"); 
//        }
//        if(true)
//        {
//            System.out.println("if true"); 
//        }
//        
//          new AdditionalFns().isMailExist("nareman@yahoo.com");
//          System.out.println("------------");
//        new AdditionalFns().isMailExist("naareemaan@yahoo.com");
//    }
    public List<Partener> getAllPartenerFromSameType(int id) {
        DaoFactory daoFactory2 = new DaoFactory();
        PartnTypeFacade partnTypeFacade2 = daoFactory2.getPartnTypeDoa();
        daoFactory2.beginTransaction();

        List<Partener> allofpartenerOfOneCat = partnTypeFacade2.getAllpartenerOfOneCat(id);

        for (Partener prtn : allofpartenerOfOneCat) {
            System.out.println(prtn.getName());
        }

        daoFactory2.commitTransaction();
        daoFactory2.close();

        return allofpartenerOfOneCat;
    }

    public Partener getPartenrSpecificInfo(int id)
    {
        Partener partener = new Partener();
        
        DaoFactory daoFactory3= new DaoFactory();
        PartenerFacade partenerFacade3 = daoFactory3.getPartenerDoa();
        //daoFactory3.beginTransaction();
        partener=partenerFacade3.find(id);
        //daoFactory3.commitTransaction();
        //daoFactory3.close();
        
        return partener;
                
    }
    
}
