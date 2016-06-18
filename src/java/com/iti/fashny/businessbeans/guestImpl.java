/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.UploadImage;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartenerFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Resouce;
import com.iti.fashny.interfaces.Guest;

/**
 *
 * @author MANAR ADEL
 */
public class guestImpl implements Guest {

    UploadImage uploadImage;

    @Override
    public void logout() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signUp(Client c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        DaoFactory daoFactory = new DaoFactory();
        ClientFacade clientFacade = daoFactory.getClientDoa();
//        ClientFacade clientFacade = daoFactory.getResouceDoa();
        uploadImage = new UploadImage();

        try {
            daoFactory.beginTransaction();
            clientFacade.create(c);
            
            uploadImage.setFile(c.getPic());
            uploadImage.forClient("" + c.getId());
            String imgPath = uploadImage.handleFileUpload();
            
            c.setProfilePic(new Resouce(null, imgPath));
            clientFacade.edit(c);
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public void forgetPassword(String email) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirmForgetPassword(String email, String confirmationCode) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void signUp(Partener p) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        DaoFactory daoFactory = new DaoFactory();
        PartenerFacade partenerFacade = daoFactory.getPartenerDoa();

        try {
            daoFactory.beginTransaction();
            partenerFacade.create(p);
            //clientFacade.create(new Client(null, "omr", "2cli2@cc.com", "asasd2", "asdasd2", new Date(), (short) 1, (short) 2, new Timestamp(System.currentTimeMillis()), "2"));
            daoFactory.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
        //daoFactory.close();

    }

    @Override
    public void signUp(Company C) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
