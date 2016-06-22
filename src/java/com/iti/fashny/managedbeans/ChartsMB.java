/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.businessbeans.ChartsBusiness;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Hosam
 */
@ManagedBean
@RequestScoped
public class ChartsMB {

    private BarChartModel usersModel;
    private PieChartModel genderModel;
    private BarChartModel topCompanies;
    private ChartsBusiness chartsBusiness;

    @PostConstruct
    public void init() {
        chartsBusiness = new ChartsBusiness();
        createBarModels();
        createPieModels();

    }

    public BarChartModel getUsersModel() {
        return usersModel;
    }

    public PieChartModel getGenderModel() {
        return genderModel;
    }

    public BarChartModel getTopCompanies() {
        return topCompanies;
    }

    
    
    private void createBarModels() {
        createBarModel();
        createAnimatedModel();
    }

    private void createBarModel() {
        usersModel = initBarModel();

        usersModel.setTitle("Site users");
        usersModel.setLegendPosition("ne");

        Axis xAxis = usersModel.getAxis(AxisType.X);
        xAxis.setLabel("Users");

        Axis yAxis = usersModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number");
        yAxis.setMin(0);
    }

    private void createAnimatedModel() {

        topCompanies = initAnimBarModel();
        topCompanies.setTitle("Top Companies");
        topCompanies.setAnimate(true);
        topCompanies.setLegendPosition("ne");
        Axis yAxis = topCompanies.getAxis(AxisType.Y);
        yAxis = topCompanies.getAxis(AxisType.Y);
        yAxis.setMin(0);

    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries all = new ChartSeries();
        all.setLabel("All");
        all.set("Clients", chartsBusiness.getClientsCount()[0]);
        all.set("Companies", chartsBusiness.getCompaniesCount()[0]);
        all.set("Hotels", chartsBusiness.getHotelsCount()[0]);
        all.set("Restaurants", chartsBusiness.getRestaurantssCount()[0]);

        ChartSeries active = new ChartSeries();
        active.setLabel("Active");
        active.set("Clients", chartsBusiness.getClientsCount()[1]);
        active.set("Companies", chartsBusiness.getCompaniesCount()[1]);
        active.set("Hotels", chartsBusiness.getHotelsCount()[1]);
        active.set("Restaurants", chartsBusiness.getRestaurantssCount()[1]);

        model.addSeries(all);
        model.addSeries(active);

        return model;
    }

    private BarChartModel initAnimBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries companies = new ChartSeries();
        companies.setLabel("Company");

        Iterator it = chartsBusiness.getCompanyTripCount().iterator();

            int x = 0;
        
        while (it.hasNext() && x<5) {
            Object[] o = (Object[]) it.next();

            companies.set(o[0], (Number) o[1]);
            x++;
        }
            model.addSeries(companies);
            
             
        return model;
    }

    private void createPieModels() {
        createGenderModel();
    }

    private void createGenderModel() {
        genderModel = new PieChartModel();

        genderModel.set("Male", chartsBusiness.getGenderCount()[0]);
        genderModel.set("Female", chartsBusiness.getGenderCount()[1]);

        genderModel.setTitle("Gender");
        genderModel.setLegendPosition("w");
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
