/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PartnerBusiness;
import com.iti.fashny.businessbeans.PartnerTypeBusiness;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import com.iti.fashny.entities.Resouce;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name = "PartnerCRUDSBean")
@SessionScoped
public class PartnerCRUDSBean 
{
 
    PartnerBusiness partnerBusiness;
    
    private List<Partener> items = null;
    private Partener selected = new Partener();
    private List<Partener> filteredItems;

    public PartnerBusiness getPartnerBusiness() {
        return partnerBusiness;
    }

    public void setPartnerBusiness(PartnerBusiness partnerBusiness) {
        this.partnerBusiness = partnerBusiness;
    }


    public List<Partener> getItems() {
        return items;
    }

    public void setItems(List<Partener> items) {
        this.items = items;
    }

    public Partener getSelected() {
        return selected;
    }

    public void setSelected(Partener selected) {
        this.selected = selected;
    }

    public List<Partener> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Partener> filteredItems) {
        this.filteredItems = filteredItems;
    }
    
      public void create() {
//          selected = new Partener();
//          selected.setName("dfa");
//          selected.setActive(Boolean.TRUE);
//          selected.setAddress("dfasfdsf");
//          selected.getContactEmail();
//          selected.getDescription();
//          selected.setEmail("adfcsd");
//          selected.setPassword("dfdfaf");
//          selected.setWebsite("dfadfsf");
//          selected.setAddress("dfadf");
//          selected.setType(new PartnType(1));
//          selected.setWorkHours("dfasdf");
//          selected.setValidated(Boolean.TRUE);
//          selected.setMobile1("sfad");
//          selected.setMobile2("fsfda");
//          selected.setPhone("wfa");
//          selected.setActive(Boolean.TRUE);
          
          
          selected.setType(new PartnType(1));
          
        if (getSelected() != null) {
            try {
                System.out.println(selected.getName());
                new PartnerBusiness().add(selected);
                //return"/info";
                
            } catch (Exception ex) {
                ex.printStackTrace();
               // return"";
            }
        }
     //   return"/info";
    }
      
      
      
      
      
      
      
      
      ///////////////////////////
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
            r.setId(selected.getId());
            r.setDescription(logo);
            r.setPath(path + fileName);
            r.setType(1);

            DaoFactory daoFactory = new DaoFactory();
            ResouceFacade resouceDoa = daoFactory.getResouceDoa();
            daoFactory.beginTransaction();
            resouceDoa.create(r);
            selected.setProfilePic(r);
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

      
      
      
}
