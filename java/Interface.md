# Interface (== 통로, 틀, 껍데기)
  모든 멤버가 상수이고, 모든 메소드가 추상메소드인 **껍데기**

  - 특징
    - interface앞에 붙는 접근제한자는 public과 default만 가능
    - 객체는 완벽한 자식클래스를 생성해서 형변환해서 생성 (ex, A a = new AChild();)
    - 자식클래스를 정의할 때 implements를 사용 (like extends)
    - interface앞에는 abstract가 생략되어 있다
    - 모든 멤버에는 public static final이 생략되어 있다
    - 모든 메소드에는 public abstract가 생략되어 있다
    - static abstract method()를 사용할 수 없음 (static과 abstract가 충돌)
    - 당연히 생성자 생성 불가
    - class에서 interface를 상속받을 때, 다중상속 가능 (ex, class AChild implements A, B, ...)
    - interface끼리는 extends로 다중상속 가능 (ex, interface C extends A, B, ...)
      add) class끼리는 다중상속 불가. 단일상속만 가능 
      add) 클래스에서 접근제한자가 생략된 생성자는 클래스의 접근제한자를 따라가

  - 추상클래스와의 비교
    - 공통점
      - 추상메소드가 있을 수 있다
      - 자식객체를 생성하고 형변환해서 객체를 만든다

    - 차이점
      - class이고 interface이다
      - 추상클래스는 일반메소드 or 변수 or 생성자를 포함할 수 있다

  - 인터페이스와 추상클래스를 왜 사용하는가?
    - 내생각 : 메소드의 이름을 정해놓고, 메소드 이름의 설정을 제한하려고 ? 같이 프로젝트할 때 ?