## Controller에서 view로 데이터 전송
```
  req.setAttribute("list", list); // key, value
  // jsp페이지에서 request.getAttribute("list"); 이렇게 받는다.
```

## view에서 Controller로 데이터 전송
  - jsp에서 (html 태그 내에 있는)name을 식별자로 한다
```
  만약, jsp에서 name="addr" 이라면
  Controller에서 String addr = req.getParameter("addr");로 받아온다
```