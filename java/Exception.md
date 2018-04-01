# Exception (예외)
  프로그램이 정상적으로 진행되지 못하게 하는 **돌발행동**

  - 예외 관련 상속도
```
      Object
        |
     Throwable
      |      |
   Error    Exception
            |       |
 CheckedException  RuntimeException
                    |  "is a 관계" ex) NumberFormatExceptoin is a RuntimeException
                  NumberFormatException
```  

  - 설명  
    Error는 자바 시스템 관련 문제 시 발생하는 것이므로 프로그래머가 할 수 없음
    Exception만 프로그래머가 잡을 수 있는데,  
    **CheckedException**은 컴파일러가 컴파일 시에 체킹하는 예외이고,  
    **RuntimeException**은 실행시에 체킹하는 예외로써 처리를 안하더라도 컴파일이 정상적으로 수행됨

  - 예외처리의 목적
    - 프로그램 진행 시 발생할 수 있는 돌발상황을 예외로 미리 정해놓고 해당하는 예외가 발생할 경우, 적절한 조치를 취해서 프로그램이 정상적으로 작동하도록 하는 것

  - 예외의 특징
    - 예외는 생성자나 메소드에서만 발생
    - 클래스 설계 시 예외를 발생시킬 때는 **throw**사용 // throw : 강제적으로 예외 발생
    - 예외가 발생하는 생성자나 메소드는 **throws**를 사용해서 명시함 // throws : 예외처리를 하라는 명시
    - 예외는 자신의 Exception 또는 그 상위(부모)Exception으로 잡을 수 있음
    - 상속관계의 예외처리 catch문의 순서는 자식예외부터 나와야한다. (구체적인 예외처리 or 세련된 예외처리)

  - 처리 방법
    - 예외를 잡아버리는 법
      ```
        try{
          예외를 발생시키는 구문
        }catch(발생된 예외 객체){
          (넘어온 예외객체를 이용한) 예외 처리 로직
        }
      ```
    - 예외를 떠넘기는 법
      - throws 이용(호출한 곳 or JVM으로)
      - JVM으로 넘기면(main에서 throws하는 것) 컴파일은 되지만 실행하면 콘솔창에 Exception예외 출력

  - try 블럭 안에 2개 이상의 예외 발생 시 처리
    - 상속관계의 Exception이라면, 하위(자식) 예외부터 잡아줌
    - 상속관계가 아닌 Exception이라면, catch절의 순서는 상관 없다

  - finally절
    try절이 수행된 후 or try catch절이 수행된 후 **항상 수행되는 절**

    - 특징
      - try{}이 먼저 나와야 기술될 수 있음
      - try{}이 기술되면 반드시 catch{} or finally{} or catch{} finally{}가 나와야 한다
      - return을 만나도 수행된다
        add) System.exit(정수); // 얘는 못이겨