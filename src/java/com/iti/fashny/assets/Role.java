/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

/**
 *
 * @author Abdalla
 */
public enum Role {
    Admin,Client,Company,Partner,Guest;

    public  String getAdminName() {
        return Admin.name();
    }

    public  String getClientName() {
        return Client.name();
    }

    public  String getCompanyName() {
        return Company.name();
    }

    public  String getGuestName() {
        return Guest.name();
    }

    public  String getPartnerName() {
        return Partner.name();
    }
    
    
    
}
