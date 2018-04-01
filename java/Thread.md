# Thread
  프로세스를 구성하는 **제어의 흐름**

  - Process와 Thread
    - Process : 프로그램 **실행의 단위**
    - Thread : Process를 구성하는 **작업 단위**

  - 장점
    - 경량 프로세스라는 이름처럼 가볍다 (이유는, 프로세스의 공통 Resource를 쓰레드끼리 공유하기 때문에)
    - 쓰레드는 여러개 돌려도 프로그램이 느려지지 않아 (다만, 쉬는 시간을 주어야만 해)
    - 사용 예시
      - 토렌트에서 동시에 여러 파일을 다운
      - word에서 입력과 동시에 오타 처리
      - 웹브라우저에서 음악을 들으면서 서핑하는 것
      - 게임의 등장인물들, 배경음악, ...

  - 생성법
    - java.lang.Thread 상속법

    - java.lang.Runable 상속법 (이 녀석은 interface)

    ```
      class B extends Thread{
        public void run(){
          while(true){
            System.out.println("서빙하다");
            try{
              Thread.sleep(1000); // 1초
            }catch(InterruptedException ie){}
          }
        }
      }

      class BB implements Runnalbe {// Runnable에는 run이라는 추상메소드 밖에 없지만, 인터페이스는 다중상속이 가능하기 때문에 떄에 맞게 사용한다
        public void run(){
          while(true){
            System.out.println("음식을 만든다");
            try{
              Thread.sleep(1000);
            } catch(InterruptedException ie){}
          }
        }
      }

      class BUser{
        public static void main(String[] args){
          B b1 = new B();
          b1.start();
          B b2 = new B();
          b2.start();

          Thread th = new Thread(new BB());
          th.start();
          System.out.println("BUser의 Thread 종료");

          while(true){ //이 while문이 맨 마지막이 아니고 맨 위 혹은 중간에 있다면 main메소드가 while문에서 갖히기 떄문에 다른 쓰레드들이 실행이 안돼.
            System.out.println("계산한다");
            try{
              Thread.sleep(3000);
            }catch(InterruptedException ie){}
          }
        }
      }

    ```

    - 시작 메소드
      - 쓰레드객체.start();

    - 비유 : 고용인

    - Thread의 Lifecycle
      - 총 5가지 상태
      - 상태이동은 메소드 호출이나 스케쥴러에 의해 가능
      - 소멸상태 run() 수행완료되면 이동됨
      - wait()또는 sleep() 사용 시에는 InterruptedException 발생
        add) [쓰레드의Lifecycle](http://www.tutorialdost.com/Java-Programming-Tutorial/36-Java-Thread-Life-Cycle.aspx)

    - Priority ( remember, yield() )
      - Ready 상태의 쓰레드 중에서 우선적으로 CPU를 점유할 수 있는 쓰레드를 판별하기 위한 Level 값
      - 범위 : 1 ~ 10
      - 상수 
      	MAX_PRIORITY  
      	MIN_PRIORITY  
      	NORM_PRIORITY  

    - 주요메소드
      - start()
      - sleep()
      - join()
      - yield()

    - 동기화
      - 하나 이상의 쓰레드가 어떤 연산에 동시에 접근할 때 그 연산에 대한 값에 **무결성**을 보장하기 위해 수행영역에 lock을 걸어주는 것
      - 비유 : 화장실의 문고리
    
      - ex)
        ```
        int i = 0;
        i++;

        # CPU의 연산 단계 #
        1> i값을 읽음     i==0   i==0
        2> i값을 증가     i==0   i==0
        3> i값을 대입     i==1   i==1
          // 읽고, 증가하고, 대입한 후에 읽고, 증가하고 대입해야 하는데,
          // 읽고, 증가하는 동안에 다음 i++가 읽으면 0을 읽어서 i++을 두 번 읽어도 1만 증가됨 (극단적인 예)
        ```

      - 방법 
        synchronized를 메소드나 영역(블럭)에 써주면 그 영역에 대해서 동기화처리가 됨  
  
        - 메소드 전체
          ```
            synchronized void m() {}
          ```

        - 특정 로직
          ```
            void m(){
              로직 1
              { synchronized(this){
                로직 2
              }}
              로직 3
            }
          ```