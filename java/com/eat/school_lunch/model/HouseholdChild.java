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
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "HOUSEHOLD_CHILDREN")
public class HouseholdChild extends Model implements Serializable {
        
    /**
     * Set the users first name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        setString("first_name", firstName);
    }

    /**
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return getString("first_name");
    }

    /**
     * sets the last name of the user
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        setString("last_name", lastName);
    }

    /**
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return getString("last_name");
    }
    
    /**
     * sets the middle initial
     * @param middleInitial 
     */
    public void setMiddleInitial(String middleInitial) {
        setString("middle_initial", middleInitial);
    }
    
    /**
     * 
     * @return the middle initial
     */
    public String getMiddleInitial() {
        return getString("middle_initial");
    }
    
    /**
     * sets whether the child is a student
     * @param isStudent 
     */
    public void setIsStudent(Boolean isStudent) {
        setBoolean("is_student", isStudent);
    }
    
    /**
     * 
     * @return whether the child is a student
     */
    public Boolean getIsStudent() {
        return getBoolean("is_student");
    }
    
    /**
     * sets whether the child is a foster child
     * @param isFosterChild 
     */
    public void setIsFosterChild(Boolean isFosterChild) {
        setBoolean("is_foster_child", isFosterChild);
    }
    
    /**
     * 
     * @return whether the child is a foster child
     */
    public Boolean getIsFosterChild() {
        return getBoolean("is_foster_child");
    }
    
    /**
     * sets whether the child is homeless, migrant or a runaway
     * @param isHMR 
     */
    public void setIsHMR(Boolean isHMR) {
        setBoolean("is_hmr", isHMR);
    }
    
    /**
     * 
     * @return whether the child is homeless, migrant or a runaway
     */
    public Boolean getIsHMR() {
        return getBoolean("is_hmr");
    }
    
    @Override
    public String toString() {
        return "First Name: " + getFirstName() + ", Last Name: " + getLastName() + ", Middle Initial: " + getMiddleInitial() + 
                ", Is Student: " + getIsStudent() + ", Is Foster Child: " + getIsFosterChild() + ", Is HMR: " + getIsHMR();
    }

    /**
     * 
     * @return the user's full name
     */
    public String getFullName() {
        return getFirstName() + " " + getMiddleInitial() + " " + getLastName();
    }
}
