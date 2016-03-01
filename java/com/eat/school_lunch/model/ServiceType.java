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
@Table(value = "SERVICE_TYPE")
public class ServiceType extends Model implements Serializable {
    
    /**
     * sets the label of the service
     * @param label 
     */
    public void setLabel(String label) {
        setString("label", label);
    }
    
    /**
     * 
     * @return the label of the service
     */
    public String getLabel() {
        return getString("label");
    }
    
    /**
     * sets the description of the service
     * @param description 
     */
    public void setDescription(String description) {
        setString("description", description);
    }
    
    /**
     * 
     * @return the description of the service
     */
    public String getDescription() {
        return getString("description");
    }
    
    @Override
    public boolean equals(Object o) {
        ServiceType serviceType = (ServiceType) o;
        return serviceType.getLongId().equals(getLongId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
