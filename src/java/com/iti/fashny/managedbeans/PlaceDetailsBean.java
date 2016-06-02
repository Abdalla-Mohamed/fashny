/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "test")
@RequestScoped
public class PlaceDetailsBean {

    @ManagedProperty(value = "#{placeView_1}")
    private PlaceViewManagedBean_1 placeView;

    @PostConstruct
    public void init() {
        placeView.getSelected();
        System.out.println(placeView.getSelected().getName());
    }

    public void setPlaceView(PlaceViewManagedBean_1 placeView) {
        this.placeView = placeView;
    }

    public PlaceViewManagedBean_1 getPlaceView() {
        return placeView;
    }

    public String getParam() {
        /**
         * Takes the parameter from the flash context
         */
        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().get("param");
    }

}
