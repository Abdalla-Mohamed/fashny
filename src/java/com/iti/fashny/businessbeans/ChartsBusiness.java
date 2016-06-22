/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.businessbeans;

import com.iti.fashny.assets.PartnerType;
import com.iti.fashny.daos.ClientFacade;
import com.iti.fashny.daos.CompanyFacade;
import com.iti.fashny.daos.DaoFactory;
import com.iti.fashny.daos.PartnTypeFacade;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Hosam
 */
public class ChartsBusiness {
    
    DaoFactory daoFactory = new DaoFactory();
    
    public int[] getClientsCount(){
        
        ClientFacade clientFacade = daoFactory.getClientDoa();
        
        int[] x =clientFacade.getClientsCount();

        // [0] is all , [1] is the active
        return x;
    }
    
    public int[] getCompaniesCount(){
        
        CompanyFacade companyFacade = daoFactory.getCompanyDoa();
        
        int[] x =companyFacade.getCompanyCount();
 
        // [0] is all , [1] is the active
        return x;
    }
    
    
    public int[] getHotelsCount(){
        
        PartnTypeFacade partnerType = daoFactory.getPartnTypeDoa();
        
        int[] x =partnerType.getHotelsCount();
 
        // [0] is all , [1] is the active
        return x;
    }
    
    
    public int[] getRestaurantssCount(){
        
        PartnTypeFacade partnerType = daoFactory.getPartnTypeDoa();
        
        int[] x =partnerType.getRestaurantsCount();
       
        // [0] is all , [1] is the active
        return x;
    }
    
    
    public int[] getGenderCount(){
        
        ClientFacade clientFacade = daoFactory.getClientDoa();
        
        int[] x =clientFacade.getGenderCount();
       
        // [0] is males , [1] is the females
        return x;
    }
    
    public List<Object> getCompanyTripCount(){
        
       CompanyFacade companyFacade = daoFactory.getCompanyDoa();
       
        return companyFacade.getCompanyTripsCount();
    }
    
    
    
    public static void main(String[] args) {
        ChartsBusiness business = new ChartsBusiness();
        
//        for(int x= 0; x< business.getClientsCount().length ; x++){
        
//            System.out.println(business.getClientsCount()[0]);
//            System.out.println(business.getClientsCount()[1]);
//            System.out.println(business.getCompaniesCount()[1]);
//            System.out.println(business.getCompaniesCount()[0]);
//            System.out.println(business.getHotelsCount()[0]);
//            System.out.println(business.getHotelsCount()[1]);
           
            Iterator it = business.getCompanyTripCount().iterator();
         while(it.hasNext()){
             Object[] o = (Object[]) it.next();
             System.out.println(o[0]);
             System.out.println(o[1]);
         } 
            
//    }
    }
        
    
}
