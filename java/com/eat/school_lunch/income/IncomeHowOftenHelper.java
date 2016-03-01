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
package com.eat.school_lunch.income;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.IncomeHowOften;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DoverD
 */
public class IncomeHowOftenHelper extends BaseHelper implements Serializable {
    
    /**
     * 
     * @param dbUser
     * @param incomeHowOftenId
     * @return the income how often based on the id
     */
    public static IncomeHowOften getIncomeHowOftenById(BaseDBSessionBean dbUser, Long incomeHowOftenId) {
        dbUser.open();
        return IncomeHowOften.findById(incomeHowOftenId);
    }
    
    /**
     * 
     * @param dbUser
     * @return all the options
     */
    public static List<IncomeHowOften> getAll(BaseDBSessionBean dbUser) {
        dbUser.open();
        return IncomeHowOften.findAll();
    }
    
    /**
     * 
     * @param dbUser
     * @param label
     * @return the income how often object based on the label passed
     */
    public static IncomeHowOften getIncomeHowOftenByLabel(BaseDBSessionBean dbUser, String label) {
        dbUser.open();
        return IncomeHowOften.findFirst("label=?", label);
    }
}
