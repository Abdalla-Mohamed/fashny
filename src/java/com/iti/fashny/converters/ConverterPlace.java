/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.converters;

import com.iti.fashny.businessbeans.PlaceBusiness;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.managedbeans.SearchMB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Abdalla
 */
@FacesConverter("converterPlace")
public class ConverterPlace implements Converter {

    public ConverterPlace() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                PlaceBusiness placeBusiness = new PlaceBusiness();
                List<Place> places = placeBusiness.view();
                for (Place place : places) {
                    if (place.getId() == Integer.parseInt(value)) {
                        return place;
                    }
                }

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            } catch (Exception ex) {
                Logger.getLogger(ConverterPlace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Place) object).getId());
        } else {
            return null;
        }
    }

}
