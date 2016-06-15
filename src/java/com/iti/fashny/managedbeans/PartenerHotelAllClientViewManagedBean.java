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
    List<Partener> partenerList;
    AdditionalFns additionalFns;
   private Partener photel;

    public PartenerHotelAllClientViewManagedBean() 
    {
        additionalFns = new AdditionalFns();
        partenerList=additionalFns.getAllPartenerFromSameType(3);
        
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
    
    
}
