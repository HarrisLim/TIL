# Innerclass
  클래스 내부에 정의되는 클래스

  - 특징
    - 모든 접근제한자가 올 수 있음
    - static이 올 수 있다 (기존의 class 앞에는 static 사용 불가)
    - 내부클래스가 static자원을 하나만 가져도 static 내부클래스가 되어야 한다 (main()이 존재하려면 static 내부클래스가 되어야 한다)
    - static 내부클래스에는 외부클래스의 static멤버나 static메소드만 접근 가능 (객체를 생성하면 당연히 static이 없어도 사용 가능)
    - 내부클래스 객체 생성방법
      - 일반 내부클래스
        ```
          Outter 객체이름 = new Outter생성자();
          Outter.Inner 객체이름 = Outter의 객체이름.new Inner생성자();
        ```
      - static 내부클래스
        ```
          Outter.Inner 객체이름 =  new Outter.Inner생성자();
        ```

  - 사용 목적
    - 외부클래스의 자원을 자기의 것처럼 사용하기 위해

```
class B{
  static class A{
    static int i;
    int j;
  }
  class C{
  	String name = "내부클래스";
  }
}

class BUser{
  public static void main(String args[]){
    B b = new B();
    B.C c = b.new C();
    System.out.println("c.name -> " + c.name);

    System.out.println("B.A.i -> " + B.A.i);
    B.A a = new B.A();
    System.out.println("a.j -> " + a.j);
  }
}

```