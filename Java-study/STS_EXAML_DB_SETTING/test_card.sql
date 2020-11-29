--------------------------------------------------------
--  파일이 생성됨 - 일요일-11월-29-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TEST_CARD
--------------------------------------------------------

  CREATE TABLE "SCOTT_B"."TEST_CARD" 
   (	"USER_ID" VARCHAR2(20 BYTE), 
	"BUY_AMOUNT" NUMBER DEFAULT 1
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SCOTT_B.TEST_CARD
SET DEFINE OFF;
Insert into SCOTT_B.TEST_CARD (USER_ID,BUY_AMOUNT) values ('김휘진',3);
Insert into SCOTT_B.TEST_CARD (USER_ID,BUY_AMOUNT) values ('김휘진',3);
Insert into SCOTT_B.TEST_CARD (USER_ID,BUY_AMOUNT) values ('김휘진',3);
--------------------------------------------------------
--  Constraints for Table TEST_CARD
--------------------------------------------------------

  ALTER TABLE "SCOTT_B"."TEST_CARD" MODIFY ("USER_ID" NOT NULL ENABLE);
