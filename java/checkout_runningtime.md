# java running time 확인법
  - 확인하고 싶은 부분을 묶어서 확인한다.
```
  long startTime = System.currentTimeMillis();

  확인하고 싶은 부분을 여기에 쓴다.

  long endTime = System.currentTimeMillis();
  long lTime = endTime - startTime;
  System.out.println("TIME : " + lTime + "(ms)");
```