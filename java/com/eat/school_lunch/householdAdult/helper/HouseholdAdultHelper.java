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
package com.eat.school_lunch.householdAdult.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.HouseholdAdult;
import java.io.Serializable;

/**
 *
 * @author DoverD
 */
public class HouseholdAdultHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param householdAdultId
     * @return the household adult based on the id
     */
    public static HouseholdAdult getHouseholdAdultById(BaseDBSessionBean dbUser, Long householdAdultId) {
        
        dbUser.open();
        return HouseholdAdult.findById(householdAdultId);
    }
    
    /**
     * saves the household adult information
     * @param dbUser
     * @param adult 
     */
    public static void saveHouseholdAdult(BaseDBSessionBean dbUser, HouseholdAdult adult) {
        dbUser.open();
        adult.saveIt();
    }
    
    /**
     * delete the household adult
     * @param dbUser
     * @param adult 
     */
    public static void deleteHouseholdAdult(BaseDBSessionBean dbUser, HouseholdAdult adult) {
        dbUser.open();
        adult.delete();
    }
}
