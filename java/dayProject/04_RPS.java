import java.io.*; 
import java.util.*;

class RPS
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Random r = new Random();
	int circle;
	int countWin;
	int countDraw;
	int countLose;

	void doReferee(){ // 심판
		try{
			String line = "";
			int i = 0;

			p("가위바위보 몇 판을 하시겠습니까? (기본 3판) ");
			line = br.readLine();
			if(line.equals("")) {	// 그냥 엔터치면 3한건데 Numberxxxx 예외처리하네
				i = 3;
			}
			i = Integer.parseInt(line);
			if(i >= 1){ // 정상적이지 않을 때
				circle = i;		// 판 수를 멤버변수에 대입.
				pln("판 수: "+ circle);
				pln("");
				while((circle/2)+1 > countWin && (circle/2)+1 > countLose) {
					doValid();
				}
				int countSum = countDraw + countLose + countWin;
				pln(countSum+"전 "+countWin+"승 "+countDraw+"무 "+countLose+"패");
			} else{ // 정상적이지 않을 때
				pln("");
				pln("1이상만 입력해주세요.");
				doReferee();
			}
		}catch(IOException ie){
		}catch(NumberFormatException ne){
			pln("");
			pln("정수만 입력해주세요.");
			doReferee();
		}
	}

	int checkWin(int data){	// 컴퓨터
		switch(data){
		case 0: pln("비겼다"); break;
		case 1: pln("졌다"); break;
		case 2: pln("이겼다"); break;
		}
		return data;
	}

	int doMe(String data){	// 나
		int i = 0;

		if(data.equals("가위")){
		} else if(data.equals("바위")){
			i = 1;
		} else if(data.equals("보")){
			i = 2;
		} else {
			pln("");
			pln("(가위, 바위, 보) 중 선택해주세요"); // 여기서 위에 for문으로 가게 해야돼.
			doValid();
		}
		return i;
	}

	void doValid(){
		try{
			int countR = r.nextInt(3);
			p("(가위, 바위, 보) ");
			String data = br.readLine();
			doMe(data);
			checkWin(countR);
			switch(countR){ // 0: 비겼다, 1: 졌다, 2: 이겼다
				case 0: countDraw++; break;
				case 1: countLose++; break;
				case 2: countWin++; break;
			}
		}catch(IOException ie){
		}
		
	}
	
	void pln(String str){ // 프린트 메소드
		System.out.println(str);
	}
	void p(String str){ // 프린트 메소드
		System.out.print(str);
	}

	public static void main(String[] args) 
	{
		RPS rps = new RPS();
		rps.doReferee();
	}
}

// 엔터치면 3판 되게, (가위, 바위, 보) 중에 잘못입력한 것은 "전"에 추가하면 안되지. 

