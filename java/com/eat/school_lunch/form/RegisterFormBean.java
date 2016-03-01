/* 
 * Copyright (c) 2015, Darryl Dover
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.eat.school_lunch.form;

import com.eat.school_lunch.adult.helper.FormHouseholdAdultHelper;
import com.eat.school_lunch.assist_prog.helper.AssistanceProgramHelper;
import com.eat.school_lunch.assist_prog.helper.FormAssistanceProgramHelper;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseManagedBean;
import com.eat.school_lunch.child.helper.FormHouseholdChildHelper;
import com.eat.school_lunch.child.helper.HouseholdChildHelper;
import com.eat.school_lunch.comparator.CityDescriptionComparator;
import com.eat.school_lunch.form.helper.FormHelper;
import com.eat.school_lunch.householdAdult.helper.HouseholdAdultHelper;
import com.eat.school_lunch.income.ChildIncomeHelper;
import com.eat.school_lunch.income.IncomeHowOftenHelper;
import com.eat.school_lunch.location.LocationHelper;
import com.eat.school_lunch.model.AssistanceProgram;
import com.eat.school_lunch.model.ChildIncome;
import com.eat.school_lunch.model.City;
import com.eat.school_lunch.model.Form;
import com.eat.school_lunch.model.FormAssistanceProgram;
import com.eat.school_lunch.model.FormHouseholdAdult;
import com.eat.school_lunch.model.FormHouseholdChild;
import com.eat.school_lunch.model.HouseholdAdult;
import com.eat.school_lunch.model.HouseholdChild;
import com.eat.school_lunch.model.IncomeHowOften;
import com.eat.school_lunch.model.Region;
import com.eat.school_lunch.model.RegionCity;
import com.eat.school_lunch.model.User;
import com.eat.school_lunch.model.UserAddress;
import com.eat.school_lunch.user.helper.UserHelper;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "registerFormBean")
@ViewScoped
public class RegisterFormBean extends BaseManagedBean implements Serializable {
    
    private List<HouseholdChild> children;
    private Boolean inEditMode = false;
    private Form form;
    private Boolean usingSNAP = false;
    private Boolean usingTANF = false;
    private Boolean usingFDPIR = false;
    private List<AssistanceProgram> assistancePrograms;
    private AssistanceProgram assistInfo;
    private List<FormAssistanceProgram> formAssistancePrograms;
    private Integer progressBarValue = 0;
    private Integer activeIndex = 0;
    private String streetAddress;
    private String selectedRegionName;
    private List<City> cities;
    private List<Region> regions;
    private String selectedCityName;
    private String apartNum;
    private String zipcode;
    private String userPhoneNum;
    private String userEmail;
    private String userFullName;
    private String signature;
    private Integer totalLevels = 5;
    private UserAddress userAddress;
    private boolean errorOccurred = false;
    private int currentLevel = 1;
    private Boolean skippedAssistanceProgs = false;
    private Boolean skippedAllIncome = false;
    private List<HouseholdChildEarnedIncome> householdChildren;
    private List<ChildIncome> childrenIncome;
    private List<IncomeHowOften> howOftenIncome;
    private List<HouseholdAdult> householdAdults;
    
    /**
     * sets the list of adult household members
     * @param householdAdults 
     */
    public void setHouseholdAdults(List<HouseholdAdult> householdAdults) {
        this.householdAdults = householdAdults;
    }
    
    /**
     * 
     * @return the list of adult household members
     */
    public List<HouseholdAdult> getHouseholdAdults() {
        return householdAdults;
    }
    
    /**
     * sets the list to choose from of how often someone receives income
     * @param howOftenIncome 
     */
    public void setHowOftenIncome(List<IncomeHowOften> howOftenIncome) {
        this.howOftenIncome = howOftenIncome;
    }
    
    /**
     * 
     * @return the list to choose from of how often someone receives income
     */
    public List<IncomeHowOften> getHowOftenIncome() {
        return howOftenIncome;
    }
    
    /**
     * sets the list of household children
     * @param householdChildren 
     */
    public void setHouseholdChildren(List<HouseholdChildEarnedIncome> householdChildren) {
        this.householdChildren = householdChildren;
    }
    
    /**
     * 
     * @return the list of household children
     */
    public List<HouseholdChildEarnedIncome> getHouseholdChildren() {
        return householdChildren;
    }
    
    /**
     * sets the list of children and their income
     * @param childrenIncome 
     */
    public void setChildrenIncome(List<ChildIncome> childrenIncome) {
        this.childrenIncome = childrenIncome;
    }
    
    /**
     * 
     * @return the list of children and their income
     */
    public List<ChildIncome> getChildrenIncome() {
        return childrenIncome;
    }
    
    /**
     * sets whether the income section should be skipped
     * @param skippedAllIncome 
     */
    public void setSkippedAllIncome(Boolean skippedAllIncome) {
        this.skippedAllIncome = skippedAllIncome;
    }
    
    /**
     * 
     * @return whether the income section should be skipped
     */
    public Boolean getSkippedAllIncome() {
        return skippedAllIncome;
    }
    
    /**
     * sets whether the user skipped the assistance program part
     * @param skippedAssistanceProgs 
     */
    public void setSkippedAssistanceProgs(Boolean skippedAssistanceProgs) {
        this.skippedAssistanceProgs = skippedAssistanceProgs;
    }
    
    /**
     * 
     * @return whether the user skipped the assistance program part
     */
    public Boolean getSkippedAssistanceProgs() {
        return skippedAssistanceProgs;
    }

    /**
     *
     * @return the current level
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * sets the current level
     *
     * @param currentLevel
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    /**
     * sets whether an error occurred
     * @param errorOccurred 
     */
    public void setErrorOccurred(boolean errorOccurred) {
        this.errorOccurred = errorOccurred;
    }
    
    /**
     * 
     * @return whether an error occurred
     */
    public boolean getErrorOccurred() {
        return errorOccurred;
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
    
    public void setTotalLevels(Integer totalLevels) {
        this.totalLevels = totalLevels;
    }
    
    public Integer getTotalLevels() {
        return totalLevels;
    }
    
    /**
     * sets the user's signature
     * @param signature 
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    /**
     * 
     * @return the user's signature
     */
    public String getSignature() {
        return signature;
    }
    
    /**
     * sets the user's full name
     * @param userFullName 
     */
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    
    /**
     * 
     * @return the user's full name
     */
    public String getUserFullName() {
        return userFullName;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }
    
    public String getUserPhoneNum() {
        return userPhoneNum;
    }
    
    /**
     * sets the zip code
     * @param zipcode 
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    /**
     * 
     * @return the zip code
     */
    public String getZipcode() {
        return zipcode;
    }
    
    /**
     * sets the apartment number
     * @param apartNum 
     */
    public void setApartNum(String apartNum) {
        this.apartNum = apartNum;
    }
    
    /**
     * 
     * @return the apartment number
     */
    public String getApartNum() {
        return apartNum;
    }
    
    /**
     * sets the selected city
     * @param selectedCityName  
     */
    public void setSelectedCityName(String selectedCityName) {
        this.selectedCityName = selectedCityName;
    }
    
    /**
     * 
     * @return the selected region
     */
    public String getSelectedCityName() {
        return selectedCityName;
    }
    
    /**
     * sets the regions of the location
     * @param regions 
     */
    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
    
    /**
     * 
     * @return the regions of the location
     */
    public List<Region> getRegions() {
        return regions;
    }
    
    /**
     * sets the selected region
     * @param selectedRegionName 
     */
    public void setSelectedRegionName(String selectedRegionName) {
        this.selectedRegionName = selectedRegionName;
    }
    
    /**
     * 
     * @return the selected region
     */
    public String getSelectedRegionName() {
        return selectedRegionName;
    }
    
    /**
     * sets the address of the user's street
     * @param streetAddress 
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    /**
     * 
     * @return the address of the user's street
     */
    public String getStreetAddress() {
        return streetAddress;
    }
    
    /**
     * sets the active index of the tab view
     * @param activeIndex 
     */
    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    /**
     * 
     * @return the active index of the tab view
     */
    public Integer getActiveIndex() {
        return activeIndex;
    }
    
    /**
     * sets the value of the progress bar
     * @param progressBarValue 
     */
    public void setProgressBarValue(Integer progressBarValue) {
        this.progressBarValue = progressBarValue;
    }
    
    /**
     * 
     * @return the value of the progress bar
     */
    public Integer getProgressBarValue() {
        return progressBarValue;
    }
    
    /**
     * sets the form assistance programs
     * @param formAssistancePrograms 
     */
    public void setFormAssistancePrograms(List<FormAssistanceProgram> formAssistancePrograms) {
        this.formAssistancePrograms = formAssistancePrograms;
    }
    
    /**
     * 
     * @return the form assistance programs
     */
    public List<FormAssistanceProgram> getFormAssistancePrograms() {
        return formAssistancePrograms;
    }
    
    /**
     * sets the assistance program to display information about in a dialog
     * @param assistInfo 
     */
    public void setAssistInfo(AssistanceProgram assistInfo) {
        this.assistInfo = assistInfo;
    }
    
    /**
     * 
     * @return the assistance program to display information about in a dialog
     */
    public AssistanceProgram getAssistInfo() {
        return assistInfo;
    }
    
    /**
     * sets the list of available assistance programs
     * @param assistancePrograms 
     */
    public void setAssistancePrograms(List<AssistanceProgram> assistancePrograms) {
        this.assistancePrograms = assistancePrograms;
    }
    
    /**
     * 
     * @return the list of available assistance programs
     */
    public List<AssistanceProgram> getAssistancePrograms() {
        return assistancePrograms;
    }
    
    /**
     * sets whether the user or anyone in their household participated in the SNAP assistance program
     * @param usingSNAP
     */
    public void setUsingSNAP(Boolean usingSNAP) {
        this.usingSNAP = usingSNAP;
    }
    
    /**
     * 
     * @return whether the user or anyone in their household participated in the SNAP assistance program
     */
    public Boolean getUsingSNAP() {
        return usingSNAP;
    }
    
    /**
     * sets whether the user or anyone in their household participated in the TANF assistance program
     * @param usingTANF
     */
    public void setUsingTANF(Boolean usingTANF) {
        this.usingTANF = usingTANF;
    }
    
    /**
     * 
     * @return whether the user or anyone in their household participated in the TANF assistance program
     */
    public Boolean getUsingTANF() {
        return usingTANF;
    }
    
    /**
     * sets whether the user or anyone in their household participated in the FDPIR assistance program
     * @param usingFDPIR
     */
    public void setUsingFDPIR(Boolean usingFDPIR) {
        this.usingFDPIR = usingFDPIR;
    }
    
    /**
     * 
     * @return whether the user or anyone in their household participated in the FDPIR assistance program
     */
    public Boolean getUsingFDPIR() {
        return usingFDPIR;
    }
    
    /**
     * sets the form
     * @param form 
     */
    public void setForm(Form form) {
        this.form = form;
    }
    
    /**
     * 
     * @return the form
     */
    public Form getForm() {
        return form;
    }
    
    /**
     * sets whether its in edit mode
     * @param inEditMode 
     */
    public void setInEditMode(Boolean inEditMode) {
        this.inEditMode = inEditMode;
    }
    
    /**
     * 
     * @return whether its in edit mode
     */
    public Boolean getInEditMode() {
        return inEditMode;
    }
    
    /**
     * sets the list of registered children in the form
     * @param children 
     */
    public void setChildren(List<HouseholdChild> children) {
        this.children = children;
    }
    
    /**
     * 
     * @return the list of registered children in the form
     */
    public List<HouseholdChild> getChildren() {
        return children;
    }
    
    /**
     * initialize the info
     */
    @PostConstruct
    public void init() {
        
//        System.out.println("Current Level: " + getCurrentLevel());
//        setCurrentLevel(0);
        
        if(getUserBean().getUser()!=null) {
            
            setUserFullName(getUserBean().getUser().getUserFullName());
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {

                initForm(dbUser);                
            }
            initRegionCity();
            
            if(getInEditMode()) {
                getRequestContext().execute("PF('welcomeBackDlg').show();");
            } else {
                getRequestContext().execute("PF('welcomeDlg').show();");
            }

            String email;
            if(getUserBean().getUser().getEmailAddress()!=null && !getUserBean().getUser().getEmailAddress().isEmpty()) {
                email = getUserBean().getUser().getEmailAddress();
            } else {
                email = null;
            }
            setUserEmail(email);

            String phoneNum;
            if(getUserBean().getUser().getPhoneNumber()!=null && !getUserBean().getUser().getPhoneNumber().isEmpty()) {
                phoneNum = getUserBean().getUser().getPhoneNumber();
            } else {
                phoneNum = null;
            }
            setUserPhoneNum(phoneNum);
        }
    }
    
    /**
     * initializes the region city
     */
    public void initRegionCity() {
        
        if (getUserBean().getUser().getUserAddressId() != null) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                setUserAddress(UserHelper.getUserAddressById(dbUser, getUserBean().getUser().getUserAddressId()));
            }
            System.out.println("Address not null");
            setStreetAddress(getUserAddress().getStreetAddress());
            setSelectedRegionName(getUserAddress().getRegion().getDescription());
            System.out.println("Region name: " + getSelectedRegionName());
            onRegionChange();
            System.out.println("Region after method: " + getSelectedRegionName());
            setSelectedCityName(getUserAddress().getCity().getDescription());
            System.out.println("City id: " + getSelectedCityName());
            setZipcode(getUserAddress().getZipcode());
            setApartNum(getUserAddress().getAptNum());
        }

        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            setRegions(new ArrayList(LocationHelper.getAllRegions(dbUser)));
        }
        
        System.out.println("Num of regions: " + getRegions().size());
    }
    
    /**
     * initialize the form information
     * @param dbUser 
     */
    public void initForm(BaseDBSessionBean dbUser) {
        
        setChildren(new ArrayList<HouseholdChild>());
        if(getUserBean().getForm()==null) {
            setForm(new Form());
            getForm().setUserId(getUserBean().getUser().getLongId());
            getForm().setDateStarted(new Timestamp(new Date().getTime()));
            getForm().setLastStepSaved(1L);
            FormHelper.saveForm(dbUser, getForm());
            getUserBean().setForm(getForm());
            setFormAssistancePrograms(new ArrayList());
//            setCurrentLevel(1);
            setInEditMode(false);
        } else {
//            setCurrentLevel(0);
            setInEditMode(true);
            //get the form information
            setForm(FormHelper.getFormByUserId(dbUser, getUserBean().getUser().getLongId()));

            //get the household children information
            initHouseholdChildDB(dbUser);
            initHouseholdAdults(dbUser);
            initChildIncome(dbUser);

            //get assistance programs belonging to this form if any
            setFormAssistancePrograms(new ArrayList(FormAssistanceProgramHelper.getFormAssistanceProgramByFormId(dbUser, getForm().getLongId())));
            
            setSignature(getForm().getSignature());
        }
        
        if(getChildren().isEmpty()) {
            addChild();
        }
        
        populateAssistancePrograms(dbUser);
        initializeHowOftenIncome(dbUser);
    }
    
    /**
     * initializes the list of how often income
     * @param dbUser 
     */
    public void initializeHowOftenIncome(BaseDBSessionBean dbUser) {
        
        setHowOftenIncome(new ArrayList());
        getHowOftenIncome().addAll(new ArrayList(IncomeHowOftenHelper.getAll(dbUser)));
    }
    
    /**
     * initializes the list of household children
     * @param dbUser 
     */
    public void initHouseholdChildDB(BaseDBSessionBean dbUser) {
        
        setChildren(new ArrayList());
        List<FormHouseholdChild> formHouseholdChildren = new ArrayList(FormHouseholdChildHelper.getFormHouseholdChildrenByFormId(dbUser, getForm().getLongId()));
        for(FormHouseholdChild fhc:formHouseholdChildren) {
            getChildren().add(HouseholdChildHelper.getHouseholdChildById(dbUser, fhc.getHouseholdChildId()));
        }
    }
    
    /**
     * initializes the list of household adults
     * @param dbUser 
     */
    public void initHouseholdAdults(BaseDBSessionBean dbUser) {
        
        setHouseholdAdults(new ArrayList());
        List<FormHouseholdAdult> formHouseholdAdults = new ArrayList(FormHouseholdAdultHelper.getFormHouseholdAdultsByFormId(dbUser, getForm().getLongId()));
        for(FormHouseholdAdult adult:formHouseholdAdults) {
            getHouseholdAdults().add(HouseholdAdultHelper.getHouseholdAdultById(dbUser, adult.getHouseholdAdultId()));
        }
        
        //if none of the adults are there, need to include the user themselves
        if(getHouseholdAdults().isEmpty()) {
            HouseholdAdult adult = new HouseholdAdult();
            adult.setFirstName(getUserBean().getUser().getFirstName());
            adult.setLastName(getUserBean().getUser().getLastName());
            HouseholdAdultHelper.saveHouseholdAdult(dbUser, adult);
            //save to the form also
            FormHouseholdAdult fha = new FormHouseholdAdult();
            fha.setFormId(getForm().getLongId());
            fha.setHouseholdAdultId(adult.getLongId());
            FormHouseholdAdultHelper.saveFormHouseholdAdult(dbUser, fha);
            getHouseholdAdults().add(adult);
        }
    }
    
    /**
     * initializes the list of child income objects
     * @param dbUser 
     */
    public void initChildIncome(BaseDBSessionBean dbUser) {
        
        setChildrenIncome(new ArrayList());
        for(HouseholdChild hhc:getChildren()) {
            ChildIncome childIncome = ChildIncomeHelper.getChildIncomeByHouseholdChildId(dbUser, hhc.getLongId());
            if(childIncome != null) {
                if(childIncome.getIncomeHowOftenId()!=null) {
                    childIncome.setIncomeChosenLabel(childIncome.getIncomeHowOften(dbUser).getLabel());
                }
            } else {
                childIncome = new ChildIncome();
                childIncome.setHouseholdChildId(hhc.getLongId());
                //save the information 
                ChildIncomeHelper.saveChildIncome(dbUser, childIncome);
            }
            getChildrenIncome().add(childIncome);
        }
    }
    
    /**
     * initializes the list of household children
     */
    public void initHouseholdChild() {
        
        setChildren(new ArrayList());
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            List<FormHouseholdChild> formHouseholdChildren = new ArrayList(FormHouseholdChildHelper.getFormHouseholdChildrenByFormId(dbUser, getForm().getLongId()));
            for(FormHouseholdChild fhc:formHouseholdChildren) {
                getChildren().add(HouseholdChildHelper.getHouseholdChildById(dbUser, fhc.getHouseholdChildId()));
            }
        }
    }
    
    /**
     * adds a child the list
     */
    public void addChild() {
        HouseholdChild child = new HouseholdChild();
        getChildren().add(child);    
    }
    
    /**
     * adds the household adult
     */
    public void addAdult() {
        HouseholdAdult adult = new HouseholdAdult();
        getHouseholdAdults().add(adult);
    }
    
    /**
     * 
     * @param child
     * @return whether the household child object is empty
     */
    public Boolean isEmpty(HouseholdChild child) {
        //check to make sure the first name exists
        if(child.getFirstName()!=null && !child.getFirstName().isEmpty()) {
            return false;
        }
        
        //check to make sure the middle initial exists
        if(child.getLastName()!=null && !child.getLastName().isEmpty()) {
            return false;
        }
        
        //check to make sure the last name exists
        if(child.getLastName()!=null && !child.getLastName().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public int processAdults() {
        System.out.println("Process adults");
        int level = 6;
        Boolean errorFound = false;
        int i=1;
        StringBuilder stmt = new StringBuilder();
        for(HouseholdAdult adult:getHouseholdAdults()) {
            
            if(adult.getFirstName()==null || adult.getFirstName().isEmpty()) {
                errorFound = true;
                stmt.append("Row ").append(i).append("'s first name is missing.<br />");
            }
            
            if(adult.getLastName()==null || adult.getLastName().isEmpty()) {
                errorFound = true;
                stmt.append("Row ").append(i).append("'s last name is missing.<br />");
            }
            i++;
        }
        
        if(!errorFound) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                
                for(HouseholdAdult adult:getHouseholdAdults()) {
                    //save the children associated with this form

                    //save the household adult
                    HouseholdAdultHelper.saveHouseholdAdult(dbUser, adult);
                    //save the form household adult association
                    FormHouseholdAdult fha = FormHouseholdAdultHelper.getFormHouseholdAdultByAdultId(dbUser, adult.getLongId());
                    if(fha == null) {
                        fha = new FormHouseholdAdult();
                        fha.setFormId(getForm().getLongId());
                        fha.setHouseholdAdultId(adult.getLongId());
                        FormHouseholdAdultHelper.saveFormHouseholdAdult(dbUser, fha);
                    }
                }
            }
            
            level++;
        } else {
            sendErrorFacesMessage(null, stmt.toString());
        }
        return level;
    }
    
    /**
     * process children and remove the empty ones
     */
    public int processChildren() {
                
        System.out.println("Process Children");
        int level = 3;
        
        Boolean errorFound = false;
        int i = 1;
        StringBuilder stmt = new StringBuilder();
        for(HouseholdChild child:getChildren()) {
            
            if(child.getFirstName()==null || child.getFirstName().isEmpty()) {
                errorFound = true;
                stmt.append("Row ").append(i).append("'s first name is missing.<br />");
            }
            
            if(child.getMiddleInitial() == null || child.getMiddleInitial().isEmpty()) {
                errorFound = true;
                stmt.append("Row ").append(i).append("'s middle initial is missing.<br />");
            }
            
            if(child.getLastName() == null || child.getLastName().isEmpty()) {
                errorFound = true;
                stmt.append("Row ").append(i).append("'s last name is missing.<br />");
            }
            i++;
        }
        
        if(!errorFound) {
            Integer numOfFHMR = 0;
            
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {

                for(HouseholdChild child:getChildren()) {
                    System.out.println("HouseholdChild: " + child);
                }            

                //save the house children to the form
                for(HouseholdChild child:getChildren()) {
                    //save the children associated with this form

                    //save the household child
                    HouseholdChildHelper.saveHouseholdChild(dbUser, child);
                    //save the form household child association
                    FormHouseholdChild fhc = FormHouseholdChildHelper.getFormHouseholdChildByChildId(dbUser, child.getLongId());
                    if(fhc == null) {
                        fhc = new FormHouseholdChild();
                        fhc.setFormId(getForm().getLongId());
                        fhc.setHouseholdChildId(child.getLongId());
                        FormHouseholdChildHelper.saveFormHouseHoldChild(dbUser, fhc);
                    }
                                        
                    if(child.getIsFosterChild() || child.getIsHMR()) {
                        numOfFHMR++;
                    }
                }
                System.out.println("Num of child: " + getChildren().size());
                System.out.println("Num of hmr: " + numOfFHMR);
                //if all the children are foster children or their homeless, runaway or migrant, they go all the way to
                //confirmation page
                if(numOfFHMR == getChildren().size()) {
                    level = 6;
                    setSkippedAssistanceProgs(true);
                    setSkippedAllIncome(true);
                } else {

                    //get the list of active assistance programs
                    populateAssistancePrograms(dbUser);
                    //add the current level
                    level = 4;
                }
                
                initChildIncome(dbUser);
            }                        
        } else {
            sendErrorFacesMessage(null, stmt.toString());
        }
        
        return level;
    }
    
