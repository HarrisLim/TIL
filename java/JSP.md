# JSP ( Java Server Page )

### 자동으로 import해주는 패키지
  ```
    import javax.servlet.http.*;
    import javax.servlet.*;
    import javax.servlet.jsp.*;
  ```

### JSP Elements (6가지) : 3가지 필수
  - 지시어 ( Directive Element )
    - <%@ %>
    ```
      <%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>
    ```
  - 스크립팅원소 ( Scripting Element )
    - <% %> 스크립트렛 Scriptlet
      - servlet의 service() 안에 구현
      ```
        (1)
          <% 
            for(int i=1; i<=7; i++){
              out.println("<h3>"+i+"</h3>");
            }
          %>
      
        (2)
          <%
            for(int i=1; i<=4; i++){
          %>
            <h3><%=i%></h3>
            <h3><%out.println(i);%></h3> // 이것도 윗 줄 것처럼 가능
          <%
            }		
          %>
      ```
    - <%! %> 선언 Declaration
      - servlet의 Class 밑에 선언 (ex: 멤버변수, 메소드)
    - <%= %> 식 Expression
      - servlet의 service()안에 구현
  - 액션 ( Action ) == JSP 표준태그
    - service()에 생성
    ```
      // html태그와 다르게 끝에 /를 써줘야해
      <jsp:useBean id="pool" class="jeong.db.ConnectionPoolBean" scope="session"/>
    ```

### scope (메모리 영역)
  - page < request < session < application
  - default : page
  - page
    - 각 페이지만
  - request
    - 요청하는 객체만
  - session
    - 하나의 사용자(브라우저, 서버측에 접속되어 있는 사용자)
    - 로그인할 때 사용하며 좋다
  - application
    - 그냥 딱 한 번만 만들어져. 전체 context에 공유가 된다

### Comment (주석)
  - <% %> 내
    - java 주석
    ```
      //, /**/
    ```
  - <% %> 외
    - html 주석
      - j라는 변수를 선언해주지 않아서 에러 // 실행은 하고 보여주지 않는다는 것을 의미
      ```
        <!-- -->
      ```   
    - jsp주석
      - j라는 변수를 선언해주지 않아도 에러가 나지 않는다
      ```
        <%-- --%>
      ```
  ```
  *참고
    <%
      for(int i=1; i<=7; i++){
        if(j==3) continue;
        out.println("<h3>"+i+"</h3>");
      }
    %>
  ```

### LifeCycle
  1. jsp페이지 접속
  2. init()이 수행되고 service()를 수행
  3. jsp페이지가 로딩될 때 마다 service()를 수행
  4. jsp페이지가 수정 or 서버종료되면 destroy()를 수행
  5. 해당 페이지에 재접속하면 1번부터 반복 수행

### 변환 과정
  - 웹페이지에서 JSP를 요청하는 순간 JSP -> JAVA -> Class 순으로 변환
  - 경로에 보면 jsp에서 변환된 .java파일과 java파일에서 컴파일된 .class파일이 있다.
    ```
      tomcat9\work\Catalina\localhost\"라우터이름"\org\apache\jsp
    ```
