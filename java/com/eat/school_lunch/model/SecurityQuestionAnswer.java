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
import com.eat.school_lunch.securityquestion.helper.SecurityQuestionHelper;
import com.eat.school_lunch.user.helper.UserHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
@Table(value = "SECURITY_QUESTIONS_ANSWERS")
public class SecurityQuestionAnswer extends Model implements Serializable {
    
    /**
     * sets the id of the user
     * @param userId 
     */
    public void setUserId(Long userId) {
        setLong("user_id", userId);
    }
    
    /**
     * 
     * @return the id of the user
     */
    public Long getUserId() {
        return getLong("user_id");
    }
    
    /**
     * sets the id of the security question
     * @param securityQuestionId 
     */
    public void setSecurityQuestionId(Long securityQuestionId) {
        setLong("security_question_id", securityQuestionId);
    }
    
    /**
     * 
     * @return the id of the security question
     */
    public Long getSecurityQuestionId() {
        return getLong("security_question_id");
    }
    
    /**
     * sets the answer
     * @param answer 
     */
    public void setAnswer(String answer) {
        setString("answer", answer);
    }
    
    /**
     * 
     * @return the answer
     */
    public String getAnswer() {
        return getString("answer");
    }
    
    /**
     * create a new security question answer object based on the parameters passed
     * @param userId
     * @param questionId
     * @param answer 
     */
    public SecurityQuestionAnswer(Long userId, Long questionId, String answer) {
        setUserId(userId);
        setSecurityQuestionId(questionId);
        setAnswer(answer);
    }
    
    public SecurityQuestionAnswer() {}
    
    /**
     * 
     * @return the user
     */
    public User getUser() {
        User user;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            user = UserHelper.getUserById(dbUser, getUserId());
        }
        
        return user;
    }
    
    /**
     * 
     * @return the security question
     */
    public SecurityQuestion getSecurityQuestion() {
        SecurityQuestion question;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            question = SecurityQuestionHelper.getSecurityQuestionById(dbUser, getSecurityQuestionId());
        }
        
        return question;
    }
}
