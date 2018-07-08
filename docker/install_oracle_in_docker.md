# docker로 oracle 실행 (on Mac)
  - 설치
    1. docker run --name oracle11g -d -p 8080:8080 -p 1521:1521 -v /my/oracle/data/u01/app/oracle -v /my/oracle/init/sh_sql_dmp_files/docker-entrypoint-initdb.d sath89/oracle-xe-11g
    (실제로 github를 보면 이렇게 되어 있지만, ":"를 붙여서 넣으면 파일을 찾지 못한다. 그래서 위에 써놓은 코드처럼 ":"를 빼고 설치해야한다. docker run --name oracle11g -d -p 8080:8080 -p 1521:1521 -v /my/oracle/data:/u01/app/oracle -v /my/oracle/init/sh_sql_dmp_files:/docker-entrypoint-initdb.d sath89/oracle-xe-11g)

    2. docker logs -f oracle11g

  - sqlplus 실행법
    - docker exec -it oracle11g sqlplus
    - system/oracle

  - db 기본설정
```
  hostname: localhost
  port: 1521
  sid: xe
  username: system
  password: oracle
```
