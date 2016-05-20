/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Abdalla
 */
public class DaoFactory {

    final static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("fasa7ny");

    private EntityManager entityManager;

    public DaoFactory() {
    }

    public AdminFacade getAdminDoa() {
        return new AdminFacade(getEntityManager());
    }

    public ClientFacade getClientDoa() {
        return new ClientFacade(getEntityManager());
    }
    public CompanyFacade getCompanyDoa() {
        return new CompanyFacade(getEntityManager());
    }
    public JoinTripFacade getJoinTripDoa() {
        return new JoinTripFacade(getEntityManager());
    }
    public PartenerFacade getPartenerDoa() {
        return new PartenerFacade(getEntityManager());
    }
    public PartnTypeFacade getPartnTypeDoa() {
        return new PartnTypeFacade(getEntityManager());
    }
    public PlaceFacade getPlaceDoa() {
        return new PlaceFacade(getEntityManager());
    }
    public ResouceFacade getResouceDoa() {
        return new ResouceFacade(getEntityManager());
    }
    public ServiceCategoreyFacade getServiceCategoreyDoa() {
        return new ServiceCategoreyFacade(getEntityManager());
    }
    public ServiceFacade getServiceDoa() {
        return new ServiceFacade(getEntityManager());
    }
    public TagFacade getTagDoa() {
        return new TagFacade(getEntityManager());
    }
    public TripFacade getTripDoa() {
        return new TripFacade(getEntityManager());
    }

//    --------------------------------------------------------------------------
    
    
    public void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public void commitTransaction() {
        getEntityManager().getTransaction().commit();
        close();
    }

    public void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
        close();
    }

    public void close() {
        if(isConnected())
        getEntityManager().close();
    }


//    --------------------------------------------------------------------------


    private EntityManager getEntityManager() {
        if (!isConnected()) {
            entityManager = emfactory.createEntityManager();

        }
        return entityManager;
    }

    private boolean isConnected(){
     return (entityManager != null && entityManager.isOpen());
    }
}
