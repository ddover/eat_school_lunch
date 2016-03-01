package com.eat.school_lunch.location;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.model.City;
import com.eat.school_lunch.model.Country;
import com.eat.school_lunch.model.CountryRegion;
import com.eat.school_lunch.model.Region;
import com.eat.school_lunch.model.RegionCity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author DoverD
 */
public class LocationHelper extends BaseHelper implements Serializable {

    /**
     *
     * @return all countries
     * @param dbUser
     */
    public static List<Country> getCountries(BaseDBSessionBean dbUser) {
        dbUser.open();
        List<Country> countries = new ArrayList(Country.findAll());

        return countries;
    }
    
    /**
     * 
     * @param dbUser
     * @param countryId
     * @return the country based on the id passed
     */
    public static Country getCountryById(BaseDBSessionBean dbUser, Long countryId) {
        dbUser.open();
        return Country.findById(countryId);
    }

    /**
     *
     * @param dbUser
     * @return all the regions
     */
    public static List<Region> getAllRegions(BaseDBSessionBean dbUser) {
        dbUser.open();
        return Region.findAll();
    }

    /**
     *
     * @param country
     * @param dbUser
     * @return all regions based on their country
     */
    public static List<Region> getRegionByCountry(BaseDBSessionBean dbUser, Country country) {
        dbUser.open();
        List<Region> regions = new ArrayList(country.getAll(Region.class));

        return regions;
    }

    /**
     * @param dbUser
     * @param countryStr
     * @return all regions based on their country
     */
    public static List<Region> getRegionByCountry(BaseDBSessionBean dbUser, String countryStr) {
        dbUser.open();
        Country country = getCountryByName(dbUser, countryStr);
        List<Region> regions = new ArrayList(country.getAll(Region.class));

        return regions;
    }

    /**
     *
     * @param region
     * @param dbUser
     * @return all cities based on the region
     */
    public static List<City> getCitiesByRegion(BaseDBSessionBean dbUser, Region region) {
        dbUser.open();
        List<City> cities = new ArrayList(region.getAll(City.class));

        return cities;
    }

    /**
     * @param dbUser
     * @param regionId
     * @param asc 
     * @return all cities based on the region
     */
    public static List<City> getCitiesByRegionId(BaseDBSessionBean dbUser, Long regionId, boolean asc) {
        dbUser.open();
        Region region = getRegionById(dbUser, regionId);
        String order = asc ? "asc" : "desc";
        List<City> cities = new ArrayList(region.getAll(City.class).orderBy("description " + order));

        return cities;
    }
    
    /**
     * @param dbUser
     * @param regionStr
     * @param asc 
     * @return all cities based on the region
     */
    public static List<String> getCitiesStrByRegion(BaseDBSessionBean dbUser, String regionStr, boolean asc) {
        dbUser.open();
        Region region = getRegionByName(dbUser, regionStr);
        String order = asc ? "asc" : "desc";
        List<City> cities = new ArrayList(region.getAll(City.class).orderBy("description " + order));
        Set<String> results = new TreeSet();
        for(City city:cities) {
            results.add(city.getDescription());
        }

        return new ArrayList(results);
    }

    /**
     * @param dbUser
     * @param country
     * @return country based on the description
     */
    public static Country getCountryByName(BaseDBSessionBean dbUser, String country) {
        dbUser.open();
        Country countryResult = Country.findFirst("description = ?", country);

        return countryResult;
    }

    /**
     * @param dbUser
     * @param region
     * @return the region based on the description
     */
    public static Region getRegionByName(BaseDBSessionBean dbUser, String region) {
        dbUser.open();

        Region regionResult = Region.findFirst("description = ?", region);
        return regionResult;
    }

    /**
     *
     * @param dbUser
     * @param regionId
     * @return the region based on the id passd
     */
    public static Region getRegionById(BaseDBSessionBean dbUser, Long regionId) {
        dbUser.open();
        Region region = Region.findById(regionId);

        return region;
    }

    /**
     *
     * @param dbUser
     * @param cityId
     * @return the city based on the id passed
     */
    public static City getCityById(BaseDBSessionBean dbUser, Long cityId) {
        dbUser.open();
        City city = City.findById(cityId);

        return city;
    }

    /**
     * @param dbUser
     * @param city
     * @return the city based on the description
     */
    public static City getCityByName(BaseDBSessionBean dbUser, String city) {
        dbUser.open();
        City cityResult = City.findFirst("description = ?", city);

        return cityResult;
    }

