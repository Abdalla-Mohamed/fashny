/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.exceptions.Fasa7nyException;
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

    public List<Company> getUnconcirmCompanies() {
        List<Company> unconfirmCompanies = new ArrayList<>();
        try {

            unconfirmCompanies = getEntityManager().createNamedQuery("Company.findByValidated").setParameter("validated", false).getResultList();
//            for (Place placerslt : unconfirmPlaces) {
//                System.out.println(placerslt.getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unconfirmCompanies;
    }

    public Company login(String email, String pass) throws Fasa7nyException {
        System.out.println("=========================");
        System.out.println(email + "," + pass);
        System.out.println("=========================");
        List result = (List) getEntityManager().createQuery(HQL_LOGIN)
                .setParameter("email", email).setParameter("password", pass).getResultList();
        if (result == null || result.isEmpty()) {
            throw new Fasa7nyException();
        }
        return (Company) result.get(0);

    }

}
