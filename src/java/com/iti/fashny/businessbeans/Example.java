/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.AdminInterface;
import com.iti.fashny.managedbeans.AdminConfirmationPanel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdalla
 */
public class Example {

    public static void main(String[] args) {
// 
    CompanyController  cc=  new CompanyController();
    List<Company>  companys = new ArrayList<>();
        try {
            companys=cc.view();
                    } catch (Exception ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Company company : companys) {
            System.out.println("$$$$$$$$"+company.getName());
        }
    }

    static public void exampleForReading() {
        Tag tag = new Tag(1, "sport", null);

        // create DaoFactory to get daos and deal with connection
        DaoFactory daoFactory = new DaoFactory();

        List<Tag> tagResults = new ArrayList<>();

        try {

            // get doas
            TagFacade tagFacade = daoFactory.getTagDoa();

            // search/read/select 
            tagResults = tagFacade.findByExample(tag);

            for (Tag tagrslt : tagResults) {
                System.out.println(tagrslt.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();

        }

    }

    static public void tst() {
        Tag tag = new Tag(2);
        Place p = new Place();
        p.setName("sd");
        p.setTagList(new ArrayList<>());
        p.getTagList().add(tag);

        // create DaoFactory to get daos and deal with connection
        DaoFactory daoFactory = new DaoFactory();

        List<Place> places = new ArrayList<>();

        try {

            // get doas
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();

            // search/read/select 
            places = placeFacade.findByExample(p);

            for (Place place : places) {
                System.out.println(place.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();

        }

    }

}
