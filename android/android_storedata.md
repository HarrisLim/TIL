
# 데이터 영구저장법
  - io.파일
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
    