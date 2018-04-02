import java.io.*;
import java.util.*;

class DirLotto 
{
	String path;
	String listName = "list.txt";
	File f;
	FileReader fr;
	BufferedReader br;
	String studentF[]; // 파일 학생
	String studentD[]; // 폴더(디렉토리) 학생
	int count;
	int dirCount;
	int realCount;

	void inputPath() { // 경로 입력
		try{
			BufferedReader brLine = new BufferedReader(new InputStreamReader(System.in));
			p("파일 경로: ");
			path = brLine.readLine();
			f = new File(path);
			if(f.exists()){
//				pln("** 경로 존재 **");
				if(f.isDirectory()){
					pln("");
					pln("** 디렉토리(폴더) **");
					showOnList();
					showOffList();
				}else{
					pln("파일이야");
					inputPath();
				}
			} else {
				pln("경로 다시 !");
				inputPath();
			}

		}catch(IOException ie){
		}
	}

	void showOnList(){ // studentD[]에 폴더 이름 대입 & 출력
		File dChild[] = f.listFiles();
		studentD = new String[dChild.length];
		for(int i=0; i<dChild.length; i++){
			if(!(dChild[i].isFile())){
				String dName = dChild[i].getName();
				if(!(dName.equals(null))){
					studentD[dirCount] = dName;
					pln("폴더 이름["+dirCount+"] "+studentD[dirCount]);
					dirCount++;
				}
			}
		}
	}

	void compare(){ // studentF[]와 studentD[]를 비교하여, 같다면 공백으로 대체
		for(int i=0; i<count; i++){ // 파일이름
			for(int j=0; j<dirCount; j++){ // 폴더이름
				if(studentF[i].equals(studentD[j])){ // 이름이 겹치면,
//					pln("studentF["+i+"] : "+studentF[i]+",,, studentD["+j+"] : "+ studentD[j]);
					studentF[i] = "";
				}
			}
//			pln(studentF[i]); // 겹치는 사람은 공백으로 표현되는 것을 확인할 수 있지.
		}
	}

	void showOffList(){ // studentF[]안의 공백에 뒷 사람을 당겨서 채움
		compare();
		File fChild[] = f.listFiles();
		for(int i=0; i<count; i++){
			if(studentF[i].length() != 0){ // 공백이 아니면
				studentF[realCount] = studentF[i];
//				pln("studentF["+realCount+"] : " + studentF[realCount] + ",,, studentF["+i+"] : " + studentF[i]); // 사람 비교하는 거, 
				realCount++;
			}
		}
		showWinner();
	}

	void showWinner(){ // 당첨자 메소드
//		pln("real -> " + realCount); // 겹치지 않는 수
		for(int i=0; i<realCount; i++){
			pln("");
//			pln("겹치지 않는 학생 : " + studentF[i]); // 겹치지 않는 학생
			pln("Winner! " + studentF[makeRandom(realCount)]); 
			break;
		}
	}
	
	int makeRandom(int num){ // 랜덤 만들기
		Random r = new Random();
		int ranNum = r.nextInt(num);
		return ranNum;
	}

	void getCount(){ // 파일 안의 사람 수 구하기
		try{
			fr = new FileReader(listName);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){
				count++;
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){
		}
	}

	void getStudent(){ // studentF[]에 파일 내의 이름 대입 & 파일 내의 이름 출력
		try{
			int a = 0;
			studentF = new String[count];
			fr = new FileReader(listName);
			br = new BufferedReader(fr);
			String line = null;
			pln("** 파일안의 이름 **");
			for(int i=0; i<count; i++){
				line = br.readLine();
				studentF[i] = line;
				pln("파일안의 이름["+i+"] "+studentF[i]);
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){
		}
	}

	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}

	public static void main(String[] args) 
	{
		DirLotto d = new DirLotto();
		d.getCount();
		d.getStudent();
		d.inputPath();
	}
}
