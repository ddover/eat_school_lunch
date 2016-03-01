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
@Table(value = "COUNTRIES_REGIONS")
public class CountryRegion extends Model implements Serializable {

    /**
     * sets the country id
     *
     * @param countryId
     */
    public void setCountryId(Long countryId) {
        setLong("country_id", countryId);
    }

    /**
     *
     * @return the id of the country
     */
    public Long getCountryId() {
        return getLong("country_id");
    }

    /**
     * sets the id of the region
     *
     * @param regionId
     */
    public void setRegionId(Long regionId) {
        setLong("region_id", regionId);
    }

    /**
     *
     * @return the id of the region
     */
    public Long getRegionId() {
        return getLong("region_id");
    }
}
