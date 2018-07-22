# handling with jQuery

### input태그 내에 readonly 속성 추가/제거
```
  // 추가
  $(".phoneNum").attr("readonly":true);
  // 다중 추가
  $(".phoneNum").attr({"readonly":true, "type":"submit"});
  // 삭제
  $(".phoneNum").removeAttr("readonly");
```