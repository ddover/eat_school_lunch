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
 *//**
 * Table for Security Questions
 * Author:  DoverD
 * Created: Feb 14, 2016
 */

USE EAT_SCHOOL_LUNCH;
DROP TABLE IF EXISTS SECURITY_QUESTIONS;
CREATE TABLE SECURITY_QUESTIONS (
    ID                      INTEGER NOT NULL  auto_increment PRIMARY KEY,
    QUESTION                VARCHAR(4000)
);

-- Insert the security questions
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What High School did my mother attend?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What is my best friend's first name?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("In which city did I get married?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("In which city was my mother born?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What was my kindergarten teacher's last name?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What was the first book I ever read?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What street did I grow up on?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What is the name of the first school I attended?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What is the first name of my oldest cousin?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("In what city did your parents meet?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What is the name of the place your wedding reception was held?");
INSERT INTO SECURITY_QUESTIONS (QUESTION) VALUES ("What was your first pet’s name?");
COMMIT;