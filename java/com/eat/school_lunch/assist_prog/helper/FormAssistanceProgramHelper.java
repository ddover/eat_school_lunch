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
package com.eat.school_lunch.assist_prog.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.AssistanceProgram;
import com.eat.school_lunch.model.FormAssistanceProgram;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DoverD
 */
public class FormAssistanceProgramHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @return the list of form assistance programs
     */
    public static List<FormAssistanceProgram> getFormAssistanceProgramByFormId(BaseDBSessionBean dbUser, Long formId) {
        dbUser.open();
        return FormAssistanceProgram.where("form_id = ?", formId);
    }
    
    /**
     * saves the form assistance program
     * @param dbUser
     * @param fap 
     */
    public static void saveFormAssistanceProgram(BaseDBSessionBean dbUser, FormAssistanceProgram fap) {
        dbUser.open();
        fap.saveIt();
    }
    
    /**
     * removes all formassistanceprograms based on the form id passed
     * @param dbUser
     * @param formId 
     */
    public static void removeAllFormAssistanceProgramsByFormId(BaseDBSessionBean dbUser, Long formId) {
        dbUser.open();
        FormAssistanceProgram.delete("form_id = ?", formId);
    }
    
    /**
     * deletes the FormAssistanceProgram based on the id passed
     * @param dbUser
     * @param id 
     */
    public static void removeFormAssistanceProgramById(BaseDBSessionBean dbUser, Long id) {
        dbUser.open();
        FormAssistanceProgram.delete("id = ?", id);
    }
    
    /**
     * deletes the FormAssistanceProgram
     * @param dbUser
     * @param fap 
     */
    public static void removeFormAssistanceProgram(BaseDBSessionBean dbUser, FormAssistanceProgram fap) {
        dbUser.open();
        fap.delete();
    }
    
    /**
     * 
     * @param dbUser
     * @param formId
     * @param assistanceProgramId
     * @return the FormAssistanceProgram based on the form id and assistance program id
     */
    public static FormAssistanceProgram getFormAssistanceProgramByFormIdAssisProgId(BaseDBSessionBean dbUser, Long formId, Long assistanceProgramId) {
        dbUser.open();
        return FormAssistanceProgram.findFirst("form_id = ? and assistance_program_id = ?", formId, assistanceProgramId);
    }
}