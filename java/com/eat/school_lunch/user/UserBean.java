package com.eat.school_lunch.user;

import com.eat.school_lunch.model.User;
import com.ocpsoft.pretty.PrettyContext;
import com.eat.school_lunch.user.helper.UserHelper;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.model.Form;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.servlet.RewriteWrappedRequest;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(UserBean.class);
    private String urlLastVisited;
    private User user;
    private Form form;
    
    /**
     * sets the last url visited, useful for when logging in and going back to
     * the page you were on
     *
     * @param urlLastVisited
     */
    public void setUrlLastVisited(String urlLastVisited) {
        this.urlLastVisited = urlLastVisited;
    }

    /**
     *
     * @return the last url visited, useful for when logging in and going back
     * to the page you were on
     */
    public String getUrlLastVisited() {
        return urlLastVisited!=null?urlLastVisited : getContextPath()+"/";
    }

    /**
     * sets the user
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @return whether the user is logged in
     */
    public boolean getLoggedIn() {
        return user != null;
    }

    /**
     * logs out user and invalidates the session
     */
    public void logoutUser() {
        System.out.println("Logging out user " + getFullPath());
        try (BaseDBSessionBean dbUser = new BaseDBSessionBean()) {
            UserHelper.logOutUser(dbUser, getUser());
            setUser(null);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.invalidateSession();
        }
    }

    /**
     *
     * @return the full path of home site
     */
    public String getFullPath() {
        return getServerName() + getContextPath();
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
     * @return the context path
     */
    public String getContextPath() {
//        System.out.println("Context: " + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
//        System.out.println("Request too: " + RewriteWrappedRequest.getCurrentInstance(getRequest()).getRequestURL());
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }

    public HttpServletRequest getRequest() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
    }
    
    /**
     * sets the url to the page the user is currently on. This is so when the user login/register,
     * they'll be forwarded to the page they were at before hand.
     */
    public void initializeUrl() {
        System.out.println("before login " + RewriteWrappedRequest.getCurrentInstance(getRequest()).getRequestURL());
        String prettyURL = PrettyContext.getCurrentInstance(FacesContext.getCurrentInstance()).getRequestURL().toString();
        System.out.println("Pretty Context: " + prettyURL);
        setUrlLastVisited(getContextPath() + prettyURL);
        System.out.println("new path: " + getUrlLastVisited());
    }
    
    /**
     * 
     * @return the form
     */
    public Form getForm() {
        return form;
    }
    
    /**
     * sets the form
     * @param form 
     */
    public void setForm(Form form) {
        this.form = form;
    }
}
