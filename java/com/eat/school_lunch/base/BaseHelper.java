package com.eat.school_lunch.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
public class BaseHelper implements Serializable
{
    private static final Logger logger = LogManager.getLogger(BaseHelper.class);
    
    /**
     * 
     * @return the resource bundle
     */
    public static ResourceBundle getMessages()
    {
        return ResourceBundle.getBundle("ApplicationResources");
    }
    
    public static String getProp(String key) {
        Properties props = new Properties();
        String value = null;
        
        try {
            InputStream input = new FileInputStream("C:\\Users\\DoverD\\Documents\\NetBeansProjects\\scheduler\\src\\main\\java\\com\\scheduler\\resources\\Application_Resources.properties");
            props.load(input);
            value = props.getProperty(key);
        }catch(IOException e) {
            logger.error(e);
        }
        
        return value;
    }
    
    /**
     * 
     * @return the context path
     */
    public String getContextPath()
    {
//        System.out.println("Context: " + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath());
//        System.out.println("Request too: " + RewriteWrappedRequest.getCurrentInstance(getRequest()).getRequestURL());
        return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }
}
