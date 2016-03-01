package com.eat.school_lunch.comparator;

import com.eat.school_lunch.model.City;
import java.util.Comparator;

/**
 *
 * @author DoverD
 */
public class CityDescriptionComparator implements Comparator<City> {
    
    private boolean ascending = false;
    
    /**
     * constructor
     */
    public CityDescriptionComparator(){}
    
    public CityDescriptionComparator(boolean ascending)
    {
        this.ascending = ascending;
    }
    
    /**
     * 
     * @param city1
     * @param city2
     * @return the comparison result of the article updated dates
     */
    @Override
    public int compare(City city1, City city2)
    {
        return city1.getDescription().compareTo(city2.getDescription());
    }
}
