/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.entities.Company;
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
            tags = companyFacade.findAll();
        } finally {
            daoFactory.close();
        }
        return tags;

    }

    public void gitTripsOfCompany(Company company) throws Exception {
        DaoFactory daoFactory = new DaoFactory();
        try {
            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
            daoFactory.beginTransaction();
            Company find = companyFacade.find(company.getId());

            System.out.println(find.getName());
            List<Trip> tripsOfCompanyList = find.getTripList();
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
//        return tripResults;
    }

//    public List<Company> gitTripsOfCompany(List<Company> companiesList) throws Exception {
//
//        DaoFactory daoFactory = new DaoFactory();
////        List<Company> companyResults = new ArrayList<>();
//        try {
//            CompanyFacade companyFacade = daoFactory.getCompanyDoa();
//            for (Company company: companiesList) {
//                System.out.println(company.getName());
//                List<Trip> tripsOfCompanyList = company.getTripList();
//                for (Trip trip : tripsOfCompanyList) {
//                    System.out.println(trip.getName());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // close connection
//            daoFactory.close();
//        }
//        return companiesList;
//}
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
