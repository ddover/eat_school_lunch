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

import com.eat.school_lunch.assist_prog.helper.AssistanceProgramHelper;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.form.helper.FormHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "FORM_ASSISTANCE_PROGRAM")
public class FormAssistanceProgram extends Model implements Serializable {
    
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
     * sets the id of the assistance program
     * @param assistanceProgramId 
     */
    public void setAssistanceProgramId(Long assistanceProgramId) {
        setLong("assistance_program_id", assistanceProgramId);
    }
    
    /**
     * 
     * @return the id of the assistance program
     */
    public Long getAssistanceProgramId() {
        return getLong("assistance_program_id");
    }
    
    /**
     * sets the case number of the program
     * @param caseNumber 
     */
    public void setCaseNumber(String caseNumber) {
        setString("case_number", caseNumber);
    }
    
    /**
     * 
     * @return the case number of the program
     */
    public String getCaseNumber() {
        return getString("case_number");
    }
    
    /**
     * 
     * @return the form based on the form id in this class
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
     * @return the assistance program based on the id
     */
    public AssistanceProgram getAssistanceProgram() {
        AssistanceProgram program;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            program = AssistanceProgramHelper.getAssistanceProgramById(dbUser, getAssistanceProgramId());
        }
        
        return program;
    }
}