    /**
     * @param dbUser
     * @param id
     * @return the country based on the CountryRegion id
     */
    public static Country getCountryByCountryRegion(BaseDBSessionBean dbUser, Long id) {
        dbUser.open();
        CountryRegion cr = CountryRegion.findById(id);
        Country country = Country.findById(cr.getCountryId());

        return country;
    }
    
    /**
     * 
     * @param dbUser
     * @param regionId
     * @return the country based on the region id of the country_region object
     */
    public static Country getCountryByCountryRegionRegionId(BaseDBSessionBean dbUser, Long regionId) {
        dbUser.open();
        CountryRegion cr = CountryRegion.findFirst("region_id = ?", regionId);
        Country country = Country.findById(cr.getCountryId());
        
        return country;
    }

    /**
     *
     * @param dbUser
     * @param countryRegionId
     * @return
     */
    public static Region getRegionByCountryRegion(BaseDBSessionBean dbUser, Long countryRegionId) {
        dbUser.open();
        CountryRegion cr = CountryRegion.findById(countryRegionId);
        Region region = Region.findById(cr.getRegionId());

        return region;
    }

    /**
     *
     * @param dbUser
     * @param countryStr
     * @param regionStr
     * @return id of the country region
     */
    public static Long getIdOfCountryRegion(BaseDBSessionBean dbUser, String countryStr, String regionStr) {
        dbUser.open();
        Country country = Country.findFirst("description = ?", countryStr);
        Region region = Region.findFirst("description = ?", regionStr);
        CountryRegion cr = CountryRegion.findFirst("country_id = ? and region_id = ?", country.getLongId(), region.getLongId());

        return cr.getLongId();
    }

    /**
     *
     * @param dbUser
     * @param regionId
     * @param cityId
     * @return the region city object based on the region id and the city id
     */
    public static RegionCity getRegionCityByRegionIdCityId(BaseDBSessionBean dbUser, Long regionId, Long cityId) {
        dbUser.open();
        RegionCity rc = RegionCity.findFirst("region_id=? and city_id=?", regionId, cityId);

        return rc;
    }

    /**
     * saves the city
     *
     * @param dbUser
     * @param city
     */
    public static void saveCity(BaseDBSessionBean dbUser, City city) {
        dbUser.open();
        city.saveIt();
    }

    public static void saveRegionCity(BaseDBSessionBean dbUser, Long regionId, Long cityId) {
        dbUser.open();
        RegionCity rc = new RegionCity();
        rc.setRegionId(regionId);
        rc.setCityId(cityId);

        rc.saveIt();
    }
    
    /**
     * 
     * @param dbUser
     * @param abbr
     * @return the region based on the abbreviation
     */
    public static Region getRegionByAbbr(BaseDBSessionBean dbUser, String abbr) {
        dbUser.open();
        Region region = Region.findFirst("abbr = ?", abbr);
        
        return region;
    }
    
    /**
     * 
     * @param dbUser
     * @param zipcode
     * @return the region city based on the zip code
     */
    public static RegionCity getRegionCityByZipcode(BaseDBSessionBean dbUser, String zipcode) {
        dbUser.open();
        
        RegionCity rc = RegionCity.findFirst("zip=?", zipcode);        
        return rc;
    }
    
    /**
     * 
     * @param dbUser
     * @return all the regioncitys
     */
    public static List<RegionCity> getAllRegionCitys(BaseDBSessionBean dbUser) {
        dbUser.open();
        
        return RegionCity.findAll();
    }
    
    /**
     * 
     * @param dbUser
     * @param location
     * @return the cities close to the query
     */
    public static List<City> getCitiesLikeQuery(BaseDBSessionBean dbUser, String location) {
        dbUser.open();
        List<City> cities = new ArrayList(City.where("description like '" + location + "%'"));
        return cities;
    }
    
    /**
     * 
     * @param dbUser
     * @param cityId
     * @return the list of regioncitys based on the city id
     */
    public static List<City> getRegionCitysByCityId(BaseDBSessionBean dbUser, Long cityId) {
        dbUser.open();
        
        return RegionCity.where("city_id=?", cityId);
    }
    
    /**
     * 
     * @param dbUser
     * @param regionCityId
     * @return the region city object based on the id
     */
    public static RegionCity getRegionCityById(BaseDBSessionBean dbUser, Long regionCityId) {
        dbUser.open();
        return RegionCity.findById(regionCityId);
    }
}
