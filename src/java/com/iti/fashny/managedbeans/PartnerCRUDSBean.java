/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name = "PartnerCRUDSBean")
@SessionScoped
public class PartnerCRUDSBean implements Serializable
{
 
    PartnerBusiness partnerBusiness;
    
    private List<Partener> items = null;
    private Partener selected ;
    //private Partener selected = new Partener();
    private List<Partener> filteredItems;
    
    
     List<PartnType> findAll ;
    private int partnTybeID;
    AdditionalFns additionalFns;

    
    public PartnerCRUDSBean()
    {
        partnerBusiness = new PartnerBusiness();
        
        
        additionalFns = new AdditionalFns();
        findAll = additionalFns.getAllPartnType();
    }

    public List<PartnType> getFindAll() {
        return findAll;
    }

    public void setFindAll(List<PartnType> findAll) {
        this.findAll = findAll;
    }

    public int getPartnTybeID() {
        return partnTybeID;
    }

    public void setPartnTybeID(int partnTybeID) {
        this.partnTybeID = partnTybeID;
    }

    public AdditionalFns getAdditionalFns() {
        return additionalFns;
    }

    public void setAdditionalFns(AdditionalFns additionalFns) {
        this.additionalFns = additionalFns;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    public PartnerBusiness getPartnerBusiness() {
        return partnerBusiness;
    }

    public void setPartnerBusiness(PartnerBusiness partnerBusiness) {
        this.partnerBusiness = partnerBusiness;
    }


    public List<Partener> getItems() {
        if(items==null)
        {
            try 
            {
                items=partnerBusiness.view();
            } catch (Exception ex) 
            {
               // Logger.getLogger(PartnerCRUDSBean.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        return items;
    }

//    public List<Partener> getItems() {
//        return items;
//    }
    
    
        public List<Partener> changingItems() {
       
             List<Partener> partenerList=new ArrayList<>();
            try 
            {
                partenerList=partnerBusiness.view();
            } catch (Exception ex) 
            {
               // Logger.getLogger(PartnerCRUDSBean.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        
        return partenerList;
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
    
    
    /////////////////////////////////////////////////////
    public Partener prepareCreate()
    {
        selected = new Partener();
        return selected;
    }
    
    public Partener getPartener(java.lang.Integer id) {
        return partnerBusiness.showSpecificInfo(id);
    }
    
       public List<Partener> getItemsAvailableSelectMany() {
        List<Partener> partenerList = null;
        try {
            partenerList = partnerBusiness.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return partenerList;
    }
       
       
    public List<Partener> getItemsAvailableSelectOne() {
        List<Partener> partenerList = null;
        try {
            partenerList = partnerBusiness.view();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return partenerList;
    }
    
         public void onRowEdit(RowEditEvent event) {
         selected=(Partener) event.getObject();
         update();
         FacesMessage msg = new FacesMessage("Partener Edited", ((Partener) event.getObject()).getName());
         FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
           public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Partener Edit Cancelled", ((Partener) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
           
           
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
           
    
      public void destroy() {
        if (selected != null) {
            try {
                selected.setActive(Boolean.FALSE);
                partnerBusiness.update(selected);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /////////////////////////////////////////////////////
    
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
          
          //selected.setType(new PartnType(1));
          
        if (getSelected() != null) {
            try {
                System.out.println(selected.getName());
                partnerBusiness.add(selected);
                //return"/info";
                
            } catch (Exception ex) {
                ex.printStackTrace();
               // return"";
            }
        }
     //   return"/info";
    }
      
      
      
     public String partenerDetails(int id)
     {
         selected = partnerBusiness.showSpecificInfo(id);
        return "Partener";
     }
          public void update() {
        if (selected != null) {
            try {
                partnerBusiness.update(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
