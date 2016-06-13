/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.converters;

import com.iti.fashny.businessbeans.TagBusiness;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.managedbeans.TagManagedBeen;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (value != null && value.trim().length() > 0) {
            try {
                TagManagedBeen tagManagedBeen = (TagManagedBeen) fc.getViewRoot().getViewMap().get("tagBean");
                List<Tag> tags = new TagBusiness().view();
                for (Tag tag : tags) {
                    if (tag.getId() == Integer.parseInt(value)) {
                        return tag;
                    }
                }

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            } catch (Exception ex) {
                Logger.getLogger(TagConverter.class.getName()).log(Level.SEVERE, null, ex);
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
