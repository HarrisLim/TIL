# AJAX
  - Asynchronous JavaScript and XML의 준말
  - 비동기방식으로 웹페이지의 특정 부분만 서버에 요청하고 응답받을 수 있다

### 기본 form
```
  function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      // 브라우저에 코드가 완료되면 4번 리턴, 정상적으로 처리되면 상태코드 200. (401, 500같은 상태코드)
      if (this.readyState == 4 && this.status == 200) {
        // responseText는 text를 데이터폼에서 받아와서 사용.
        document.getElementById("demo").innerHTML = this.responseText;
      }
    };
    // "ajax_info.txt"이 부분이 데이터폼이다. txt, xml, jsp등 반환형식을 맞춰주면 사용이 가능하다
    xhttp.open("GET", "ajax_info.txt", true);
    xhttp.send();
  }
```
### data 넘기는 형식
  - txt
    ```
      이렇게 그냥 텍스트형식으로 쓰면 된다
    ```
  - jsp
    ```
      out.write("여기에 쓰는 데이터가 넘겨진다");
    ``` 
  - xml
    ```
      <CD>
        <TITLE>Happy</TITLE>
        <ARTIST>Pharrell Williams</ARTIST>
      </CD>
      <CD>
        <TITLE>Everglow</TITLE>
        <ARTIST>Cold Play</ARTIST>
      </CD>
    ```
  - json
    ```
      {"title":"Happy", "artist":"Pharrell Williams"},
      {"title":"Everglow", "artist":"Cold Play"}
    ```

### jQuery를 활용한 AJAX
#### load
```
  $(document).ready(function(){
    $("button").click(function(){
      $("#div1").load("demo_test.txt");
    });
  });
```
#### get
```
  $(document).ready(function(){
    $("button").click(function(){
      $.get("test.jsp", function(data, status){
        // data는 test.jsp에서 읽어온 데이터, status는 현재 상태 error인지 성공인지, 
        alert("Data: " + data + "\nStatus: " + status);
      });
    });
  });
```
#### post
```
  $(document).ready(function(){
    $("button").click(function(){
      // test.jsp에서 request.getParameter로 밑의 데이터를 받아서,
        다시 밑의 function()에 있는 data로 넘길 수 있다.
      $.post("test.jsp", 
        {
          name: "홍길동",
          city: "대전시"
        },
        function(data,status){
          alert("Data: " + data + "\nStatus: " + status);
        });
    });
  });
```
#### ajax
  - $.get이나 $.post를 쓰는 것 대신 $.ajax를 쓰면 get과 post를 지정해주지 않아도 되서 좀 더 편하지 ?
```
  function showHint(str) {
    var request = $.ajax({
      url: "ajax.jsp",
      method: "GET",
      data: { q : str },
      dataType: "html"
    });
    request.done(function( msg ) {
      $("#txtHint").html( msg );
    });

    // 밑에 것은 안해봤지만 요청 실패하면 하는 거겠지
    request.fail(function( jqXHR, textStatus ) {
      alert( "Request failed: " + textStatus );
    });
  }
```