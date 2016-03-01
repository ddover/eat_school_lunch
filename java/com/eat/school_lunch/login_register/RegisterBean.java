package com.eat.school_lunch.login_register;

import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseManagedBean;
import com.eat.school_lunch.email.EmailValidator;
import com.eat.school_lunch.model.SecurityQuestion;
import com.eat.school_lunch.model.SecurityQuestionAnswer;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.password.PasswordHash;
import com.eat.school_lunch.securityquestion.helper.SecurityQuestionAnswerHelper;
import com.eat.school_lunch.securityquestion.helper.SecurityQuestionHelper;
import com.eat.school_lunch.user.helper.UserHelper;
import com.eat.school_lunch.utils.PhoneNumberValidator;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean extends BaseManagedBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(RegisterBean.class);

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String city;
    private String state;
    private String country;
    private String confirmPassword;
    private List<SecurityQuestion> securityQuestions1;
    private List<SecurityQuestion> securityQuestions2;
    private List<SecurityQuestion> securityQuestions3;
    private String answer1;
    private String answer2;
    private String answer3;
    private String selectedQuestionId1;
    private String selectedQuestionId2;
    private String selectedQuestionId3;
    
    /**
     * sets the id of the selected question
     * @param selectedQuestionId1 
     */
    public void setSelectedQuestionId1(String selectedQuestionId1) {
        this.selectedQuestionId1 = selectedQuestionId1;
    }
    
    /**
     * 
     * @return the id of the selected question
     */
    public String getSelectedQuestionId1() {
        return selectedQuestionId1;
    }
    
    /**
     * sets the id of the selected question
     * @param selectedQuestionId2 
     */
    public void setSelectedQuestionId2(String selectedQuestionId2) {
        this.selectedQuestionId2 = selectedQuestionId2;
    }
    
    /**
     * 
     * @return the id of the selected question
     */
    public String getSelectedQuestionId2() {
        return selectedQuestionId2;
    }
    
    /**
     * sets the id of the selected question
     * @param selectedQuestionId3
     */
    public void setSelectedQuestionId3(String selectedQuestionId3) {
        this.selectedQuestionId3 = selectedQuestionId3;
    }
    
    /**
     * 
     * @return the id of the selected question
     */
    public String getSelectedQuestionId3() {
        return selectedQuestionId3;
    }
    
    
    /**
     * sets the first answer
     * @param answer1 
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    
    /**
     * 
     * @return the first answer
     */
    public String getAnswer1() {
        return answer1;
    }
    
    /**
     * sets the second answer
     * @param answer2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    
    /**
     * 
     * @return the second answer
     */
    public String getAnswer2() {
        return answer2;
    }
    
    /**
     * sets the third answer
     * @param answer3 
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    
    /**
     * 
     * @return the third answer
     */
    public String getAnswer3() {
        return answer3;
    }
    
    /**
     * sets the list of the first questions
     * @param securityQuestions1 
     */
    public void setSecurityQuestions1(List<SecurityQuestion> securityQuestions1) {
        this.securityQuestions1 = securityQuestions1;
    }
    
    /**
     * 
     * @return the list of the first questions
     */
    public List<SecurityQuestion> getSecurityQuestions1() {
        return securityQuestions1;
    }
    
    /**
     * sets the list of the second questions
     * @param securityQuestions2 
     */
    public void setSecurityQuestions2(List<SecurityQuestion> securityQuestions2) {
        this.securityQuestions2 = securityQuestions2;
    }
    
    /**
     * 
     * @return the list of the second questions
     */
    public List<SecurityQuestion> getSecurityQuestions2() {
        return securityQuestions2;
    }
    
    /**
     * sets the list of the third questions
     * @param securityQuestions3 
     */
    public void setSecurityQuestions3(List<SecurityQuestion> securityQuestions3) {
        this.securityQuestions3 = securityQuestions3;
    }
    
    /**
     * 
     * @return the list of the third questions
     */
    public List<SecurityQuestion> getSecurityQuestions3() {
        return securityQuestions3;
    }

    /**
     * sets the confirmation of the password
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     *
     * @return the confirmation of the password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * sets the first name of the registering user
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return the first name of the registering user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the last name of the registering user
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return the last name of the registering user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set the email address of the registering user
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * @return the email address of the registering user
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * sets the password of the registering user
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return the password of the registering user
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the city of the registering user
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return the city of the registering user
     */
    public String getCity() {
        return city;
    }

    /**
     * sets the state of the registering user
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return the state of the registering user
     */
    public String getState() {
        return state;
    }

    /**
     * sets the country of the registering user
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return the country of the registering user
     */
    public String getCountry() {
        return country;
    }
    
    @PostConstruct
    public void init() {
        initQuestions();
    }
    
    /**
     * initialize the security questions
     */
    public void initQuestions() {
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            setSecurityQuestions1(new ArrayList(SecurityQuestionHelper.getAllSecurityQuestions(dbUser)));
            setSecurityQuestions2(new ArrayList(SecurityQuestionHelper.getAllSecurityQuestions(dbUser)));
            setSecurityQuestions3(new ArrayList(SecurityQuestionHelper.getAllSecurityQuestions(dbUser)));
        }
    }

    /**
     * registers the user
     */
    public void registerUser() {
        Boolean errorFound = false;
        
        //make sure its a valid email, else make sure its a valid phone number 
        Boolean validEmail;
        Boolean validPhoneNum;
        EmailValidator validator = new EmailValidator();
        validEmail = validator.validate(getEmailAddress().toLowerCase());
        validPhoneNum = PhoneNumberValidator.validatePhoneNumber(getEmailAddress());
        if(!validEmail && !validPhoneNum) {
            errorFound = true;
            sendErrorFacesMessage(null, "You must enter a valid email address or phone number!");
        }
        
        //test to make sure the password and confirm password match
        if(!getPassword().equals(getConfirmPassword())) {
            errorFound = true;
            sendErrorFacesMessage(null, "The password/confirm password does not match!");
        }
        
        try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            //test if user exists by email
            if(validEmail) {
                if (UserHelper.getUserExists(dbUser, getEmailAddress())) {
                    System.out.println("In database. somethings wrong");
                    errorFound = true;
                    sendErrorFacesMessage(null, "A user with the email address you gave already exists.");
                }
            }
            
            //test if user exists by phone
            if(validPhoneNum) {
                String phoneNum = getEmailAddress().replaceAll("[\\D]", "");
                if(UserHelper.getUserExistsByPhoneNum(dbUser, phoneNum)) {
                    errorFound = true;
                    sendErrorFacesMessage(null, "A user with the phone number you gave already exists.");
                }
            }
        }
        
        if (!errorFound) {
            System.out.println("Not in database");
            
            try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                getUserBean().setUser(UserHelper.signUpUser(dbUser, getEmailAddress(), getPassword(), getFirstName(), getLastName(), getCity(), getState(), getCountry()));
                
                User user = getUserBean().getUser();
                //save user question information 1
                SecurityQuestionAnswer sqa1 = new SecurityQuestionAnswer(user.getLongId(), Long.parseLong(getSelectedQuestionId1()), PasswordHash.createHash(getAnswer1()));
                SecurityQuestionAnswerHelper.saveSecurityQuestionAnswer(dbUser, sqa1);
                
                //save user question information 2
                SecurityQuestionAnswer sqa2 = new SecurityQuestionAnswer(user.getLongId(), Long.parseLong(getSelectedQuestionId2()), PasswordHash.createHash(getAnswer2()));
                SecurityQuestionAnswerHelper.saveSecurityQuestionAnswer(dbUser, sqa2);
                
                //save user question information 3
                SecurityQuestionAnswer sqa3 = new SecurityQuestionAnswer(user.getLongId(), Long.parseLong(getSelectedQuestionId3()), PasswordHash.createHash(getAnswer3()));
                SecurityQuestionAnswerHelper.saveSecurityQuestionAnswer(dbUser, sqa3);
                                
            } catch(InvalidKeySpecException | NoSuchAlgorithmException e) {
                LOGGER.error(e);
            }
            
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(getUserBean().getContextPath() + "/options");
            } catch(IOException e) {
                LOGGER.error(e);
            }
        }
    }
    
    /**
     * updates question 2 and 3 based on the selection of question 1
     */
    public void updateFromQuestion1() {
        
        //remove question for part 2
        if(getSelectedQuestionId1()!=null && !getSelectedQuestionId1().isEmpty()) {
            Boolean question2Found = false;
            SecurityQuestion question2Remove = null;
            for(SecurityQuestion question:getSecurityQuestions2()) {
                if(getSelectedQuestionId1().equals(question.getLongId().toString())) {
                    question2Remove = question;
                    question2Found = true;
                    break;
                }
            }
            if(question2Found && question2Remove!=null) {
                getSecurityQuestions2().remove(question2Remove);
            }
        
            //remove question for part 3
            Boolean question3Found = false;
            SecurityQuestion question3Remove = null;
            for(SecurityQuestion question:getSecurityQuestions3()) {
                if(getSelectedQuestionId1().equals(question.getLongId().toString())) {
                    question3Remove = question;
                    question3Found = true;
                    break;
                }
            }
            if(question3Found && question3Remove!=null) {
                getSecurityQuestions3().remove(question3Remove);
            }
        }
    }
    
    /**
     * updates question 1 and 3 based on the selection of question 1
     */
    public void updateFromQuestion2() {
        
        //remove question for part 3
        if(getSelectedQuestionId2()!=null && !getSelectedQuestionId2().isEmpty()) {
            Boolean question3Found = false;
            SecurityQuestion question3Remove = null;
            for(SecurityQuestion question:getSecurityQuestions3()) {
                if(getSelectedQuestionId2().equals(question.getLongId().toString())) {
                    question3Remove = question;
                    question3Found = true;
                    break;
                }
            }
            if(question3Found && question3Remove!=null) {
                getSecurityQuestions3().remove(question3Remove);
            }
        
            //remove question for part 1
            Boolean question1Found = false;
            SecurityQuestion question1Remove = null;
            for(SecurityQuestion question:getSecurityQuestions1()) {
                if(getSelectedQuestionId2().equals(question.getLongId().toString())) {
                    question1Remove = question;
                    question1Found = true;
                    break;
                }
            }
            if(question1Found && question1Remove!=null) {
                getSecurityQuestions1().remove(question1Remove);
            }
        }
    }
    
    /**
     * updates question 1 and 2 based on the selection of question 1
     */
    public void updateFromQuestion3() {
        
        //remove question for part 1
        if(getSelectedQuestionId3()!=null && !getSelectedQuestionId3().isEmpty()) {
            Boolean question3Found = false;
            SecurityQuestion question3Remove = null;
            for(SecurityQuestion question:getSecurityQuestions1()) {
                if(getSelectedQuestionId3().equals(question.getLongId().toString())) {
                    question3Remove = question;
                    question3Found = true;
                    break;
                }
            }
            if(question3Found && question3Remove!=null) {
                getSecurityQuestions1().remove(question3Remove);
            }
        
            //remove question for part 2
            Boolean question2Found = false;
            SecurityQuestion question2Remove = null;
            for(SecurityQuestion question:getSecurityQuestions2()) {
                if(getSelectedQuestionId3().equals(question.getLongId().toString())) {
                    question2Remove = question;
                    question2Found = true;
                    break;
                }
            }
            if(question2Found && question2Remove!=null) {
                getSecurityQuestions2().remove(question2Remove);
            }
        }
    }
}
