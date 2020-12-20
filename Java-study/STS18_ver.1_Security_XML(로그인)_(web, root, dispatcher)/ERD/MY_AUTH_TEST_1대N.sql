DROP SEQUENCE uid_seq2;

DROP TABLE memberOnec CASCADE CONSTRAINTS;

CREATE SEQUENCE uid_seq2
START WITH 1
INCREMENT BY 1;

CREATE TABLE memberOnec(
    mid NUMBER(10),
    memberId VARCHAR2(50) UNIQUE,
    memberPassword VARCHAR2(200) NOT NULL,
    realMemberPassword VARCHAR2(200) NOT NULL,
    springSecurityEnable CHAR(1) DEFAULT '1',  -- 활성화 1, 비활성화 0
    auth VARCHAR2(50) DEFAULT 'ROLE_USER' NOT NULL,
    CONSTRAINT mid_pk PRIMARY KEY(mid)
);

-- 예시데이터
INSERT ALL
INTO memberOnec (mid, memberId, memberPassword, realMemberPassword)
VALUES (uid_seq2.nextval, 'tjddjs90', 'zzzz1', 'zzzz1')
INTO memberOnec (mid, memberId, memberPassword, realMemberPassword, auth)
VALUES (uid_seq2.nextval + 1, 'tjddjs901', 'zzz1z', 'zzzz1', 'ROLE_ADMIN')
SELECT * FROM DUAL;

COMMIT;

-- 확인
SELECT * FROM memberOnec;

-- 쿼리를 사용한 인증용 쿼리 동작 여부 확인
-- ★컬럼이름 중요★, 별명을 사용하여 username, userpw, enabled, authority 로 SELECT 되게 하자
SELECT mid, memberId as username, memberPassword as password, springSecurityEnable as enabled, auth as authority
FROM memberOnec
WHERE memberId = 'tjddjs90';




