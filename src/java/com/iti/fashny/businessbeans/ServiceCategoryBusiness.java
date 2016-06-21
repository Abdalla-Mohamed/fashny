/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.ServiceCategoreyFacade;
import com.iti.fashny.daos.ServiceFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.JoinTrip;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Service;
import com.iti.fashny.entities.ServiceCategorey;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ServiceCategoryBusiness implements Commens<ServiceCategorey> {

    @Override
    public ServiceCategorey login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(ServiceCategorey t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ServiceCategoreyFacade serviceCatFacade = daoFactory.getServiceCategoreyDoa();
        try {
            daoFactory.beginTransaction();
            serviceCatFacade.create(t);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(ServiceCategorey t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ServiceCategoreyFacade serviceCatFacade = daoFactory.getServiceCategoreyDoa();
        try {
            daoFactory.beginTransaction();
            serviceCatFacade.edit(t);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<ServiceCategorey> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<ServiceCategorey> serviceCategories = new ArrayList<>();
        try {
            // get doas
            ServiceCategoreyFacade serviceFacade = daoFactory.getServiceCategoreyDoa();
            // search/read/select 
            serviceCategories = serviceFacade.findAll();
            for (ServiceCategorey service : serviceCategories) {
                System.out.println(service.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return serviceCategories;
    }

    @Override
    public List<ServiceCategorey> searchByExample(ServiceCategorey t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceCategorey showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ServiceCategorey> getCategory(Partener p) {
        List<ServiceCategorey> partnerCat;
        partnerCat = p.getServiceCategoreyList();
        return partnerCat;
    }
    
    
}
