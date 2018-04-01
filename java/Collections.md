# Collections

### 정의
  - 같은 타입의 참조형(Object) 데이터를 저장하는 저장소로써 생성시 그 크기가 고정되지 않는 '가변배열' 클래스

  - 상속도
  ```
      java.util.Collection  (Collection 계열)
       |			   |
  java.util.List	java.util.Set
       |			   |
  Vector/ArrayList	 java.util.SortedSet
       |
  TreeSet
  
      java.util.Map		(Map 계열)
            |
  Hashtable/HashMap/TreeMap
  ```

  - 특징
    - List
      순서를 보존 O  
      중복을 허용 O

    - Set
      순서를 보존 X  
      중복을 허용 X

    - Map
      순서를 보존 X  
      Key는 중복 허용 X, Value는 중복 허용 O
