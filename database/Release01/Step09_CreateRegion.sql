USE EAT_SCHOOL_LUNCH;
DROP TABLE IF EXISTS REGIONS;
CREATE TABLE REGIONS (
    ID          INTEGER NOT NULL auto_increment PRIMARY KEY,
    DESCRIPTION VARCHAR(100),
    ABBR        VARCHAR(10)
);

INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Alabama", "AL");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Alaska", "AK");
-- INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("American Samoa");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Arizona", "AZ");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Arkansas", "AR");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("California", "CA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Colorado", "CO");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Connecticut", "CT");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Delaware", "DE");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("District of Columbia", "DC");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Florida", "FL");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Georgia", "GA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Hawaii", "HI");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Idaho", "ID");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Illinois", "IL");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Indiana", "IN");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Iowa", "IA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Kansas", "KS");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Kentucky", "KY");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Louisiana", "LA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Maine", "ME");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Maryland", "MD");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Massachusetts", "MA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Michigan", "MI");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Minnesota", "MN");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Mississippi", "MS");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Missouri", "MS");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Montana", "MT");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Nebraska", "NE");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Nevada", "NV");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("New Hampshire", "NH");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("New Jersey", "NJ");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("New Mexico", "NM");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("New York", "NY");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("North Carolina", "NC");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("North Dakota", "ND");
-- INSERT INTO REGIONS (DESCRIPTION) VALUES ("Northern Marianas Islands");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Ohio", "OH");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Oklahoma", "OK");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Oregon", "OR");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Pennsylvania", "PA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Puerto Rico", "PR");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Rhode Island", "RI");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("South Carolina", "SC");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("South Dakota", "SD");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Tennessee", "TN");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Texas", "TX");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Utah", "UT");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Vermont", "VT");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Virginia", "VA");
-- INSERT INTO REGIONS (DESCRIPTION) VALUES ("Virgin Islands");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Washington", "WA");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("West Virginia", "WV");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Wisconsin", "WI");
INSERT INTO REGIONS (DESCRIPTION,ABBR) VALUES ("Wyoming", "WY");
COMMIT;