/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.validators;

import com.iti.fashny.businessbeans.AdditionalFns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author MANAR ADEL
 */
@FacesValidator("com.iti.fashny.validators.TripBookingValidator")
public class TripBookingValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent component, Object o) throws ValidatorException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int bookingNumber = (int) component.getAttributes().get("bookNum");
        int maxBookingNumber = (int) component.getAttributes().get("maxBookNum");

        if (bookingNumber > maxBookingNumber) {
            FacesMessage message = new FacesMessage("Count Booking can not be greater than Max Booking");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);

        }
    }

}
