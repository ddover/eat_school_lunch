/* 
 * Copyright (c) 2015, Darryl Dover
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.eat.school_lunch.utils;

/**
 *
 * @author DoverD
 */
public class PhoneNumberValidator {
    
    public static void main(String[] args) {
        System.out.println("Phone number 1234567890 validation result: "+validatePhoneNumber("1234567890"));
        System.out.println("Phone number 123-456-7890 validation result: "+validatePhoneNumber("123-456-7890"));
        System.out.println("Phone number 123-456-7890 x1234 validation result: "+validatePhoneNumber("123-456-7890 x1234"));
        System.out.println("Phone number 123-456-7890 ext1234 validation result: "+validatePhoneNumber("123-456-7890 ext1234"));
        System.out.println("Phone number (123)-456-7890 validation result: "+validatePhoneNumber("(123)-456-7890"));
        System.out.println("Phone number 123.456.7890 validation result: "+validatePhoneNumber("123.456.7890"));
        System.out.println("Phone number 123 456 7890 validation result: "+validatePhoneNumber("123 456 7890"));
    }
 
    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        //validating phone nnumber where area code is in braces but different
        else if(phoneNo.matches("\\(\\d{3}\\)\\d{3}\\d{4}")) return true;
        //for the format (###)###-####
        else if(phoneNo.matches("\\(\\d{3}\\)\\d{3}-\\d{4}")) return true;
        //return false if nothing matches the input
        else return false;
         
    }
}
