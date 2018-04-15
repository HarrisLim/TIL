# Network 
  server와 client가 연결되는 통로  
  
## protocol 
  server -> client 혹은 client -> server로 데이터를 전송할 때 각각의 맞는 기능을 부여할 수 있다  

  - 예
    - 전송할 때 문자열 앞에 001#이 붙으면 관리자 메시지
    - 전송할 때 문자열 앞에 002#이 붙으면 귓속말  
    이러한 식으로 server와 client가 알아들을 수 있게 설정한다  
    protocol을 정리한 뒤 **프로토콜 설계 문서**를 만드는 게 일반적이다

