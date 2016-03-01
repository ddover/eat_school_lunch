package com.eat.school_lunch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;

/**
 *
 * @author DoverD
 */
//@DbName("EAT_SCHOOL_LUNCH")
@Table(value = "USER_STATISTICS")
public class UserStatistic extends Model implements Serializable {

    /**
     * sets the user id of the statistics
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        set("user_id", userId);
    }

    /**
     *
     * @return the user id of the statistics
     */
    public Long getUserId() {
        return getLong("user_id");
    }

    /**
     * sets the time the user logged in
     *
     * @param logged_In
     */
    public void setLogged_In(Timestamp logged_In) {
        setTimestamp("logged_in", logged_In);
    }

    /**
     *
     * @return the time the user logged in
     */
    public Timestamp getLogged_In() {
        return getTimestamp("logged_in");
    }

    /**
     * sets the time the user logged out
     *
     * @param logged_Out
     */
    public void setLogged_Out(Timestamp logged_Out) {
        setTimestamp("logged_out", logged_Out);
    }

    /**
     *
     * @return the time the user logged out
     */
    public Timestamp getLogged_Out() {
        return getTimestamp("logged_out");
    }

    /**
     * sets the device the user used to access the website
     *
     * @param deviceType
     */
    public void setDeviceType(String deviceType) {
        set("device_type", deviceType);
    }

    /**
     *
     * @return the device the user used to access the website
     */
    public String getDeviceType() {
        return getString("device_type");
    }
}
