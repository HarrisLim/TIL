# 정규표현식

### 정수 실수 걸러내기
```
  // 정수
  var regexp = /[^0-9]/g;
  v = $(this).val();
  if(regexp.test(v)) $(this).val(v.replace(regexp,'')); // 걸러낸 값으로 다시 대입

  // 실수
  var regexp = /[^-\.0-9]/g;
  v = $(this).val();
  if(regexp.test(v)) $(this).val(v.replace(regexp,'')); // 걸러낸 값으로 다시 대입
```

``` 특정문자 치환 
  // undefined가 뜨는 id가 test인 곳의 text에서 undefined를 ''로 치환 
  var regex = /.*undefined/g; // g는 전체를 순환하면서 전체적으로 모두 찾는 것.
  $("#test").html($("#test").html().replace(regex, ''));
```