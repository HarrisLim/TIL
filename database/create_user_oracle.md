# user 생성
  1. CREATE USER test1 IDENTIFIED BY test2; -- test1은 username, test2는 pw
  2. grant connect, dba, resource to test1; -- test1에게 모든 권한 부여
  3. conn test1/test2 -- test1로 연결