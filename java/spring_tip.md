# Spring 꿀팁

### @RequestParam 쉽게 하기 in Controller on Spring
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

### 경로 변수화 in Controller on Spring
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

### 4가지 스코프 추가하는 법 in Controller on Spring
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
  1. DaoImpl or ServiceImpl에서 @Repository("xx")로 이름을 준다.
  2. ServiceImpl or Controller에서 @Autowired -> @Resource(name="xx")로 바꿔주면 해결 :)
  - ex)
```
  @Autowired ->  @Resource(name="xx")
```
