/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.TagFacade;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.Commens;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdalla
 */
public class TagsController implements Commens<Tag> {

    @Override
    public Tag login(String email, String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Tag t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            TagFacade tagFacade = daoFactory.getTagDoa();
            daoFactory.beginTransaction();
            tagFacade.create(t);
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.commitTransaction();
        }
    }

    @Override
    public void update(Tag t) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            TagFacade tagFacade = daoFactory.getTagDoa();
            daoFactory.beginTransaction();
            tagFacade.edit(t);
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.commitTransaction();
        }
    }

    @Override
    public List<Tag> view() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Tag> tags = new ArrayList<>();
        try {
            TagFacade tagFacade = daoFactory.getTagDoa();
            tags = tagFacade.findAll();
        } finally {
            daoFactory.close();
        }
        return tags;

    }

    @Override
    public List<Tag> searchByExample(Tag t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tag showSpecificInfo(int id) {
        DaoFactory daoFactory = new DaoFactory();
        Tag tag;
        try {
            TagFacade tagFacade = daoFactory.getTagDoa();
            tag = tagFacade.find(id);
        } finally {
            daoFactory.close();
        }
        return tag;

    }

    @Override
    public void delete(Tag t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
