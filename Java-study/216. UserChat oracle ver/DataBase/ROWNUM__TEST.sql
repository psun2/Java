SELECT sub."데이터의 길이", BOARD.* FROM BOARD, (SELECT COUNT(sub.로우넘) AS "데이터의 길이" FROM (SELECT ROWNUM as 로우넘, BOARD.* FROM BOARD) sub) sub; 

SELECT ROWNUM AS NO, BOARD.* FROM BOARD;

SELECT MAX(sub.NO) FROM (SELECT ROWNUM AS NO, BOARD.* FROM BOARD) sub; 

SELECT * FROM (SELECT MAX(sub.NO) FROM (SELECT ROWNUM AS NO, BOARD.* FROM BOARD) sub) sub1, (SELECT ROWNUM AS NO, BOARD.* FROM BOARD) sub2 ORDER BY NO DESC;


CREATE VIEW BOARD_VIEW AS
SELECT * FROM (SELECT MAX(sub.NO) FROM (SELECT ROWNUM AS NO, BOARD.* FROM BOARD) sub) sub1, (SELECT ROWNUM AS NO, BOARD.* FROM BOARD) sub2;

-- 1페이지
SELECT BOARD_VIEW.* FROM BOARD_VIEW WHERE NO <= (SELECT MAX(NO) FROM BOARD_VIEW) - 0 AND NO > (SELECT MAX(NO) FROM BOARD_VIEW) - 10 ORDER BY NO DESC;
