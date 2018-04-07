# IO (입출력) 

### Stream ([JAVA 9 API Java.lang.System](https://docs.oracle.com/javase/9/docs/api/java/lang/System.html)) 

  - 정의 : 데이터 흐름

  - 근원지(Data Source)와 목적지(Data Destination)  
    - Data Source -> Data Destination

  - 예
    - 뇌 -> 손가락 -> keyboard (System.in) -> monitor (System.out) -> 눈 -> 뇌
    - File -> File
    - File -> Memory(RAM)

  - 특성
    - 단방향성
    - FIFO(First In First Out)
    - 유연성 (스트림 간의 결합이 가능하다는 뜻)  
      - ex) new BufferedReader(new InputStreamReader(System.in));
    - 지연성 (중간 중간에 지연될 수 있는)

  - 구분
    - 입력/출력
      - 입력  
        - XXXInputStream, XXXReader
      - 출력  
        - XXXOutputStream, XXXWriter
	 
    - 전송단위
      - 바이트 스트림 (byte stream) (1byte)  
        - XXXInputStream, XXXOutputStream
      - 문자 스트림 (char stream) (2byte)  
        - XXXReader, XXXWriter
	 
    - 성격
      - 근본 스트림 (Node stream)
        - Source나 Destination과 직접 연결된 스트림
        - ex) System.in, System.out
      - 브릿지 스트림 (Bridge stream)
        - 바이트 스트림을 문자 스트림으로 변경하는 스트림
        - ex) new InputStreamReader, new OutputStreamWriter
      - 필터 스트림 (Filter stream) == 목적 스트림 == 응용 스트림  
        - 해당 목적을 이루기 위한 스트림
        - ex) new BufferedReader  

 
```
  InputStream is = System.in; // 표준입력(keyboard)
  OutputStream os = System.out; // 표준출력(monitor)
  void m(){
    try{
      byte[] b = new byte[8]; // 실제로는 512, 1028을 사용. 8을 사용하면 밑의 반복문이 더 많이 돌아
      int count = 0;
      while((count = is.read(b)) != -1){
        os.write(b, 0, count); // OutputStream에 write메소드 오버로딩이 있으니 참고
      }
      os.flush(); // 버퍼에 넣었던 것을 출력. 직접 flush()를 하지 않으면 버퍼가 가득차야 출력돼
      }
    catch(IOException ie){
    }fianlly{
      try{
        is.close(); // 리소스와의 연결을 끊어주는 것. 그렇지 않으면 Garbage collector가 회수하지 않아.
        os.close();
      }catch(IOException ie){}
    }
  }
```  
  

  - 주요 스트림 (아래의 스트림은 모두 1byte 스트림)
    - InputStream
    - Output Stream
    - FileInputStream
    - FileOutputStream
    - BufferedInputStream
    - BufferedInputStream
    - DataInputStream
    - DataOutputStream
    - Reader
    - Writer
    - FileReader
    - FileWriter
    - InputStreamReader
    - OutputStreamWriter
    - BufferedReader
    - PrintWriter
    - ObjectInputStream (InputStream으로 받으면 Object로 변경 가능. 아주 강력한 기능. remember, Serializable(interface), transient(Modifier))
    - ObjectOutputStream  
    - ByteArrayInputStream
    - ByteArrayOutputStream
    - CharArrayReader
    - CharArrayWriter
  
  - 용어
    - Marshalling, UnMarshalling
      - ObjectxxxxxStream에서 Object를 byte로 바꾸거나 byte로 Object로 바꿀 때 (Object -> byte), (byte -> Object)  
        
```
File childs[] = f.listFiles();
  for(File child: childs){ // File로도 Enhanced Loop 가능
    if(child.isFile()){
      pln("[F] " + child.getName() + " ("+f.length()+"bytes)");
    }else{
      pln("[D] " + child.getName());
    }
  }
```  
```
// 객체를 읽어와서 객체로 쓰는 것. 
// 파일을 만들어서 다른 사람에게 전송 후 다른 사람에서 readObject하면 기능을 똑같이 사용 가능.

/*
  fos = FileOutputStream
  oos = ObjectOutputStream
  fis = FileInputStream
  ois = ObjectInputStream
*/
void wObj(){
  SuperMan obj = new SuperMan("임정수", 999);
  try{
    oos.writeObejct(obj) // Object -> byte (Marshalling)
    oos.flush();
  }catch(IOException ie){
  }finally{
    try{
      if(oos != null) oos.close();
      if(fos != null) fos.close();
    }
  }
}
void rObj(){
  try{
    Object obj = ois.readObject(); // byte -> Object (UnMarshalling)
    SuperMan man = (SuperMan)obj;
    // SuperMan의 기능을 모두 사용 가능.
    System.out.println("man.power -> " + man.power);
    man.move();
    man.shotBeam();
  }catch(IOException ie){
  }catch(ClassNotFoundException ce){
  }finally{
    try{
    if(ois != null) ois.close();
    if(fis != null) fis.close();
    }catch(IOException ie){}
  }
}
```
 


 
 
출처 : 김형수선생님