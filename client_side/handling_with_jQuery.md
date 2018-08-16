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

### ID 존재여부 확인
```
  $("#test").length // 존재하면 1, 존재하지 않으면 0

  * javacript
    document.getElementById("test"); // 존재하면 해당 태그, 존재하지 않으면 null
```

### name이 test인 태그 모두 가져오기
```
  $("input[name=test]);

  // 만약 test가 radio버튼이라면 체크된 버튼의 value를 추출
  $("input[name=test]:checked").val();
```

### 선택자의 부모, 형제, 자식 개수 확인
```
  // 부모
  $("#test").parents().length;

  // 형제
  $("#test").siblings().length;

  // 자식
  $("#test").children().length;
```

### 선택자 안의 원하는 태그 잡기
```
  // id가 test인 태그 자식에서 input태그를 잡는다.
  $("#test input");
```

### Select box에서 index값 가져오기
```
  $("#test")[0].selectedIndex;
```

### 선택된 checkbox의 check제거
```
  // 여기서 주의해야할 점은, attr로 하면 내가 직접 선택한 것은 선택해제가 안되고 
  // jquery를 사용해서 선택해제한 것만 해제가 된다. 그래서 prop을 사용해야한다. *주의.
  $("#test [type=checkbox]").prop('checked',false);
```

### 숫자인지 확인하기
```
  var int = 1;
  isNan(int) // false -- 숫자면 false를 반환
  jQuery.isNumeric(int) // true

  var int ="1"; // 문자열로 저장해도 똑같은 결과다. 
  isNan(int) // false -- 숫자면 false를 반환
  jQuery.isNumeric(int) // true
```