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

/**
 *
 * @author Administrator
 */
@ManagedBean(name = "test")
@RequestScoped
public class TestPlaceBean {

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

}
