DROP TABLE security_user;
DROP TABLE persistent_logins;
DROP TABLE custom_persistent_logins;

CREATE TABLE security_user (
    username VARCHAR2(100) NOT NULL,
    password VARCHAR2(100) DEFAULT '$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai' NOT NULL, -- 0000
    enabled CHAR(1) DEFAULT '1' NOT NULL,
    authority VARCHAR2(100) DEFAULT 'ROLE_MEMBER' NOT NULL,
    PRIMARY KEY (username)
);

-- 테이블 이름은 시큐리티 규칙이므로 변경 불가하나 
-- 지금 해당 컬럼외 다른 컬럼을 추가하거나 현재 컬럼을 유지한체 커스터 마이징 가능
CREATE TABLE persistent_logins (
    username VARCHAR2(64) not null,
    series VARCHAR2(64) not null,
    token VARCHAR2(64) not null,
    last_used TIMESTAMP not null,
    PRIMARY KEY (series)
);

-- 커스텀 테이블 (RememberMeTokenRepository)
CREATE TABLE custom_persistent_logins (
    username VARCHAR2(64) not null,
    series VARCHAR2(64) not null,
    tokenValue VARCHAR2(64) not null,
    lastUsed TIMESTAMP not null,
    PRIMARY KEY (series)
);

INSERT INTO security_user(username, authority) VALUES('admin', 'ROLE_ADMIN');
INSERT INTO security_user(username, authority) VALUES('member', 'ROLE_MEMBER');

COMMIT;

SELECT * FROM security_user;
SELECT * FROM persistent_logins;
SELECT * FROM custom_persistent_logins;

SELECT 
    username, password, enabled, authority  
FROM security_user 
WHERE username = 'admin';