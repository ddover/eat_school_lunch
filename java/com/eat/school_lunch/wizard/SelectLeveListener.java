/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eat.school_lunch.wizard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;

/**
 *
 * @author DoverD
 */
@ManagedBean(name = "selectLevelListener")  
@RequestScoped  
public class SelectLeveListener {  
  
    private boolean errorOccured = false;  
  
    public int handleNavigation(SelectLevelEvent selectLevelEvent) {  
        if (errorOccured) {  
            return 2;  
        } else {  
            return selectLevelEvent.getNewLevel();  
        }  
    }  
  
    public void setErrorOccured(boolean errorOccured) {  
        this.errorOccured = errorOccured;  
    }  
}
