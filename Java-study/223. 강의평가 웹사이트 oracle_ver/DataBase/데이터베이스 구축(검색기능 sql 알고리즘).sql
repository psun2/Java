-- 오류 문장
-- 1. CONCAT은 두개의인자만 허용 합니다.
-- 2. oracle 은 LIMIT을 허용 하지 않습니다.
SELECT * FROM EVALUEATION WHERE lectureDivide Like '%%' AND CONCAT(lectureName, professorName, evalueationTitle, evalueationContent) LIKE '%%' ORDER BY evaluationID DESC LIMIT 0, 6;

-- 수정후 문장
SELECT * FROM (SELECT ROWNUM as NO, EVALUEATION.* FROM EVALUEATION WHERE lectureDivide Like '%%' AND lectureName || evalueationContent || professorName || evalueationTitle LIKE '%%') sub WHERE NO >= 6 AND NO <= 11 ORDER BY sub.evaluationID DESC;

-- 최종확인
SELECT * FROM EVALUEATION;

SELECT ROWNUM, EVALUEATION.* FROM EVALUEATION;

SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like '%%' AND lectureName || evalueationContent || professorName || evalueationTitle LIKE '%%') ORDER BY likeCount DESC);

SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like '%%' AND lectureName || evalueationContent || professorName || evalueationTitle LIKE '%%') ORDER BY likeCount DESC)) sub;

SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like '%%' AND lectureName || evalueationContent || professorName || evalueationTitle LIKE '%%') ORDER BY likeCount DESC)) sub) WHERE NO >= 1 AND NO <= 6;

-- 자바에서 쓸수 있도록 변경 문 (디벨로퍼에서 실행 안됨)
"SELECT * FROM (SELECT ROWNUM as NO, sub.* FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM EVALUEATION  WHERE lectureDivide Like ? AND lectureName || evalueationContent || professorName || evalueationTitle LIKE ?) ORDER BY likeCount DESC)) sub) WHERE NO >= "+ startPage +" AND NO <= " + endPage;

-- 확인을 위한 예시 데이터 삽입
INSERT INTO 
EVALUEATION 
(evaluationID, userID, lectureName, professorName, lectureYear, semesterDivide, lectureDivide, evalueationTitle, evalueationContent, totalScore, creditScore, comfortableScore, lectureScore, likeCount)
VALUES (evaluationID_squence.nextval, '더러워염', '진짜 더러워염', '뭘보시나염', 2018, 'ㅋㅋ', 'ㅋㅋ', 'ㅋㅋ', 'ㅋㅋ', 'ㅁ', 'B', 'C', 'D', 0);

commit;