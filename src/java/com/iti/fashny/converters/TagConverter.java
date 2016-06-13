/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.converters;

import com.iti.fashny.entities.Tag;
import com.iti.fashny.managedbeans.TagManagedBeen;
import java.util.List;
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
        System.out.println("=========== AS obj============");
        if (value != null && value.trim().length() > 0) {
            try {
                TagManagedBeen tagManagedBeen = (TagManagedBeen) fc.getViewRoot().getViewMap().get("tagBean");
                System.out.println("((((****====== AS obj=====********))))");
                List<Tag> tags = tagManagedBeen.getItems();
        
                for (Tag tag : tags) {
                    System.out.println("******** "+tag.getName()+" -_-");
                    if (tag.getId() == Integer.parseInt(value)) {
                        return tag;
                    }
                }

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        System.out.println("________________as string________________");
        if (object != null) {
            return String.valueOf(((Tag) object).getId());
        } else {
            return null;
        }
    }
}
