package com.eat.school_lunch.utils;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author DoverD
 */
@FacesConverter("phoneConverter")
public class PhoneConverter implements Converter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        String phoneNumber = (String) modelValue;
//        StringBuilder formattedPhoneNumber = new StringBuilder();

        long phoneFmt = Long.parseLong(phoneNumber);
        //get a 12 digits String, filling with left '0' (on the prefix)   
        DecimalFormat phoneDecimalFmt = new DecimalFormat("0000000000");
        String phoneRawString= phoneDecimalFmt.format(phoneFmt);

        MessageFormat phoneMsgFmt=new java.text.MessageFormat("({0})-{1}-{2}");
            //suposing a grouping of 3-3-4
        String[] phoneNumArr={phoneRawString.substring(0, 3),
                  phoneRawString.substring(3,6),
                  phoneRawString.substring(6)};

//        System.out.println(phoneMsgFmt.format(phoneNumArr));

        return phoneMsgFmt.format(phoneNumArr);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Conversion is not necessary for now. However, if you ever intend to use 
        // it on input components, you probably want to implement it here.
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
