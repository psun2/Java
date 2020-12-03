/* Drop Tables */
DROP TABLE tbl_member_auth CASCADE CONSTRAINTS;
DROP TABLE tbl_member CASCADE CONSTRAINTS;

/* Create Tables */

CREATE TABLE tbl_member (
	userid varchar2(50) NOT NULL PRIMARY KEY,
	userpw varchar2(100) NOT NULL,
	username varchar2(100) NOT NULL,
	regdate date DEFAULT SYSDATE,
	updatedate date DEFAULT SYSDATE,
	enabled char(1) default '1'
);

CREATE TABLE tbl_member_auth (
	userid varchar2(50) NOT NULL,
	auth varchar2(50) NOT NULL,
	CONSTRAINT fk_member_auth FOREIGN KEY(userid) REFERENCES tbl_member(userid)
);

-- 확인
SELECT u.userid, u.userpw ,u.enabled, a.auth
FROM tbl_member u, tbl_member_auth a
WHERE u.userid = a.userid;

DELETE FROM tbl_member;
DELETE FROM tbl_member_auth;

-- 쿼리를 사용한 인증용 쿼리 동작 여부 확인
-- ★컬럼이름 중요★, 별명을 사용하여 username, userpw, enabled, authority 로 SELECT 되게 하자
SELECT userid username, userpw password, enabled
FROM tbl_member WHERE userid = 'admin90';

SELECT userid username, auth authority
FROM tbl_member_auth WHERE userid = 'admin90';








