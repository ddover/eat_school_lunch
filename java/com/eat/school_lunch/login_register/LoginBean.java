package com.eat.school_lunch.login_register;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseManagedBean;
import com.eat.school_lunch.email.EmailValidator;
import com.eat.school_lunch.form.helper.FormHelper;
import com.eat.school_lunch.user.helper.UserHelper;
import com.eat.school_lunch.utils.PhoneNumberValidator;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean extends BaseManagedBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(LoginBean.class);

    private String userEmail;

    private String userPassword;

    private boolean emptyEmail;

    private boolean emptyPassword;

    private boolean mismatch;
    
    private String login;
    
    /**
     * sets the login info
     * @param login 
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    /**
     * 
     * @return the login info
     */
    public String getLogin() {
        return login;
    }

    public void setMismatch(boolean mismatch) {
        this.mismatch = mismatch;
    }

    public boolean getMismatch() {
        return mismatch;
    }

    public void setEmptyEmail(boolean emptyEmail) {
        this.emptyEmail = emptyEmail;
    }

    public boolean getEmptyEmail() {
        return emptyEmail;
    }

    public void setEmptyPassword(boolean emptyPassword) {
        this.emptyPassword = emptyPassword;
    }

    public boolean getEmptyPassword() {
        return emptyPassword;
    }

    /**
     * sets the email typed in by the user
     *
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        System.out.println("email: hey " + userEmail);
    }

    /**
     *
     * @return the email typed in by the user
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * login the user using their email address and password
     */
    public void loginUser() {

        System.out.println("Made it to login here");
        //test if email is valid
        Boolean validEmail;
        Boolean validPhoneNum;
        EmailValidator validator = new EmailValidator();
        validEmail = validator.validate(getLogin().toLowerCase());
        validPhoneNum = PhoneNumberValidator.validatePhoneNumber(getLogin());
        if(!validEmail && !validPhoneNum) {
            sendErrorFacesMessage(null, "You must enter a valid email or mobile phone number!");
        } else {
            if(validEmail) {
                System.out.println("Valid email");
                try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    getUserBean().setUser(UserHelper.getUserByEmailAddress(dbUser, getLogin().toLowerCase(), getUserPassword()));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            } else if(validPhoneNum) {
                System.out.println("Valid phone number");
                String phoneNum = getLogin().replaceAll("[\\D]", "");
                try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    getUserBean().setUser(UserHelper.getUserByPhoneNumber(dbUser, phoneNum, getUserPassword()));
                }
            }

            if (getUserBean().getUser() != null) {
                try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    getUserBean().setForm(FormHelper.getFormByUserId(dbUser, getUserBean().getUser().getLongId()));
                }

                getRequest().getSession().setAttribute("user", getUserBean().getUser());
                try {                                           
                    FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath());
                } catch (IOException e) {
                    LOGGER.error(e);
                }                
            } else {
                sendErrorFacesMessage(null, "The email/mobile number or password you entered is incorrect!");
            }
        }
    }

    /**
     * sets the user password
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @return the user's password
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    /**
     * if the user isn't logged in go to the login page, else go to the form
     */
    public void continueApp() {
        
        try {
            if(getUserBean().getLoggedIn()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + "/form");
            } else {
                getUserBean().setUrlLastVisited(getContextPath() + "/form");
                FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + "/login");
            }
        } catch(IOException e) {
            LOGGER.error(e);
        }
    }
    
    /**
     * if the user isn't logged in go to the login page, else go to check the application's status
     */
    public void appStatus() {
        
        try {
            if(getUserBean().getLoggedIn()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + "/appStatus");
            } else {
                getUserBean().setUrlLastVisited(getContextPath() + "/appStatus");
                FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + "/login");
            }
        } catch(IOException e) {
            LOGGER.error(e);
        }
    }
}
