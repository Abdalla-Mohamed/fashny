package com.iti.fashny.interfaces;


import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Abdalla
 */
public interface Commens<T> {

     T login(String email, String password) throws Exception;

//<editor-fold defaultstate="collapsed" desc="comment">
     
////------------------------------------------------------------------------------
//    void addPlace(Place place) throws Exception;
//
//    List<Place> viewPlaces(int count) throws Exception;
//
//    List<Place> serchByPlaceExample(Place place) throws Exception;
//    Place showSpecificPlaceInfo(int id);
////------------------------------------------------------------------------------
//
//    void addTag(Tag tag) throws Exception;
//
//    List<Tag> viewTags() throws Exception;
//
//    List<Tag> serchByTagExample(Tag tag) throws Exception;
//
//    Tag showSpecificTagInfo(int id);
////------------------------------------------------------------------------------
//    List<Trip> viewTrips(int count) throws Exception;
//
//    List<Trip> serchByTripExample(Trip trip) throws Exception;
//
//    Trip showSpecificTripInfo(int id);
////------------------------------------------------------------------------------
//    List<Company> viewCompanies(int count) throws Exception;
//
//    List<Company> serchByCompanyExample(Company company) throws Exception;
//
//    Company showSpecificCompanyInfo(int id);
//
////------------------------------------------------------------------------------
//    List<Partener> viewParteners(int count, PartnType type) throws Exception;
//
//    List<Partener> serchByPartenerExample(Partener partener) throws Exception;
//
//    Partener showSpecificPartneInfo(int id);
//
//------------------------------------------------------------------------------
     
     
//</editor-fold>
  
    void add(T t) throws Exception;
    void update(T t) throws Exception;

    List<T>view() throws Exception;

    List<T> searchByExample(T t) throws Exception;

    T showSpecificInfo(int id);
    
//------------------------------------------------------------------------------
    
}
