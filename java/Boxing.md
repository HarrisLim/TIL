# AutoBoxing and UnBoxing
  - AutoBoxing : int -> Integer (기본형 -> 참조형)
  - UnBoxing : Integer -> int (참조형 -> 기본형)
  ```
    ex) 
      class Boxing {
        int i = 10;
        Integer iObj;
        void autoB(){
          iObj = i; // AutoBoxing
        }
        void unB(){
          int j = iObj; // UnBoxing
        }
      }
  ```