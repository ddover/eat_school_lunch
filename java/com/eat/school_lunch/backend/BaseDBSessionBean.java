package com.eat.school_lunch.backend;

import com.eat.school_lunch.base.BaseHelper;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.javalite.activejdbc.Base;

/**
 *
 * @author DoverD
 */
public class BaseDBSessionBean extends BaseHelper implements AutoCloseable, Serializable
{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BaseDBSessionBean.class);
    private String dbDriver;
    private String dbURL;
    private String dbUserName;
    private String dbPassword;
    
    /**
     * instantiates bean based on parameters passed
     * @param dbDriver
     * @param dbURL
     * @param dbUserName
     * @param dbPassword 
     */
    public BaseDBSessionBean(String dbDriver, String dbURL, String dbUserName, String dbPassword)
    {
        this.dbDriver = dbDriver;
        logger.info("dbDriver: " + this.dbDriver);
        this.dbURL = dbURL;
        logger.info("dbURL: " + this.dbURL);
        this.dbUserName = dbUserName;
        logger.info("dbUserName: " + this.dbUserName);
        this.dbPassword = dbPassword;
        logger.info("dbPassword: " + this.dbPassword);
    }
    
    /**
     * instantiates bean
     */
    public BaseDBSessionBean()
    {
        this(BaseHelper.getMessages().getString("dbForName"), BaseHelper.getMessages().getString("dbURL"), BaseHelper.getMessages().getString("dbUser"), BaseHelper.getMessages().getString("dbPassword"));
//        this.dbDriver = ;
//        this.dbURL = ; 
//        this.dbUserName = ;
//        this.dbPassword = ;
    }
    
    /**
     * opens a db connection
     */
    public void open()
    {
        if(!Base.hasConnection())
        {
            Base.open(dbDriver, dbURL, dbUserName, dbPassword);
        }
    }
    
    /**
     * closes the db connection
     */
    @Override
    public void close()
    {
        if(Base.hasConnection())
        {
            Base.close();
        }
    }
}
