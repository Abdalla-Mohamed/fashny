/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.converters;

import com.iti.fashny.businessbeans.CompanyController;
import com.iti.fashny.businessbeans.TagsController;
import com.iti.fashny.entities.Company;
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
@FacesConverter("converterTags")
public class ConverterTags implements Converter {

    public ConverterTags() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                TagsController tagsController = new TagsController();
                List<Tag> tags = tagsController.view();
                for (Tag tag : tags) {
                    if (tag.getId() == Integer.parseInt(value)) {
                        return tag;
                    }
                }

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            } catch (Exception ex) {
                Logger.getLogger(ConverterTags.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Tag) object).getId());
        } else {
            return null;
        }
    }

}
