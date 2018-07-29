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

### 클래스 추가/삭제
```
  // 추가
  $("#test").addClass("active");
  // 다중 추가
  $("#test").addClass("active name kanu");
  // 삭제
  $("#test").removeClass("active");
```

### 원하는 부모 태그 삭제
```
  // 삭제
  this.parentNode.parentNode.removeChild(this.parentNode);
```

