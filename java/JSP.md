# JSP ( Java Server Page )

### 자동으로 import해주는 패키지
  ```
    import javax.servlet.http.*;
    import javax.servlet.*;
    import java.io.*;
  ```

### JSP Elements (6가지) : 3가지 필수
  - 지시어 ( Directive Element )
    - <%@ %>
    ```
      <%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>
    ```
  - 스크립팅원소 ( Scripting Element )
    - <% %> 스크립트렛 Scriptlet
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
      - 클래스 밑에 선언되는 것 (ex: 멤버변수, 메소드) (스크립트렛은 service() 안에 쓰는 것을 작성)
    - <%= %> 식 Expression
  - 액션 ( Action ) == JSP 표준태그
    - <jsp:XXX /> // html의 <br>처럼 이렇게 쓰면 안되고 JSP에서는 끝에 닫는 /를 써줘야한다 (<br>은 가능)

### Comment (주석)
  - <% %> 내
    - //, /**/ // java 주석
  - <% %> 외
    - <!-- --> // html 주석
      - j라는 변수를 선언해주지 않아서 에러 // 실행은 하고 보여주지 않는다는 것을 의미
    - <%-- --%> // jsp주석
      - j라는 변수를 선언해주지 않아도 에러가 나지 않는다
  ```
    <%
      for(int i=1; i<=7; i++){
        if(j==3) continue;
        out.println("<h3>"+i+"</h3>");
      }
    %>
  ```

### LifeCycle
  1. jsp페이지 접속
  2. **init()**이 수행되고 **service()**를 수행
  3. jsp페이지가 로딩될 때 마다 **service()**를 수행
  4. jsp페이지가 수정 or 서버종료되면 **destroy()**를 수행
  5. 해당 페이지에 재접속하면 1번부터 반복 수행