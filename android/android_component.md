# Android 구성요소
### 구성요소
  - 액티비티
    - 하나의 화면을 나타내는 단위
  - 서비스
    - 특정 액티비티와 상관없이 백그라운드에서 실행되는 것
    - ex, 음악플레이어 등..
  - 컨텐트 프로바이더
    - 외부에서 가져다가 쓰는 것. 
    - ex, 주소록을 가져다가 다른 주소록이 필요한 App에서 갖다가 주소록을 사용한다.
  - 브로드캐스트 리시버
    - 방송을 받는 것.
    - 시스템에 대한 이벤트.
    - ex, 재부팅을 했을 때 알아서 원하는 App을 켜지게 할 수 있다.<br>
      alarm같은 기능

### Intent
#### 명시적 (Explicit)
  - 특정한 것을 선택했을 때 보여주는 것.
  - ex, 임정수를 찾아라.
  - Intent를 이용해서 어떠한 activity를 먼저 띄울지 설정할 수 있다. (activity가 2개 이상일 때)
```
  - Manifest.xml -
  <intent-filter>
    <action android:name="android.intent.action.MAIN" />
    <category android:name="android.intent.category.LAUNCHER" />
  </intent-filter>
```
  - Intent로 ativity로 전환
```
  Intent i = new Intent(this, SubActivity.class); // 가고싶은 activity를 2번째 파라미터로 준다.
  그 후, 이벤트 핸들러 안에서
  stratActiviti(i)를 하면, 어떠한 이벤트를 수행 시 start한다.
  finish(); // 만약, finish()를 하지 않으면 Activity스택에 Activity가 쌓인다.
            // 그러면 메모리에 계속 쌓이지. 그래서 꼭 finish()를 해줘야해.
```
  - 다른 Activity로 data넘기기
```
  // 이렇게 Intent에 있는 putExtra를 map처럼 사용한다. (key, value)
  i.putExtra("key1", et1.getText().toString());
```
  - 다른 Activity로 data받기
```
  Intent i_self = getIntent(); // 이렇게 자기 자신의 Intent를 받는 Intent를 하나 더 만들어준다
  String xx = i_self.getStringExtra("key1");  // putExtra한 것처럼 getStringExtra("key"); // String Type이니까
  
  그리고 원하는 곳의 위의 getExtra로 받은 data를 setText(xx)한다.
```
  - Main으로 되돌아오기
    - Main에서 다른 페이지로 갔다가 Main페이지로 돌아온다.
    - 이렇게 하면 main페이지 이외의 페이지에서 main으로 돌아오는 intent를 사용하는 이유가 없어진다.
    - Main
```
  // 그냥 startActivity가 아닌 밑의 것을 사용하면, 결과를 받아오기 위한 메소드이다.
  startActivityForResult(i, 0); // (Intent, requestCode) // requestCode는 내 맘대로 설정하는 것
  
  onActivityResult(int requestCode, int resultCode, Intent data)
    requestCode: 다른 activity와 연결해주는 코드. startActivityForResult에서 0으로 설정하면, 
                 Intent를 생성할 때 보내고 싶은 Class를 쓰는 곳에서, 돌아오는 코드로 인식한다
		 그래서 if(requestCode==0)으로 해서 사용한다.
    resultCode: data를 가져오는 것이 성공인지 실패인지 리턴한다. (사용 예, resultCode==Activity.RESULT_OK)
    data: 다른 activity에서 넘어온 데이터를 담고있는 Intent
```
    - Main이 아닌 Activity
```
  Intent i;
  setResult(Activity.RESULT_OK, i); // 이렇게 RESULT_OK를 보내고, i를 Main으로 보낸다.
```

#### 암시적 (Implicit)
  - 조건에 해당되는 것을 보여주려면,
  - ex, 성이 임인 사람을 찾아라.<br>
      이미지를 보여줄 때, 이미지를 보여줄 수 있는 App이 많다면 ! App을 선택하라고 listing이 뜨는 것