# 데이터 영구저장법
  - io.파일
    - res/raw폴더 or Assets 
      - res/raw: R.java로 객체를 가져온다. android에서 객체를 알아서 만들어주고,<br>
        raw에 있는 파일은 앱이 실행될 때 데이터를 불러와서 기다리고 있다.
      - Assets: 객체를 직접 생성해야하고, 이렇게 데이터를 가져오면,<br>
        데이터를 불러오는 행위를 할 때 데이터를 불러온다.<br>
	큰 용량의 데이터라면 Assets에 넣어서 불러오는 것이 앱의 실행속도를 빠르게 유지할 수 있다.
  - preference (xml)
    - io보다 훨씬 간단하게 구현할 수 있다. 
    - prefer를 activity로 부터 뽑아와서 사용한다. 
```
  // result는 xml로 저장될 파일의 이름. (실제 우리가 볼 수는 없다. 관리자권한이 없기 때문)
  // MODE_PRIVATE으로 보안을 신경쓴다.
  prefer = this.getSharedPreferences("result", MODE_PRIVATE);

  // 이런식으로 prefer에서 edit()를 뽑아서 e.putxxx로 key, value값을 저장
  Editor e = prefer.edit();
  e.putString("et_key", et.getText().toString());
```
  - DB
    - server side( Oracle, ...)
    - client side( Sqlite )
```
  // DB생성, DB_NAME이라는 이름의 DB가 있다면 OPEN
  SQLiteDatabase db = openOrCreateDatabase("DB_NAME", MODE_PRIVATE, null); 
```
    