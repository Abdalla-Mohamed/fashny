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
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.AdminInterface;
import com.iti.fashny.managedbeans.*;
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
        exampleForWriting();
//        PlaceViewManagedBean_1 pmb = new PlaceViewManagedBean_1();
//        pmb.create();
//        exampleForReading();
        //tst();
//       exampleForWriting();
//        AdminInterface adminInterface = new AdminManager();
////        AdminConfirmationPanel acp = new AdminConfirmationPanel();
//        System.out.println("places_________________");
//        for (Place p : adminInterface.findAllUncofirmPlaces()) {
//            System.out.println("--->>" + p.getName());
//        }
//        System.out.println("trips_________________");
//        for ( Trip t : acp.showUnconfirmTrips()) {
//            System.out.println("t name" + t.getName());
//        }
//        System.out.println("tags_________________");
//        for (Tag tg : acp.showUnconfirmTags()) {
//            System.out.println("tg name" + tg.getName());
//        }
//        for (Tag tt : adminInterface.findAllUncofirmTags()) {
//            System.out.println("--->>" + tt.getName());
//        }
//        

    }

    //////////////////////////////////////////////////////////////////////////
    static public void exampleForWriting() {

        // create DaoFactory to get daos and deal with connection
        DaoFactory daoFactory = new DaoFactory();

        // get daos with the same connection
        ClientFacade clientFacade = daoFactory.getClientDoa();
        AdminFacade adminFacade = daoFactory.getAdminDoa();
        CompanyFacade companyFacade = daoFactory.getCompanyDoa();
        TripFacade tripFacade = daoFactory.getTripDoa();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        try {
            //start Transaction
            daoFactory.beginTransaction();
            Company c = new Company(9);
            Resouce resouce = new Resouce(null, "test4");
            resouceDoa.create(resouce);
            Company find = companyFacade.find(c.getId());
            find.setProfilePic(resouce);
                    // do your crud
                    // adminFacade.create(new Admin(null, "sss44", "sss44", true, true, new Timestamp(System.currentTimeMillis()), "asdas4422@sds.com", "04111322"));
                    //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
                    //Integer id, String name, String password, String address, boolean validated, Date lastSeen, String email, String mobile1
                    //companyFacade.create(new Company(null,"comp1","pass","add",true,new Timestamp(System.currentTimeMillis()),  "mail","mob" ));
                    //Integer id, String name, int cost, int countBooking, String program, Date date, Date joinDeadline, int maxbooking, boolean validated)
                    // tripFacade.create(new Trip(null, "trip1",100,0,"p",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),10,false));
                    //            tripFacade.create(new Trip(null, "trip2",200,0,"pp",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),20, true,1));
//            tripFacade.create(new Trip(null, "trip3",300,0,"ppp",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),30,false,1));
//            tripFacade.create(new Trip(null, "trip4",400,0,"pppp",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),40,false,1));
                    // commit all edit and close entityManager automatic
;

            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();

            // rollBack all edit if any exception happened and close entityManager automatic
            daoFactory.rollbackTransaction();
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

    static public void exampleForReading22() {
        Place p = new Place(88, "zoo", "giza", "first street", 12.2, 22.2, Boolean.FALSE);

        // create DaoFactory to get daos and deal with connection
        DaoFactory daoFactory = new DaoFactory();

        List<Place> tagResults = new ArrayList<>();

        try {

            // get doas
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();

            // search/read/select 
            tagResults = placeFacade.findByExample(p);

            for (Place tagrslt : tagResults) {
                System.out.println(tagrslt.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }

        if (tagResults.size() > 0) {
            System.out.println("~~~~~~~ 1 obj");
            PlaceViewManagedBean_1 pmb = new PlaceViewManagedBean_1();
            //
            //

            tagResults.get(0).setDescription("I am updated");
            //pmb.update(tagResults.get(0));
//            pmb.destroy(tagResults.get(0));

            //
            //
        } else {
            System.out.println("........ non");
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
