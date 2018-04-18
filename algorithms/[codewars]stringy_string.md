# Stringy Strings // codewars
  - size를 입력받아서 size만큼 101010을 출력하는 것이다

### 내 코드
  - 나는 그냥 String을 이용하여 if문으로 풀었다.
```
public class Kata {
  public static String stringy(int size) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++){
    if (i % 2 ==0) { 
      builder.append(1); 
    } else {
      builder.append(0); 
    }
    }
    return builder.toString();
  }
}
```
### 고수 코드
  - 이 분은 현명하게 문제를 풀어서 정리하고 싶다
  - &(비트 연산자)를 사용하여 풀었다. 나도 다음에는 0과 1만 필요로하는 문제가 나오면 비트 연산자를 쓰자 !
  - 또한 StringBuilder 클래스가 있다는 것도 배웠다.
```
public class Kata {
  public static String stringy(int size) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < size + 1; i++)
      sb.append(i & 1); // 만약 size가 5이면 1과 1을 and하고 2와 1을 and하고 그 값을 append한다
    return sb.toString();
  }
}
```

### 배운 것
- StringBuilder클래스가 있다는 것
- 0과 1을 사용할 때는 비트연산자를 사용하면 코드가 훨씬 깔끔하게 나온다는 것
- if문으로만 모든 것을 하려고 하지 말 것