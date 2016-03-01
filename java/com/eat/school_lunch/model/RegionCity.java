package com.eat.school_lunch.model;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.location.LocationHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "REGIONS_CITIES")
public class RegionCity extends Model implements Serializable {

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

    /**
     * sets the id of the city
     *
     * @param cityId
     */
    public void setCityId(Long cityId) {
        setLong("city_id", cityId);
    }

    /**
     *
     * @return the id of the city
     */
    public Long getCityId() {
        return getLong("city_id");
    }
    
    /**
     * sets the zip code of the area
     * @param zip 
     */
    public void setZip(String zip) {
        setString("zip", zip);
    }
    
    /**
     * 
     * @return the zip code of the area
     */
    public String getZip() {
        return getString("zip");
    }
    
    /**
     * sets the latitude of the area
     * @param latitude 
     */
    public void setLatitude(String latitude) {
        setString("latitude", latitude);
    }
    
    /**
     * 
     * @return the latitude of the area
     */
    public String getLatitude() {
        return getString("latitude");
    }
    
    /**
     * sets the longitude of the area
     * @param longitude 
     */
    public void setLongitude(String longitude) {
        setString("longitude", longitude);
    }
    
    /**
     * 
     * @return the longitude of the area
     */
    public String getLongitude() {
        return getString("longitude");
    }
    
    /**
     * sets the county of the area
     * @param county 
     */
    public void setCounty(String county) {
        setString("county", county);
    }
    
    /**
     * 
     * @return the county of the area
     */
    public String getCounty() {
        return getString("county");
    }
    
    /**
     * 
     * @return the region based on the region id
     */
    public Region getRegion() {
        Region region;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            region = LocationHelper.getRegionById(dbUser, getRegionId());
        }
        
        return region;
    }
    
    /**
     * 
     * @return the city based on the city id
     */
    public City getCity() {
        City city;
        try(BaseDBSessionBean dbUser= new BaseDBSessionBean()) {
            city = LocationHelper.getCityById(dbUser, getCityId());
        }
        
        return city;
    }
    
    /**
     * 
     * @return the country
     */
    public Country getCountry() {
        Country country;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            country = LocationHelper.getCountryByCountryRegionRegionId(dbUser, getRegionId());
        }
        
        return country;
    }
}