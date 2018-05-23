# docker로 oracle 실행 (on Mac)
  - 설치
    1. docker run --name oracle12c -d -p 8080:8080 -p 1521:1521 -v<br>
      ~/my/oracle/data:/u01/app/oracle sath89/oracle-12c
    2. docker logs -f oracle12c

  - sqlplus 실행법
    - docker exec -it oracle12c sqlplus

### 간단 명령어
  - 도커 컨테이너 상태보기
    - docker ps
  - 컨테이너 삭제
    - docker rm xxxx(컨테이너 명)
  - 이미지 삭제
    - docker rmi xxxx/xxxx (정확히 써줘야한다)

### 내가 느낀 문제점 및 해결방안
  - docker를 껐다가 키면 완전 초기화가 되고, 처음부터 다시 설치를 해야한다.<br>
    너무 어렵다. scott계정 언락푼 것도 다시 설정해야한다.<br>
    아직은 docker를 잘 모르겠지만, VM보다는 훨씬 편하다.<br>
  - localhost:8080포트로 들어가서 ID, PW입력하면 Oracle Appliction Express로 들어갈 수 있다.<br>
    ID,PW를 입력하지 않으면 401에러가 발생하는데, 나는 이게 내 웹서버 환경의 문제인 줄 알았다.<br>
    하루 종일 고민하고 못하고 다음날에 보니까 보인다. 8080으로 들어가면 Oracle관련 사이트에 들어가져서<br>
    내 웹서버 포트를 8008로 바꿨다. 그러니까 잘 된다 !! 