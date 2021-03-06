/* 
 * Copyright (c) 2015, Darryl Dover
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.eat.school_lunch.model;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.child.helper.HouseholdChildHelper;
import com.eat.school_lunch.form.helper.FormHelper;
import com.eat.school_lunch.householdAdult.helper.HouseholdAdultHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "FORM_HOUSEHOLD_ADULTS")
public class FormHouseholdAdult extends Model implements Serializable {
    
    /**
     * sets the id of the form
     * @param formId 
     */
    public void setFormId(Long formId) {
        setLong("form_id", formId);
    }
    
    /**
     * 
     * @return the id of the form
     */
    public Long getFormId() {
        return getLong("form_id");
    }
    
    /**
     * sets the id of the household adult
     * @param householdAdultId 
     */
    public void setHouseholdAdultId(Long householdAdultId) {
        setLong("household_adult_id", householdAdultId);
    }
    
    /**
     * 
     * @return the id of the household adult
     */
    public Long getHouseholdAdultId() {
        return getLong("household_adult_id");
    }
    
    /**
     * 
     * @return the form based on the id of the form
     */
    public Form getForm() {
        Form form;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            form = FormHelper.getFormById(dbUser, getFormId());
        }
        
        return form;
    }
    
    /**
     * 
     * @return the household child based on the id of the child
     */
    public HouseholdAdult getHouseholdAdult() {
        HouseholdAdult adult;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            adult = HouseholdAdultHelper.getHouseholdAdultById(dbUser, getHouseholdAdultId());
        }
        
        return adult;
    }
}
