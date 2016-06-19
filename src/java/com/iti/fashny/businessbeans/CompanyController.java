/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;
import com.iti.fashny.interfaces.Commens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javassist.bytecode.InnerClassesAttribute.tag;

/**
 *
 * @author Abdalla
 */
public class CompanyController implements Commens<Company>, Serializable {

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
            daoFactory.rollbackTransaction();
            throw e;
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
            daoFactory.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public List<Company> view() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Company> companies = new ArrayList<>();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            companies = companyFacade.findAll();
        } finally {
            daoFactory.close();
        }
        return companies;

    }

    public List<Company> getValidateCompanyForClient() throws Exception {

        DaoFactory daoFactory = new DaoFactory();
        List<Company> companies = new ArrayList<>();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            //companies = companyFacade.getConfirmCompanies();
            companies = companyFacade.getConfirmAndActiveCompanies();
        } finally {
            daoFactory.close();
        }
        System.out.println("====================================================================");
        for (Company company : companies) {
            System.out.println(".......>>>"+ company.getName());
        }
        return companies;
    }

    public Company gitTripsOfCompany(Company company) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            company = companyFacade.refreshObj(company);
            System.out.println(company.getName());
            List<Trip> tripsOfCompanyList = company.getTripList();
            System.out.println(tripsOfCompanyList.size());
            for (Trip trip : tripsOfCompanyList) {
                System.out.println(trip.getName());
            }
            daoFactory.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return company;
    }

    public Company gitTagsOfCompany(Company company) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            company = companyFacade.refreshObj(company);
            System.out.println(company.getName());
            List<Tag> tagsOfCompanyList = company.getTagList();
            System.out.println(tagsOfCompanyList.size());
            for (Tag tag : tagsOfCompanyList) {
                System.out.println(tag.getName());
            }
            daoFactory.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return company;
    }

    public Company gitAllCompanyLists(Company companyObj) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            Company company = new Company();
            company = companyFacade.find(companyObj.getId());
            company.getTagList().size();
            companyObj.setTagList(company.getTagList());
            company.getTripList().size();
            companyObj.setTripList(company.getTripList());
            daoFactory.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            daoFactory.rollbackTransaction();
        } finally {
            // close connection
            daoFactory.close();
        }
        return companyObj;
    }

    @Override
    public List<Company> searchByExample(Company company) throws Exception {
        return null;
    }

    @Override
    public Company showSpecificInfo(int id) {
        DaoFactory daoFactory = new DaoFactory();
        Company company;
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            company = companyFacade.find(id);
        } finally {
            daoFactory.close();
        }
        return company;

    }
}
