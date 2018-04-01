# Exception (예외)
  - 
    - 프로그램이 정상적으로 진행되지 못하게 하는 **돌발행동**

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
      )
    - add,
      Error는 자바 시스템 관련 문제 시 발생하는 것이므로 프로그래머가 할 수 없음
      Exception만 프로그래머가 잡을 수 있는데,  
      CheckedException은 컴파일러가 컴파일 시에 체킹하는 예외이고,  
      RuntimeException은 실행시에 체킹하는 예외로써 처리를 안하더라도 컴파일이 정상적으로 수행됨

  - 예외처리의 목적
    - 프로그램 진행 시 발생할 수 있는 돌발상황을 예외로 미리 정해놓고 해당하는 예외가 발생할 경우, 적절한 조치를 취해서 프로그램이 정상적으로 작동하도록 하는 것
    