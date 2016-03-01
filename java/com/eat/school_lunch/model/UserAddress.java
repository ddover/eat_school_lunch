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
@Table(value = "USER_ADDRESSES")
public class UserAddress extends Model implements Serializable {
    
    /**
     * sets the user id of the address
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        setLong("user_id", userId);
    }

    /**
     *
     * @return the user id of the address
     */
    public Long getUserId() {
        return getLong("user_id");
    }
    
    /**
     * sets the country id of the user's address
     * @param countryId 
     */
    public void setCountryId(Long countryId) {
        setLong("country_id", countryId);
    }
    
    /**
     * 
     * @return the country id of the user's address
     */
    public Long getCountryId() {
        return getLong("country_id");
    }
    
    /**
     * sets the region id of the user's address
     * @param regionId 
     */
    public void setRegionId(Long regionId) {
        setLong("region_id", regionId);
    }
    
    /**
     * 
     * @return the region id of the user's address
     */
    public Long getRegionId() {
        return getLong("region_id");
    }
    
    /**
     * sets the city id of the user's address
     * @param cityId 
     */
    public void setCityId(Long cityId) {
        setLong("city_id", cityId);
    }
    
    /**
     * 
     * @return the city id of the user's address
     */
    public Long getCityId() {
        return getLong("city_id");
    }
    
    /**
     * sets the address of the street
     * @param streetAddress 
     */
    public void setStreetAddress(String streetAddress) {
        setString("street_address", streetAddress);
    }
    
    /**
     * 
     * @return the address of the street
     */
    public String getStreetAddress() {
        return getString("street_address");
    }
    
    public void setAptNum(String aptNum) {
        setString("apt_num", aptNum);
    }
    
    public String getAptNum() {
        return getString("apt_num");
    }
    
    /**
     * sets the zipcode of the area
     * @param zipcode 
     */
    public void setZipcode(String zipcode) {
        setString("zipcode", zipcode);
    }
    
    /**
     * 
     * @return the zipcode of the area
     */
    public String getZipcode() {
        return getString("zipcode");
    }
    
    /**
     * 
     * @return the country based on the country id
     */
    public Country getCountry() {
        Country country;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            country = LocationHelper.getCountryById(dbUser, getCountryId());
        }
        
        return country;
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
     * @param dbUser
     * @return the region
     */
    public Region getRegion(BaseDBSessionBean dbUser) {
        Region region;        
        region = LocationHelper.getRegionById(dbUser, getRegionId());
        
        return region;
    }
    
    /**
     * 
     * @return the city based on the id
     */
    public City getCity() {
        City city;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            city = LocationHelper.getCityById(dbUser, getCityId());
        }
        
        return city;
    }
    
    /**
     * 
     * @param dbUser
     * @return the city
     */
    public City getCity(BaseDBSessionBean dbUser) {
        City city;
        city = LocationHelper.getCityById(dbUser, getCityId());
        
        return city;
    }
}
