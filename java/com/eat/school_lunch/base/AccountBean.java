package com.eat.school_lunch.base;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.comparator.CityDescriptionComparator;
import com.eat.school_lunch.email.EmailValidator;
import com.eat.school_lunch.model.City;
import com.eat.school_lunch.model.Country;
import com.eat.school_lunch.location.LocationHelper;
import com.eat.school_lunch.model.Region;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.model.UserAddress;
import com.eat.school_lunch.user.helper.UserHelper;
import com.eat.school_lunch.utils.PhoneNumberValidator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "accountBean")
@ViewScoped
public class AccountBean extends BaseManagedBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(AccountBean.class);

    List<StreamedContent> userImages;

    private Region region;

    private City city;

    private String selectedCountryId;

    private String selectedRegionName;

    private String selectedCityName;

    private List<Region> regions;

    private List<Country> countries;

    private List<City> cities;

    private Boolean saveSuccessful;
    
    private UserAddress userAddress;
    
    private User tempUser;
    
    /**
     * sets the temporary user
     * @param tempUser 
     */
    public void setTempUser(User tempUser) {
        this.tempUser = tempUser;
    }
    
    /**
     * 
     * @return the temporary user
     */
    public User getTempUser() {
        return tempUser;
    }
    
    /**
     * sets the user address
     * @param userAddress 
     */
    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
    
    /**
     * 
     * @return the user address
     */
    public UserAddress getUserAddress() {
        return userAddress;
    }

    /**
     * sets whether the updates were successfully saved
     *
     * @param saveSuccessful
     */
    public void setSaveSuccessful(Boolean saveSuccessful) {
        this.saveSuccessful = saveSuccessful;
    }

    /**
     *
     * @return whether the updates were successfully saved
     */
    public Boolean getSaveSuccessful() {
        return saveSuccessful;
    }

