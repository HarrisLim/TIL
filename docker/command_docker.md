# Docker 명령어

#### 포트 확인
  - lsof -PiTCP -sTCP:LISTEN (열린 포트를 확인할 수 있다.)
  - docker port "container id"
#### 컨테이너 log출력 상황 (설치 후 한 번은 해줘야한다)
  - docker logs -f "container id"
#### 컨테이너 삭제
  - docker rm "container id"
  - docker rm -f "container id"
#### 컨테이너 중지
  - docker stop "container id"
#### 컨테이너 재시작
  - docker restart "container id"
#### 컨테이너 일시정지
  - docker pause "container id"
#### 컨테이너 상태 확인
  - docker ps
  - docker stats "container id"
#### images 확인
  - docker images
#### images 삭제
  - docker rmi "IMAGE ID"
  - docker rmi -f "IMAGE ID" (If state is forced)
