/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

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
   
        images= new ArrayList<>();
           for (int i = 1; i <= 4; i++) {
            images.add(new placeImage("sdfsdfsdf", "nature" + i + ".jpg"));
        }
        barImages= new ArrayList<>();
            barImages.add(new placeImage("deal with professional companies", "com.jpg"));
            barImages.add(new placeImage("deal with professional companies", "comp.jpg"));
            barImages.add(new placeImage("deal with professional Hotels", "hotel.jpg"));
            barImages.add(new placeImage("BEST Restaurant, DELICIOUS food", "rest.jpg"));
            barImages.add(new placeImage("discover new places", "place.jpg"));
            barImages.add(new placeImage("discover new places", "place2.jpg"));
            barImages.add(new placeImage("catch special offers", "offers.jpg"));
            barImages.add(new placeImage("meet new people in amazing trips", "trip.jpg"));
            barImages.add(new placeImage("meet new people in amazing trips", "trip2.jpg"));
            barImages.add(new placeImage("meet new people in amazing trips", "trips.jpg"));
        
    }

    public List<placeImage> getImages() {
        return images;
    }

    public void setImages(List<placeImage> images) {
        this.images = images;
    }

    public List<placeImage> getBarImages() {
        return barImages;
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
    
}
