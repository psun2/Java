CREATE SEQUENCE uid_seq
START WITH 1
INCREMENT BY 1;

CREATE TABLE member(
    mid NUMBER(10),
    memberId VARCHAR2(50) UNIQUE,
    memberPassword VARCHAR2(200) NOT NULL,
    springSecurityEnable CHAR(1) DEFAULT '1',
    CONSTRAINT mid_pk PRIMARY KEY(mid)
);

CREATE TABLE authentication(
   memberId VARCHAR2(50) NOT NULL,
   auth VARCHAR2(50) DEFAULT 'ROLE_USER' NOT NULL,
   CONSTRAINT fk_member_auth FOREIGN KEY(memberId) REFERENCES member(memberId)
);

-- 예시데이터
INSERT ALL
INTO member (mid, memberId, memberPassword)
VALUES (uid_seq.nextval, 'tjddjs90', 'zzzz1')
INTO member (mid, memberId, memberPassword)
VALUES (uid_seq.nextval + 1, 'tjddjs901', 'zzz1z')
INTO member (mid, memberId, memberPassword)
VALUES (uid_seq.nextval + 2, 'tjddjs902', 'z1zzz')
SELECT * FROM DUAL;

INSERT ALL
INTO authentication (memberId, auth)
VALUES ('tjddjs90', 'ROLE_ADMIN')
INTO authentication (memberId)
VALUES ('tjddjs90')
INTO authentication (memberId, auth)
VALUES ('tjddjs901', 'ROLE_USER')
SELECT * FROM DUAL;

COMMIT;

-- 확인
SELECT * FROM member;

SELECT * FROM authentication;

SELECT m.mid, m.memberId as username, m.memberPassword as password, m.springSecurityEnable as enabled, a.auth as authority
FROM member m, authentication a
WHERE m.memberId = a.memberId;

-- 쿼리를 사용한 인증용 쿼리 동작 여부 확인
-- ★컬럼이름 중요★, 별명을 사용하여 username, userpw, enabled, authority 로 SELECT 되게 하자
SELECT mid, memberId as username, memberPassword as password, springSecurityEnable as enabled
FROM member
WHERE memberId = 'tjddjs90';

SELECT memberId as username, auth as authority
FROM authentication
WHERE memberId = 'tjddjs90';