//    private boolean profileImgExists = false;
    /**
     * sets the user's images to choose from
     *
     * @param userImages
     */
    public void setUserImages(List<StreamedContent> userImages) {
        this.userImages = userImages;
    }

    /**
     *
     * @return the user's images to choose from
     */
    public List<StreamedContent> getUserImages() {
        return userImages;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     *
     * @return the region of the user
     */
    public Region getRegion() {
        return region;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    /**
     * sets the region selected
     *
     * @param regions
     */
    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    /**
     *
     * @return the region selected
     */
    public List<Region> getRegions() {
        return regions;
    }

    /**
     * sets the country selected
     *
     * @param selectedCountryId
     */
    public void setSelectedCountryId(String selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }

    /**
     *
     * @return the country selected
     */
    public String getSelectedCountryId() {
        return selectedCountryId;
    }

    /**
     * sets the name of the region selected
     *
     * @param selectedRegionName
     */
    public void setSelectedRegionName(String selectedRegionName) {
        this.selectedRegionName = selectedRegionName;
    }

    /**
     *
     * @return the name of the region selected
     */
    public String getSelectedRegionName() {
        return selectedRegionName;
    }

    /**
     * sets the name of the city selected
     *
     * @param selectedCityName
     */
    public void setSelectedCityName(String selectedCityName) {
        this.selectedCityName = selectedCityName;
    }

    /**
     *
     * @return the id of the  city selected
     */
    public String getSelectedCityName() {
        return selectedCityName;
    }

    @PostConstruct
    public void initialize() {
        if (getUserBean().getLoggedIn()) {
            setSaveSuccessful(false);
            setTempUser(new User());
            getTempUser().setEmailAddress(getUserBean().getUser().getEmailAddress());
            getTempUser().setPhoneNumber(getUserBean().getUser().getPhoneNumber());
            getTempUser().setFirstName(getUserBean().getUser().getFirstName());
            getTempUser().setLastName(getUserBean().getUser().getLastName());
            
//            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
//                System.out.println("Id: " + getUserBean().getUser().getLocationId());
            if (getUserBean().getUser().getUserAddressId() != null) {
                System.out.println("Found it");
                
                try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    setUserAddress(UserHelper.getUserAddressById(dbUser, getUserBean().getUser().getUserAddressId()));
                }
                if(getUserAddress()!=null) {
                    if(getUserAddress().getCountry()!=null) {
                        setSelectedCountryId(getUserAddress().getCountry().getLongId().toString());
                    }
                    if(getUserAddress().getRegion()!=null) {
                        setSelectedRegionName(getUserAddress().getRegion().getDescription());
                    }
                    if(getUserAddress().getCity()!=null) {
                        setSelectedCityName(getUserAddress().getCity().getDescription());
                    }
                    handleCountryChange();
                    handleRegionChange();
                }
            }

            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                setCountries(LocationHelper.getCountries(dbUser));
            }
        }
    }

    /**
     * sets the countries
     *
     * @param countries
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     *
     * @return all countries
     * @throws IOException
     */
    public List<Country> getCountries() throws IOException {
        return countries;
    }

    /**
     * sets the cities
     *
     * @param cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     *
     * @return the cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * sets the regions based on the country chosen
     */
    public void handleCountryChange() {
        System.out.println("Made herr country");
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            setSelectedCountryId(LocationHelper.getCountryByName(dbUser, "United States").getLongId().toString());
        }

        System.out.println("Country: " + getSelectedCountryId());

        if (getSelectedCountryId() != null && !getSelectedCountryId().isEmpty()) {
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                Country country = LocationHelper.getCountryById(dbUser, Long.parseLong(getSelectedCountryId()));
                setRegions(LocationHelper.getRegionByCountry(dbUser, country));
            }
        } else {
            setRegions(new ArrayList());
        }
        System.out.println("Regions: " + getRegions().size());
    }

    /**
     * sets the cities based on the country chosen
     */
    public void handleRegionChange() {
        System.out.println("Made here region");
        
        setCities(new ArrayList());
        List<City> tempCities = new ArrayList();
        if(getSelectedRegionName()!=null && !getSelectedRegionName().isEmpty()) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                Region tmpRegion = LocationHelper.getRegionByName(dbUser, getSelectedRegionName());
                tempCities.addAll(LocationHelper.getCitiesByRegionId(dbUser, tmpRegion.getLongId(), true));
            }
            
            Set<City> citiesSet = new TreeSet<>(new CityDescriptionComparator());
            for(City regionCity:tempCities) {
                citiesSet.add(regionCity);
            }
            
            getCities().addAll(citiesSet);
        } else {
            setSelectedCityName(null);
        }
    }

    /**
     * saves the user changes
     */
    public void saveUserChanges() {
        boolean errorFound = false;
        System.out.println("User: " + getUserBean().getUser().getEmailAddress() + " New: " + getUserBean().getUser().getEmailAddress());
        //test if valid email
        String email = getTempUser().getEmailAddress().toLowerCase();
        Boolean validEmail;
        Boolean validPhoneNum;
        EmailValidator validator = new EmailValidator();
        validEmail = validator.validate(email);
        String phoneNum = getTempUser().getPhoneNumber().replaceAll("[\\D]", "");
        validPhoneNum = PhoneNumberValidator.validatePhoneNumber(phoneNum);
        
        //test whether an email or phone number was provided
        if((email==null || email.isEmpty()) && (phoneNum==null || phoneNum.isEmpty())) {
            sendErrorFacesMessage(null, "You must provide an email or phone number!");
            errorFound = true;
        } else {
        
            if(!validEmail && !validPhoneNum) {
                sendErrorFacesMessage(null, "You must enter a valid email or mobile phone number!");
                errorFound = true;
            }
        }

        //if no error was found, save and refresh the page
        if (!errorFound) {            
            if(getUserAddress()==null) {
                setUserAddress(new UserAddress());
            }
            
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                //save the users personal information
                getUserBean().getUser().setEmailAddress(getTempUser().getEmailAddress());
                getUserBean().getUser().setFirstName(getTempUser().getFirstName());
                getUserBean().getUser().setLastName(getTempUser().getLastName());
                getUserBean().getUser().setPhoneNumber(getTempUser().getPhoneNumber());
                
                //save the user's address
                getUserAddress().setUserId(getUserBean().getUser().getLongId());
                
                if(getSelectedRegionName()!=null && !getSelectedRegionName().isEmpty()) {
                    Region tmpRegion = LocationHelper.getRegionByName(dbUser, getSelectedRegionName());
                    getUserAddress().setRegionId(tmpRegion.getLongId());
                }
                if(getSelectedCityName()!=null && !getSelectedCityName().isEmpty()) {
                    City tempCity = LocationHelper.getCityByName(dbUser, getSelectedCityName());
                    getUserAddress().setCityId(tempCity.getLongId());
                }
                UserHelper.saveUserAddress(dbUser, getUserAddress());
                //update the the users
                getUserBean().getUser().setUserAddressId(getUserAddress().getLongId());
                UserHelper.saveUser(dbUser, getUserBean().getUser());
                
            }

            getUserBean().setUser(getUserBean().getUser());
            
            //determine is mobile
            if(getIsUsingComputer()) {
                sendInfoFacesMessage("", "Your changes have been saved.", "accountMessages");
            } else {
                getRequestContext().execute("PF('saveSuccessVar').show();");
            }
            setSaveSuccessful(true);
        }
    }
}
