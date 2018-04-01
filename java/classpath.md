# Classpath
  - -classpath 옵션 (유효범위 : javac.exe, java.exe (javac, java를 실행할 때만))
    - 디렉토리법
      ```
      // classes, PUser, 절대경로는 예시.

      #> javac -classpath ./classes PUser.java
      #> java -classpath .;C:\jeong\p.jar PUser
      ```
    - jar법
      ```
        #> javac -classpath C:\jeong\p.jar PUser.java
        #> java -classpath .;C:\jeong\p.jar PUser
      ```

  - set classpath (유효범위 : 현재 command (다른 콘솔창이나 해당 콘솔창을 껐다가 키면 안돼))
    - 디렉토리법
      ```
        #> set classpath=.;./classes // 띄어쓰기 주의
        #> javac PUser.java
        #> java PUser
      ```
    - jar법
      ```
        #> set classpath=.;C:\jeong\p.jar
        #> javac PUser.java
        #> java PUser
      ```

  - 시스템 설정 (유효범위 : OS) // 자동으로 classpath를 잡아주는 IDE툴이 있기 떄문에, 패키지가 충돌할 수 있어
    - 디렉토리법
      ```
        #> javac PUser.java
        #> java PUser
      ```
    - jar법
      ```
        #> javac PUser.java
        #> java PUser
      ```