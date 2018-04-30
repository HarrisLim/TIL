# Collection 타입으로 2개 이상 사용
  - 클래스를 이용하면 한 번에 2개 이상의 타입을 줄 수 있다.
  - 이렇게 사용하는 것이 컬렉션을 두 개 이용하거나 map을 이상하게 사용하는 것보다 가독성이 좋다고 느낀다
```
    - 선언 -
  class P{
    String column;
    String type;

    P(String column, String type){
      this.column=column;
      this.type=type;
    }
  }
    - 구현 -
  Vector<P> tables = new Vector<P>();
  tables.add(new P(xxx, xxx));
  for(P table: tables){
	System.out.println("column: "+table.column+", type: "+ table.type);
  }
```
