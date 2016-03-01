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
package com.eat.school_lunch.adult.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.FormHouseholdAdult;
import com.eat.school_lunch.model.HouseholdAdult;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DoverD
 */
public class FormHouseholdAdultHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param id
     * @return the form house hold adult by id
     */
    public static FormHouseholdAdult getFormHouseholdAdultById(BaseDBSessionBean dbUser, Long id) {
        dbUser.open();
        return FormHouseholdAdult.findById(id);
    }
    
    /**
     * 
     * @param dbUser
     * @param adultId
     * @return the form house hold adult based on the parameters
     */
    public static FormHouseholdAdult getFormHouseholdAdultByAdultId(BaseDBSessionBean dbUser, Long adultId) {
        dbUser.open();
        return FormHouseholdAdult.findFirst("household_adult_id=?", adultId);
    }
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @return all the form house hold adults based on the form id
     */
    public static List<FormHouseholdAdult> getFormHouseholdAdultsByFormId(BaseDBSessionBean dbUser, Long formId) {
        dbUser.open();
        return FormHouseholdAdult.where("form_id=?", formId);
    }
    
    /**
     * saves the form household adult info
     * @param dbUser
     * @param adult 
     */
    public static void saveFormHouseholdAdult(BaseDBSessionBean dbUser, FormHouseholdAdult adult) {
        dbUser.open();
        adult.saveIt();
    }
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @param householdAdultId
     * @return the form household adult based on the form id and adult id
     */
    public static FormHouseholdAdult getFormHouseholdAdultByFormIdAdultId(BaseDBSessionBean dbUser, Long formId, Long householdAdultId) {
        dbUser.open();
        return FormHouseholdAdult.findFirst("form_id=? and household_adult_id=?", formId, householdAdultId);
    }
    
    /**
     * deletes the form household adult
     * @param dbUser
     * @param fha 
     */
    public static void deleteFormHouseholdAdult(BaseDBSessionBean dbUser, FormHouseholdAdult fha) {
        dbUser.open();
        fha.delete();
    }
}
