package com.eat.school_lunch.base;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.password.PasswordHash;
import com.eat.school_lunch.user.helper.UserHelper;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "editPasswdBean")
@ViewScoped
public class EditPasswdBean extends BaseManagedBean implements Serializable {

    private String currentPassword;

    private String newPassword;

    private String confirmPassword;

    private static final Logger LOGGER = LogManager.getLogger(EditPasswdBean.class);

    /**
     * sets the confirm password
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return the confirm password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * sets the new password
     *
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * sets the current password of the user
     *
     * @param currentPassword
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     *
     * @return the current password of the user
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * save the new password
     */
    public void saveNewPasswd() {
        boolean errorFound = false;

        try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            //test if the current password is null or empty
            if (getCurrentPassword() == null || getCurrentPassword().isEmpty()) {
                errorFound = true;
                sendErrorFacesMessage("", "We need your current password!", "editPasswdMsg");
            } else if (!PasswordHash.validatePassword(getCurrentPassword(), getUserBean().getUser().getPassword())) {
                errorFound = true;
                sendErrorFacesMessage("", "Your current password is not correct", "editPasswdMsg");
            }

            //test if the new password is empty or null
            if (getNewPassword() == null || getNewPassword().isEmpty()) {
                errorFound = true;
                sendErrorFacesMessage("", "We need your new password to change it!", "editPasswdMsg");
            }

            //test if the confirmation password is empty or null
            if (getConfirmPassword() == null || getConfirmPassword().isEmpty()) {
                errorFound = true;
                sendErrorFacesMessage("", "We need you to confirm your new password!", "editPasswdMsg");
            }

            //test to make sure the new password and confirmation password matches
            if (getNewPassword() != null && getConfirmPassword() != null) {
                if (!getConfirmPassword().equals(getNewPassword())) {
                    errorFound = true;
                    sendErrorFacesMessage("", "Your new password and the confirm password does not match!", "editPasswdMsg");
                }
            }

            //if no errors are found, save the user and send a message saying the password was saved
            if (!errorFound) {
                UserHelper.saveUsersNewPassword(dbUser, getUserBean().getUser(), getNewPassword());
                if(getIsUsingComputer()) {
                    getRequestContext().execute("PF('compAcctSuccessVar').show();");
                } else {
                    getRequestContext().execute("PF('mobileAcctSuccessVar').show();");
                }
//                clearAll();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * clear out everything
     */
    public void clearAll() {
        setNewPassword(null);
        setCurrentPassword(null);
        setConfirmPassword(null);
    }
}
