# Spring 꿀팁

### @RequestParam 쉽게 하기 in Controller
  - post방식으로 넘겨올 때, @RequestParam을 여러개 써야한다.<br>
    그런데 DTO를 바로 파라미터로 가져올 수 있기 때문에 DTO를 쓰는 게 더 편하고 간결하다.
  - 여기서 DTO에 있는 생성자를 사용하는 것이 아니라 setter를 사용하는 것을 기억!
  - ex) (1)을 (2)로 사용가능
```
(1)
  @RequestMapping(value="board/update.do", method=RequestMethod.POST)
  public String update(@RequestParam("seq") long seq,
                       @RequestParam("writer") String writer, 
                       @RequestParam("email") String email,
                       @RequestParam("subject") String subject,
                       @RequestParam("content") String content) {
    Board board = new Board(seq, writer, email, subject, content, null);
    service.updateService(board);
    return "redirect:/board/list.do";
  }

(2)
  @RequestMapping(value="board/update.do", method=RequestMethod.POST)
  public String update(Board board) {
    service.updateService(board);
    return "redirect:/board/list.do";
  }
```

### 경로 변수화 in Controller
  - 패스(경로)를 변수화 시킬 수 있다.
  - 이미 write.do가 있으면 여기로 안오고 write.do를 찾아가지만,<br>
    list2.do가 없을 때, list2.do로 하면 list2.jsp페이지로 간다.<br>
    그런데 같은 형태로 board/{view}를 2개 이상 만들면 어디를 찾아갈지 몰라서 어디를 갈지를 몰라.
  - 하나의 Controller가 공유될 수 있는 것을 확인할 수 있다는 것을 확인 가능<br>
    그런데 모든 것을 통합하면 유지보수가 힘들지.
  - ex) (1)을 (2)와 (3)으로 사용할 수 있지
```
(1)
  @RequestMapping("board/list.do")
  public ModelAndView list() {
    List<Board> list = service.listService();
    String view = "/board/list";
    ModelAndView mv = new ModelAndView(view, "list", list);
    return mv;
  }

(2)
  @RequestMapping("board/{view}.do")
  public ModelAndView list(@PathVariable String view) {
    List<Board> list = service.listService();
    view = "/board/"+view;
    ModelAndView mv = new ModelAndView(view, "list", list);
    return mv;
  }

(3)
  @RequestMapping("{dir}/{view}.do")
  public ModelAndView list(@PathVariable String dir, @PathVariable String view) {
    List<Board> list = service.listService();
    view = "/"+dir+"/"+view;
    ModelAndView mv = new ModelAndView(view, "list", list);
    return mv;
  }
```

### 4가지 스코프 추가하는 법 in Controller
#### Application
  - ServletContext application = session.getServletContext();

#### Session (메소드 파라미터 내)
  - HttpSession session

#### Page (메소드 파라미터 내)
  - Object page

#### Request (메소드 파라미터 내)
  - HttpServletRequest request
```	
  public String xxxx(HttpSession session, HttpServletRequest request, Object page) {
    System.out.println("session: "+ session);
    ServletContext application = session.getServletContext();
    System.out.println("request: "+ request);
    System.out.println("page: "+ page);
    System.out.println("application: "+ application);
  }
```

### DaoImpl or ServiceImpl이 겹칠 때
  - (DAO or Service) Interface는 하나인데 (DAO or Service)를 구현하는 클래스가 2개 이상이면<br>
    serviceImle에서 누구를 가져올지 몰라서 404 Error !!
  - 해결책 
    1. DaoImpl or ServiceImpl에서 @Repository("xx")로 이름을 준다.
    2. ServiceImpl or Controller에서 @Autowired -> @Resource(name="xx")로 바꿔주면 해결 :)
    - ex)
```
  @Autowired ->  @Resource(name="xx")
```

### ModelAndView 클래스로 attribute 2개 이상 보내기
```
  ModelAndView(view, "list", list); // 이런 식으로 view : 경로, "list": key, list: value 이렇게 했었다.

  ModelAndView mv = new ModelAndView();
  mv.setViewName("/board/list");
  mv.addObject("page", page);
  mv.addObject("paging", paging);
```

