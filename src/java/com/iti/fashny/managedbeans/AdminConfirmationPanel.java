/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.AdminManager;
import static com.iti.fashny.businessbeans.Example.tst;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.AdminInterface;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Amira Anis
 */
@ManagedBean
@RequestScoped
public class AdminConfirmationPanel {

    AdminInterface adminInterface ; 
    /**
     * Creates a new instance of Confirm
     */
    public AdminConfirmationPanel() {
        adminInterface = new AdminManager();
    }
    
    public List<Place>  showUnconfirmPlaces()
    {
        List<Place> UnconfirmPlacesList = new ArrayList<>();
        UnconfirmPlacesList = adminInterface.findAllUncofirmPlaces();
        //if (UnconfirmPlacesList != null)
            return UnconfirmPlacesList;
        //else
    }
    public List<Trip>  showUnconfirmTrips()
    {
        List<Trip> UnconfirmTripList = new ArrayList<>();
        UnconfirmTripList = adminInterface.findAllUncofirmTrips();
        //if (UnconfirmPlacesList != null)
            return UnconfirmTripList;
        //else
    }
    public List<Tag>  showUnconfirmTags()
    {
        List<Tag> UnconfirmTagList = new ArrayList<>();
        UnconfirmTagList = adminInterface.findAllUncofirmTags();
        //if (UnconfirmPlacesList != null)
            return UnconfirmTagList;
        //else
    }
    
//    public static void main(String[] args) {
//       AdminConfirmationPanel acp= new AdminConfirmationPanel();
//       // List<Place> UnconfirmPlacesList = new ArrayList<>();
//
//        for (Place p : acp.showUnconfirmPlaces()) {
//            System.out.println("name" + p.getName());
//        }
//        acp.showUnconfirmPlaces();
//    }
           
}
