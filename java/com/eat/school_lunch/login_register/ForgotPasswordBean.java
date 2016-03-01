package com.eat.school_lunch.login_register;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.email.EmailValidator;
import com.eat.school_lunch.model.SecurityQuestionAnswer;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.password.PasswordHash;
import com.eat.school_lunch.securityquestion.helper.SecurityQuestionAnswerHelper;
import com.eat.school_lunch.user.helper.UserHelper;
import com.eat.school_lunch.utils.PhoneNumberValidator;
import com.eat.school_lunch.wizard.WizardController;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "forgotPasswordBean")
@ViewScoped
public class ForgotPasswordBean extends WizardController implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(ForgotPasswordBean.class);

    private SecurityQuestionAnswer securityQuestion;

    private String userAnswer;

    private String password;

    private String confirmPassword;

    private String login;

    private User selectedUser;

    private List<SecurityQuestionAnswer> questionAnswers;

    /**
     * sets the list of security question answers
     *
     * @param questionAnswers
     */
    public void setQuestionAnswers(List<SecurityQuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    /**
     *
     * @return the list of security question answers
     */
    public List<SecurityQuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    /**
     * sets the selected user
     *
     * @param selectedUser
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    /**
     *
     * @return the selected user
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     * sets the login
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * sets the password the user wants use
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return the password the user wants use
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the confirm password the user wants use
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return the confirm password the user wants use
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * sets the answer the user gave
     *
     * @param userAnswer
     */
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     *
     * @return the answer the user gave
     */
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * sets the security question
     *
     * @param securityQuestion
     */
    public void setSecurityQuestion(SecurityQuestionAnswer securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     *
     * @return the security question
     */
    public SecurityQuestionAnswer getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * initializes the list of questions to ask the user
     */
    public void initializeQuestions() {

        try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            setQuestionAnswers(new ArrayList(SecurityQuestionAnswerHelper.getSecurityQuestionAnswersByUserId(dbUser, getSelectedUser().getLongId())));
        }
    }

    /**
     * picks a random security question answer
     */
    public void initRandomQuestion() {

        Boolean errorFound = false;

        Boolean validEmail;
        Boolean validPhoneNum;
        EmailValidator validator = new EmailValidator();
        validEmail = validator.validate(getLogin().toLowerCase());
        String phoneNum = getLogin().replaceAll("[\\D]", "");
        validPhoneNum = PhoneNumberValidator.validatePhoneNumber(phoneNum);
        if (!validEmail && !validPhoneNum) {
            sendErrorFacesMessage(null, "You must enter a valid email or mobile phone number!");
            errorFound = true;
        } else if (validEmail) {
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                setSelectedUser(UserHelper.getUserByEmailAddress(dbUser, getLogin()));
                if (getSelectedUser() == null) {
                    sendErrorFacesMessage(null, "No user was found with this email.");
                    errorFound = true;
                }
            }
        } else if (validPhoneNum) {
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                setSelectedUser(UserHelper.getUserByPhoneNumer(dbUser, phoneNum));
                if (getSelectedUser() == null) {
                    sendErrorFacesMessage(null, "No user was found with that phone number.");
                    errorFound = true;
                }
            }
        }

        if (!errorFound && getSelectedUser() != null) {

            //select the random security question answer
            generateNewQuestion();

            //go to the next level
            setCurrentLevel(getCurrentLevel() + 1);
        }
    }

    /**
     * determine if the question was correct, if so, go to the next level
     */
    public void determineQuestionCorrect() {

        try {
            if (getUserAnswer() == null || getUserAnswer().isEmpty()) {
                sendErrorFacesMessage(null, "You must enter an answer.");
            } else if (!PasswordHash.validatePassword(getUserAnswer(), getSecurityQuestion().getAnswer())) {

                sendErrorFacesMessage(null, "Incorrect Answer");
            } else {

                //go to the next level
                setCurrentLevel(getCurrentLevel() + 1);
            }
        } catch(InvalidKeySpecException | NoSuchAlgorithmException e) {
            LOGGER.error(e);
        }
    }

    /**
     * generate a new question
     */
    public void generateNewQuestion() {

        //if the list of questions is empty, run the method to generate them
        if (getQuestionAnswers() == null || getQuestionAnswers().isEmpty()) {
            initializeQuestions();
        }

        Random randomizer = new Random();
        SecurityQuestionAnswer randomSelection = getQuestionAnswers().get(randomizer.nextInt(getQuestionAnswers().size()));

        if (getSecurityQuestion() == null) {
            setSecurityQuestion(randomSelection);
        } else {
            if (randomSelection.getLongId().equals(getSecurityQuestion().getLongId())) {
                generateNewQuestion();
            }

            setSecurityQuestion(randomSelection);
        }

        setUserAnswer(null);
    }

    /**
     * change the password
     */
    public void changePassword() {

        Boolean errorFound = false;
        Boolean passwordProvided = false;
        Boolean confirmPasswordProvided = false;

        if (getPassword() != null && !getPassword().isEmpty()) {
            passwordProvided = true;
        } else {
            sendErrorFacesMessage(null, "You must provide a new password.");
            errorFound = true;
        }

        if (getConfirmPassword() != null && !getConfirmPassword().isEmpty()) {
            confirmPasswordProvided = true;
        } else {
            sendErrorFacesMessage(null, "You must a provide a confirmation password.");
            errorFound = true;
        }

        if (passwordProvided && confirmPasswordProvided) {
            if (!getPassword().equals(getConfirmPassword())) {
                sendErrorFacesMessage(null, "You passwords do not match.");
                errorFound = true;
            }
        }

        if (!errorFound) {
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                UserHelper.saveUsersNewPassword(dbUser, getSelectedUser(), getPassword());
            } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                LOGGER.error(e);
            }

            if (getIsUsingComputer()) {
                getRequestContext().execute("PF('newPwdSuccessVar').show();");
            } else {
                getRequestContext().execute("PF('mobileNewPwdSuccessVar').show();");
            }
        }
    }
}
