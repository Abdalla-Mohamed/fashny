/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.PartnerBusiness;
import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.businessbeans.TripBusiness;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Trip;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import org.hibernate.cfg.HbmBinder;

/**
 *
 * @author Abdalla
 */
@ManagedBean
@ApplicationScoped
public class HomeMB {

    List<placeImage> images ;
    List<placeImage> barImages ;
    
    
    /**
     * Creates a new instance of HomeMB
     */
    public HomeMB() {
   
//        images= new ArrayList<>();
//           for (int i = 1; i <= 4; i++) {
//            images.add(new placeImage("sdfsdfsdf", "nature" + i + ".jpg"));
//        }
       
    }

    public List<placeImage> getImages() {
        return images;
    }

    public void setImages(List<placeImage> images) {
        this.images = images;
    }


//get the first two images from Places , trips , companies , parteners   
    
    public List<placeImage> getBarImages() {
       // places list
        List<Place> places = null;
        PlaceBusiness placeBusiness = new PlaceBusiness();
       //trips list
        List<Trip> trips = null;
        TripBusiness tripBusiness = new TripBusiness();
      // companies list
        List<Company> companies =null;
        CompanyController companyController =new CompanyController();
      // parteners list
        List<Partener>parteners = null;
        PartnerBusiness partnerBusiness = new PartnerBusiness();
      // bar images list  
       List<placeImage>bar = new ArrayList();
        // full the lists       
         try {
           places  = placeBusiness.viewActive();
           trips  = tripBusiness.viewValidated();
           companies = companyController.getValidateCompanyForClient();
           parteners = partnerBusiness.viewActive();
        } catch (Exception ex) {
            Logger.getLogger(HomeMB.class.getName()).log(Level.SEVERE, null, ex);
        }
       // add first 2 pics of every list
         for (int x =0; x<2;x++) {
          
           bar.add(new placeImage(places.get(x).getName(),getFirstImg(places.get(x))));
           bar.add(new placeImage(trips.get(x).getName(),getTripFirstImg(trips.get(x))));
                if(companies.get(x).getProfilePic() != null)
           bar.add(new placeImage(companies.get(x).getName(),companies.get(x).getProfilePic().getPath()));
                if(parteners.get(x).getProfilePic() != null)
           bar.add(new placeImage(parteners.get(x).getName(),parteners.get(x).getProfilePic().getPath()));
         
        }
        
        return bar;
    }     

    public void setBarImages(List<placeImage> barImages) {
        this.barImages = barImages;
    }

   

    

//----------------------------------------------------------------------------    
//----------------------------------------------------------------------------    
    public class placeImage{
        String placeName;
        String imgPath;

        public placeImage(String placeName, String imgPath) {
            this.placeName = placeName;
            this.imgPath = imgPath;
        }

        public String getImgPath() {
            return imgPath;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }
        
        
    }
    
    public String getFirstImg(Place place) {
        String path = "0";
        if (place.getResouceList() != null && !place.getResouceList().isEmpty()) {
            path = place.getResouceList().get(0).getPath();
        }
        return path;
    }  
    
     public String getTripFirstImg(Trip trip) {
         String path = "0";
        if (trip.getResouceList() != null && !trip.getResouceList().isEmpty()) {
            path = trip.getResouceList().get(0).getPath();
        }
        return path;  
    }
        
   
}
