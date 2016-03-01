package com.eat.school_lunch.base;

import com.ocpsoft.pretty.PrettyContext;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.user.UserBean;
import com.eat.school_lunch.utils.GenPropHelper;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DoverD
 */
public abstract class BaseManagedBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(BaseManagedBean.class);

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    /**
     * sets the user bean
     *
     * @param userBean
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     *
     * @return the user bean
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     *
     * @return the full path of home site
     */
    public String getFullPath() {
        
        String port = "";
        
        String inDevelopment = "";
        try(BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            
            inDevelopment = GenPropHelper.getGenPropByKeyName(dbUser, "inDevelopment").getKeyValue();
        }
        
        if (inDevelopment.equalsIgnoreCase("true")) {
            
            port = ":"+Integer.toString(getPort());
        }
        return getSchema() + "://" + getServerName() + port + getContextPath();
    }
    
    public String getSchema() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getScheme();
    }
    
    public int getPort() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerPort();
    }

    /**
     *
     * @return the context path
     */
    public String getContextPath() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }

    /**
     *
     * @return the remote address
     */
    public String getRemoteAddr() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
    }

    /**
     *
     * @return the host
     */
    public String getRemoteHost() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost();
    }

    /**
     *
     * @return the port of the site
     */
    public int getRemotePort() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getLocalPort();
    }

    /**
     *
     * @return name of the server
     */
    public String getServerName() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
    }

    /**
     *
     * @param parameter
     * @return the parameter passed in throught the request
     */
    public String getParameter(String parameter) {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(parameter);
    }

    /**
     * sends a faces message
     *
     * @param summary
     * @param detail
     */
    public void sendInfoFacesMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    /**
     * sends info message to the message with a certain client id
     *
     * @param summary
     * @param detail
     * @param keyId
     */
    public void sendInfoFacesMessage(String summary, String detail, String keyId) {
        FacesContext.getCurrentInstance().addMessage(keyId, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    /**
     * sends error message to the message
     *
     * @param summary
     * @param detail
     */
    public void sendErrorFacesMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    /**
     * sends error message to the message with a certain client id
     *
     * @param summary
     * @param detail
     * @param keyId
     */
    public void sendErrorFacesMessage(String summary, String detail, String keyId) {
        FacesContext.getCurrentInstance().addMessage(keyId, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    /**
     * sends warning message to the message
     *
     * @param summary
     * @param detail
     */
    public void sendWarnFacesMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    /**
     * sends warning message to the message with a certain client id
     *
     * @param summary
     * @param detail
     * @param keyId
     */
    public void sendWarnFacesMessage(String summary, String detail, String keyId) {
        FacesContext.getCurrentInstance().addMessage(keyId, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public boolean getBrowserIsIE() {
        return getRequest().getHeader("user-agent").contains("MSIE");
    }

    public HttpServletRequest getRequest() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
    }

    public Map<String, String> getFacesContextMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    /**
     * refreshes the page
     */
    public void refreshPage() {
        try {
//            System.out.println("Here here " + getRequest().getContextPath());
            FacesContext.getCurrentInstance().getExternalContext().redirect(getRequest().getRequestURL().toString());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     *
     * @return the full path including the pretty url part
     */
//    public String getCurrentUrl()
//    {
//        return getFullPath() + PrettyContext.getCurrentInstance().getRequestURL().toURL();
//    }
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     *
     * @return whether or not its postback
     */
    public boolean isPostBack() {
        boolean isPostBack = false;

        if (getFacesContext() != null) {
            isPostBack = FacesContext.getCurrentInstance().isPostback();
        }

        return isPostBack;
    }

    public RequestContext getRequestInstance() {
        return RequestContext.getCurrentInstance();
    }

    public ResourceBundle getMessages() {
        return ResourceBundle.getBundle("ApplicationResources");
    }
    
    /**
     * 
     * @return if the device is using mobile
     */
    public boolean getIsMobile() {
        
        UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("User-Agent"));
        return userAgent.getOperatingSystem().getDeviceType().compareTo(DeviceType.MOBILE) == 0;
    }
    
    /**
     * 
     * @return the pretty context
     */
    public PrettyContext getPrettyContext() {
        return PrettyContext.getCurrentInstance();
    }
    
    /**
     * 
     * @return if the user is on a computer
     */
    public Boolean getIsUsingComputer() {
        UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("User-Agent"));
        String deviceType = userAgent.getOperatingSystem().getDeviceType().getName();
        return deviceType.toLowerCase().contains("computer");
    }
    
    /**
     * 
     * @return the request context
     */
    public RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }
}
