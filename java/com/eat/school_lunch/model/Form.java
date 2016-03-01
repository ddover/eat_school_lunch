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
import com.eat.school_lunch.user.helper.UserHelper;
import java.io.Serializable;
import java.sql.Timestamp;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "FORMS")
public class Form extends Model implements Serializable {
    
    /**
     * sets the user id
     * @param userId 
     */
    public void setUserId(Long userId) {
        setLong("user_id", userId);
    }
    
    /**
     * 
     * @return the user id
     */
    public Long getUserId() {
        return getLong("user_id");
    }
    
    /**
     * sets the last step saved in the form being filled out by the user
     * @param lastStepSaved 
     */
    public void setLastStepSaved(Long lastStepSaved) {
        setLong("last_step_saved", lastStepSaved);
    }
    
    /**
     * 
     * @return last step saved in the form being filled out by the user
     */
    public Long getLastStepSaved() {
        return getLong("last_step_saved");
    }
    
    /**
     * sets the date the user started filling out the form
     * @param dateStarted 
     */
    public void setDateStarted(Timestamp dateStarted) {
        setTimestamp("date_started", dateStarted);
    }
    
    /**
     * 
     * @return the date the user started filling out the form
     */
    public Timestamp getDateStarted() {
        return getTimestamp("date_started");
    }
    
    /**
     * sets the date the user completed the form
     * @param dateCompleted 
     */
    public void setDateCompleted(Timestamp dateCompleted) {
        setTimestamp("date_completed", dateCompleted);
    }
    
    /**
     * 
     * @return the date the user completed the form
     */
    public Timestamp getDateCompleted() {
        return getTimestamp("date_completed");
    }
    
    /**
     * sets whether the user viewed the non-discrimination statement
     * @param nonDiscStmt 
     */
    public void setNonDiscStmt(Boolean nonDiscStmt) {
        setBoolean("non_disc_stmt", nonDiscStmt);
    }
    
    /**
     * 
     * @return whether the user viewed the non-discrimination statement
     */
    public Boolean getNonDiscStmt() {
        return getBoolean("non_disc_stmt");
    }
    
    /**
     * sets whether the user viewed the use of information statement 
     * @param infoUseStmt 
     */
    public void setInfoUseStmt(Boolean infoUseStmt) {
        setBoolean("info_use_stmt", infoUseStmt);
    }
    
    /**
     * 
     * @return whether the user viewed the use of information statement
     */
    public Boolean getInfoUseStmt() {
        return getBoolean("info_use_stmt");
    }
    
    /**
     * sets whether the user viewed the attesting statement
     * @param attestingStmt 
     */
    public void setAttestingStmt(Boolean attestingStmt) {
        setBoolean("attesting_stmt", attestingStmt);
    }
    
    /**
     * 
     * @return whether the user viewed the attesting statement
     */
    public Boolean getAttestingStmt() {
        return getBoolean("attesting_stmt");
    }
    
    /**
     * sets whether the user viewed the ethnicity statement
     * @param ethnicityStmt 
     */
    public void setEthnicityStmt(Boolean ethnicityStmt) {
        setBoolean("ethnicity_stmt", ethnicityStmt);
    }
    
    /**
     * 
     * @return whether the user viewed the ethnicity statement
     */
    public Boolean getEthnicityStmt() {
        return getBoolean("ethnicity_stmt");
    }
    
    /**
     * sets the signature for the form
     * @param signature 
     */
    public void setSignature(String signature) {
        setString("signature", signature);
    }
    
    /**
     * 
     * @return the signature for the form
     */
    public String getSignature() {
        return getString("signature");
    }
    
    /**
     * 
     * @return the user object
     */
    public User getUser() {
        User user;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            user = UserHelper.getUserById(dbUser, getUserId());
        }
        
        return user;
    }
}
