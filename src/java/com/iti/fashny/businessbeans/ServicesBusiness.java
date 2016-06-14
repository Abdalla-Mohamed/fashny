/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.ServiceFacade;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.entities.Service;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ServicesBusiness implements Commens<Service> {

    @Override
    public Service login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Service t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ServiceFacade serviceFacade = daoFactory.getServiceDoa();

        try {

            daoFactory.beginTransaction();
            serviceFacade.create(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void update(Service t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        ServiceFacade serviceFacade = daoFactory.getServiceDoa();

        try {

            daoFactory.beginTransaction();
            serviceFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Service> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Service> serviceResults = new ArrayList<>();
        try {
            // get doas
            ServiceFacade serviceFacade = daoFactory.getServiceDoa();
            // search/read/select 
            serviceResults = serviceFacade.findAll();
            for (Service service : serviceResults) {
                System.out.println(service.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return serviceResults;
    }

    @Override
    public List<Service> searchByExample(Service t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
