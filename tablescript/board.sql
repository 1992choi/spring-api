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