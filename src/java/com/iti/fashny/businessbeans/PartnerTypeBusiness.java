/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.daos.PartnTypeFacade;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MANAR ADEL
 */
public class PartnerTypeBusiness implements Commens<PartnType>
{

    @Override
    public PartnType login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(PartnType t) throws Exception 
    {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            partnTypeDoa.create(t);
            //partenerDoa.create(t);
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

            daoFactory.close();

        }
    }

    @Override
    public void update(PartnType t) throws Exception 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            partnTypeDoa.edit(t);
            //partenerDoa.edit(t);
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

            daoFactory.close();

        }
    }

    @Override
    public List<PartnType> view() throws Exception 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        

          //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<PartnType> partnTypeResults = new ArrayList<>();

        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerDoa = daoFactory.getPartenerDoa();
        PartnTypeFacade partnTypeDoa = daoFactory.getPartnTypeDoa();

//        daoFactory.beginTransaction();
//        daoFactory.commitTransaction();
        try {
            daoFactory.beginTransaction();
            //partnTypeDoa.create(new PartnType(1,"Organization"));
             partnTypeResults = partnTypeDoa.findAll();
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            for (PartnType partnType : partnTypeResults) {
                System.out.println(partnType.getName());
            }

            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();

        } finally {
            // close connection
            daoFactory.close();
        }
        return partnTypeResults;
    }

    @Override
    public List<PartnType> searchByExample(PartnType t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PartnType showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
