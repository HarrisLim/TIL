# Android

### Android Studio
  - 설치
  - manifests - AndroidManifest는 웹에서 web.xml이랑 비슷하다고 생각하면 돼

### view
  - res폴더 내에 있는 것에서 확인

### 용어
  - Acticvity: 한 화면의 단위
  - Widget: java에서는 UI-Component라고 했었어. 하나의 쟁반이라고 생각하자.
  - Dialog: 화면 중간에 창이 뜨는 것. (이벤트처리 가능)
    - Alert dialg
    - Progress dialog (종류 2개)
    - DataPicker dialog
    - Semi dialog
  - Notification: 앱을 종료해도 notification에 남게 하는 것
  - Toast: 화면 하단 중간에 알림 창을 띄운다.
    - 경고창의 위치를 변경 가능 but, 시간초를 변경할 수는 없다.
### 단위
  - dp (Device independent) <- 가장 많이 쓰인다
  - in (Inches)
  - mm (Millimeters)
  - pt (Points)
  - px (Pixels)
  - sp (Scaled pixels)

### 아이콘 변경
  - res 우클릭 - new - image Asset
  - Name을 설정할 때, 대문자 불가

### Layout
  - 중첩 Layout: layout내에 layout을 사용하는 것
#### Linear
  - Linear(선형)이라는 이름처럼 하나의 선으로 정렬한다
  - 기본값은 horizontal(가로)이고, android:orientation="vertical"로 주면 세로로 정렬한다.
  - android:layout_weight="1" 이렇게 비중을 줘서 크기를 정확하게 나눌 수 있다. (Linear만 가능)
  - 계층적이고, 직관적이다. 그러나 코드의 depth가 깊어진다.
#### Relative
  - 기준은 왼쪽 상단(0,0)이야
  - 좌표값을 주는 건 좋은 게 아니다.
  - android:layout_alignParentRight="true"로 우측에 놓을 수 있다.<br>
    Linear로도 가능하지만, Relative가 훨씬 간단해
  - 기준을 잡아서 위치를 설정할 수 있다. (using id)
#### Frame
  - Widget을 (겹치기, 띄우기)에 좋다.
#### Table
  - 기본적으로 알고있는 테이블생각하면 돼
  - 글자수가 다르더라도 셀을 채우는 특징이 있기 때문에, 크기가 같다
  - 근데 실제로 table을 만들 때 이 layout을 사용하지 않고 그냥 게시판을 불러온다<br>
    단순한 것에는 쓰인다.
#### Absolute
  - 잘 사용하지 않는다
  - 기준은 왼쪽 위(0,0)
  - layout_x, layout_y로 좌표를 잡아서 화면에 출력한다
  - 해상도가 달라지면 원래 설정했던 위치와 달라진다.

### dimens.xml
  - 하나의 값을 name으로 설정하고 다른 xml파일에서 사용할 수 있다.
```
  - dimens.xml - 
  <resources>
    <dimen name="d1">20dp</dimen>
  </resources>

  - 사용하는 xml - 
    @dimen/d1
```

### Event
  - xml파일에서 id를 주고, java파일로 와서 그 아이디를 갖고 이벤트를 준다.
  - 사진 넘기는 소스 (MainActivity.java) 나중에 참고하자.
```
  public class MainActivity extends AppCompatActivity {
    ImageView iv;
    final static int[] IMGS = {R.drawable.a, R.drawable.song, R.drawable.sin};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      iv = (ImageView)findViewById(R.id.img);
     
      iv.setOnTouchListener(new View.OnTouchListener(){ // 익명내부클래스
        float x=0.0f, y=0.0f;
        int i=0;
        float a=0.0f, b=0.0f;
        @Override
        public boolean onTouch(View view, MotionEvent ev) {
          x = ev.getX();
          y = ev.getY();
          int action = ev.getAction();
          if(action == MotionEvent.ACTION_DOWN){
            a = x;
          }else if(action == MotionEvent.ACTION_UP){
            b = x;
            if (a + 50 < b) i--;
            else if (a > b+50) i++;
            if (i >= IMGS.length) i = 0;
            else if (i < 0) i = IMGS.length - 1;
            iv.setBackgroundResource(IMGS[i]);
          }
          return true;
        }
      });
    }
  }

```

### 문법 tip
  - java파일에서 R.drawable.img01을 하면 res 폴더의 drawable 폴더의 img01을 찾는다.<br>
    R.id.img를 하면 res폴더에서 id가 img인 태그를 찾는다. ex, { findViewById(R.id.img) }

### Resouse 종류
#### drawable
  - 아이콘이 아닌 이미지
  - 
#### mipmap
  - 아이콘
#### layout
  - layout을 정해주는 xml파일
### values
  - 이름이 정해져있는 xml이 있다. 
  - ex, dimens.xml, strings, ...

### 데이터 참조
  - xml끼리
```
  @color/c01
```
  - java에서 xml에 있는 것
```
  Resources r = getResource();
  r.getXXX();
```

### apk만드는 법 
  - bulid - gensrate signed apk를 클릭
  - keystore: 보안파일
    - 보통 하나만 만들지. keystore를 통해서 업로드한 앱은 keystore가 없으면 유지보수를 할 수가 없어.

### animation 용어
  - res안에 anim 폴더를 만들자 (꼭 anim으로 만들어야한다)
  - 5가지 태그
    - alpha 투명도
    - rotate 회전
    - scale 크기
    - translate 이동
    - 위의 4가지 코드를 합해서 set으로 묶어줄 수 있다. 

### WebView (widget)
  - 안드로이드 내에 웹킷이라는 엔진으로 구동시키는 것.
  - 앱 위에 브라우저를 띄우는 것