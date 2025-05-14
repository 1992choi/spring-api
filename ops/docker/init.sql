/* 스키마 생성 */
CREATE DATABASE IF NOT EXISTS api;
CREATE DATABASE IF NOT EXISTS apisub;



/* 권한 설정 */
GRANT ALL PRIVILEGES ON api.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON apisub.* TO 'root'@'%';
FLUSH PRIVILEGES;



/* api database 설정 */
use api;

-- TABLE 생성
CREATE TABLE MEMBER (
    member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    age INT
);

CREATE TABLE TB_USER (
    user_id BIGINT PRIMARY KEY,
    user_name VARCHAR(255)
);

CREATE TABLE TEAM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE EMPLOYEE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    team_id BIGINT,
    CONSTRAINT fk_employee_team
      FOREIGN KEY (team_id)
          REFERENCES TEAM(id)
);

CREATE TABLE Dummy (
   dummy_id BIGINT PRIMARY KEY,
   data1 VARCHAR(255),
   data2 VARCHAR(255),
   data3 VARCHAR(255)
);

-- 데이터 생성
INSERT INTO MEMBER (name, age) VALUES ('Alice', 28);
INSERT INTO MEMBER (name, age) VALUES ('Bob', 35);
INSERT INTO MEMBER (name, age) VALUES ('Charlie', 22);
INSERT INTO MEMBER (name, age) VALUES ('Diana', 31);

INSERT INTO TB_USER (user_id, user_name) VALUES (1, 'alice');
INSERT INTO TB_USER (user_id, user_name) VALUES (2, 'bob');
INSERT INTO TB_USER (user_id, user_name) VALUES (3, 'charlie');
INSERT INTO TB_USER (user_id, user_name) VALUES (4, 'diana');

INSERT INTO TEAM (name) VALUES ('Development');
INSERT INTO TEAM (name) VALUES ('Marketing');

INSERT INTO EMPLOYEE (name, team_id) VALUES ('Alice', 1);
INSERT INTO EMPLOYEE (name, team_id) VALUES ('Bob', 1);
INSERT INTO EMPLOYEE (name, team_id) VALUES ('Charlie', 2);
INSERT INTO EMPLOYEE (name, team_id) VALUES ('Diana', 2);



/* api sub database 설정 */
use apisub;

-- TABLE 생성
CREATE TABLE TB_BOARD (
  BOARD_ID INT AUTO_INCREMENT PRIMARY KEY,
  TITLE VARCHAR(300),
  CONTENT VARCHAR(4000),
  REG_ID VARCHAR(100),
  REG_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 데이터 생성
INSERT INTO TB_BOARD (TITLE, CONTENT, REG_ID)
VALUES ('First Post', 'This is the content of the first post.', 'user123');
