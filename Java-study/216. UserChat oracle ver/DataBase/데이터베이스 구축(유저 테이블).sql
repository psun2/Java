-- ���� ���̺� ����
CREATE TABLE MEMBER (
userID  VARCHAR2(20),
userPassword VARCHAR2(20),
userName VARCHAR2(20),
userAge INT,
userGender VARCHAR2(20),
userEmail VARCHAR2(50),
userProfile VARCHAR2(50)
);

-- ���̺� ��Ű�� Ȯ��
DESC MEMBER;

-- ��Ÿ�� ���� ���̺� �÷� ����
ALTER TABLE MEMBER RENAME COLUMN userPasswrd to userPassword;

-- ���̺� ����
DROP TABLE MEMBER;
