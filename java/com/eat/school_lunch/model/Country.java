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
@Table(value = "COUNTRIES")
public class Country extends Model implements Serializable {

    /**
     * sets the description of the country
     *
     * @param description
     */
    public void setDescription(String description) {
        set("description", description);
    }

    /**
     *
     * @return the description of the country
     */
    public String getDescription() {
        return getString("description");
    }
    
    /**
     * sets the country code
     * @param code 
     */
    public void setCode(String code) {
        setString("code", code);
    }
    
    /**
     * 
     * @return the country code
     */
    public String getCode() {
        return getString("code");
    }
}
