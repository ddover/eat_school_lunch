package com.eat.school_lunch.utils;

import com.eat.school_lunch.model.GenProp;
import com.eat.school_lunch.backend.BaseDBSessionBean;
import com.eat.school_lunch.base.BaseHelper;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author DoverD
 */
public class GenPropHelper extends BaseHelper implements Serializable
{
    private static final Logger logger = LogManager.getLogger(GenPropHelper.class);
    
    /**
     * 
     * @param dbUser
     * @param keyName
     * @return the genprop based on the key name
     */
    public static GenProp getGenPropByKeyName(BaseDBSessionBean dbUser, String keyName)
    {
        dbUser.open();
        return GenProp.findFirst("key_name = ?", keyName);
    }
    
    /**
     * 
     * @param dbUser
     * @param genPropId
     * @return the genprop based on the id passed
     */
    public static GenProp getGenPropById(BaseDBSessionBean dbUser, Long genPropId)
    {
        dbUser.open();
        return GenProp.findFirst("id = ?", genPropId);
    }
}
