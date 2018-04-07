# Modifier (제한자) 
클래스, 인터페이스, 변수, 메소드, 생성자 앞에 붙어서 그 기능을 제한하는 예약어

  - 접근제한자(Access Modifier)

    1. public : 제한이 없다.
    2. protected : 같은 패키지 안 or 다른 패키지여도 상속관계의 부모 자원
    3. default : 같은 패키지 안
    4. private : 자신의 클래스 안

    - 넓은 범위 순서
      - public > protected > default > private
    - 강력한 순서
      - private > default > protected > public

  - 소유제한자(static)

      붙으면 클래스 소유, 안붙으면 객체 소유

    - 특징
      - class와 constructor앞에는 못 붙음 (member, method앞에만 붙어)
      - static method 내에서는 객체 생성없이 static 자원을 호출 가능 (해당 자원에 클래스이름이 생략됨)
      - local variable에는 static이 못붙음 (member variable만이 소유의 대상이 됨)
      - 다른 클래스에서 static 자원을 호출할 때는 클래스 이름을 생략할 수 없음 (같은 클래스 내에서만 가능)
      - static 자원은 메모리에 오직 1개 생성되고, 해당 클래스의 모든 객체에게 공유되어짐
      - static 메소드에서는 this나 super를 사용할 수 없음. (this, super는 객체나 부모의 객체를 가리키는 예약어)

  - 수정제한자(final)

      붙으면 수정을 할 수 없음

    - 특징
      - 클래스, 변수, 메소드 앞에 사용 가능 (생서자는 오버라이딩이 불가하므로 사용 불가.)
      - 클래스 앞에 붙으면 자식을 못 낳고, 변수 앞에 붙으면 초기값을 못 바꾸고, 메소드 앞에 붙으면 오버라이딩 불가
      - 멤버변수에 static final이 많은 이유는, final은 어차피 초기값은 변경할 수 없으니까 클래스 소속으로 만들어서 메모리에 1개만 생성.

  - 추상제한자(abstract)

      붙으면 추상화가 됨

    - 특징
      - class또는 method앞에만 붙을 수 있음
      - abstract가 붙은 클래스를 추상클래스
      - abstract가 붙은 메소드를 추상메소드
      - 하나 이상의 추상메소드만 있어도 추상클래스가 되어야 함
      - 생성자 앞에 붙을 수 없음. (생성자는 오버라이딩이 불가하기 때문)
      - 추상클래스 객체는 완벽한 자식 클래스를 만들어 그 객체를 형변환하여 생성함
      - 추상클래스에는 일반메소드도 포함 가능함
      - final abstract를 함께 사용할 수 없음 (abstract는 자식에서 오버라이딩을 해야하는데, final은 오버라이딩을 불가하게 해)
      - static abstract를 함께 사용할 수 없음 (추상클래스는 객체화 불가, static은 객체화하지 않아도 호출 가능하기 때문에 문법적으로 에러 발생)

 
 
출처 : 김형수선생님