/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.managedbeans;

import com.iti.fashny.entities.Tag;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Amira Anis
 */
@FacesConverter("tagConverter")
public class TagConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Tag tag = new Tag(value);
        return tag;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object tagObject) {
        if (tagObject == null) {
            return "";
        }

        if (tagObject instanceof Tag) {
            // return String.valueOf(((Tag) o).getName());
            return tagObject.toString();
        }
        else 
        return ""; 
    }
}
