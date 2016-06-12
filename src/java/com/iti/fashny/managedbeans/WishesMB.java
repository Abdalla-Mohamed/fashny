/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ClientBusiness;
import com.iti.fashny.businessbeans.WishesBusiness;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Wishes;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Client client ;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public WishesMB(){
        wishesBusiness = new WishesBusiness();
        DaoFactory daoFactory = new DaoFactory();
        ClientFacade cf = daoFactory.getClientDoa();
        client = cf.find(5);
              
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
    
    public void addWish(Client client , Place place){
    
        try {
            wishesBusiness.addWish(client, place);
            
        } catch (Exception ex) {
            Logger.getLogger(WishesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
