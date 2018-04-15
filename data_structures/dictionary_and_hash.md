# Dictionary (== Map)
  - [key, value]로 값을 쌍으로 저장한다. key는 value를 찾기 위한 식별자이다(key값은 중복x, value값은 중복o)
  - ex) 주소록, 어학사전
```
  dictionary.set(1, "사과");
  dictionary.set(2, "사과");
  dictionary.set(3, "딸기");
  이렇게 dictionary에 원소를 넣으면, (물론, dictionary클래스를 미리 만들어야 한다)
     key           value
    [ Gandalf ]   [ 사과 ]
    [ John ]      [ 딸기 ]
    [ Tyrion ]    [ 사과 ]
  이런 식으로 저장될 수 있다. 정렬을 해주지 않는다.
  dictionary.get(Gandalf); // 사과
  dictionary.get(John); // 딸기
```
  
# HashTable (== HashMap)
  - [key, value]로 값을 쌍으로 저장한다. key는 value를 찾기 위한 식별자이다(key값은 중복x, value값은 중복o)
  - Hashing: 특정 값을 가장 신속하게 찾는 방법
  - key에 해당하는 value를 찾아주므로 조회 속도가 빠르고 간단하다
```
  가장 흔한 해시 함수는 "루즈루즈해시함수"이다
  루즈루즈해시함수란, 문자열에서 문자 하나 하나의 ASCII값을 구해서 모두 더한 값을 key로 사용하는 것이다

  Gandalf: 71 + 97 + 110 + 100 + 97 + 108 + 102 -> 685(해시값)
  John: 74 + 111 + 104 + 110  -> 399(해시값)

  Gandalf가 key로 입력되면 685로 변환하여 key로 사용한다
  이것이 lose lose hash funtion

  루즈루즈 해시함수를 개선한 함수가 있다. djb2함수이다
  코드로 보자

  var djb2HashCode = function(key){
    var hash = 5381; // djb2함수에서는 5381로 쓰는 것이 일반적이다
    for(var i=0; i<key.length; i++){
      hash = hash * 33 + key.charCodeAr(i); // 33또한 일반적
    }
    return hash % 1013 // 1013은 그냥 1000 이상의 수를 써준 것이다
  }
```
  - Dicitonary나 HashTable의 key값은 중복이 안되는데, ASCII값으로 해시값을 구하다보면 중복될 수 있다  
    중복되면, 나중에 입력된 값이 먼저 입력된 값을 덮어쓰기 때문에 원소의 손실이 발생한다
  - 해결 방법 3가지
    - 체이닝 (separate chaining)
      - 테이블의 인덱스별로 연결 리스트를 생성해 그 안에 원소를 저장하는 기법
    - 선형 탐사 (linear probing)
      - 새 원소 추가 시 인덱스가 이미 점유된 상태라면 인덱스를 1증가시키면서 점유되지 않은 공간까지 찾는다
    - 이중 해싱
      - 충돌이 발생하면 기존의 해싱 함수와는 다른 추가적인 해싱 함수를 이용해 주소를 증가시켜 다시 조사한다

### 체이닝
```
 [ 5]--> [Jonathan][사과]-->[Jamie][딸기]-->[Sue][귤]-->null
   .
   .
 [10]--> [Nathan][수박]-->null
   .
   .
 [16]-->[Tyrion][콜라]-->[Aaron][환타]-->null
   .
   .

  이렇게 key가 중복되면 linked list를 테이블의 인덱스에 생성하여 원소의 손실을 방지한다
```  
  
### 선형 탐사
```
  [ 5] [Jonathan][사과]
  [ 6] [Jamie][딸기]
  [ 7] [Sue][귤]
    .
    .
  [10] [Nathan][수박]
    .
    .
  [16] [Tyrion][콜라]
  [17] [Aaron][환타]
    .
    .

위의 체이닝과는 다르게 인덱스를 +1를 증가시키면서 원소를 추가한다
원소를 찾을 때에도 key에 해당하는 index를 찾고 key값이 같다면 출력하고 같지 않다면
+1을 하면서 key가 같을 때까지 찾는다
```
