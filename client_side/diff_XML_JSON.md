# XML과 JSON코드 비교
### XML코드
  - 장점: 가독성이 좋다.
  - 단점: 메타데이터(설명하는 태그)가 많다
  ```
    <?xml version="1.0" encoding="utf-8 ?>
    <books>
    <book kind="IT">
      <title>오라클</title>
      <author>정수</author>
      <price>15000</price>
    </book>
    <book kind="IT">
      <title>자바</title>
      <author>승균</author>
      <price>10000</price>
    </book>
    </books>
  ```

### JSON코드
  - 장점: 실질적으로 보내는 데이터만 쓰기때문에, 데이터가 XML보다 적다
  - 단점: 가독성이 좋지 않다
  ```
    {"books": [
        {"kind":"IT", "title":"오라클", "author":"정수", "price":"15000"},
        {"kind":"IT", "title":"자바", "author":"승균", "price":"10000"}
      ]
    }
  ```