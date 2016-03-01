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
import com.eat.school_lunch.child.helper.HouseholdChildHelper;
import com.eat.school_lunch.income.IncomeHowOftenHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "CHILD_INCOME")
public class ChildIncome extends Model implements Serializable {
    
    private String incomeChosenLabel;
    
    /**
     * sets the id of the house hold child
     * @param householdChild 
     */
    public void setHouseholdChildId(Long householdChild) {
        setLong("household_child_id", householdChild);
    }
    
    /**
     * 
     * @return the id of the house hold child
     */
    public Long getHouseholdChildId() {
        return getLong("household_child_id");
    }
    
    /**
     * sets the income of the child
     * @param income 
     */
    public void setIncome(Double income) {
        setDouble("income", income);
    }
    
    /**
     * 
     * @return the income of the child
     */
    public Double getIncome() {
        return getDouble("income");
    }
    
    /**
     * sets the id of the object of how often the children are paid
     * @param incomeHowOftenId 
     */
    public void setIncomeHowOftenId(Long incomeHowOftenId) {
        setLong("income_how_often_id", incomeHowOftenId);
    }
    
    /**
     * 
     * @return the id of the object of how often the children are paid
     */
    public Long getIncomeHowOftenId() {
        return getLong("income_how_often_id");
    }
    
    /**
     * sets the label of the income chosen
     * @param incomeChosenLabel 
     */
    public void setIncomeChosenLabel(String incomeChosenLabel) {
        this.incomeChosenLabel = incomeChosenLabel;
    }
    
    /**
     * 
     * @return the label of the income chosen
     */
    public String getIncomeChosenLabel() {
        return incomeChosenLabel;
    }
    
    /**
     * 
     * @return the household child based on the id
     */
    public HouseholdChild getHouseholdChild() {
        HouseholdChild child;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            child = HouseholdChildHelper.getHouseholdChildById(dbUser, getHouseholdChildId());
        }
        
        return child;
    }
    
    /**
     * 
     * @return the income how often based on the id
     */
    public IncomeHowOften getIncomeHowOften() {
        IncomeHowOften incomeHowOften;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            incomeHowOften = IncomeHowOftenHelper.getIncomeHowOftenById(dbUser, getIncomeHowOftenId());
        }
        
        return incomeHowOften;
    }
    
    public IncomeHowOften getIncomeHowOften(BaseDBSessionBean dbUser) {
        IncomeHowOften incomeHowOften;
        incomeHowOften = IncomeHowOftenHelper.getIncomeHowOftenById(dbUser, getIncomeHowOftenId());
        
        return incomeHowOften;
    }
}
