package com.eat.school_lunch.login_register;

import com.eat.school_lunch.base.BaseManagedBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "loginRegisterBean")
@ViewScoped
public class LoginRegisterBean extends BaseManagedBean implements Serializable {
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    @ManagedProperty(value = "#{registerBean}")
    private RegisterBean registerBean;
    
    private Long activeTab;
    
    /**
     * sets the tab showing
     * @param activeTab 
     */
    public void setActiveTab(Long activeTab) {
        this.activeTab = activeTab;
    }
    
    /**
     * 
     * @return the tab showing
     */
    public Long getActiveTab() {
        return activeTab;
    }
    
    /**
     * sets the registerbean
     * @param registerBean 
     */
    public void setRegisterBean(RegisterBean registerBean) {
        this.registerBean = registerBean;
    }
    
    /**
     * 
     * @return registerBean registerBean
     */
    public RegisterBean getRegisterBean() {
        return registerBean;
    }
    
    /**
     * sets the login bean
     * @param loginBean 
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    /**
     * 
     * @return the login bean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * initialization stuff
     */
    @PostConstruct
    public void init() {
        setActiveTab(1L);
    }
    
    /**
     * sets the position of the tab
     * @param position 
     */
    public void changeTab(String position) {
        setActiveTab(Long.parseLong(position));
    }
}
