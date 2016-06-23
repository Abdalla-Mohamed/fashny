/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class InterestsBusiness implements Commens<Tag> {

    @Override
    public Tag login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Tag t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Tag t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        TagFacade tagFacade = daoFactory.getTagDoa();

        try {

            daoFactory.beginTransaction();
            tagFacade.edit(t);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            exception.printStackTrace();
            daoFactory.rollbackTransaction();
        }
    }

    @Override
    public List<Tag> view() throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        List<Tag> tagList = new ArrayList<>();
        try {
            // get doas
            TagFacade tagFacade = daoFactory.getTagDoa();
            // search/read/select 
            tagList = tagFacade.findAll();
            for (Tag tag : tagList) {
                System.out.println(tag.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return tagList;
    }

    @Override
    public List<Tag> searchByExample(Tag t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tag showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        InterestsBusiness interestsBusiness = new InterestsBusiness();
        try {
            List<Tag> lt = interestsBusiness.view();
            for (Tag tag : lt) {
                System.out.println(tag.getName());
            }
        } catch (Exception ex) {
            Logger.getLogger(InterestsBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addInterests(Client c, List<Tag> tags) {
        DaoFactory factory = new DaoFactory();
        ClientFacade cf = factory.getClientDoa();  
        try {
            factory.beginTransaction();

             c.setTagList(tags);
             cf.edit(c);
            factory.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            factory.rollbackTransaction();
        }

    }
}
