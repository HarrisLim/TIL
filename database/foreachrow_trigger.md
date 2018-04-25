# FOR EACH ROW in Trigger [Oracle 11g]
  - 이게 오라클 트리거에서 사용되는 옵션절인데,  
    트리거를 만들 때 한 행마다 돌리는 것으로 이해를 했는데 실제로 해보니까 안되더라.
  - 실제로는 한 행마다 돌리는 것으로 생각하지 말고 한 테이블당 돌리는 것으로 이해하자.
  - 같은 테이블에 쓰려면 안해도 돼.  

#### 같은 테이블만 있을 때
  - for each row를 쓰면 오류 발생
```
  create or replace TRIGGER EMP_TRI
    AFTER
      insert on EMP2
      for each row
    BEGIN
      update EMP2 set ENAME='FORD' where EMPNO=7788;
    END;
  /
  insert into EMP2(EMPNO, ENAME, JOB) values(4, 'HARRIS', 'SINNGER');
```
  - for each row를 쓰지 않으면 정상 작동
```
  create or replace TRIGGER EMP_TRI
    AFTER
      insert on EMP2
    BEGIN
      update EMP2 set ENAME='FORD' where EMPNO=7788;
    END;
  /
  insert into EMP2(EMPNO, ENAME, JOB) values(4, 'HARRIS', 'SINNGER');
```

#### 다른 테이블만 있을 때
  - for each row를 사용해도, 하지 않아도 가능
```
  create or replace TRIGGER EMP_TRI
    AFTER
      insert on EMP2
      -- for each row  *추가해도 추가하지 않아도 가능
    BEGIN
      update DEPT2 set DNAME='SALES' where DNAME='SALESMAN';
    END;
  /
  insert into EMP2(EMPNO, ENAME, JOB) values(5, 'HARRIS', 'SINNGER');
```

#### 같은 테이블, 다른 테이블이 둘 다 있을 때
  - for each row를 쓰면 오류 발생
```
  create or replace TRIGGER EMP_TRI
    AFTER
      insert on EMP2
      for each row
    BEGIN
      update DEPT2 set DNAME='SALES' where DNAME='SALESMAN';
      update EMP2 set ENAME='FORD1' where EMPNO=7788;
    END;
  /
  insert into EMP2(EMPNO, ENAME, JOB) values(7, 'HARRIS', 'SINNGER');
```
  - for each row를 지우면 정상 작동
```
  create or replace TRIGGER EMP_TRI
    AFTER
      insert on EMP2
    BEGIN
      update DEPT2 set DNAME='SALES' where DNAME='SALESMAN';
      update EMP2 set ENAME='FORD1' where EMPNO=7788;
    END;
  /
  insert into EMP2(EMPNO, ENAME, JOB) values(7, 'HARRIS', 'SINNGER');
```

#### 결론
AFTER에 있는 테이블이 BEGIN에 있다면 for each row를 사용하면 안된다