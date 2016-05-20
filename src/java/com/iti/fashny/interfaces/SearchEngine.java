/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.interfaces;

import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import java.util.List;

/**
 *
 * @author Abdalla
 */
public interface SearchEngine {

    List<Client> searchByExample(Client client) throws Exception;

    List<Company> searchByExample(Company company) throws Exception;

    List<Admin> searchByExample(Admin admin) throws Exception;

    List<Partener> searchByExample(Partener partener) throws Exception;

    List<Tag> searchByExample(Tag tag) throws Exception;

    List<Place> searchByExample(Place place) throws Exception;

    List<Trip> searchByExample(Trip trip) throws Exception;
    
}
