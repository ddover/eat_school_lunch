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
package com.eat.school_lunch.securityquestion.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.SecurityQuestionAnswer;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DoverD
 */
public class SecurityQuestionAnswerHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param userId
     * @param questionId
     * @return the security question answer object
     */
    public static SecurityQuestionAnswer getSecurityQuestionAnswerByUserIdQuestionId(BaseDBSessionBean dbUser, Long userId, Long questionId) {
        
        dbUser.open();
        return SecurityQuestionAnswer.findFirst("user_id=? and security_question_id=?", userId, questionId);
    }
    
    /**
     * saves the security question answer object
     * @param dbUser
     * @param sqa 
     */
    public static void saveSecurityQuestionAnswer(BaseDBSessionBean dbUser, SecurityQuestionAnswer sqa) {
        dbUser.open();
        sqa.saveIt();
    }
    
    /**
     * 
     * @param dbUser
     * @param userId
     * @return the security question answer based on the user id passed
     */
    public static List<SecurityQuestionAnswer> getSecurityQuestionAnswersByUserId(BaseDBSessionBean dbUser, Long userId) {
        dbUser.open();
        return SecurityQuestionAnswer.where("user_id=?", userId);
    }
}
