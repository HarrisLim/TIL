# [object object] 내용 확인하는 법
  javascript에서 값을 확인하려고 console.log를 찍어보면 [object obejct]라고 써있는 경우가 많다.<br>
  안에 어떠한 멤버변수나 메소드가 있는지 확인하기 위해서 아래와 같은 for문을 사용할 수 있다. 

```
  for(var key in obj){
    console.log("Attriute: "+ key +", value : "+ obj[key]);
  }

```

  위와 같은 코드를 작성하고 console.log를 보면 또 [object object]라고 나오는 경우가 있는데,<br>
  그럴 땐, 꼬리에 꼬리를 무는 것처럼 계속 확인하면 된다. 