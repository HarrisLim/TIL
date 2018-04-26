# 계정 생성 [오라클 11g]

### sql plus
  - 계정 생성
    - conn system/'password'
    - create user TEST identified by 'password'
  - 접근
    - grant CONNECT, RESOURCE to TEST;
    - conn TEST/JAVA

### SQL Developer
  - 계정 생성
    - conn system/'password'
    - create user TEST identified by 'password'
 - 접근
   - +를 눌러서 새 계정 만들기를 해서 접근해야 해