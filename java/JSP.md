# JSP ( Java Server Page )

### 자동으로 import해주는 패키지
  ```
    import javax.servlet.http.*;
    import javax.servlet.*;
    import javax.servlet.jsp.*;
  ```

### JSP Elements (6가지)
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


      (1) String name = request.getParameter("name");
      (2) <jsp:setProperty name="dto" property="name" param="name"/> // property: dto의 멤버변수, param은 post방식으로 받아온 name
      (3) <jsp:setProperty name="dto" property="*"/> // * 이렇게 사용하려면 property이름과 param이름이 같아야한다

      (1)번 코드를 (2)번 코드로 쓸 수 있다. (액션 활용)
      (2)번 코드를 (3)번 코드로 쓸 수 있다. (이게 더 편해 but, 주의사항확인) 
    ```
  - EL ( Expression Language )
    - ${}
    ```
      EL과 JSTL을 사용하려면 추가해줘야해.
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    ```
  - JSTL ( Java Standard Tag Library )
    - <c:xxx />
    - JSTL api를 보면 JSTL.core를 많이 쓴다.
    ```
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    ```
  - CTL ( Custom Tag Library )
    - java, jsp로 생성 가능
    - 직접 만들어서 사용하는 tag
    - 양날의 검이야, 우리만 쓰면 괜찮은데 다른 사람이 유지보수할 때 힘들다

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

### DBCP
  - DataBase Connection Pool

### 패턴
  - 용어
    - Java Beans: 기능 덩어리, JSP의 꽃
      - DAO: 데이터를 Access한다 ( Data Access Object )
      - DTO: 데이터를 이동(변환)시킨다 ( Data Transfer Object )
#### 모델 1 (MV)
  - View(html, css, js) + Model(java, db)
  - DAO, DTO 클래스를 만들어서 활용했다
  - DAO
    - 각 jsp페이지 마다 씌여질 java코드를 메소드로 분리
  - DTO
    - 컬럼명으로 된 멤버변수를 만들고 setter, getter 메소드도 만들었다. 생성자로 멤버변수에 row를 입력
#### 모델 2 (MVC)
  - Model, View, Controller (실제 형태는 V - C - M라고 보자)
  - service(Manager) Class를 만든다.
  - DAO를 public으로 하지 않는다
  - View에서 View로 갈 수 없다. 꼭 controller를 거쳐야해

### Singleton Object Model
  - 어떠한 객체를 생성할 때 마다 만든다면 그 객체를 한 번만 생성되게하기 위해 static으로 공유시켜버린다.
```
  (1)
  private AddrService service = new AddrService();
  public static AddrService getInstance(){
    return service;
  }

  (2)
  private final static AddrService service = new AddrService();
  public static AddrService getInstance(){
    return service;
  }

  (1)번: 기본, (2)번: Singleton Object Model
```