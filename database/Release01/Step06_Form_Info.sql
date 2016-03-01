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
 * Author:  DoverD
 * Created: Dec 22, 2015
 */

USE EAT_SCHOOL_LUNCH;
DROP TABLE IF EXISTS FORMS;
CREATE TABLE FORMS (
    ID               INTEGER NOT NULL auto_increment PRIMARY KEY,
    USER_ID          INTEGER,
    LAST_STEP_SAVED  INTEGER,
    DATE_STARTED     DATETIME,
    DATE_COMPLETED   DATETIME,
    NON_DISC_STMT    BOOLEAN DEFAULT '0',
    INFO_USE_STMT    BOOLEAN DEFAULT '0',
    ATTESTING_STMT   BOOLEAN DEFAULT '0',
    ETHNICITY_STMT   BOOLEAN DEFAULT '0',
    SIGNATURE        MEDIUMTEXT
);
COMMIT;