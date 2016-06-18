/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.entities.Partener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name = "PartenerHotelAllClientViewManagedBean")
@SessionScoped
public class PartenerHotelAllClientViewManagedBean 
{
    List<Partener> partenerList; // for hotels type.id = 1;
    AdditionalFns additionalFns;
    private Partener photel;
   
   List<Partener> transList; // for Transportations type.id = 2
   
   List<Partener> resturList; // for Restaurant type.id = 3
   List<Partener> photogrList; // for PhotoGrapher type.id = 4
   
   
   //List<Partener> partenerList;
   

    public PartenerHotelAllClientViewManagedBean() 
    {
        additionalFns = new AdditionalFns();
        
        partenerList=additionalFns.getAllPartenerFromSameType(1);
        transList=additionalFns.getAllPartenerFromSameType(2);
        resturList=additionalFns.getAllPartenerFromSameType(3);
        photogrList=additionalFns.getAllPartenerFromSameType(4);
    }

    public List<Partener> getTransList() {
        return transList;
    }

    public void setTransList(List<Partener> transList) {
        this.transList = transList;
    }

    public List<Partener> getResturList() {
        return resturList;
    }

    public void setResturList(List<Partener> resturList) {
        this.resturList = resturList;
    }

    public List<Partener> getPhotogrList() {
        return photogrList;
    }

    public void setPhotogrList(List<Partener> photogrList) {
        this.photogrList = photogrList;
    }
    
    

    public List<Partener> getPartenerList() {
        return partenerList;
    }

    public void setPartenerList(List<Partener> partenerList) {
        this.partenerList = partenerList;
    }

    public AdditionalFns getAdditionalFns() {
        return additionalFns;
    }

    public void setAdditionalFns(AdditionalFns additionalFns) {
        this.additionalFns = additionalFns;
    }

    public Partener getPhotel() {
        return photel;
    }

    public void setPhotel(Partener photel) {
        this.photel = photel;
    }

   
    
    
    
    public String placeDetails(int id) 
    {
        photel = additionalFns.getPartenrSpecificInfo(id);
      
        return "PartenerHotelShowDetailsClientView";
    }
    
        public String RestDetails(int id) 
    {
        photel = additionalFns.getPartenrSpecificInfo(id);
      
        return "PartenerResturanteShowDetailsClientView";
    }
    
}