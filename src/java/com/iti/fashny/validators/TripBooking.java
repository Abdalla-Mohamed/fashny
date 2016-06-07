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
public class TripBooking implements Validator
{
        boolean signOk = true;
        private Pattern pattern;
        private Matcher matcher;
        private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
        public boolean validate(final String hex) 
        {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
        }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        
         if (!validate(o.toString())) 
         {
         signOk = false;
         
        FacesMessage message = new FacesMessage("Email is not valid");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
         
        }

       
         if (!new AdditionalFns().isMailExist(o.toString())) 
         {
        
         
        FacesMessage message = new FacesMessage("Email Already Exist");
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
         
        }
         
         
        
//        if(validation fail)
//        {     
//        }
        
    }

    
}
