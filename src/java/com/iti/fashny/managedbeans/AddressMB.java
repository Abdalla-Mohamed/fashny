/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Bakar M.M.R
 */
@ManagedBean
@SessionScoped
public class AddressMB implements Serializable {

    private Map<String, Map<String, String>> egypt = new HashMap<String, Map<String, String>>();
    private String governorate;
    private String city;
    private Map<String, String> governorates;
    private Map<String, String> cities;
    private String address;
    public AddressMB() {

        governorates = new HashMap<String, String>();
        governorates.put("Alexandria", "Alexandria");
        governorates.put("Aswan", "Aswan");
        governorates.put("Cairo", "Cairo");
        governorates.put("Giza", "Giza");
        governorates.put("Luxor", "Luxor");
        governorates.put("Matruh", "Matruh");
        governorates.put("Red Sea", "Red Sea");
        governorates.put("South Sinai", "South Sinai");

        Map<String, String> map = new HashMap<String, String>();
        map.put("Aswan", "Aswan");
        map.put("Abo-sembel", "Abo-sembel");
        map.put("new Aswan", "new Aswan");
        map.put("Daraw", "Daraw");
        map.put("Kom-ombo", "Kom-ombo");
        map.put("Nasr-Elnoba", "Nasr-Elnoba");
        map.put("Kalbsha", "Kalbsha");
        map.put("Edfo", "Edfo");
        map.put("El-radesya", "El-radesya");
        map.put("El-baslya", "El-baslya");
        map.put("El-seba3ya", "El-seba3ya");
        egypt.put("Aswan", map);

        map = new HashMap<String, String>();
        map.put("Zayton", "Zayton");
        map.put("Elzawya", "Elzawya");
        map.put("El-salam", "El-salam");
        map.put("El-marg", "El-marg");
        map.put("Matarya", "Matarya");
        map.put("3ain-shams", "3ain-shams");
        map.put("Nozha", "Nozha");
        map.put("Heliopolis", "Heliopolis");
        map.put("Nasr city", "Nasr city");
        map.put("El-waily", "El-waily");
        map.put("Sharabya", "Sharabya");
        map.put("El-sa7el", "El-sa7el");
        map.put("shobra", "shobra");
        map.put("Naser", "Naser");
        map.put("DownTown", "DownTown");
        map.put("Bab-El-sha3rya", "Bab-El-sha3rya");
        map.put("Azbakia", "Azbakia");
        map.put("Bolak", "Bolak");
        map.put("Moskee", "Moskee");
        map.put("3abedeen", "3abedeen");
        map.put("West-cairo", "West-cairo");
        map.put("EL-mokatam", "EL-mokatam");
        map.put("Elkhalefa", "Elkhalefa");
        map.put("Sayda-Zainab", "Sayda-Zainab");
        map.put("masr-elkadema", "masr-elkadema");
        map.put("Dar-elsalam", "Dar-elsalam");
        map.put("El -basateen", "El -basateen");
        map.put("Ma3adi", "Ma3adi");
        map.put("Tora", "Tora");
        map.put("ma3sara", "ma3sara");
        map.put("15th-may", "15th-may");
        map.put("Helwan", "Helwan");
        map.put("tebeen", "tebeen");
        map.put("new cairo", "new cairo");
        map.put("Badr", "Badr");
        map.put("El-Shorouk", "El-Shorouk");
        map.put("Rod el farag", "Rod el farag");
        map.put("El-amerya", "El-amerya");
        map.put("Hadayek elkoba", "Hadayek elkoba");
        egypt.put("Cairo", map);

        map = new HashMap<String, String>();
        map.put("north-Giza", "north-Giza");
        map.put("El-warak", "El-warak");
        map.put("Bolak-eldakror", "Bolak-eldakror");
        map.put("Doki", "Doki");
        map.put("3agoza", "3agoza");
        map.put("3omranya", "3omranya");
        map.put("El-haram", "El-haram");
        map.put("South-Giza", "South-Giza");
        map.put("6th-october", "6th-october");
        map.put("Elsheikh-zayed", "Elsheikh-zayed");
        map.put("El-hawamdya", "El-hawamdya");
        map.put("Badrasheen", "Badrasheen");
        map.put("El-saf", "El-saf");
        map.put("Atfeeh", "Atfeeh");
        map.put("El-3ayat", "El-3ayat");
        map.put("El-bawety", "El-bawety");
        map.put("Manshat-elkanater", "Manshat-elkanater");
        map.put("Ausim", "Ausim");
        map.put("kerdasa", "kerdasa");
        map.put("Abulnomros", "Abulnomros");
        map.put("Kafr-3'ataty", "Kafr-3'ataty");
        egypt.put("Giza", map);

        map = new HashMap<String, String>();
        map.put("El-montazah", "El-montazah");
        map.put("El-3agamy", "El-3agamy");
        map.put("El-3amerya", "El-3amerya");
        map.put("East-Alex", "East-Alex");
        map.put("Elgomrok", "Elgomrok");
        map.put("West ALex", "West ALex");
        map.put("Borg El-3arab", "Borg El-3arab");
        egypt.put("Alexandria", map);

        map = new HashMap<String, String>();
        map.put("Marsa-Matrouh", "Marsa-Matrouh");
        map.put("Elahama", "Elahama");
        map.put("El-3alameen", "El-3alameen");
        map.put("El-dab3a", "El-dab3a");
        map.put("El-negeela", "El-negeela");
        map.put("sedi-barani", "sedi-barani");
        map.put("El-saloom", "El-saloom");
        map.put("Sewa", "Sewa");
        egypt.put("Matruh", map);

        map = new HashMap<String, String>();
        map.put("El-tor", "El-tor");
        map.put("Sharm-el-sheikh", "Sharm-el-sheikh");
        map.put("Dahab", "Dahab");
        map.put("Nuweiba", "Nuweiba");
        map.put("Taba", "Taba");
        map.put("Saint Catherine", "Saint Catherine");
        map.put("Ras-sedr", "Ras-sedr");
        map.put("Abo-redes", "Abo-redes");
        map.put("Abo-zenema", "Abo-zenema");
        egypt.put("South Sinai", map);

        map = new HashMap<String, String>();
        map.put("Hurgada", "Hurgada");
        map.put("Ras-Ghareb", "Ras-Ghareb");
        map.put("Sagafaga", "Sagafaga");
        map.put("Elkoseer", "Elkoseer");
        map.put("Marsa-3alam", "Marsa-3alam");
        map.put("Shalateen", "Shalateen");
        map.put("Halayeb", "Halayeb");
        egypt.put("Red Sea", map);

        map = new HashMap<String, String>();
        map.put("Luxor", "Luxor");
        map.put("new Luxor", "new Luxor");
        map.put("new Theba", "new Theba");
        map.put("Elzaynea", "Elzaynea");
        map.put("El-bayadya", "El-bayadya");
        map.put("El-qarna", "El-qarna");
        map.put("Armant", "Armant");
        map.put("Eltawd", "Eltawd");
        map.put("Esna", "Esna");
        egypt.put("Luxor", map);

    }

    public Map<String, String> getGovernorates() {
        return governorates;
    }

    public Map<String, Map<String, String>> getEgypt() {
        return egypt;
    }

    public void setEgypt(Map<String, Map<String, String>> egypt) {
        egypt = egypt;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
       this.governorate = governorate;
        
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }

    public void onCountryChange() {
        if (governorate != null && !governorate.equals("")) {
            cities = egypt.get(governorate);
        } else {
            cities = new HashMap<String, String>();
        }
    }

    public void displayLocation() {
        FacesMessage msg;
        if (city != null && governorate != null && city != "" && governorate != "") {
            msg = new FacesMessage("Selected", city + " of " + governorate);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "choose governerate and city please");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getAddress() {
      
        if (city != null && governorate != null && city != "" && governorate != "") {
            address = city + "," + governorate;
        } 
        else if(governorate != null && governorate != ""){
            address = governorate;
        }
        else {
            address = "";
        }
        return address;
    }

    public String getCityFromAddress(String address) {
        String[] split = address.split(",");
        return split[1];
    }

    public String getGovernerateFromAddress(String address) {
        String[] split = address.split(",");
        return split[0];
    }

}
