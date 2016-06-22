/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.daos.AdminFacade;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.ResouceFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.ClientReviewPlace;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bakar M.M.R
 */
public class PlaceBusiness implements Commens<Place> {

    @Override
    public Place login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Place t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeFacade = daoFactory.getPlaceDoa();
        try {

            daoFactory.beginTransaction();
            placeFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Place t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeFacade = daoFactory.getPlaceDoa();
        try {

            daoFactory.beginTransaction();
            placeFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Place> view() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Place> placeResults = new ArrayList<>();
        try {
            // get doas
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            // search/read/select 
            placeResults = placeFacade.findAll();
            for (Place place : placeResults) {
                System.out.println(place.getName());
                List<Resouce> resouceList = place.getResouceList();
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
        return placeResults;
    }
    
        public void getPlaceResoures(Place place) {
        DaoFactory daoFactory = new DaoFactory();

        try {
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            daoFactory.beginTransaction();
            placeFacade.refresh(place);
            place.getResouceList();
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.rollbackTransaction();
            e.printStackTrace();
        }

    }

    @Override
    public List<Place> searchByExample(Place t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Place showSpecificInfo(int id) {
        Place place = new Place();
        DaoFactory dao = new DaoFactory();
        PlaceFacade p = dao.getPlaceDoa();
        place = p.find(id);
        place.getResouceList().size();
        dao.close();
        return place;
    }

    public Place getComments(Place place) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            daoFactory.beginTransaction();
            place = placeFacade.find(place.getId());
            List<ClientReviewPlace> clientReviewPlaceList = place.getClientReviewPlaceList();
            System.out.println(clientReviewPlaceList.size());
            for (ClientReviewPlace crl : clientReviewPlaceList) {
                System.out.println(crl.getClientId().getName() + ":" + crl.getComment());
            }
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return place;
    }

    public Place getResources(Place place) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            daoFactory.beginTransaction();
            place = placeFacade.find(place.getId());
            List<Resouce> resouceList = place.getResouceList();
            System.out.println(resouceList.size());
            for (Resouce resouceList1 : resouceList) {
                System.out.println(resouceList1.getPath());
            }
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return place;
    }

    public void addImageToPlace(UploadedFile image, Place place) {
        DaoFactory daoFactory = new DaoFactory();
        PlaceFacade placeDoa = daoFactory.getPlaceDoa();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        System.out.println("~~~~~~~~~~~~~~~~~~~  " + image.getFileName() + " ~~~~~~~~~~~~~~~");
        try {
            daoFactory.beginTransaction();

            Integer placeId = place.getId();

            UploadImage uploadImage = new UploadImage();
            uploadImage.setFile(image);
            uploadImage.forPlace("" + placeId);
            String filePath = uploadImage.handleFileUpload();

            Resouce resouce = new Resouce(null, filePath);
            resouce.setPlaceList(new ArrayList<>());
            resouce.getPlaceList().add(place);
            resouceDoa.create(resouce);

//            Place find = placeDoa.find(placeId);
//            System.out.println("image count::"+find.getResouceList().size());

            place.getResouceList().add(resouce);
            
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }

    }

    public boolean deleteImageFromPlace(Resouce selectedPic) {
        boolean deleted = false;
        DaoFactory daoFactory = new DaoFactory();
        ResouceFacade resouceDoa = daoFactory.getResouceDoa();
        try{
            daoFactory.beginTransaction();
            
            Resouce find = resouceDoa.find(selectedPic.getId());
            find.getPlaceList().clear();
            resouceDoa.remove(find);
            
            Files.delete(new File(selectedPic.getPath()).toPath());
            deleted =true;
            
            daoFactory.commitTransaction();
        }catch(Exception exception){
            exception.printStackTrace();
            deleted =false;
            daoFactory.rollbackTransaction();
        }
        return deleted;
    }
   
    
    //___________________view valdited and active places_____________
    
    public List<Place> viewActive() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Place> placeResults = new ArrayList<>();
        try {
            // get doas
            PlaceFacade placeFacade = daoFactory.getPlaceDoa();
            // search/read/select 
            placeResults = placeFacade.findAllActive();
            for (Place place : placeResults) {
                System.out.println(place.getName());
                List<Resouce> resouceList = place.getResouceList();
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
        return placeResults;
    }

    //___________________view valdited and active places_____________

}
