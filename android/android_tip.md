# Android

### Android Studio
  - 설치
  - manifests - AndroidManifest는 웹에서 web.xml이랑 비슷하다고 생각하면 돼

### view
  - res폴더 내에 있는 것에서 확인

### 용어
  - Acticvity: 한 화면의 단위
  - Widget: java에서는 UI-Component라고 했었어. 하나의 쟁반이라고 생각하자.

### 단위
  - dp가 가장 많이 쓰인다

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