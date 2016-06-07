package com.iti.fashny.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.ClientReviewPlace;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.List;

/**
 *
 * @author Bakar M.M.R
 */
public interface AdminInterface {

    public void addAdmin(Admin admin) throws Fasa7nyException;

    public void updateAdmin(Admin admin) throws Exception;

    public void deactiveAdmin(Admin admin) throws Exception;

    public List<Admin> FinAllAdmin();

    public void confirmPlace(Place place) throws Exception;

    public void deactivateTag(Tag tag) throws Exception;

    public void confirmTag(Tag tag) throws Exception;
    
    public void confirmCompany(Company company) throws Exception;

    public void confirmTrip(Trip trip) throws Exception;

    public List<ClientReviewPlace> FindAllComment();
    
    public List<Company> findAllUncofirmCompany();
    
    public List<Place> findAllUncofirmPlaces();

    public List<Trip> findAllUncofirmTrips();

    public List<Tag> findAllUncofirmTags();
    
//    public Admin signin(Admin admin);
//    public Admin findAdmin(int id);
//    public void addPlace(Place place) throws Exception;
//    public void updatePlace(Place place) throws Exception;
//    public void deactivatePlace(Place place) throws Exception;
//    public Place findPlace(int id);
//    FindAllPlace();
//    public void addTag(Tag tag) throws Exception;
//    public void updateTag(Tag tag) throws Exception;
//    findAllTag();
//    AddlClient(Client);-Company-Partener-
//    deactivate client-company-partner
//    public void deactivateTrip(Trip trip)throws Exception;
}
