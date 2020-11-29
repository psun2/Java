--------------------------------------------------------
--  파일이 생성됨 - 일요일-11월-29-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TEST_TICKET
--------------------------------------------------------

  CREATE TABLE "SCOTT_B"."TEST_TICKET" 
   (	"USER_ID" VARCHAR2(20 BYTE), 
	"TICKET_COUNT" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SCOTT_B.TEST_TICKET
SET DEFINE OFF;
Insert into SCOTT_B.TEST_TICKET (USER_ID,TICKET_COUNT) values ('김휘진',3);
Insert into SCOTT_B.TEST_TICKET (USER_ID,TICKET_COUNT) values ('김휘진',3);
Insert into SCOTT_B.TEST_TICKET (USER_ID,TICKET_COUNT) values ('김휘진',3);
Insert into SCOTT_B.TEST_TICKET (USER_ID,TICKET_COUNT) values ('아',3);
Insert into SCOTT_B.TEST_TICKET (USER_ID,TICKET_COUNT) values ('아',3);
--------------------------------------------------------
--  Constraints for Table TEST_TICKET
--------------------------------------------------------

  ALTER TABLE "SCOTT_B"."TEST_TICKET" ADD CONSTRAINT "TICKET_BUY_LIMIT" CHECK (ticket_count BETWEEN 1 AND 5) ENABLE;
  ALTER TABLE "SCOTT_B"."TEST_TICKET" MODIFY ("TICKET_COUNT" NOT NULL ENABLE);
  ALTER TABLE "SCOTT_B"."TEST_TICKET" MODIFY ("USER_ID" NOT NULL ENABLE);
