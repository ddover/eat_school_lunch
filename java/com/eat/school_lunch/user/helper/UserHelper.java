package com.eat.school_lunch.user.helper;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import com.eat.school_lunch.email.EmailValidator;
import com.eat.school_lunch.password.PasswordHash;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.model.UserAddress;
import com.eat.school_lunch.model.UserStatistic;
import com.eat.school_lunch.utils.GenPropHelper;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
public class UserHelper extends BaseHelper implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(UserHelper.class);
    
    /**
     * 
     * @param dbUser
     * @param phoneNumber
     * @return the user based on the phone number passed
     */
    public static User getUserByPhoneNumer(BaseDBSessionBean dbUser, String phoneNumber) {
        dbUser.open();
        return User.findFirst("phone_number = ?", phoneNumber);
    }

    /**
     * 
     * @param dbUser
     * @param phoneNumber
     * @param password
     * @return the user based on the phone number
     */
    public static User getUserByPhoneNumber(BaseDBSessionBean dbUser, String phoneNumber, String password) {
        dbUser.open();
        User user = User.findFirst("phone_number = ?", phoneNumber);
        boolean validPassword = false;
        if (user != null) {
            //test if the user password is equal to the hashed password in the database
            try {
                validPassword = PasswordHash.validatePassword(password, user.getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                LOGGER.error(e);
            }
        }

        if (validPassword) {
            HttpServletRequest hsr = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
            UserAgent userAgent = UserAgent.parseUserAgentString((hsr.getHeader("User-Agent")));
            String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
            System.out.println("Device Type: " + deviceType);
            String os = userAgent.getOperatingSystem().getGroup().getName();
            System.out.println("OS: " + os);
            
            UserStatistic userStats = new UserStatistic();
            userStats.setUserId(user.getLongId());
            Timestamp ts = new Timestamp(new Date().getTime());
            userStats.setLogged_In(ts);
            userStats.setLogged_Out(null);
            userStats.setDeviceType(deviceType + " " + os);
            userStats.saveIt();
            user.setUserStats(userStats);
            user.saveIt();
        } else {
            user = null;
        }

        return user;
    }
    
    /**
     * 
     * @param dbUser
     * @param emailAddress
     * @return the user based on the email address passed
     */
    public static User getUserByEmailAddress(BaseDBSessionBean dbUser, String emailAddress) {
        dbUser.open();
        return User.findFirst("email_address = ?", emailAddress);
    }
    
    /**
     * @param dbUser
     * @param emailAddress
     * @param password
     * @return the user based on the user email passed
     */
    public static User getUserByEmailAddress(BaseDBSessionBean dbUser, String emailAddress, String password) {
        dbUser.open();
        User user = User.findFirst("email_address = ?", emailAddress);
        boolean validPassword = false;
        if (user != null) {
            //test if the user password is equal to the hashed password in the database
            try {
                validPassword = PasswordHash.validatePassword(password, user.getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                LOGGER.error(e);
            }
        }

        if (validPassword) {
            HttpServletRequest hsr = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
            UserAgent userAgent = UserAgent.parseUserAgentString((hsr.getHeader("User-Agent")));
            String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
            System.out.println("Device Type: " + deviceType);
            String os = userAgent.getOperatingSystem().getGroup().getName();
            System.out.println("OS: " + os);
            
            UserStatistic userStats = new UserStatistic();
            userStats.setUserId(user.getLongId());
            Timestamp ts = new Timestamp(new Date().getTime());
            userStats.setLogged_In(ts);
            userStats.setLogged_Out(null);
            userStats.setDeviceType(deviceType + " " + os);
            userStats.saveIt();
            user.setUserStats(userStats);
            user.saveIt();
        } else {
            user = null;
        }

        return user;
    }

    /**
     * logs out the user
     *
     * @param dbUser
     * @param user
     */
    public static void logOutUser(BaseDBSessionBean dbUser, User user) {
        dbUser.open();
        user.getUserStats().setLogged_Out(new Timestamp(new Date().getTime()));
        user.getUserStats().saveIt();
    }

    /**
     * @param dbUser
     * @param emailAddress
     * @return whether the user exists based on the email address
     */
    public static boolean getUserExists(BaseDBSessionBean dbUser, String emailAddress) {
        dbUser.open();
        return User.findFirst("email_address = ?", emailAddress) != null;
    }
    
    /**
     * 
     * @param dbUser
     * @param phoneNum
     * @return whether the user exists based on the phone number
     */
    public static Boolean getUserExistsByPhoneNum(BaseDBSessionBean dbUser, String phoneNum) {
        dbUser.open();
        return User.findFirst("phone_number=?", phoneNum) != null;
    }

    /**
     *
     * saves the user's new
     *
     * @param dbUser
     * @param user
     * @param password
     */
    public static void saveUsersNewPassword(BaseDBSessionBean dbUser, User user, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        dbUser.open();
        user.setPassword(PasswordHash.createHash(password));
        user.saveIt();
    }

    /**
     * @param dbUser
     * @param emailAddress
     * @param password
     * @param firstName
     * @param lastName
     * @param city
     * @param state
     * @param country
     * @return the user
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public static User signUpUser(BaseDBSessionBean dbUser, String emailAddress, String password, String firstName, String lastName, String city, String state, String country)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        
        Boolean validEmail;
        EmailValidator validator = new EmailValidator();
        validEmail = validator.validate(emailAddress.toLowerCase());
        
        dbUser.open();

        User user = new User();
        if(validEmail) {
            user.setEmailAddress(emailAddress.toLowerCase());
        } else {
            String formattedPhoneNum = emailAddress.replaceAll("[\\D]", "");
            user.setPhoneNumber(formattedPhoneNum);
        }
        user.setPassword(PasswordHash.createHash(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.saveIt();

        HttpServletRequest hsr = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        UserAgent userAgent = UserAgent.parseUserAgentString((hsr.getHeader("User-Agent")));
        String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
        String os = userAgent.getOperatingSystem().getGroup().getName();
        
        UserStatistic userStats = new UserStatistic();
        userStats.setUserId(user.getLongId());
        Timestamp ts = new Timestamp(new Date().getTime());
        userStats.setLogged_In(ts);
        userStats.setLogged_Out(null);
        userStats.setDeviceType(deviceType + " " + os);
        userStats.saveIt();

        user.setUserStats(userStats);
        user.saveIt();

        //give the user a folder to hold stuff in the future
//        createUserFolder(dbUser, user);
        return user;
    }

    /**
     * create a folder for the user
     *
     * @param dbUser
     * @param user
     */
    public static void createUserFolder(BaseDBSessionBean dbUser, User user) {
        StringBuilder userDir = new StringBuilder();

        String filePath = GenPropHelper.getGenPropByKeyName(dbUser, "profileDir").getKeyName();
        for (String str : filePath.split("--")) {
            userDir.append(str);
            userDir.append(File.separator);
        }
        userDir.append(user.getEmailAddress());

        File dir = new File(userDir.toString());
        dir.mkdir();

        //create file in directory for profile images
        try {
            File profilePicDir = new File(dir.getCanonicalPath().concat(File.separator).concat(GenPropHelper.getGenPropByKeyName(dbUser, "profileImgFolderName").getKeyName()));
            profilePicDir.mkdir();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * @param dbUser
     * @param user_Id
     * @return the user based on the user email passed
     */
    public static User getUserById(BaseDBSessionBean dbUser, Long user_Id) {
        dbUser.open();
        return User.findFirst("id = ?", user_Id);
    }

    /**
     * @param dbUser
     * @param email
     * @return whether the user email was found in the database
     */
    public static boolean getUserEmailFound(BaseDBSessionBean dbUser, String email) {
        dbUser.open();
        return User.findFirst("email_address = ?", email) != null;
    }

    /**
     * save the user
     *
     * @param dbUser
     * @param user
     */
    public static void saveUser(BaseDBSessionBean dbUser, User user) {
        dbUser.open();
        user.saveIt();
    }

    /**
     * @param dbUser
     * @param user
     * @return the saved user
     */
    public static User getSaveUser(BaseDBSessionBean dbUser, User user) {
        dbUser.open();
        user.saveIt();

        return user;
    }
    
    /**
     * 
     * @param dbUser
     * @param userAddressId
     * @return the user address based on the id passed
     */
    public static UserAddress getUserAddressById(BaseDBSessionBean dbUser, Long userAddressId) {
        dbUser.open();
        return UserAddress.findById(userAddressId);
    }
    
    /**
     * saves the user's address to the database
     * @param dbUser
     * @param userAddress 
     */
    public static void saveUserAddress(BaseDBSessionBean dbUser, UserAddress userAddress) {
        dbUser.open();
        userAddress.saveIt();
    }
}
