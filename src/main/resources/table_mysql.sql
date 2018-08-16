-- mysql
CREATE TABLE TEMPC
(
    ID INT ,
    NAME VARCHAR(25)
);
CREATE TABLE TEMP_STAT
(
    GID BIGINT,
    SID INT,
    START_TIME TIMESTAMP (6),
    SUMMONER_NAME VARCHAR(20),
    OWNER INT,
    CID INT,
    CHAMPION_NAME VARCHAR(30),
    WIN INT,
    LOSE INT,
    DURATION INT,
    CREATED_DATE DATE,
    ACCOUNT_ID INT,
    CURRENT_ACCOUNT_ID INT,
    SEASON INT,
    VERSION VARCHAR(20),

    PRIMARY KEY (GID, SID)
);

SELECT
    CHAMPION_NAME,
    SUM(WIN)+SUM(LOSE) TOTAL,
    SUM(WIN) WIN,
    SUM(LOSE) LOSE,
    ROUND(SUM(WIN)/(SUM(WIN)+SUM(LOSE)),4) RATE
FROM TEMP_STAT S
WHERE OWNER=1
GROUP BY CHAMPION_NAME
ORDER BY WIN DESC,LOSE