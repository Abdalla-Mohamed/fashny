/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdditionalFns;
import com.iti.fashny.businessbeans.guestImpl;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MANAR ADEL
 */
@ManagedBean(name = "SignUpCreatePartenerBean")
@SessionScoped
public class SignUpCreatePartenerBean 
{
    guestImpl gImpl ;
    Partener selected = new Partener();
     List<PartnType> findAll ;
    private int partnTybeID;
    AdditionalFns additionalFns;
    
    public SignUpCreatePartenerBean()
    {
        gImpl = new guestImpl();
        
        additionalFns = new AdditionalFns();
        findAll = additionalFns.getAllPartnType();
    }

    public guestImpl getgImpl() {
        return gImpl;
    }

    public void setgImpl(guestImpl gImpl) {
        this.gImpl = gImpl;
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

    public Partener getSelected() {
        return selected;
    }
    

    public void setSelected(Partener selected) {
        this.selected = selected;
    }
    
    
    public void getAllInfo()
    {
        
    }
    
    public void registerNewPartener()
    {
         if (getSelected() != null) {
            try {
                System.out.println(selected.getName());
                gImpl.signUp(selected);
                //return"/info";
                
            } catch (Exception ex) {
                ex.printStackTrace();
               // return"";
            }
        }
    }
    
    public void handleFileUpload()
    {
        
    }
}

