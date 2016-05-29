/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.converters;

import com.iti.fashny.businessbeans.CompanyController;
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
@FacesConverter("converterCompany")
public class ConverterCompany implements Converter {

    public ConverterCompany() {
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                CompanyController companyController = new CompanyController();
                List<Company> companys = companyController.view();
                for (Company company : companys) {
                    if (company.getId() == Integer.parseInt(value)) {
                        return company;
                    }
                }

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            } catch (Exception ex) {
                Logger.getLogger(ConverterCompany.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Company) object).getId());
        } else {
            return null;
        }
    }

}
