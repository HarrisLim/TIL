import java.io.*; 

class 비트캠프
{
	String 위치 = "종로";
	int 강의실번호 = 5;
	int 학생수 = 20;
	
	String 과목1 = "자바";
	String 과목2 = "C언어";
	String 과목3 = "C++";
	String 과목4 = "자바스크립트";
	
	void 정보(){
		System.out.println(위치+"에 있는 비트캠프학원 "+강의실번호+"강의실의 학생 수는 "+학생수+"이다.");
		System.out.println("수강할 수 있는 과목은 "+과목1+", "+과목2+", "+과목3+", "+과목4+"입니다.");
		System.out.println("\n");
	}

	void 수강등록확인(String 과목){
		System.out.println("네. " +과목+"에 수강등록 되었습니다.");
	}
}

class 학생
{
	비트캠프 비트;

	String 이름 = "임정순";
	String 학교 ="서울대";
	String 성별 = "여자";
	int 나이 = 27;
	학생(){}
	학생(String 이름, String 학교, String 성별, int 나이){
		this.이름 = 이름;
		this.학교 = 학교;
		this.성별 = 성별;
		this.나이 = 나이;
	}

	void 수강등록(String 과목){
		비트 = new 비트캠프();
		System.out.println("안녕하세요. 제 이름은 "+이름+"이고, 학교는 "+학교+"이고, 성별은 "+성별+"이고, 나이는 "+나이+"인데요. "+과목+"과목을 수강하고 싶습니다.");
		비트.수강등록확인(과목);
		System.out.println("\n");
	}
}

class 학부모
{
	// 학원에 전화한다는 학생 클래스를 직접 가져와서 쓴 것.
	// 딸에게 전화한다는 학생 클래스의 이름을 파라미터로 받아서 String으로 쓴 것.
	학생 학;

	void 학원에전화한다(학생 학){
		비트캠프 비트 = new 비트캠프();
		System.out.println(학.이름+"엄마인데요~ "+"거기가 "+비트.위치+"에 있는 비트캠프맞나요?");
	}

	void 자녀에게전화한다(String 이름){
		if(학.성별.equals("여자")){
			System.out.println("딸.." + 이름+"~ 언제오니 ?");
		}else{
			System.out.println("아들.." + 이름+"~ 언제오니 ?");
		}
	}
}