//    public void saveChildInfo() {
//        System.out.println("Process Children");
//        
//        Boolean errorFound = false;
//        int i = 1;
//        StringBuilder stmt = new StringBuilder();
//        for(HouseholdChild child:getChildren()) {
//            
//            if(child.getFirstName()==null || child.getFirstName().isEmpty()) {
//                errorFound = true;
//                stmt.append("Row ").append(i).append("'s first name is missing.<br />");
//            }
//            
//            if(child.getMiddleInitial() == null || child.getMiddleInitial().isEmpty()) {
//                errorFound = true;
//                stmt.append("Row ").append(i).append("'s middle initial is missing.<br />");
//            }
//            
//            if(child.getLastName() == null || child.getLastName().isEmpty()) {
//                errorFound = true;
//                stmt.append("Row ").append(i).append("'s last name is missing.<br />");
//            }
//            i++;
//        }
//        
//        if(!errorFound) {
//            Integer numOfFHMR = 0;
//            
//            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
//
//                for(HouseholdChild child:getChildren()) {
//                    System.out.println("HouseholdChild: " + child);
//                }            
//
//                //save the house children to the form
//                for(HouseholdChild child:getChildren()) {
//                    //save the children associated with this form
//
//                    //save the household child
//                    HouseholdChildHelper.saveHouseholdChild(dbUser, child);
//                    //save the form household child association
//                    FormHouseholdChild fhc = FormHouseholdChildHelper.getFormHouseholdChildByChildId(dbUser, child.getLongId());
//                    if(fhc == null) {
//                        fhc = new FormHouseholdChild();
//                        fhc.setFormId(getForm().getLongId());
//                        fhc.setHouseholdChildId(child.getLongId());
//                        FormHouseholdChildHelper.saveFormHouseHoldChild(dbUser, fhc);
//                    }
//                                        
//                    if(child.getIsFosterChild() || child.getIsHMR()) {
//                        numOfFHMR++;
//                    }
//                }
//                System.out.println("Num of child: " + getChildren().size());
//                System.out.println("Num of hmr: " + numOfFHMR);
//                //if all the children are foster children or their homeless, runaway or migrant, they go all the way to
//                //confirmation page
//                if(numOfFHMR == getChildren().size()) {
////                    setCurrentLevel(6);
////                    setFinalChildren(new ArrayList(getChildren()));
//                } else {
//
//                    //get the list of active assistance programs
//                    populateAssistancePrograms(dbUser);
//                }
//            }
//        } else {
//            sendErrorFacesMessage(null, stmt.toString());
//        }
//    }
    
    /**
     * sets the list of assistance programs
     * @param dbUser 
     */
    public void populateAssistancePrograms(BaseDBSessionBean dbUser) {
        
        setAssistancePrograms(new ArrayList());
        List<AssistanceProgram> assistProgs = new ArrayList(AssistanceProgramHelper.getActiveAssistancePrograms(dbUser));
        if(!getFormAssistancePrograms().isEmpty()) {
            //go through the list of form assistance programs
            for(FormAssistanceProgram fap:getFormAssistancePrograms()) {
                AssistanceProgram program = AssistanceProgramHelper.getAssistanceProgramById(dbUser, fap.getAssistanceProgramId());
                program.setBeingUsed(true);
                program.setCaseNumber(fap.getCaseNumber());
                getAssistancePrograms().add(program);
            }
            
            //add the programs that not being used so the user can choose from it
            for(AssistanceProgram assistProg:assistProgs) {
                Boolean found = false;
                for(AssistanceProgram ap:getAssistancePrograms()) {
                    if(ap.getProgramAbbr().equals(assistProg.getProgramAbbr())) {
                        found = true;
                    }
                }
                
                if(!found) {
                    getAssistancePrograms().add(assistProg);
                }
            }
        } else {
            getAssistancePrograms().addAll(assistProgs);
        }
        
//        //remove the assistance programs
//        if(!getFormAssistancePrograms().isEmpty()) {
//            FormAssistanceProgramHelper.removeAllFormAssistanceProgramsByFormId(dbUser, getForm().getLongId());
//        }
//        
//        if(getAssistancePrograms().isEmpty()) {
//            setAssistancePrograms(new ArrayList(AssistanceProgramHelper.getActiveAssistancePrograms(dbUser)));
//        }
    }
    
