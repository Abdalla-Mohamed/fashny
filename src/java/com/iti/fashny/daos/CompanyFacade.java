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
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Hosam
 */
public class CompanyFacade extends AbstractFacade<Company> {

    private static final String HQL_LOGIN = "FROM Company c WHERE c.email =:email AND c.password =:password ";
    private static final String HQL_CLIENT_COMPANY = "FROM Company c WHERE c.validated = :validated AND c.active = :active";
    private static final String HQL_Company_Count = "select count(c) from Company c";
    private static final String HQL_VALIDATED_Company_Count = "select  count(c) FROM Company c where c.validated = 1 AND c.active = 1";
    private static final String HQL_COMPANY_TRIPS_COUNT = "select c.name , size(c.tripList) as t FROM Company c "
                                     + " where c.validated = 1 AND c.validated = 1 AND c.active = 1 group by c.name order by t desc";
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
    public List<Company> getConfirmAndActiveCompanies() {
        List<Company> validActiveCompanies = new ArrayList<>();
        try {
               validActiveCompanies = (List) getEntityManager().createQuery(HQL_CLIENT_COMPANY)
                .setParameter("validated", true).setParameter("active",true).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validActiveCompanies;
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
            } else if (company.getActive() == false) {
                throw new DeletedAccountException();

            }
        }

        return company;

    }
    
     //_____________________________count all and active Companies_________________
    public int[] getCompanyCount(){
    
         int [] o = new int [2] ;
     try{
      Query q = getEntityManager().createQuery(HQL_Company_Count);
         o[0] =  (int) (long) q.getSingleResult();
         
       q = getEntityManager().createQuery(HQL_VALIDATED_Company_Count);
         o[1] =  (int) (long) q.getSingleResult();
        
                         
         
      }catch(Exception e){
          e.printStackTrace();
      }
        // [0] is all , [1] is the active
       return o;
}
    //_____________________________count all and active Companies_________________
   
    
    //__________________________get companies name and number of trips_____________
    
    
    public List<Object> getCompanyTripsCount(){
        
        List<Object> comp = new ArrayList<>();
           
      try{
      Query q = getEntityManager().createQuery(HQL_COMPANY_TRIPS_COUNT);
      
         comp = q.getResultList();
         
      }catch(Exception e){
          e.printStackTrace();
      }
          
        return comp;
    }
       
    
    //__________________________get companies name and number of trips_____________
}
