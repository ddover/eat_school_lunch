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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * helper class for getting the assistance programs
 * @author DoverD
 */
public class AssistanceProgramHelper extends BaseHelper implements Serializable {
    
    private static final Logger LOGGER = LogManager.getLogger(AssistanceProgramHelper.class);
    
    /**
     * 
     * @param dbUser
     * @param id
     * @return the assistance program based on the id passed
     */
    public static AssistanceProgram getAssistanceProgramById(BaseDBSessionBean dbUser, Long id) {
        dbUser.open();
        return AssistanceProgram.findById(id);
    }
    
    /**
     * 
     * @param dbUser
     * @return all the active assistance programs
     */
    public static List<AssistanceProgram> getActiveAssistancePrograms(BaseDBSessionBean dbUser) {
        
        dbUser.open();
        return new ArrayList(AssistanceProgram.where("active = ?", true));
    }
}
