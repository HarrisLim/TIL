# Cascade 옵션
  - FK로 참조된 데이터가 있을 때, 열이나 테이블을 한 번에 지우는 법
  - delete cascade(열) 와 drop cascade(테이블)

  - 먼저 테스트 테이블을 만들자
```
  -- (1) 테이블 생성
  create table BOARD(
      BOARDNO number constraint BOARD_PK primary key,
      test1 varchar(20)
  );
  create table BOARDTEST(
      BOARDTESTNO number constraint BOARDTEST_PK primary key,
      test2 varchar(15),
      BOARDNO number
  );
  
  -- (2) 비교할 테이블 생성
  create table BOARD2(
      BOARDNO number constraint BOARD2_PK primary key,
      test1 varchar(20)
  );
  create table BOARDTEST2(
      BOARDTESTNO number constraint BOARDTEST2_PK primary key,
      test2 varchar(15),
      BOARDNO number
  );
  
  -- (3) delete cadcade옵션을 적용시킨 foreign key
  alter table BOARDTEST
  add constraint BOARDTEST_FK foreign key(BOARDNO)
  references BOARD(BOARDNO) on delete cascade;
  
  -- (4) cascade 옵션을 적용시키지 않은 foreign key
  alter table BOARDTEST2
  add constraint BOARDTEST2_FK foreign key(BOARDNO)
  references BOARD2(BOARDNO);
  
  -- BOARD -- 
      -----------------------------------
     |      BOARDNO     |      TEST1     |
      -----------------------------------
     |         1        |     test1      |
      -----------------------------------
     |         2        |     test2      |
      -----------------------------------
     |         3        |     test3      |
      -----------------------------------
  
  -- BOARDTEST -- 
      ----------------------------------------------------
     |    BOARDTESTNO   |      TEST2     |    BOARDNO     |
      ----------------------------------------------------
     |        001       |     test001    |       1        |
      ----------------------------------------------------
     |        002       |     test002    |       2        |
      ----------------------------------------------------
     |        003       |     test003    |       3        |
      ----------------------------------------------------
  
```
### delete cascade
```
  --on delete cascade 해서 참조된 것도 지울 수 있어('참조된 열', '참조한 열' 둘 다 지워)
  delete from BOARD where BOARDNO=003;

  -- on delete cascade 안해서 참조한 row가 있어서 무결성 제약조건에 위배되어서 실행 불가
  delete from BOARD2 where BOARDNO=001;
```
### drop cascade
```
-- 테이블 그냥 지우는 건데, delete cascade 유무에 상관없이 지워진다
drop table BOARD cascade constraint; 

-- 마찬가지다. 테이블 그냥 지우는 건데, delete cascade 유무에 상관없이 지워진다
drop table BOARD2 cascade constraint;
```