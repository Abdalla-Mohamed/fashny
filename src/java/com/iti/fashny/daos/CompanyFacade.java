/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;

/**
 *
 * @author Hosam
 */
public class CompanyFacade extends AbstractFacade<Company> {

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
}
