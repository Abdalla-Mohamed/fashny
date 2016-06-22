/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Admin;
import com.iti.fashny.entities.ClientReviewPlace;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.AdminInterface;
import com.iti.fashny.managedbeans.CompanyManagedBean;
import com.iti.fashny.managedbeans.PartnerCRUDSBean;
import com.iti.fashny.managedbeans.PlaceViewManagedBean_1;
import com.iti.fashny.managedbeans.TripManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amira Anis
 */
public class AdminManager implements AdminInterface {

    private PlaceBusiness placeBusiness;
    private TripBusiness tripBusiness;
    private TagBusiness tagBusiness;
    private CompanyController companyController;
    private PartnerBusiness partnerBusiness;

    @Override
    public void addAdmin(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAdmin(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deactiveAdmin(Admin admin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> FinAllAdmin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmPartener(Partener partener) throws Exception {
        if (partener!= null) {
            try {
                partener.setValidated(Boolean.TRUE);
                partnerBusiness = new PartnerBusiness();
                partnerBusiness.update(partener);
            } catch (Exception ex) {
                Logger.getLogger(PartnerCRUDSBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void confirmPlace(Place place) throws Exception {

        if (place != null) {
            try {

                place.setValidated(Boolean.TRUE);
                placeBusiness = new PlaceBusiness();
                placeBusiness.update(place);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deactivateTag(Tag tag) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmTag(Tag tag) throws Exception {
        if (tag != null) {
            try {

                tag.setVaidated(Boolean.TRUE);
                tagBusiness = new TagBusiness();
                tagBusiness.update(tag);
            } catch (Exception ex) {
                Logger.getLogger(PlaceViewManagedBean_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void confirmCompany(Company company) throws Exception {
        if (company != null) {
            try {

                company.setValidated(Boolean.TRUE);
                companyController= new CompanyController();
                companyController.update(company);
            } catch (Exception ex) {
                Logger.getLogger(CompanyManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void confirmTrip(Trip trip) throws Exception {
        if (trip != null) {
            try {

                trip.setValidated(Boolean.TRUE);
                tripBusiness = new TripBusiness();
                tripBusiness.update(trip);
            } catch (Exception ex) {
                Logger.getLogger(TripManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<ClientReviewPlace> FindAllComment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Partener> findAllUncofirmPartener() {
        DaoFactory daoFactory = new DaoFactory();
        List<Partener> unconfirmPartener = new ArrayList<>();

        try {

            // get doas
            PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
            unconfirmPartener = partenerFacade.getUnconcirmPartener();

            for (Partener partenerRslt : unconfirmPartener) {
                System.out.println( partenerRslt.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            daoFactory.close();
        }

        return unconfirmPartener;
    }
    @Override
    public List<Place> findAllUncofirmPlaces() {
        DaoFactory daoFactory = new DaoFactory();
        List<Place> unconfirmPlaces = new ArrayList<>();

        try {

            // get doas
            PlaceFacade palceFacade = daoFactory.getPlaceDoa();
            unconfirmPlaces = palceFacade.getUnconcirmPlaces();

            for (Place placerslt : unconfirmPlaces) {
                System.out.println(placerslt.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            daoFactory.close();
        }

        return unconfirmPlaces;
    }

    @Override
    public List<Trip> findAllUncofirmTrips() {
        DaoFactory daoFactory = new DaoFactory();
        List<Trip> unconfirmTrips = new ArrayList<>();

        try {

            // get doas
            TripFacade tripFacade = daoFactory.getTripDoa();
            unconfirmTrips = tripFacade.getUnconcirmTrips();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return unconfirmTrips;
    }

    @Override
    public List<Tag> findAllUncofirmTags() {
        DaoFactory daoFactory = new DaoFactory();
        List<Tag> unconfirmTags = new ArrayList<>();
        try {

            TagFacade tagFacade = daoFactory.getTagDoa();
            unconfirmTags = tagFacade.getUnconcirmTags();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return unconfirmTags;
    }

    @Override
    public List<Company> findAllUncofirmCompany() {
        DaoFactory daoFactory = new DaoFactory();
        List<Company> unconfirmCompanies = new ArrayList<>();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            unconfirmCompanies = companyFacade.getUnconfirmCompanies();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return unconfirmCompanies;
    }
}
