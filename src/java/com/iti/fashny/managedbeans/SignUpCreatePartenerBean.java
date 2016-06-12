/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.guestImpl;
import com.iti.fashny.entities.Partener;
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
    
    public SignUpCreatePartenerBean()
    {
        gImpl = new guestImpl();
    }

    public guestImpl getgImpl() {
        return gImpl;
    }

    public void setgImpl(guestImpl gImpl) {
        this.gImpl = gImpl;
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

