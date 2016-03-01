package com.eat.school_lunch.model;

import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "REGIONS")
public class Region extends Model implements Serializable {

    /**
     * sets the description of the region
     *
     * @param description
     */
    public void setDescription(String description) {
        setString("description", description);
    }

    /**
     *
     * @return the description of the region
     */
    public String getDescription() {
        return getString("description");
    }
    
    /**
     * sets the state abbreviation
     * @param abbreviation 
     */
    public void setAbbreviation(String abbreviation) {
        setString("abbr", abbreviation);
    }
    
    /**
     * 
     * @return the state abbreviation
     */
    public String getAbbreviation() {
        return getString("abbr");
    }
    
    @Override
    public String toString() {
        return "Description: " + getDescription() + " Abbreviation: " + getAbbreviation();
    }
}
