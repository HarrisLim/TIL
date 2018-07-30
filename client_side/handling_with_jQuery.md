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

### 형제 태그 삭제
```
  // 이전 형제 노드
    $("#test").prev();
    $("#test").prevAll();  // #test를 기준으로 이전 '모든' 형제 노드

  // 다음 형제 노드
    $("#test").next();
    $("#test").nextAll(); // #test를 기준으로 다음 '모든' 형제 노드

  // 이전/다음 형제노드 모두
    $("#test").siblings();
```