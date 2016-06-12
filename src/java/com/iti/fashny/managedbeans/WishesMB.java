/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.WishesBusiness;
import com.iti.fashny.entities.Wishes;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Hosam
 */
@ManagedBean(name = "wishes")
@RequestScoped
public class WishesMB implements Serializable{
  
    private WishesBusiness wishesBusiness ;
    private List<Wishes>wishes ;
    
    public WishesMB(){
        wishesBusiness = new WishesBusiness();
    }

    public List<Wishes> getWishes() {
        if (wishes==null){
            wishes = wishesBusiness.getClientsWishes();
            for (Wishes wishe : wishes) {
                System.out.println(wishe.getPlace()); 
                System.out.println(wishe.getPlaceCount()); 
            }
            
        }
        
        return wishes;
    }

    public void setWishes(List<Wishes> wishes) {
        this.wishes = wishes;
    }
    
    public static void main(String[] args) {
       
        WishesMB wishesMB = new WishesMB();
        wishesMB.getWishes();
    }
    
}