//    public void goBack2ChildList() {
//        goBackLevel();
//        
//        setChildren(new ArrayList());
//        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
//            List<FormHouseholdChild> formHouseholdChildren = new ArrayList(FormHouseholdChildHelper.getFormHouseholdChildrenByFormId(dbUser, getForm().getLongId()));
//            for(FormHouseholdChild fhc:formHouseholdChildren) {
//                getChildren().add(HouseholdChildHelper.getHouseholdChildById(dbUser, fhc.getHouseholdChildId()));
//            }
//        }
//    }
    
//    public void goBackLevel() {
//        subtractCurrentLevel(1);
//    }
//    
//    public void subtractCurrentLevel(int numOfLevels) {
//        setCurrentLevel(getCurrentLevel()-1);
////        Integer tempProgress = getProgressBarValue() - numOfLevels;
//        Integer percent = (int)((getCurrentLevel() * 100.0f) / getTotalLevels());
//        setProgressBarValue(percent);
//    }
    
    /**
     * adds to the current level/step based on the parameter passed
     * @param numOfLevels 
     */
//    public void addCurrentLevel(int numOfLevels) {
//        if(getForm().getLastStepSaved()!=null) {
//            if(Integer.parseInt(getForm().getLastStepSaved().toString()) <= getCurrentLevel()) {
//                getForm().setLastStepSaved(Long.parseLong(String.valueOf(getCurrentLevel())));
//                try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
//                    FormHelper.saveForm(dbUser, getForm());
//                }
//            }
//        } else {
//            getForm().setLastStepSaved(Long.parseLong(String.valueOf(getCurrentLevel())));
//            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
//                FormHelper.saveForm(dbUser, getForm());
//            }
//        }
//        setCurrentLevel(getCurrentLevel()+numOfLevels);
//        System.out.println("Going to level " + getCurrentLevel());
////        System.out.println("Current level");        
////        Integer tempProgress = getProgressBarValue() + numOfLevels;
////        System.out.println("Temp progress: " + tempProgress);
//        Integer percent = (int)((getCurrentLevel() * 100.0f) / getTotalLevels());
//        setProgressBarValue(percent);
//        System.out.println("Progress Bar: " + getProgressBarValue());
//        
//    }
    
