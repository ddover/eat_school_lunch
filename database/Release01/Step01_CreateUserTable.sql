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
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    ID  INTEGER NOT NULL  auto_increment PRIMARY KEY,
    EMAIL_ADDRESS VARCHAR(60),
    PHONE_NUMBER VARCHAR(20),
    PASSWORD VARCHAR(4000),
    FIRST_NAME VARCHAR(40),
    LAST_NAME VARCHAR(40),
    USER_ADDRESS_ID   INTEGER,
    IS_ADMIN  DEFAULT '0'
  );