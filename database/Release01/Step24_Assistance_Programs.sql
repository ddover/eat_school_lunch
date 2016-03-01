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
/**
 * Table is for listing the assistance programs
 * Author:  DoverD
 * Created: Jan 15, 2016
 */

USE EAT_SCHOOL_LUNCH;
DROP TABLE IF EXISTS ASSISTANCE_PROGRAMS;
CREATE TABLE ASSISTANCE_PROGRAMS (
    ID                      INTEGER NOT NULL  auto_increment PRIMARY KEY,
    PROGRAM_ABBR            VARCHAR(20),
    PROGRAM_NAME            VARCHAR(100),
    PROGRAM_DESCRIPTION     VARCHAR(1000),
    WEBSITE_URL             VARCHAR(200),
    ACTIVE                  INTEGER DEFAULT 1
);

-- Insert the snap assistance information
INSERT INTO ASSISTANCE_PROGRAMS (PROGRAM_ABBR, PROGRAM_NAME, PROGRAM_DESCRIPTION, WEBSITE_URL) VALUES 
("SNAP", "Supplemental Nutrition Assistance Program", "SNAP offers nutrition assistance to millions of eligible, low-income individuals and families and provides economic benefits to communities.", "http://www.fns.usda.gov/snap/supplemental-nutrition-assistance-program-snap");

-- Insert the TANF assistance information
INSERT INTO ASSISTANCE_PROGRAMS (PROGRAM_ABBR, PROGRAM_NAME, PROGRAM_DESCRIPTION, WEBSITE_URL) VALUES 
("TANF", "Temporary Assistance for Needy Families", "TANF is designed to help needy families achieve self-sufficiency.", "http://www.acf.hhs.gov/programs/ofa/programs/tanf");

-- Insert the FDPIR assistance information
INSERT INTO ASSISTANCE_PROGRAMS (PROGRAM_ABBR, PROGRAM_NAME, PROGRAM_DESCRIPTION, WEBSITE_URL) VALUES 
("FDPIR", "Food Distribution Program on Indian Reservations", "A Federal program that provides USDA foods to low-income households, including the elderly, living on Indian reservations, 
and to Native American families residing in designated areas near reservations and in the State of Oklahoma.", "http://www.fns.usda.gov/fdpir/food-distribution-program-indian-reservations-fdpir");

commit;