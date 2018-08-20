# Oracle Data Handling

### 이번주(월화수목금토일) 날짜 검색
```
  select 
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )), -- 월
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+1, 'yy/mm/dd'    ), -- 화
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+2, 'yy/mm/dd'    ), -- 수
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+3, 'yy/mm/dd'    ), -- 목
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+4, 'yy/mm/dd'    ), -- 금
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+5, 'yy/mm/dd'    ), -- 토
  TO_CHAR( SYSDATE-( (select TO_CHAR(SYSDATE,'D') from dual) -2 )+6, 'yy/mm/dd'    ) -- 일
  from DUAL;
```

### 특정 테이블에서 현재연도 검색
```
  -- 현재연도
  select BU_RDATE 
  from BUILDING 
  where TO_CHAR(SYSDATE, 'YY/MM/DD') like (select SUBSTR(TO_CHAR(SYSDATE, 'YY/MM/DD'), 0, 2) from DUAL)||'%';

  -- 현재연도 -1
  select BU_RDATE 
  from BUILDING 
  where TO_CHAR(SYSDATE, 'YY/MM/DD') like (select SUBSTR(TO_CHAR(SYSDATE, 'YY/MM/DD'), 0, 2)-1 from DUAL)||'%';

  -- 현재연도 -2
  select BU_RDATE 
  from BUILDING 
  where TO_CHAR(SYSDATE, 'YY/MM/DD') like (select SUBSTR(TO_CHAR(SYSDATE, 'YY/MM/DD'), 0, 2)-1 from DUAL)||'%';
```