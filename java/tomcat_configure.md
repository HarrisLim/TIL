# Tomcat 초기 설정
  - WEB-INF의 web.xml 파일 안에서 밑에 처럼 매핑을 해줘야해
```
    <servlet>
      <servlet-name>HelloServletName</servlet-name>
      <servlet-class>jeong.sv.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
      <servlet-name>HelloServletName</servlet-name>
      <url-pattern>/hello</url-pattern>
    </servlet-mapping>
```

#### 새로운 폴더에서 시작할 때, 해당 폴더 안에 생성
  - WEB-INF
    - Web.xml
  - index.html

#### server.xml파일 설정
````
  1. C:\xxx\tomcat9\conf/server.xml을 찾자
  2. <Host>태그 내에 작성
    <Context docBase="C:\jeong\Web\New" path="/nc/"/> // docBase: 경로, nc: 라우터
```
