# Android widget 동적 생성
  layout.xml에서 widget을 생성하는 것처럼, 앱 내에서 어떠한 동작이 있을 시에, widget을 생성하고 싶다
  1. 추가하고 싶은 Layout의 id를 잡아준다.
```
  <LinearLayout
    android:id="@+id/dynamicArea">
```
  2. Activity.java에서 위에서 지정한 Layout의 id를 가져와서 변수로 뺀다.
```
  LinearLayout topLL = topLL = (LinearLayout)findViewById(R.id.dynamicArea);
```
  3. 생성하고 싶은 Widget을 변수화한다.
```
  TextView topTV1 = new TextView(MainActivity.this);
```
  4. topTV1라는 TextView Widget에 각각 설정을 해줄 수 있다.<br>
     여기서 보면 layout_에 대한 속성을 줄 때는 사용하려는 Layout을 또 변수화해야한다.

```
  LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 200); // (width, height)
  속성: LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT

    - .xml -                                        - .java -
  android:layout_width="match_parent"              위에서 Layout을 변수화할 때 설정했다.
  android:layout_height="100dp"                    위에서 Layout을 변수화할 때 설정했다.
  android:layout_margin="10dp"                     lp.setMargins(20, 10, 20, 10); // .java에서는 단위가 pixel이다. dp가 아니다.
  android:background="@drawable/envelope"          topTV1.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.envelope));
  android:textAlignment="center"                   topTV1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
  android:text="학원"                              topTV1.setText("학원");
  android:paddingTop="10dp"                        topTV1.setPadding(0, 10, 0, 0);
  android:textSize="60dp"                          topTV1.setTextSize(60);
```
