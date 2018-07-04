# 안드로이드 String.xml 호출법
  앱을 국제화 시켜줄 때는 String.xml을 사용하는 것은 무조건이다. 
  사용법을 알아보자.
```
  <resources>
    <string name="str_name">dingdong</string>
  </resources>
```

### in jAVA
```
  1. R.string.str_name  
  2. getResource().getString(R.string.str_name);

  만약 java에서 나오는 값과 문자열을 추가해서 사용하고 싶다면, string.xml에서
  <string name="str_name"> %1$s가 %2$s을 %1$d번 먹는다.</string> 라고 줄 수 있다.
  %1$s: string, %1$d: int
  3. getResource().getString(R.string.str_name, "정수", "밥", 2);

```
### in XML
```
  1. @string/str_name
```