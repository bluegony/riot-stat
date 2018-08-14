-- mysql
create table tempc
(
    id int ,
    name varchar(25 )
);
CREATE TABLE TEMP_STAT
(
    ACCOUNT_ID int,
    CURRENT_ACCOUNT_ID int,
    SID int,
    GID bigint, -- or long(=text type)
    WIN int,
    DURATION int,
    SEASON int,
    VERSION VARCHAR(20),
    CREATED_DATE DATE,
    START_TIME TIMESTAMP (6),
    CID int,
    OWNER int,
    LOSE int
);