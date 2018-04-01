
# this and super

### this
  - 정의
    -자신의 객체 or 자신의 생성자를 의미하는 대명사  

  - 용법
    - 지역변수와 이름이 같은 멤버를 해당지역에서 접근할 때
    - 생성자에서 자신의 다른 생성자를 호출할 때  
	  ```
	    ex) B(){
	          this(20);
	        }
            B(int age){
              this.age = age;
            }
      ```  

    - 자신의 객체를 다른 클래스에게 넘겨줄 때  
      ```
        ex)	
          class C {
            String name = "정수";
            int age = 10;

            void create(){
              CUser user = new CUser(this);
              name = "test";
              user.use();
            }
            void m(){
            System.out.println("m()");
            }
            public static void main(String[] agrs){
            new C().create();
            }
          }
          class CUser	{
            C c;
            CUser(C c){
            this.c = c;
            }
            void use(){
              System.out.println("name -> " + c.name + ", age -> " + c.age);
              c.m();
            }
          }
      ```  

### super
  - 정의
    - 부모의 객체 or 부모의 생성자를 의미하는 대명사

  - 사용
    - 이름이 같은 부모의 멤버를 자식클래스에서 접근할 때
    - 오버라이딩되기 전의 부모메소드를 자식에서 호출할 때
    - 자식에서 부모의 생성자를 호출할 때  
      ```
        class D {
          String name = "부모이름";
          D(String name){
            this.name = name;
          }
          void m(){
            System.out.println("D의 m()");
          }
        }

        class DChild extends D {
          String name = "자식이름";
          DChild(){
            super("이박명");
          }
          void show(){
            System.out.println("name -> " + this.name);
            System.out.println("name -> " + super.name);
            m();
            super.m();
          }
          void m(){
            System.out.println("DChild의 m()");
          }
        }
        
        class DUser {
          public static void main(String[] agrs){
            DChild dc = new DChild();
            dc.show();
          }
        }
      ``` 