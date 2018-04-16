# 테이블의 기본키, 외래키 등 확인하는 법

  SELECT * FROM user_constraints;  
  constraint_type 컬럼을 보면 표시되어있다  
  P: PK, R: FK, U: UK, C: NOT NULL(or CHECK)  
  
  그런데, constraint를 구성하는 컬럼을 보고싶다면  
  SELECT * FROM user_cons_columns;  
  table_name과 column_name을 보면 어떤 테이블안의 어떤 컴럼에 걸려있는지 알 수 있다