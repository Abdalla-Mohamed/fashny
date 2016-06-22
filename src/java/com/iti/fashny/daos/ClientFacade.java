/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Client;
import com.iti.fashny.exceptions.DeletedAccountException;
import com.iti.fashny.exceptions.Fasa7nyException;
import com.iti.fashny.exceptions.InvalidLoginDataException;
import com.iti.fashny.exceptions.NotConfirmAccountException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Hosam
 */
public class ClientFacade extends AbstractFacade<Client> {

    private static final String GET_CLIENT_TRIPS = "select j.trip  from JoinTrip j where j. = :partenerId";
    private static final String HQL_LOGIN = "FROM Client c WHERE c.email =:email AND c.password =:password ";
    private static final String HQL_Clients_Count = "select count(c) from Client c";
    private static final String HQL_ACTIVE_Clients_Count = "select  count(c) FROM Client c where c.active = 1";
    private static final String HQL_GET_MALES = "select  count(c) FROM Client c where c.gender = 1 and c.active = 1";
    private static final String HQL_GET_FEMALES = "select  count(c) FROM Client c where c.gender = 2 and c.active = 1";
   
    Client client;

    ClientFacade(EntityManager em) {
        super(Client.class, em);
    }

    public boolean validMail(String mail) {

        boolean valid = false;

        List resultList = getEntityManager().createNamedQuery("Client.findByEmail").setParameter("email", mail).getResultList();

        if (resultList.size() >= 1) {
            valid = true;
        }

        return valid;
    }

    public Client login(String email, String pass) throws InvalidLoginDataException, DeletedAccountException {
        Client client;
        System.out.println("=========================");
        System.out.println(email + "," + pass);
        System.out.println("=========================");
        List result = getEntityManager().createQuery(HQL_LOGIN)
                .setParameter("email", email).setParameter("password", pass).getResultList();

        if (result == null || result.isEmpty()) {
            throw new InvalidLoginDataException();
        } else {
            client = (Client) result.get(0);
            if (client.getActive() == false) {
                throw new DeletedAccountException();

            }
        }

        return client;
    }

    //_____________________________count all and active clients_________________
    public int[] getClientsCount(){
    
         int [] o = new int [2] ;
     try{
      Query q = getEntityManager().createQuery(HQL_Clients_Count);
         o[0] =  (int) (long) q.getSingleResult();
         
       q = getEntityManager().createQuery(HQL_ACTIVE_Clients_Count);
         o[1] =  (int) (long) q.getSingleResult();
        
                         
         
      }catch(Exception e){
          e.printStackTrace();
      }
       // [0] is all , [1] is the active
       return o;
}
    
    //_____________________________count all and active clients_________________

    
    //________________________count active males and females____________________
    
    public int[] getGenderCount(){
    
         int [] o = new int [2] ;
     try{
      Query q = getEntityManager().createQuery(HQL_GET_MALES);
         o[0] =  (int) (long) q.getSingleResult();
         
       q = getEntityManager().createQuery(HQL_GET_FEMALES);
         o[1] =  (int) (long) q.getSingleResult();
        
                         
         
      }catch(Exception e){
          e.printStackTrace();
      }
       // [0] is males , [1] is the females
       return o;
}
    
    //________________________count active males and females____________________

}
