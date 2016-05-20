package com.iti.fashny.interfaces;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amira Anis
 */
public interface ClientInterface {
    public void setInterest(Client client, ArrayList<Tag> interests) throws Exception;
//    public ArrayList<place> getMyInterest(Client client) throws Exception;
//    public ArrayList<String> getMyWish(Client client) throws Exception;
    public void wish(Client client, Place place) throws Exception;

    public void comment(Client client, Place place, String comment) throws Exception;
    public void removePlaceFromWishList(Client client, Place place) throws Exception;
    public void JoinTrip(Client client, Trip trip,int bookingCount) throws Exception;
    public ArrayList<Trip> showClientOldTrips(Client client) throws Exception;
    public void rateOldTrip(Client client, Trip trip , int rate) throws Exception;
    public ArrayList<Trip> showBookedTrips(Client client) throws Exception;
    public void cancelBookedTrips(Client client, Trip trip) throws Exception;
}
