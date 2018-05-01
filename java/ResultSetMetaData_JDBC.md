# ResultSetMetaData 인터페이스
  - JDBC를 이용하여 자바로 Sqlplus같은 DB를 핸들링할 수 있는 도스창을 만들려고 했다
  - insert, drop, delete 등은 간단했지만, select를 하려면 각 테이블의 Column과 타입등을 가져와야 한다
  - 밑의 코드처럼 간단하게 가져올 수 있다
```
  void select(String sql){
    if(sql.contains(";")) sql = sql.substring(0, sql.length()-1);
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    try{
      rs = stmt.executeQuery(sql);
      rsmd = rs.getMetaData();
      int count = rsmd.getColumnCount();
      for(int i=1; i<=count; i++){
        String cName = rsmd.getColumnName(i);
        java.p(cName+"\t");
      }
      java.pln("");
      for(int i=1; i<=count; i++){
        java.p("---------");
      }
      java.pln("");
      while(rs.next()){
        java.pln("");
        for(int i=1; i<=count; i++){				
          if(rsmd.getColumnTypeName(i).equals("NUMBER")){
            java.p(rs.getInt(rsmd.getColumnName(i))+"\t");
          }else if(rsmd.getColumnTypeName(i).equals("VARCHAR2")){
           java.p(rs.getString(rsmd.getColumnName(i))+"\t");
          }else if(rsmd.getColumnTypeName(i).equals("DATE")){
            java.p(rs.getDate(rsmd.getColumnName(i))+"\t");
          }
        }
      }
      java.pln("");
    }catch(SQLException se){
    }finally{
      try{
        if(rs!=null) rs.close();
      }catch(SQLException se){}
    }
  }
```
### 밑의 코드는 ResultSetMetaData를 몰랐을 때 하나하나 빼오려고 했던 코드
  - 위의 코드와 비교하면 엄청 지저분하다
```
  void select(String sql){
    int i=0;
    int intCol=0;
    String strCol=null;
    java.sql.Date dateCol=null;
    doFirst(sql);
    ResultSet rs = null;
    try{
      for(P table: tables){			
        java.p(table.column+"\t");
      }
      java.pln("\n------------------------------------------");
      if(sql.contains(";")) sql = sql.substring(0, sql.length()-1);
      rs = stmt.executeQuery(sql);
      while(rs.next()){
        for(P table: tables){
          if(table.type.equals("NUMBER")){
            intCol = rs.getInt(table.column);
            java.p(intCol+"\t");
          }else if(table.type.equals("VARCHAR2")){
            strCol = rs.getString(table.column);
            java.p(strCol+"\t");
          }else if(table.type.equals("DATE")){
            dateCol = rs.getDate(table.column);
            java.p(dateCol+"\t");
          }
        }
      java.pln("");
      }
    }catch(SQLException se){}
    tables.removeAll(tables);
  }

  void doFirst(String sql){
    int i = 0;
    ResultSet rs = null;
    try{
      rs = stmt.executeQuery("select COLUMN_NAME, DATA_TYPE from USER_TAB_COLUMNS where TABLE_NAME='"+pickTableName(sql)+"'");
      while(rs.next()){
        tables.add(new P(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE")));
        p = tables.get(i);
        i++;
      }
    }catch(SQLException se){}
  }

  String pickTableName(String sql){
    String tableName = null;
    int fromIdx = sql.indexOf("from ");
    int whereIdx = sql.indexOf("where");
    int idx = sql.indexOf(";");
    if(sql.contains(";")){
      if(sql.contains("where") || sql.contains("WHERE")){
        tableName = sql.substring(fromIdx+5, whereIdx-1);
      }else{
        tableName = sql.substring(fromIdx+5, idx);
      } 
    }else {
      if(sql.contains("where") || sql.contains("WHERE")){
        tableName = sql.substring(fromIdx+5, whereIdx-1);
      }else {
        tableName = sql.substring(fromIdx+5);
      }
    }
    return tableName.toUpperCase();
  }
```