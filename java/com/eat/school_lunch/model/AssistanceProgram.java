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

import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 * Creates a class for the Assistance program
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "ASSISTANCE_PROGRAMS")
public class AssistanceProgram extends Model implements Serializable {
    
    private String caseNumber;
    private Boolean beingUsed;
    
    /**
     * sets the abbreviation of the program
     * @param programAbbr 
     */
    public void setProgramAbbr(String programAbbr) {
        setString("program_abbr", programAbbr);
    }
    
    /**
     * 
     * @return the abbreviation of the program
     */
    public String getProgramAbbr() {
        return getString("program_abbr");
    }
    
    /**
     * sets the name of the program
     * @param programName 
     */
    public void setProgramName(String programName) {
        setString("program_name", programName);
    }
    
    /**
     * 
     * @return the name of the program
     */
    public String getProgramName() {
        return getString("program_name");
    }
    
    /**
     * sets the description of the program
     * @param programDescription 
     */
    public void setProgramDescription(String programDescription) {
        setString("program_description", programDescription);
    }
    
    /**
     * 
     * @return the description of the program
     */
    public String getProgramDescription() {
        return getString("program_description");
    }
    
    /**
     * sets the website url of the program
     * @param websiteUrl
     */
    public void setWebsiteUrl(String websiteUrl) {
        setString("website_url", websiteUrl);
    }
    
    /**
     * 
     * @return the website url of the program
     */
    public String getWebsiteUrl() {
        return getString("website_url");
    }
    
    /**
     * sets whether the assistance program is active
     * @param active 
     */
    public void setActive(Boolean active) {
        setBoolean("active", active);
    }
    
    /**
     * 
     * @return whether the assistance program is active
     */
    public Boolean getActive() {
        return getBoolean("active");
    }
    
    /**
     * sets the case number, temporary value
     * @param caseNumber 
     */
    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }
    
    /**
     * 
     * @return the case number, temporary value
     */
    public String getCaseNumber() {
        return caseNumber;
    }
    
    /**
     * sets whether the program is being used, temporary value
     * @param beingUsed 
     */
    public void setBeingUsed(Boolean beingUsed) {
        this.beingUsed = beingUsed;
    }
    
    /**
     * 
     * @return whether the program is being used, temporary value
     */
    public Boolean getBeingUsed() {
        return beingUsed;
    }
}
