/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Criteria;

/**
 *
 * @author Hosam
 */
public class CompanyFacade extends AbstractFacade<Company> {

    private static final String HQL_LOGIN = "FROM Company c WHERE c.email =:email AND c.password =:password ";

    CompanyFacade(EntityManager em) {
        super(Company.class, em);
    }

    public boolean validMail(String mail) {

        boolean valid = false;

        List resultList = getEntityManager().createNamedQuery("Company.findByEmail").setParameter("email", mail).getResultList();

        if (resultList.size() >= 1) {
            valid = true;
        }

        return valid;
    }

    @Override
    protected void addAssociationExample(Criteria c, Company mainExample) {

        List<Tag> tags = mainExample.getTagList();

        if (tags != null) {
            addTagConditionOnExample(c, tags, "tagList");
        }

    }

    public List<Company> getUnconfirmCompanies() {
        List<Company> unconfirmCompanies = new ArrayList<>();
        try {

            unconfirmCompanies = getEntityManager().createNamedQuery("Company.findByValidated").setParameter("validated", false).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unconfirmCompanies;
    }

    public List<Company> getConfirmCompanies() {
        List<Company> unconfirmCompanies = new ArrayList<>();
        try {

            unconfirmCompanies = getEntityManager().createNamedQuery("Company.findByValidated").setParameter("validated", true).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unconfirmCompanies;
    }

    public Company login(String email, String pass) throws InvalidLoginDataException, NotConfirmAccountException, DeletedAccountException {
        Company company;
        System.out.println("=========================");
        System.out.println(email + "," + pass);
        System.out.println("=========================");
        List result = (List) getEntityManager().createQuery(HQL_LOGIN)
                .setParameter("email", email).setParameter("password", pass).getResultList();
        if (result == null || result.isEmpty()) {
            throw new InvalidLoginDataException();
        } else {
            company = (Company) result.get(0);
            if (company.getValidated() == false) {
                throw new NotConfirmAccountException();
            } else if (company.getActive()== false) {
                throw new DeletedAccountException();
                  
            }
        }

        return company;

    }
}
