# Overriding and Overloading

### Overriding
  - 정의
    - 상속 관계에서 부모의 메소드를 자식에서 바꾸는 것

  - 특징
    - 자식 메소드의 접근제한자가 부모 메소드의 접근제한자보다 넓거나 같아야 한다.  
      (public > protected > default > private)
    - 반환 타입이 일치해야 함.
    - 파라미터의 타입과 순서가 같아야 함 (식별자는 상관없어)
    - 생성자는 오버라이딩 개념이 없어  
      ```
        ex)
          class A {
            void m(int i, String str){
              System.out.println("A의 m()");
            }
          }

          class AChild extends A {
            void m(int i, String str){
              System.out.println("AChild의 m()");
            }
          }
      ```
	
### Overloading
  - 정의
    - 파라미터의 타입이나 갯수나 순서가 다른 같은 이름의 생성자나 메소드를 여러개 정의하는 것

  - 종류
    - 생성자
    - 메소드

  - 특징
    - 파라미터의 타입이나 순서가 달라야 함
    - 접근제한자는 신경쓰지 않아
    - 반환 타입도 신경쓰지 않아
    - 형변환이 가능한 파라미터 타입이라도 오버로딩으로 인정함
      ```
        ex)	
          void m(){
            System.out.println("m()");
          }
          void m(int i){
            System.out.println("m(int i)");
          }
          void m(int i, String str){
            System.out.println("m(int i, String str)");
          }
          int m(int i){
            System.out.println("int m(int i)");
          }
      ```
