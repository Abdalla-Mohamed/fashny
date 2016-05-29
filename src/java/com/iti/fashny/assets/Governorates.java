/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdalla
 */
public enum Governorates {
Alexandria("Alexandria" ) 
,Aswan ("Aswan")
,Asyut("Asyut") 
,Beheira("Beheira")
,Beni_Suef("Beni Suef")
,Cairo("Cairo")
,Dakahlia("Dakahlia")
,Damietta("Damietta")
,Faiyum("Faiyum")
,Gharbia("Gharbia")
,Giza("Giza")
,Ismailia("Ismailia")
,Kafr_elSheikh("Kafr elSheikh")
,Luxor("Luxor")
,Matruh("Matruh")
,Minya("Minya")
,Monufia("Monufia")
,New_Valley("New Valley")
,North_Sinai("North Sinai")
,Port_Said("Port Said")
,Qalyubia("Qalyubia")
,Qena("Qena")
,Red_Sea("Red Sea")
,Sharqia("Sharqia")
,Sohag("Sohag")
,South_Sinai("South Sinai")
,Suez("Suez");

private final String govName;
private final String[] cities;

    private Governorates(String govName, String[] cities) {
        this.govName = govName;
        
        this.cities =cities ;
    }
    private Governorates(String govName) {
        this.govName = govName;
        this.cities = new String[0];
    }


    public String getGovName() {
        return govName;
    }

 
}
