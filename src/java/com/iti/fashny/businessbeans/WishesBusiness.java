/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Wishes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hosam
 */
public class WishesBusiness {

    DaoFactory daoFactory = new DaoFactory();
    PlaceFacade facade = daoFactory.getPlaceDoa();

    List<Wishes> wishes;

    public List<Wishes> getClientsWishes() {

        wishes = facade.getClientsWishes();

        return wishes;
    }

    public void addWish(Client client, Place place) throws Exception {

        ClientFacade clientFacade = daoFactory.getClientDoa();

          
            daoFactory.beginTransaction();
                 
            Client client1 = new Client();
            client1 = clientFacade.refreshObj(client);
            if (!client1.getPlaceList().contains(place)) {
                try {
                    client1.getPlaceList().add(place);

                    
                    clientFacade.edit(client1);
                    daoFactory.commitTransaction();
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                    daoFactory.rollbackTransaction();
                }

            }
       
    }

    public List<Place> getNotWishedPlaces(Client client) {

        List<Place> places = facade.getNotWishedPlaces(client);

        return places;
    }

    
}
