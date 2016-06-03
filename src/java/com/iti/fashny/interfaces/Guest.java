package com.iti.fashny.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.entities.Trip;

/**
 *
 * @author MANAR ADEL
 */
public interface Guest {

    void logout() throws Exception;
    public String LOGGINED_PAGE_URL = "/fashny/faces/guestPages/login.xhtml";
    public String DENIED_PAGE_URL = "/fashny/faces/genralPages/accessDenied.xhtml";

    void forgetPassword(String email) throws Exception; //CustomException;

    void confirmForgetPassword(String email, String confirmationCode) throws Exception; //CustomException;

    public void signUp(Client c);

}
