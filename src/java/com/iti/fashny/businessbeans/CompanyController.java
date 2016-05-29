/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Company;
import com.iti.fashny.interfaces.Commens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javassist.bytecode.InnerClassesAttribute.tag;

/**
 *
 * @author Abdalla
 */
public class CompanyController implements Commens<Company>,Serializable{

    @Override
    public Company login(String email, String password) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
}
    
    @Override
    public void add(Company company) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            companyFacade.create(company);
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.commitTransaction();
        }
    }

    @Override
    public void update(Company company) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            companyFacade.edit(company);
            daoFactory.commitTransaction();
        } catch (Exception e) {
            daoFactory.commitTransaction();
        }
    }

    @Override
    public List<Company> view() throws Exception {
  
        DaoFactory daoFactory = new DaoFactory();
        List<Company> tags = new ArrayList<>();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            tags=companyFacade.findAll();
        } finally {
            daoFactory.close();
        }
        return tags;
        
    }

    @Override
    public List<Company> searchByExample(Company company) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
}

    @Override
    public Company showSpecificInfo(int id) {
          DaoFactory daoFactory = new DaoFactory();
        Company company ;
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            company=companyFacade.find(id);
        } finally {
            daoFactory.close();
        }
        return company;
        
    }

    
}
