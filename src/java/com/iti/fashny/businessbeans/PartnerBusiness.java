/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PartnTypeFacade;
import com.iti.fashny.daos.PlaceFacade;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.ServiceCategorey;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MANAR ADEL
 */
public class PartnerBusiness implements Commens<Partener> {

    @Override
    public Partener login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Partener t) throws Exception {
        System.out.println(t.getName());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            //partnTypeDoa.create(new PartnType(1,"Organization"));
            partenerDoa.create(t);
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

            daoFactory.close();

        }

    }

    @Override
    public void update(Partener t) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            //partnTypeDoa.create(new PartnType(1,"Organization"));
            partenerDoa.edit(t);
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

            daoFactory.close();

        }

    }

    @Override
    public List<Partener> view() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<Partener> partnerResults = new ArrayList<>();

        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            //partnTypeDoa.create(new PartnType(1,"Organization"));
            partnerResults = partenerDoa.findAll();
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            for (Partener partener : partnerResults) {
                System.out.println(partener.getName());
            }

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

        } finally {
            // close connection
            daoFactory.close();
        }
        return partnerResults;
    }

    @Override
    public List<Partener> searchByExample(Partener t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Partener showSpecificInfo(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Partener partenr = new Partener();
        DaoFactory dao = new DaoFactory();
        PartenerFacade partenerDoa = dao.getPartenerDoa();
        partenr = partenerDoa.find(id);
        return partenr;
    }

    public Partener getCategoryList(Partener partener) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            PartenerFacade partenerFacade = daoFactory.getPartenerDoa();
            daoFactory.beginTransaction();
            partener = partenerFacade.find(partener.getId());
            List<ServiceCategorey> serviceCategoreyList = partener.getServiceCategoreyList();
            System.out.println(serviceCategoreyList.size());
            for (ServiceCategorey serviceCategorey : serviceCategoreyList) {
                System.out.println( serviceCategorey.getName());
            }
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return partener;
    }
}
