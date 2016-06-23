/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.Commens;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Amira Anis
 */
public class TripBusiness implements Commens<Trip>, Serializable {

    @Override
    public Trip login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Trip t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        TripFacade tripFacade = daoFactory.getTripDoa();
        try {

            daoFactory.beginTransaction();
            tripFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Trip t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        TripFacade tripFacade = daoFactory.getTripDoa();

        try {

            daoFactory.beginTransaction();
            tripFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Trip> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> tripResults = new ArrayList<>();
        try {
            TripFacade tripFacade = daoFactory.getTripDoa();
            tripResults = tripFacade.findAll();
            for (Trip trip : tripResults) {
                System.out.println(trip.getName());
                List<Resouce> resouceList = trip.getResouceList();
                for (Resouce resouceList1 : resouceList) {
                    System.out.println(resouceList1.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return tripResults;
    }

    @Override
    public List<Trip> searchByExample(Trip t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trip showSpecificInfo(int id) {
        Trip trip = new Trip();
        DaoFactory dao = new DaoFactory();

        try {
            TripFacade p = dao.getTripDoa();
            trip = p.find(id);
            trip.getCompanyId();
            trip.getJoinTripList().size();
            trip.getPlaceList().size();
            trip.getResouceList().size();
            trip.getTagList().size();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            dao.close();
        }
        return trip;
    }

    public Trip gitAllCompanyLists(Trip tripObj) {
        DaoFactory daoFactory = new DaoFactory();
        try {
            TripFacade tripFacade = daoFactory.getTripDoa();
            daoFactory.beginTransaction();
            Trip trip = new Trip();
            trip = tripFacade.refreshObj(tripObj);
            tripObj.setPlaceList(trip.getPlaceList());
            tripObj.setTagList(trip.getTagList());
            tripObj.setJoinTripList(trip.getJoinTripList());
//            System.out.println(trip.getName());
//            List<Place> placesOfTripList = trip.getPlaceList();
//            List<Tag> tagsOfPlaceList = trip.getTagList();
//         

            System.out.println("places : -->" + tripObj.getPlaceList().size());
            System.out.println("tags : -->" + tripObj.getTagList().size());
//            for (Place place : placesOfTripList) {
//                System.out.println(place.getName());
//            }
            daoFactory.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return tripObj;
    }

    public void addImageToTrip(UploadedFile image, Trip trip) {

        DaoFactory daoFactory = new DaoFactory();
        TripFacade placeDoa = daoFactory.getTripDoa();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        System.out.println("~~~~~~~~~~~~~~~~~~~  " + image.getFileName() + " ~~~~~~~~~~~~~~~");
        try {
            daoFactory.beginTransaction();

            Integer tripId = trip.getId();

            UploadImage uploadImage = new UploadImage();
            uploadImage.setFile(image);
            uploadImage.forTrip("" + tripId);
            String filePath = uploadImage.handleFileUpload();

            Resouce resouce = new Resouce(null, filePath);
            resouce.setTripList(new ArrayList<>());
            resouce.getTripList().add(trip);
            resouceDoa.create(resouce);

//            Place find = placeDoa.find(placeId);
//            System.out.println("image count::"+find.getResouceList().size());
            trip.getResouceList().add(resouce);

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }

    }

    public boolean deleteImageFromTrip(Resouce selectedPic) {
        boolean deleted = false;
        DaoFactory daoFactory = new DaoFactory();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        try {
            daoFactory.beginTransaction();

            Resouce find = resouceDoa.find(selectedPic.getId());
            find.getPlaceList().clear();
            resouceDoa.remove(find);

            Files.delete(new File(selectedPic.getPath()).toPath());
            deleted = true;

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            deleted = false;
            daoFactory.rollbackTransaction();
        }
        return deleted;
    }

    public Trip getResources(Trip trip) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            TripFacade tripFacade = daoFactory.getTripDoa();
            daoFactory.beginTransaction();
            trip = tripFacade.find(trip.getId());
            List<Resouce> resouceList = trip.getResouceList();
            System.out.println(resouceList.size());
            for (Resouce resouceList1 : resouceList) {
                System.out.println(resouceList1.getPath());
            }
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            daoFactory.close();
        }
        return trip;
    }

    //_____________________ get validated trips_____________
    public List<Trip> viewValidated() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> tripResults = new ArrayList<>();
        try {
            TripFacade tripFacade = daoFactory.getTripDoa();
            tripResults = tripFacade.getValidTrips();
            for (Trip trip : tripResults) {
                System.out.println(trip.getName());
                List<Resouce> resouceList = trip.getResouceList();
                for (Resouce resouceList1 : resouceList) {
                    System.out.println(resouceList1.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return tripResults;
    }

    //_____________________ get validated trips_____________
}
