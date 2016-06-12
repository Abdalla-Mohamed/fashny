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

       
            if (!client.getPlaceList().contains(place)) {
                try {
                    client.getPlaceList().add(place);

                    daoFactory.beginTransaction();
                    clientFacade.edit(client);
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

    public static void main(String[] args) {

        WishesBusiness wishesBusiness = new WishesBusiness();
//        List<Place> places = new ArrayList<>();
//
//        ClientFacade clientFacade = wishesBusiness.daoFactory.getClientDoa();
//        Client c = new Client(2);
//        c = clientFacade.find(2);
//
//        Place p = new Place();
//
//        p = wishesBusiness.facade.find(3);
//        places.add(p);
//        p = wishesBusiness.facade.find(2);
//        places.add(p);
//        p = wishesBusiness.facade.find(4);
//        places.add(p);
//        p = wishesBusiness.facade.find(1);
//        places.add(p);
//        p = wishesBusiness.facade.find(5);
//        places.add(p);
//        p = wishesBusiness.facade.find(1);
//        places.add(p);
//
//        try {
//            wishesBusiness.addWish(c, places);
//        } catch (Exception ex) {
//            Logger.getLogger(WishesBusiness.class.getName()).log(Level.SEVERE, null, ex);
//        }

        ClientFacade clientFacade = wishesBusiness.daoFactory.getClientDoa();
        Client c = new Client();
        c = clientFacade.find(5);

        PlaceFacade facade = wishesBusiness.daoFactory.getPlaceDoa();

        List<Place> places = facade.getNotWishedPlaces(c);
        System.out.println(places.size());

        for (Place place : places) {
            System.out.println(place.getName());
        }

    }
}
