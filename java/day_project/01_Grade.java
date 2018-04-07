import java.io.*;

class Grade 
{
	double avg;
	double subject[] = new double[5];

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void input(){ // 과목과 점수 입력
		int i;
		for(i=0; i<subject.length; i++){
			checkData(i);
		}
	}

	char calGrade(double avg){ // 평균을 받아서 등급 계산
		int a = (int)avg/10;
		switch(a){
			case 10: return 'S';
			case 9: return 'S';
			case 8: return 'A'; 
			case 7: return 'B'; 
			case 6: return 'C'; 
			case 5: return 'D'; 
		}
		return 'F';
	}
	
	void checkData(int i){ // 점수를 0~100점 사이의 값으로 제한
		try {
			print("어떤 과목을 수강하였습니까? ("+(i+1)+"/5) ");
			String sub = br.readLine();
			print(sub+"의 점수 : ");
			String line = br.readLine();
			subject[i] = Double.parseDouble(line);
			if((subject[i] < 0) || (subject[i] > 100)){
				while((subject[i] < 0) || (subject[i] > 100)){
					println("");
					println("** 0점이상 100점이하만 입력해주세요. **");
					print(sub+": ");
					 line = br.readLine();
					subject[i] = Double.parseDouble(line);
				}
			}
		} catch (IOException ie) {
		}
	}

	double calAvg(double[] sub){ // 평균 계산
		double sum=0;

		for(int i=0; i<sub.length;i++){
			sum += sub[i];
		}
		this.avg = sum/sub.length;
		return this.avg;
	}

	void print(String str){ // 프린트 메소드
		System.out.print(str);
	}
	void println(String str){ // 프린트 메소드
		System.out.println(str);
	}

	public static void main(String[] args) 
	{
		Grade g = new Grade();
		g.avg = (double)g.calAvg(g.subject);
		g.input();
		g.println("");
		g.println("평균: " + g.calAvg(g.subject));
		g.println("당신의 평균은 "+g.avg+"이고, 학점은 "+g.calGrade(g.avg)+"입니다.");
	}
}
