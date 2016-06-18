/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PartnTypeFacade;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name = "EditProfilePartener")
@SessionScoped
public class EditProfilePartenerBean 
{


 
    
    Partener selected = new Partener();
    List<PartnType> findAll ;
    private int partnTybeID;
    AdditionalFns additionalFns;
    
    
    
    
    public EditProfilePartenerBean()
    {
        additionalFns = new AdditionalFns();
        
//     DaoFactory daoFactory = new DaoFactory();
//     PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
//     daoFactory.beginTransaction();
//     selected = partenerFacade.find(4);
//     daoFactory.commitTransaction();
//     daoFactory.close();  
     
        selected=additionalFns.getPartenerObject();
        
     /////////////////////
     
     findAll = additionalFns.getAllPartnType();
     
//        DaoFactory daoFactory1 = new DaoFactory();
//        PartnTypeFacade partnTypeFacade = daoFactory1.getPartnTypeDoa();
//        daoFactory1.beginTransaction();
//        findAll = partnTypeFacade.findAll();
//        
//         for(PartnType pt : findAll)
//        {
//            System.out.println(pt.getId());
//            System.out.println(pt.getName());
//            System.out.println("---------------");
//        }
//        
//        daoFactory1.commitTransaction();
//        daoFactory1.close();
//     
        
    }

    public Partener getSelected() {
        return selected;
    }

    public void setSelected(Partener selected) {
        this.selected = selected;
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
    
    
    
    public String editPartenerProfile()
    {
       // c.setId(loginManagedBean.c.getId());
     
     selected.setId(selected.getId());
     DaoFactory daoFactory = new DaoFactory();
     PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
     daoFactory.beginTransaction();
     partenerFacade.edit(selected);
     daoFactory.commitTransaction();
     daoFactory.close();   
        
        return "/info";
    }
    
}
