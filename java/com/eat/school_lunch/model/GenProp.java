package com.eat.school_lunch.model;

import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 * This table is for storing the application properties but the values are
 * stored in the database so that every time values are changed, tomcat doesn't
 * have to be restarted.
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "GEN_PROPS")
public class GenProp extends Model implements Serializable {

    /**
     * sets the key name
     *
     * @param keyName
     */
    public void setKeyName(String keyName) {
        setString("key_name", keyName);
    }

    /**
     *
     * @return the key name
     */
    public String getKeyName() {
        return getString("key_name");
    }

    /**
     * sets the value of the key name
     *
     * @param keyValue
     */
    public void setKeyValue(String keyValue) {
        setString("key_value", keyValue);
    }

    /**
     *
     * @return the value of the key name
     */
    public String getKeyValue() {
        return getString("key_value");
    }
}
