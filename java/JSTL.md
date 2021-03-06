# JSTL (JSP Standard Tag Library) [api](https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/)

### 비교연산자
```
  java    jstl
   ==  ->  eq
   !=  ->  ne

  * list나 map안에 데이터가 있느냐를 확인하는 empty
```
### for문
```
  <c:forEach items="${buildList }" var="build"> // buildList는 controller에서 보내주는 Key값
    이 부분에 build.xx (xx: DTO안에 있는 멤버변수)로 값을 가져올 수 있다. 
  </c:forEach>

  // index사용
  <c:forEach items="${List }" var="L" varStatus="loop">
    ${loop.index} // 0부터 시작하는 index
  </c:forEach>
```

### if else문
  - jstl에서는 if라고 하지않고 choose라고 한다. 또한, else라고 하지않고 otherwise라고 한다.
```
  <c:choose>
    <c:when test="${build.buildState eq 1 }">
      1입니다
    </c:when>
    <c:when test="${build.buildState eq 2 }">
      2입니다
    </c:when>
    <c:otherwise>
      else입니다
    </c:otherwise>
  </choose>
```

### controller와 jsp간의 2차원 배열
```
  - controller - 
//선언
  HashMap<BuildDTO, List<PriceDTO>> mapList = new HashMap<BuildDTO, List<PriceDTO>>(); 
  List<BuildDTO> buildList = mrService.mrBuildSelectService(estateNo);

// 데이터 삽입 
  for(int i=0; i<buildList.size(); i++) {
    List<PriceDTO> priceList = mrService.mrPriceSelectService(buildList.get(i).getBuild_no());
    mapList.put(buildList.get(i), priceList);
  }

// jsp로 보내기 위해 mv에 추가 (mv: ModelAndView) 
  mv.addObject("buildList", buildList);
  mv.addObject("mapList", mapList);


  - jsp - 

  <c:forEach items="${buildList }" var="build">
    <c:forEach items="${mapList.get(build) }" var="price">
      <c:choose>
        <c:when test="$price.monthly ne 0}">
          ${price.monthly} / ${price.deposit}
        </c:when>			       						
      </c:choose>
    </c:forEach>
  </c:forEach>
```

### and or 조건
  - java와 같다.
```
  and: &&, or: ||
  <c:when test="${mapList.monthly eq 0 && mapList.lease ne 0 || mapList.salePrice eq 0}">
```

### 날짜형식 변경 (fmt)
```
  // 1. 최상단에 fmt를 사용하겠다는 코드 한 줄을 추가
  <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

  // 2. ex) qna.qna_rdate라는 넘어온 date값의 format을 yyyy.MM.dd로 변경
  <fmt:formatDate value="${qna.qna_rdate }" pattern="yyyy.MM.dd"/>
```