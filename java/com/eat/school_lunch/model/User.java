package com.eat.school_lunch.model;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.user.helper.UserHelper;
import java.io.Serializable;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "USERS")
public class User extends Model implements Serializable {

    private UserStatistic userStats;

    /**
     * Set the users first name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        set("first_name", firstName);
    }

    /**
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return get("first_name").toString();
    }

    /**
     * sets the last name of the user
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        set("last_name", lastName);
    }

    /**
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return get("last_name").toString();
    }

    /**
     * sets the user's email address
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        setString("email_address", emailAddress);
    }

    /**
     *
     * @return the user's email address
     */
    public String getEmailAddress() {
        return getString("email_address");
    }

    /**
     * sets the user's phone number
     *
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber) {
        set("phone_number", phoneNumber);
    }

    /**
     *
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return getString("phone_number");
    }

    /**
     * sets the user's address
     *
     * @param userAddressId
     */
    public void setUserAddressId(Long userAddressId) {
        setLong("user_address_id", userAddressId);
    }

    /**
     *
     * @return the user's address
     */
    public Long getUserAddressId() {
        return getLong("user_address_id");
    }

    /**
     * sets the password
     *
     * @param password
     */
    public void setPassword(String password) {
        set("password", password);
    }

    /**
     *
     * @return the password
     */
    public String getPassword() {
        return get("password").toString();
    }
    
    /**
     * sets whether the user is an admin
     * @param isAdmin 
     */
    public void setIsAdmin(Boolean isAdmin) {
        setBoolean("is_admin", isAdmin);
    }
    
    /**
     * 
     * @return whether the user is an admin
     */
    public Boolean getIsAdmin() {
        return getBoolean("is_admin");
    }

    /**
     * sets the user statistics
     *
     * @param userStats
     */
    public void setUserStats(UserStatistic userStats) {
        this.userStats = userStats;
    }

    /**
     *
     * @return the user statistics
     */
    public UserStatistic getUserStats() {
        return userStats;
    }

    /**
     *
     * @return the full name of the user; however if anonymous, return anonymous
     */
    public String getUserFullName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     *
     * @return the header name
     */
    public String getHeaderName() {
        return getFirstName();
    }
    
    /**
     * 
     * @return the user address based on the user address id
     */
    public UserAddress getUserAddress() {
        UserAddress userAddress;
        try(BaseDBSessionBean dbUSer = new BaseDBSessionBean()) {
            userAddress = UserHelper.getUserAddressById(dbUSer, getUserAddressId());
        }
        
        return userAddress;
    }
    
    public UserAddress getUserAddress(BaseDBSessionBean dbUSer) {
        UserAddress userAddress;
        userAddress = UserHelper.getUserAddressById(dbUSer, getUserAddressId());
        
        return userAddress;
    }
}
