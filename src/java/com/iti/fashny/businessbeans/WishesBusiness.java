/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.entities.Wishes;
import java.util.List;

/**
 *
 * @author Hosam
 */
public class WishesBusiness {
    
    DaoFactory daoFactory = new DaoFactory();
    PlaceFacade facade = daoFactory.getPlaceDoa();
    
    List<Wishes> wishes ;
    
    public List<Wishes> getClientsWishes(){
        
        wishes = facade.getClientsWishes();
        
        return wishes;
    }
     
            public static void main(String[] args) {
                
        WishesBusiness wishesBusiness = new WishesBusiness();
        List<Wishes> clientsWishes = wishesBusiness.getClientsWishes();
                for (Wishes clientsWishe : clientsWishes) {
                    System.out.println(clientsWishe.getPlace());
                    System.out.println(clientsWishe.getPlaceCount());
                }
        
    }
}