//    public void gotoNextLevel() {
//        System.out.println("Goto level region: " + getSelectedRegionName());
//        addCurrentLevel(1);        
//    }
//    
//    /**
//     * go to whatever level passed
//     * @param level 
//     */
//    public void gotoLevel(String level) {
////        System.out.println("");
//        addCurrentLevel(Integer.parseInt(level));
//    }
//    
//    /**
//     * goes to the level of the wizard
//     * @param level 
//     */
//    public void travelToLevel(String level) {
//        setCurrentLevel(Integer.parseInt(level));
//        setInEditMode(true);
//    }
    
    public int processCertify() {
        Boolean errorFound = false;
        int level = 2;
        
        //checks whether the zip code is null or empty
        if(getZipcode()==null || getZipcode().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot your zip code.");
        }
        
        //checks whether the state is null or empty
        if(getSelectedRegionName()==null || getSelectedRegionName().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot to choose a state.");
        }
        
        //checks whether the city is null or empty
        if(getSelectedCityName()==null || getSelectedCityName().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot to choose a city.");
        }
        
        //make sure the statements have been read
        if(!getForm().getNonDiscStmt()) {
            errorFound = true;
            sendErrorFacesMessage(null, "Please acknowledge you read the 'USDA Non-Discrimination Statement' by clicking the checkbox below.");
        }
        
        if(!getForm().getInfoUseStmt()) {
            errorFound = true;
            sendErrorFacesMessage(null, "Please acknowledge you read the 'Use of Information Statement' by clicking the checkbox below.");
        }
        
        if(!getForm().getAttestingStmt()) {
            errorFound = true;
            sendErrorFacesMessage(null, "Please acknowledge you read the 'Attesting Statement' by clicking the checkbox below.");
        }
        
        if(!getForm().getEthnicityStmt()) {
            errorFound = true;
            sendErrorFacesMessage(null, "Please acknowledge you read the 'Ethnicity Statement' by clicking the checkbox below.");
        }
        
        //if no errors found go to the next step
        if(!errorFound) {
            setChildren(new ArrayList());
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                List<FormHouseholdChild> formHouseholdChildren = new ArrayList(FormHouseholdChildHelper.getFormHouseholdChildrenByFormId(dbUser, getForm().getLongId()));
                for(FormHouseholdChild fhc:formHouseholdChildren) {
                    getChildren().add(HouseholdChildHelper.getHouseholdChildById(dbUser, fhc.getHouseholdChildId()));
                }
                
                //if the list of children are empty, then add one
                if(getChildren().isEmpty()) {
                    getChildren().add(new HouseholdChild());
                }
                
                //save address
                UserAddress userAddress;
                Long userAddressId = getUserBean().getUser().getUserAddressId();
                if(userAddressId != null) {
                    userAddress = UserHelper.getUserAddressById(dbUser, userAddressId);
                } else {
                    userAddress = new UserAddress();
                    userAddress.setUserId(getUserBean().getUser().getLongId());
                }
                
                Long countryId = LocationHelper.getCountryByName(dbUser, "United States").getLongId();
                userAddress.setStreetAddress(getStreetAddress());
                userAddress.setCountryId(countryId);
                userAddress.setRegionId(LocationHelper.getRegionByName(dbUser, getSelectedRegionName()).getLongId());
                userAddress.setCityId(LocationHelper.getCityByName(dbUser, getSelectedCityName()).getLongId());
                userAddress.setZipcode(getZipcode());
                userAddress.setAptNum(getApartNum());
                UserHelper.saveUserAddress(dbUser, userAddress);
                
                if(userAddressId==null) {
                    User user = getUserBean().getUser();
                    user.setUserAddressId(userAddress.getLongId());
                    UserHelper.saveUser(dbUser, user);
                }
                
                //save the acknowledgements
                getForm().setNonDiscStmt(true);
                getForm().setInfoUseStmt(true);
                getForm().setAttestingStmt(true);
                getForm().setEthnicityStmt(true);
                
                //save the signature
                getForm().setSignature(getSignature());
                FormHelper.saveForm(dbUser, getForm());
                getUserBean().setForm(getForm());
            }
            level++;
        }
        
        return level;
    }
    
    public void processCertifyNSkip() {
        Boolean errorFound = false;
        
        //checks whether the zip code is null or empty
        if(getZipcode()==null || getZipcode().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot your zip code.");
        }
        
        //checks whether the state is null or empty
        if(getSelectedRegionName()==null || getSelectedRegionName().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot to choose a state.");
        }
        
        //checks whether the city is null or empty
        if(getSelectedCityName()==null || getSelectedCityName().isEmpty()) {
            errorFound = true;
            sendErrorFacesMessage(null, "You forgot to choose a city.");
        }
        
        //if no errors found go to the next step
        if(!errorFound) {
            setChildren(new ArrayList());
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                List<FormHouseholdChild> formHouseholdChildren = new ArrayList(FormHouseholdChildHelper.getFormHouseholdChildrenByFormId(dbUser, getForm().getLongId()));
                for(FormHouseholdChild fhc:formHouseholdChildren) {
                    getChildren().add(HouseholdChildHelper.getHouseholdChildById(dbUser, fhc.getHouseholdChildId()));
                }
                
                //save address
                UserAddress userAddress;
                Long userAddressId = getUserBean().getUser().getUserAddressId();
                if(userAddressId != null) {
                    userAddress = UserHelper.getUserAddressById(dbUser, userAddressId);
                } else {
                    userAddress = new UserAddress();
                    userAddress.setUserId(getUserBean().getUser().getLongId());
                }
                
                Long countryId = LocationHelper.getCountryByName(dbUser, "United States").getLongId();
                userAddress.setStreetAddress(getStreetAddress());
                userAddress.setCountryId(countryId);
                userAddress.setRegionId(LocationHelper.getRegionByName(dbUser, getSelectedRegionName()).getLongId());
                userAddress.setCityId(LocationHelper.getCityByName(dbUser, getSelectedCityName()).getLongId());
                UserHelper.saveUserAddress(dbUser, userAddress);
                
                if(userAddressId==null) {
                    User user = getUserBean().getUser();
                    user.setUserAddressId(userAddress.getLongId());
                    UserHelper.saveUser(dbUser, user);
                }
                
                //save the signature
                getForm().setSignature(getSignature());
                FormHelper.saveForm(dbUser, getForm());
                getUserBean().setForm(getForm());
            }
            
            exit();
            
        }
    }
    
    /**
     * log out the information
     */
    public void exit() {
        getForm().setLastStepSaved(Long.parseLong(String.valueOf(getCurrentLevel())));
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            FormHelper.saveForm(dbUser, getForm());
        }
        
        getUserBean().logoutUser();
        getRequestContext().execute("PF('takeBreakDlg').show();");
    }
    
    /**
     * remove the children in parameter
     * @param children2Remove 
     */
    public void removeChildren(List<HouseholdChild> children2Remove) {
        getChildren().removeAll(children2Remove);
    }
    
    /**
     * process the assistance programs
     */
    public int processAssistPrograms() {
        
        int level = 4;
        Boolean errorFound = false;
        StringBuilder errorStmt = new StringBuilder();
        for(AssistanceProgram assistanceProgram:getAssistancePrograms()) {
            if(assistanceProgram.getBeingUsed() && (assistanceProgram.getCaseNumber()==null || assistanceProgram.getCaseNumber().isEmpty())) {
                errorFound = true;
//                errorStmt.append("You put 'Yes' for ").append(assistanceProgram.getProgramAbbr()).append(", so you must provide a case number").append("<br />");
                errorStmt.append("Please provide a case number for ").append(assistanceProgram.getProgramAbbr()).append(" because you selected 'Yes'.<br />");
                System.out.println("Error found: " + errorStmt.toString());
            }
        }
        
        //if no error found, save the assistance information
        if(!errorFound) {

            Boolean gotoChildIncomeSection = true;
            FormAssistanceProgram fap;
            for(AssistanceProgram assistanceProgram:getAssistancePrograms()) {
                
                try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    fap = FormAssistanceProgramHelper.getFormAssistanceProgramByFormIdAssisProgId(dbUser, getForm().getLongId(), assistanceProgram.getLongId());
                    
                    //if existed and assistance program is not being used anymore
                    if(fap!=null && !assistanceProgram.getBeingUsed()) {
                        FormAssistanceProgramHelper.removeFormAssistanceProgram(dbUser, fap);
                    } else if(fap==null && assistanceProgram.getBeingUsed()) {
                        fap = new FormAssistanceProgram();
                        fap.setFormId(getForm().getLongId());
                        fap.setAssistanceProgramId(assistanceProgram.getLongId());
                        fap.setCaseNumber(assistanceProgram.getCaseNumber());
                        FormAssistanceProgramHelper.saveFormAssistanceProgram(dbUser, fap);
                        gotoChildIncomeSection = false;
                    } else if(fap!=null && assistanceProgram.getBeingUsed()) {
                        FormAssistanceProgramHelper.saveFormAssistanceProgram(dbUser, fap);
                        gotoChildIncomeSection = false;
                    }
                }
            }       
            
            //go to confirmationi page
            if(gotoChildIncomeSection) {
                level++;
            } else {
                level = level + 2;    
                setSkippedAllIncome(true);
            }
        } else {
            sendErrorFacesMessage(null, errorStmt.toString());
        }
        
        return level;
    }
    
    /**
     * when the user clicks on the question mark beside the assistance program, upload information about the assistance program
     * @param assistProgId 
     */
    public void initAssistProgInfo(String assistProgId) {
        
        System.out.println("Here in assistInfo");
        AssistanceProgram assistProg;
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            assistProg = AssistanceProgramHelper.getAssistanceProgramById(dbUser, Long.parseLong(assistProgId));
        }
        
        setAssistInfo(assistProg);
    }
    
    /**
     * 
     * @return the value of the submitted form
     */
    public int submitForm() {
        
        getForm().setDateCompleted(new Timestamp(new Date().getTime()));
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            FormHelper.saveForm(dbUser, getForm());
        }
        
        getUserBean().setForm(getForm());
        
        return 7;
    }
    
    /**
     * add the next index tab
     */
    public void addActiveIndex() {
        setActiveIndex(getActiveIndex() + 1);
    }
    
    /**
     * gets the cities based on the region selected
     */
    public void onRegionChange() {
        
        System.out.println("Made it to region change");
        setCities(new ArrayList());
        List<City> tempCities = new ArrayList();
        
        if(getSelectedRegionName()!=null && !getSelectedRegionName().isEmpty()) {
            System.out.println("Selected Region: " + getSelectedRegionName());
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                Long regionId = LocationHelper.getRegionByName(dbUser, getSelectedRegionName()).getLongId();
                tempCities.addAll(LocationHelper.getCitiesByRegionId(dbUser, regionId, true));
            }
            
            Set<City> citiesSet = new TreeSet<>(new CityDescriptionComparator());
            for(City regionCity:tempCities) {
//                System.out.println("City: " + regionCity.getDescription());
                citiesSet.add(regionCity);
            }
            
            getCities().addAll(citiesSet);
            System.out.println("Region 2: " + getSelectedRegionName());
        } else {
            System.out.println("Region is null");
            setSelectedCityName(null);
            setSelectedRegionName(null);
            setZipcode(null);
        }
    }
    
    /**
     * updates the zip code based on the city selected
     */
    public void onCityChange() {
        if(getSelectedCityName()!=null && !getSelectedCityName().isEmpty()) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                Long regionId = LocationHelper.getRegionByName(dbUser, getSelectedRegionName()).getLongId();
                Long cityId = LocationHelper.getCityByName(dbUser, getSelectedCityName()).getLongId();
                RegionCity rc = LocationHelper.getRegionCityByRegionIdCityId(dbUser, regionId, cityId);
                setZipcode(rc.getZip());
            }
        } else {
            setZipcode(null);
        }
    }
    
    /**
     * sets the list of cities based on the the state selected
     * @param cities 
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    /**
     * 
     * @return the list of cities based on the the state selected
     */
    public List<City> getCities() {
        return cities;
    }
    
    /**
     * removes the household child object information
     * @param item2Remove 
     */
    public void removeChild(String item2Remove) {
        System.out.println("Row to remove: " + item2Remove);
        HouseholdChild hhc = getChildren().remove(Integer.parseInt(item2Remove));
        FormHouseholdChild fhc;
        if(hhc.getId()!=null) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                fhc = FormHouseholdChildHelper.getFormHouseholdChildByFormIdChildId(dbUser, getForm().getLongId(), hhc.getLongId());

                //if found delete the form household child object
                if(fhc!=null) {
                    FormHouseholdChildHelper.deleteFormHouseholdChild(dbUser, fhc);
                }

                //remove the house hold child object
                HouseholdChildHelper.deleteHouseholdChild(dbUser, hhc);
            }
        }
    } 
    
    /**
     * removes the household adult object information
     * @param item2Remove 
     */
    public void removeAdult(String item2Remove) {
        HouseholdAdult adult = getHouseholdAdults().remove(Integer.parseInt(item2Remove));
        FormHouseholdAdult fha;
        if(adult.getId()!=null) {
            if(!Objects.equals(adult.getLongId(), getUserBean().getUser().getLongId())) {
                try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                    fha = FormHouseholdAdultHelper.getFormHouseholdAdultByFormIdAdultId(dbUser, getForm().getLongId(), adult.getLongId());

                    //if found delete the form household adult object
                    if(fha!=null) {
                        FormHouseholdAdultHelper.deleteFormHouseholdAdult(dbUser, fha);
                    }

                    //remove the house hold adult object
                    HouseholdAdultHelper.deleteHouseholdAdult(dbUser, adult);
                }
            } else {
                getHouseholdAdults().add(adult);
                //inform the user they cannot remove themselves
                sendErrorFacesMessage(null, "You can not remove yourself from this list.");
            }
        }
    }
    
    /**
     * process child income info
     * @return the level to go to
     */
    public int processChildIncome() {
        
        Boolean errorFound = false;
        int level = 5;
        for(ChildIncome childIncome:getChildrenIncome()) {
            if(childIncome.getIncome() != null) {
                if(childIncome.getIncomeChosenLabel().isEmpty()) {
                    sendErrorFacesMessage(null, "Please select how often " + childIncome.getHouseholdChild().getFirstName() + " receives income.");
                    errorFound = true;
                }
            }
        }

        //save the child income information
        if(!errorFound) {
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                for(ChildIncome childIncome:getChildrenIncome()) {
                    if(childIncome.getIncomeChosenLabel()!=null && !childIncome.getIncomeChosenLabel().isEmpty()) {
                        childIncome.setIncomeHowOftenId(IncomeHowOftenHelper.getIncomeHowOftenByLabel(dbUser, childIncome.getIncomeChosenLabel()).getLongId());
                    }
                    ChildIncomeHelper.saveChildIncome(dbUser, childIncome);
                }
            }
            
            level++;
        } 
        
        return level;
    }
  
    /**
     * 
     * @param selectLevelEvent
     * @return the level to go to based on the navigation of other methods
     */
    public int handleNavigation(SelectLevelEvent selectLevelEvent) { 
        
        System.out.println("Current Level here: " + selectLevelEvent.getCurrentLevel());
        System.out.println("New Level request: " + selectLevelEvent.getNewLevel());
        int gotoLevel = selectLevelEvent.getCurrentLevel();
        
        if(selectLevelEvent.getCurrentLevel() >= selectLevelEvent.getNewLevel()) {
            System.out.println("go back or stay");
            return selectLevelEvent.getNewLevel();
        } else {
            System.out.println("Go forward");
            switch(selectLevelEvent.getCurrentLevel()) {
                case 1:
                    gotoLevel++;
                    break;
                    
                case 2:
                    System.out.println("here to process certify");
                    gotoLevel = processCertify();
                    break;
                    
                case 3:
                    System.out.println("household children");
                    gotoLevel = processChildren();
                    break;

                case 4:
                    System.out.println("Assistance programs");
                    gotoLevel = processAssistPrograms();
                    break;
                    
                case 5:
                    System.out.println("child income info");
                    gotoLevel = processChildIncome();
                    break;
                    
                case 6:
                    System.out.println("Adults");
                    gotoLevel = processAdults();
                    break;
                    
                case 7:
                    System.out.println("Go to submit1");
                    gotoLevel = submitForm();
                    break;
                    
                case 8:
                    System.out.println("Go to submit2");
                    gotoLevel = submitForm();
                    break;
                    
                default:
                    break;
            }
            setCurrentLevel(selectLevelEvent.getCurrentLevel());
            getForm().setLastStepSaved(Long.parseLong(String.valueOf(getCurrentLevel())));
            try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
                FormHelper.saveForm(dbUser, getForm());
            }
        }
        
        return gotoLevel;  
    }
}