### AJAX
#### response객체에 JSON문자열 담기
  - Controller에 JSON객체를 만들어주는 코드가 있다
```
  @RequestMapping(value= "ajax_t01/search01.do", method=RequestMethod.GET)
  public void ajaxView01(@RequestParam("seq")int seq, HttpServletResponse response)  {
    Address address = service.selectService(seq);
    String addressJson = "null";
    if(address != null){
      addressJson = "{\"seq\":\""+address.getSeq()
      +"\",\"name\":\""+address.getName()
      +"\",\"addr\":\""+address.getAddr()
      +"\",\"rdate\":\""+address.getRdate()+"\"}";
    }
    try {
      response.setContentType("Application/json;charset=utf-8");
      response.getWriter().print(addressJson); // 브라우저에 써주는거야.
      }catch (IOException e) {
      e.printStackTrace();
    }    
  }
```
#### ObjectMapper (Object는 DTO를 의미)
  - Controller에 JSON객체를 만들어주는 코드가 없고 ObjectMapper(made by codehaus, not by Spring)를 사용.
  - 당연히 Spring의 것이 아니기 때문에 pom.xml에 ObjectMapper를 사용하는 코드를 추가해줘야한다.
```
  @RequestMapping(value= "ajax_t02/search01.do", method=RequestMethod.GET)
  public void ajaxView01(@RequestParam("seq")int seq, HttpServletResponse response)  {
    Address address = service.selectService(seq);
    ObjectMapper mapper = new ObjectMapper();
    try {
      response.setContentType("Application/json;charset=utf-8");
      response.getWriter().print(mapper.writeValueAsString(address));
    }catch (IOException e) {
      e.printStackTrace();
    }       
  }
```

#### @ResponseBody
  - 코드가 굉장히 짧아진다. @ResponseBody와 return타입을 써주면 사용가능.
```
  @RequestMapping(value= "ajax_t03/search01.do", method=RequestMethod.GET)
  public @ResponseBody Address ajaxView01(@RequestParam("seq")int seq)  {
    Address address = service.selectService(seq);
    return address;
  }
```

### 파일 업로드 and 다운로드
#### 업로드
  - 파일을 post방식으로 서버에 보내서 서버에 등록
  - CommonsMultipartResolver객체와 commons-fileupload, commons-io의 jar파일 필요
```
  - servlet-context.xml -
    <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
      <beans:property name="defaultEncoding" value="UTF-8" />
      <beans:property name="maxUploadSize" value="10485760" />
    </beans:bean>

  - pom.xml -
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.2.1</version> 
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.5</version>
    </dependency>
```
#### 다운로드
  - 이건 jsp로 만든 View가 아니라 JAVA로 구현한 View이다
```
  - servlet-context.xml -
    <beans:bean id="fileDownloadView" class="jeong.spring3.file.view.FileDownloadView" />
      <beans:bean id="fileViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
      <beans:property name="order" value="0" />
    </beans:bean>
```

### 패키지 생성 순서
  - 하위폴더를 먼저 만들어서는 안된다. 하위폴더를 먼저 만들고 상위폴더를 만들 수 없어
  - ex)
```
  1. jeong.spring3.file.model
  2. jeong.spring3.file.model.service
```

### 자주 사용하는 Resources 경로 잡아주기
  - /의 유무를 주의하자
  - ex
```
  - servlet-context.xml - 
    <beans:beans>태그 내에 추가
    <resources mapping="/js/**" location="/resources/js/" />
  
  - 사용하는 jsp - 
    <script type="text/javascript" src="./js/stringBuffer.js"></script>

  만약, servlet-context에서 설정해주지 않는다면 밑에 처럼 resources를 추가해야한다. (귀찮아)
  <script type="text/javascript" src="resources/js/stringBuffer.js"></script>
```

### Tiles framework(from apache)
  - Spring 3.2버전 이상이여야 한다.
  - 실제 웹사이트와 같이 구조를 나누어서 사용할 수 있다.
  - 필요한 클래스와 beans를 추가해서 사용해야 한다.
  - 각 페이지마다 나눠주는 설정을 하는 xml파일을 추가해야 한다.
  - header나 footer를 고정시켜놓고 body만 변경할 수 있다.