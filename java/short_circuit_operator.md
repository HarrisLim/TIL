# Short Circuit Operator
  - 논리연산자에서 and(&&, &)과 or(||, |)를 사용한다
  - Short Circuit이란 게으른 녀석인데, 코드를 보면서 정리하겠다 !

#### Short Circuit이 적용된 OR // i==0 true니까, i==1을 검사조차 하지 않는다
  ```
    int i=0, j=0;
    if(i==0 || i==1){
      System.out.println("출력!");
    }
  ```
#### Short Circuit이 적용되지 않은 OR // i==0 true지만, i==1까지 검사한다
  ```
    int i=0, j=0;
    if(i==0 | i==1){
      System.out.println("출력!");
    }
  ```
  
#### Short Circuit이 적용된 AND // i==1 false니까, i==0은 검사조차 하지 않는다
  ```
    int i=0, j=0;
    if(i==1 && i==0){
      System.out.println("출력되지 않음!");
    }
  ```

#### Short Circuit이 적용되지 않은 AND // i==0 false지만, i==0까지 검사한다
  ```
    int i=0, j=0;
    if(i==1 & i==0){
      System.out.println("출력되지 않음!");
    }
  ```