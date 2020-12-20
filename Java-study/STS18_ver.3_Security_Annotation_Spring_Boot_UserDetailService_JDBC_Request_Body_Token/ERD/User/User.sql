
/* Drop Views */

DROP VIEW userView;



/* Drop Triggers */

DROP TRIGGER DELETE_USER;
DROP TRIGGER INSERT_AUTH;



/* Drop Tables */

DROP TABLE auth CASCADE CONSTRAINTS;
DROP TABLE userMember CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE auth
(
	username varchar2(50) NOT NULL,
	-- ROLE_GUEST
	-- ROLE_MEMBER
	-- ROLE_ADMIN
	authority varchar2(50) DEFAULT 'ROLE_MEMBER' NOT NULL,
	PRIMARY KEY (username, authority)
);


CREATE TABLE userMember
(
	username varchar2(50) NOT NULL,
	password varchar2(200) NOT NULL,
	realPassword varchar2(50) NOT NULL,
	nickname varchar2(50) NOT NULL UNIQUE,
	regdate timestamp DEFAULT SYSDATE,
	enabled char(1) DEFAULT '1' NOT NULL,
	PRIMARY KEY (username)
);



/* Create Foreign Keys */

ALTER TABLE auth
	ADD FOREIGN KEY (username)
	REFERENCES userMember (username)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER DELETE_USER AFTER
DELETE ON userMember
FOR EACH ROW
DECLARE
BEGIN
DELETE FROM auth WHERE userName = :OLD.userName;
END;

/

CREATE OR REPLACE TRIGGER INSERT_AUTH AFTER
INSERT ON userMember
FOR EACH ROW
DECLARE
BEGIN
INSERT INTO auth(userName) VALUES(:NEW.userName);
END;

/




/* Create Views */

CREATE OR REPLACE VIEW userView AS SELECT
u.username,
u.password,
u.realPassword,
u.nickname,
u.regdate,
u.enabled,
a.authority
FROM userMember u, auth a
WHERE u.userName = a.userName
ORDER BY regDate ASC;



/* Comments */

COMMENT ON COLUMN auth.authority IS 'ROLE_GUEST
ROLE_MEMBER
ROLE_ADMIN';


/* TestData */

INSERT INTO userMember(username, password ,realpassword, nickname) VALUES('admin', '$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai', '0000', '운영자');
INSERT INTO auth VALUES('admin', 'ROLE_ADMIN');
INSERT INTO userMember(username, password ,realpassword, nickname) VALUES('hogn', '$2a$10$QneCaQwCDQIWEnnYT64/pOk.88K91rDL81LlZ4NxAzdJtRXiNMOoq', '0000', '홍성혁');
INSERT INTO userMember(username, password ,realpassword, nickname) VALUES('hyun', '$2a$10$6s1NtNSY3BEULN7krTuw3OfECZYR0WdICc/HYzRLVdK4ZiDR337nG', '0000', '김현준');
INSERT INTO userMember(username, password ,realpassword, nickname) VALUES('hwi', '$2a$10$suS4tGXX5zhdBGln/5QfXutWjpe6VwNRN2mee9FZ52Z7KWdf35jwi', '0000', '김휘진');
INSERT INTO userMember(username, password ,realpassword, nickname) VALUES('park', '$2a$10$5Yy/c52u4Ped7j11mMxKEeuHAIRHjiZyg5NyltWqpW5MGIh6zk/Ky', '0000', '박성언');
commit;
SELECT * FROM usermember;
SELECT * FROM auth;
SELECT * FROM userView;

