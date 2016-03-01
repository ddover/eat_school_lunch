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
package com.eat.school_lunch.child.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.FormHouseholdChild;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DoverD
 */
public class FormHouseholdChildHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param id
     * @return the formhouseholdchild object based on the id passed
     */
    public static FormHouseholdChild getFormHouseholdChildById(BaseDBSessionBean dbUser, Long id) {
        dbUser.open();
        return FormHouseholdChild.findById(id);
    }
    
    /**
     * 
     * @param dbUser
     * @param childId
     * @return the formhouseholdchild based on the child id passed
     */
    public static FormHouseholdChild getFormHouseholdChildByChildId(BaseDBSessionBean dbUser, Long childId) {
        dbUser.open();
        return FormHouseholdChild.findFirst("household_child_id = ?", childId);
    }
    
    /**
     * saves the form house hold child
     * @param dbUser
     * @param fhc 
     */
    public static void saveFormHouseHoldChild(BaseDBSessionBean dbUser, FormHouseholdChild fhc) {
        dbUser.open();
        
        fhc.saveIt();
    }
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @return the list of children part of the form based on the form id passed
     */
    public static List<FormHouseholdChild> getFormHouseholdChildrenByFormId(BaseDBSessionBean dbUser, Long formId) {
        dbUser.open();
        
        return FormHouseholdChild.where("form_id = ?", formId);
    }
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @param childId
     * @return the form_household_child based on the parameters passed 
     */
    public static FormHouseholdChild getFormHouseholdChildByFormIdChildId(BaseDBSessionBean dbUser, Long formId, Long childId) {
        dbUser.open();
        return FormHouseholdChild.findFirst("form_id=? and household_child_id=?", formId, childId);
    }
    
    /**
     * deletes the passed in form house hold child
     * @param dbUser
     * @param fhc 
     */
    public static void deleteFormHouseholdChild(BaseDBSessionBean dbUser, FormHouseholdChild fhc) {
        dbUser.open();
        
        fhc.delete();
    }
    
    /**
     * remove the form household children list
     * @param dbUser
     * @param children 
     */
    public static void deleteFormHouseholdChildren(BaseDBSessionBean dbUser, List<FormHouseholdChild> children) {
        dbUser.open();
        
        for(FormHouseholdChild child:children) {
            child.delete();
        }
    }
    
    /**
     * deletes all form household children based on the form id
     * @param dbUser
     * @param formId 
     */
    public static void deleteAllFormHouseholdChildrenByFormId(BaseDBSessionBean dbUser, Long formId) {
        
        dbUser.open();
        FormHouseholdChild.delete("form_id = ?", formId);
    }
}
