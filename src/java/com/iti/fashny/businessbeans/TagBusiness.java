/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.daos.TripFacade;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amira Anis
 */
public class TagBusiness implements Commens<Tag> {

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
        List<Tag> tagResults = new ArrayList<>();
        try {
            // get doas
            TagFacade tagFacade = daoFactory.getTagDoa();
            // search/read/select 
            tagResults = tagFacade.findAll();
            for (Tag tag : tagResults) {
                System.out.println(tag.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connection
            daoFactory.close();
        }
        return tagResults;
    }

    @Override
    public List<Tag> searchByExample(Tag t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tag showSpecificInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
