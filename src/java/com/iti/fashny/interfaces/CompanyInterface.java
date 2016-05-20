package com.iti.fashny.interfaces;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Trip;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface CompanyInterface {

//    public void signup(Company company) throws Exception;

  //  public Company signin(String name, String password) throws Exception;

    //public void updateProfile(Company company) throws Exception;

    public void addTrip(Trip trip) throws Exception;

    public void deactivateTrip(Trip trip) throws Exception;

    public List<Trip> getCompanyTrips() throws Exception;
    public List<Trip> getCompanyOldTrips() throws Exception;

    public void addCompanyResource(Resouce resource) throws Exception;

    public void deleteResource(Resouce resource) throws Exception;
    public HashMap<Place,Integer> getWishes() throws Exception;

}
