/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.PartnerBusiness;
import com.iti.fashny.businessbeans.PartnerTypeBusiness;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.PartnType;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MANAR ADEL
 */

@ManagedBean(name = "PartnerCRUDSBean")
@SessionScoped
public class PartnerCRUDSBean 
{
 
    PartnerBusiness partnerBusiness;
    
    private List<Partener> items = null;
    private Partener selected = new Partener();
    private List<Partener> filteredItems;

    public PartnerBusiness getPartnerBusiness() {
        return partnerBusiness;
    }

    public void setPartnerBusiness(PartnerBusiness partnerBusiness) {
        this.partnerBusiness = partnerBusiness;
    }


    public List<Partener> getItems() {
        return items;
    }

    public void setItems(List<Partener> items) {
        this.items = items;
    }

    public Partener getSelected() {
        return selected;
    }

    public void setSelected(Partener selected) {
        this.selected = selected;
    }

    public List<Partener> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Partener> filteredItems) {
        this.filteredItems = filteredItems;
    }
    
      public void create() {
//          selected = new Partener();
//          selected.setName("dfa");
//          selected.setActive(Boolean.TRUE);
//          selected.setAddress("dfasfdsf");
//          selected.getContactEmail();
//          selected.getDescription();
//          selected.setEmail("adfcsd");
//          selected.setPassword("dfdfaf");
//          selected.setWebsite("dfadfsf");
//          selected.setAddress("dfadf");
//          selected.setType(new PartnType(1));
//          selected.setWorkHours("dfasdf");
//          selected.setValidated(Boolean.TRUE);
//          selected.setMobile1("sfad");
//          selected.setMobile2("fsfda");
//          selected.setPhone("wfa");
//          selected.setActive(Boolean.TRUE);
          
          
          
          
        if (getSelected() != null) {
            try {
                System.out.println(selected.getName());
                new PartnerBusiness().add(selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
