# Oracle에서 SCOTT계정 락해제
  0. sqlplus 실행
  1. system계정으로 로그인
  2. select USERNAME, ACCOUNT_STATUS from DBA_USERS where username='SCOTT';
  3. SCOTT 계정의 유무와 LOCK이 걸려있는지 확인
  4. alter USER SCOTT ACCOUNT unlock;
  5. alter USER SCOTT IDENTIFIED BY tiger; -- SCOTT의 비밀번호를 tiger로 설정.
  6. select USERNAME, ACCOUNT_STATUS from DBA_USERS where username='SCOTT';로 다시 확인
  7. ACCOUNT_STATUS가 OPEN으로 변경되어 있는 것을 확인하자.
  8. CONN scott/tiger -- scott계정으로 로그인