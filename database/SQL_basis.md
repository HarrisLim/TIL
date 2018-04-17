# SQL 기본 설명
  - 주요 용어
    - TABLE
      - 관계형 DBMS에서 기본 데이터 저장 구조로써 'Entity(실체)'의 집합 저장소
    - ROW
      - 테이블의 행 (하나의 유효한 데이터) 즉, Entity(실체)
    - COLUMN
      - 테이블의 열명
```
      Table --
             |
    (COLUMN) V     (COLUMN)         (COLUMN)       (COLUMN)
   ----------------------------------------------------------
  |            |               |               |             |   
  |    NAME    |      AGE      |     GENDER    |   COUNTRY   | 
  |----------------------------------------------------------|
  |            |               |               |             |   <-- ROW(Entity)
  |    SMITH   |      24       |      man      |     UK      |
  |----------------------------------------------------------|
  |            |               |               |             |
  |    SCOTT   |      27       |     woman     |    KOREA    |
   ----------------------------------------------------------
```

  - SQL의 구분
    - DQL ( Data Query Language )
      - 테이블의 데이터를 **조회**할 때 사용 ( select )
    - DML ( Data Manipulation Language )
      - 테이블에 **데이터**를 **입력**, **수정**, **삭제**할 때 사용 ( insert, update, delete )
    - DDL ( Data Definition Language )
      - 테이블 등의 **객체**를 **생성**, **변경**, **삭제**할 때 사용 ( create, alter, drop )
    - TCL ( Transaction Control Language )
      - 테이블 내의 DML문을 DB에 **저장** or **취소**할 때 사용 ( commit, rollback, savepoint, ... )
    - DCL ( Data Control Language )
      - DB에 계정에게 권한을 **부여** or **취소**할 때 사용 ( grant, revoke )