
/* Drop Views */

DROP VIEW userView;



/* Drop Triggers */

DROP TRIGGER DELETE_USER;
DROP TRIGGER INSERT_AUTH;
DROP TRIGGER TRI_userMember_um_uid;



/* Drop Tables */

DROP TABLE auth CASCADE CONSTRAINTS;
DROP TABLE userMember CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_userMember_um_uid;




/* Create Sequences */

CREATE SEQUENCE SEQ_userMember_um_uid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE auth
(
	-- ROLE_GUEST
	-- ROLE_MEMBER
	-- ROLE_ADMIN
	authority varchar2(50) DEFAULT 'ROLE_MEMBER' NOT NULL,
	um_uid number NOT NULL,
	PRIMARY KEY (authority, um_uid)
);


CREATE TABLE userMember
(
	um_uid number NOT NULL,
	um_username varchar2(50) NOT NULL UNIQUE,
	um_password varchar2(200) NOT NULL,
	um_realPassword varchar2(50) NOT NULL,
	um_nickname varchar2(50) NOT NULL UNIQUE,
	um_regdate timestamp DEFAULT SYSDATE NOT NULL,
	um_enabled char(1) DEFAULT '1' NOT NULL,
	PRIMARY KEY (um_uid)
);



/* Create Foreign Keys */

ALTER TABLE auth
	ADD FOREIGN KEY (um_uid)
	REFERENCES userMember (um_uid)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER DELETE_USER AFTER
DELETE ON userMember
FOR EACH ROW
DECLARE
BEGIN
DELETE FROM auth WHERE um_uid = :OLD.um_uid;
END;

/

CREATE OR REPLACE TRIGGER INSERT_AUTH AFTER
INSERT ON userMember
FOR EACH ROW
DECLARE
BEGIN
INSERT INTO auth(um_uid) VALUES(:NEW.um_uid);
END;

/

CREATE OR REPLACE TRIGGER TRI_userMember_um_uid BEFORE INSERT ON userMember
FOR EACH ROW
BEGIN
	SELECT SEQ_userMember_um_uid.nextval
	INTO :new.um_uid
	FROM dual;
END;

/




/* Create Views */

CREATE OR REPLACE VIEW userView AS SELECT 
u.um_username AS username,
u.um_password AS password,
u.um_enabled AS enabled,
a.authority
FROM userMember u 
JOIN auth a
ON u.um_uid = a.um_uid;

/* Comments */

COMMENT ON COLUMN auth.authority IS 'ROLE_GUEST
ROLE_MEMBER
ROLE_ADMIN';

/* TestData */

INSERT INTO userMember(um_username, um_password, um_realpassword, um_nickname) VALUES('admin', '$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai', '0000', '운영자');
INSERT INTO auth VALUES(1, 'ROLE_ADMIN');
INSERT INTO userMember(um_username, um_password, um_realpassword, um_nickname) VALUES('hogn', '$2a$10$QneCaQwCDQIWEnnYT64/pOk.88K91rDL81LlZ4NxAzdJtRXiNMOoq', '0000', '홍성혁');
INSERT INTO userMember(um_username, um_password, um_realpassword, um_nickname) VALUES('hyun', '$2a$10$6s1NtNSY3BEULN7krTuw3OfECZYR0WdICc/HYzRLVdK4ZiDR337nG', '0000', '김현준');
INSERT INTO userMember(um_username, um_password, um_realpassword, um_nickname) VALUES('hwi', '$2a$10$suS4tGXX5zhdBGln/5QfXutWjpe6VwNRN2mee9FZ52Z7KWdf35jwi', '0000', '김휘진');
INSERT INTO userMember(um_username, um_password, um_realpassword, um_nickname) VALUES('park', '$2a$10$5Yy/c52u4Ped7j11mMxKEeuHAIRHjiZyg5NyltWqpW5MGIh6zk/Ky', '0000', '박성언');
COMMIT;
SELECT * FROM usermember;
SELECT * FROM auth;
SELECT * FROM userView;

