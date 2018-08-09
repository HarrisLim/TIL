# ajax에서 controller로 map(객체)보내기
  - java단에서 map으로 안만들고, 그냥 뷰단에서 map으로 만들고 JSON형식으로 만들어 java단으로 보낸다.
  - *기억하자. @RequestParam: parameter를 받아온다. @RequestBody: json형식을 받아온다.
### - ajax
```
  // map객체 생성
  var Map = {};
  
  // Map에 데이터 추가
  Map.privateMemo = "first";
  Map.build_no = "second";

  var Maps = JSON.stringify(Map);
  $.ajax({
    type: 'POST',
    dataType: 'JSON',
    contentType:'application/json; charset=utf-8',
    data: Maps,
    url: 'updatePrivateMemo.do',
    success: function(json) {
      alert("hi: "+json);	
    }
  });
```

### - controller(java)
```
  // *주의, ResponseBody와 RequestBody를 착각하지 말고 꼭 써줘야한다.
  @ResponseBody
  @RequestMapping(value="house/updatePrivateMemo.do", method=RequestMethod.POST)
  public int updatePrivateMemo(@RequestBody Map<String, Object> map) {
    System.out.println("build_no: "+ map.get("build_no"));
    System.out.println("privateMemo: "+ map.get("privateMemo"));
    return 1;
  }
```