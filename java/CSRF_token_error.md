# CSRF Token 에러 잡기 ([document](https://docs.spring.io/autorepo/docs/spring-security/3.2.0.CI-SNAPSHOT/reference/html/csrf.html))
  - Spring Security를 사용하니까, 403에러가 발생한다.<br>
    403 forbidden 에러가 발생하는 이유는 CSRF token을 Back단으로 보내주지 않아서 나는 에러이다.<br>

### 해결방법
  - form의 action으로 submit하여 보낼 때
```
  <form action="logout" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
```
  - form의 action으로 file을 첨부하고 submit하여 보낼 때
```
  // enctype 속성을 사용한다면 무조건 이렇게 해줘야한다.
  <form action="./upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
```
  - Ajax으로 통신할 때
```
  // Ajax를 사용하는 페이지에서 meta태그를 입력해준다
  <head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
  </head>

  // Ajax를 사용하는 페이지에서 script태그 내에 입력해준다.
  $(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
      xhr.setRequestHeader(header, token);
    });
  });
```
