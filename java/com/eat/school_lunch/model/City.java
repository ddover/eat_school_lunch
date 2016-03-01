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
@Table(value = "CITIES")
public class City extends Model implements Serializable {

    /**
     * sets the description of the city
     *
     * @param description
     */
    public void setDescription(String description) {
        set("description", description);
    }

    /**
     *
     * @return the description of the city
     */
    public String getDescription() {
        return getString("description");
    }
    
    @Override
    public String toString() {
        return "Description: " + getDescription();
    }
}
