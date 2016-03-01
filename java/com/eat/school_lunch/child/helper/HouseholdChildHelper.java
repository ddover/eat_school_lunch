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
import com.eat.school_lunch.model.HouseholdChild;
import java.io.Serializable;

/**
 *
 * @author DoverD
 */
public class HouseholdChildHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param householdChildId
     * @return the household child based on the id
     */
    public static HouseholdChild getHouseholdChildById(BaseDBSessionBean dbUser, Long householdChildId) {
        dbUser.open();
        return HouseholdChild.findById(householdChildId);
    }
    
    /**
     * saves the household child
     * @param dbUser
     * @param child 
     */
    public static void saveHouseholdChild(BaseDBSessionBean dbUser, HouseholdChild child) {
        dbUser.open();
        child.saveIt();
    }
    
    /**
     * delete the household child
     * @param dbUser
     * @param child 
     */
    public static void deleteHouseholdChild(BaseDBSessionBean dbUser, HouseholdChild child) {
        dbUser.open();
        child.delete();
    }
}
