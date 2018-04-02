import java.io.*;
import java.util.*;

class ProLottoC
{
	String fName = "list.txt";
	BufferedReader br;
	FileReader fr;
	ArrayList<String> name = new ArrayList<String>();
	int rate;
	String target;

	void showWinner(){
		if(rate<0 || rate >100){
			pln("text파일 안의 확율은 1~100사이의 숫자만 입력이 가능합니다.");
			return;
		}
		int ran100 = doRandom(100)+1;
		String winner = "";
		if(rate >= ran100) {
			winner = target;
		} else {
			winner = name.get(doRandom(19));
		}
		p("Winner ! " + winner);
	}
	void divide() throws IOException{
		try{
			fr = new FileReader(fName);
			br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";
			int i =0;
			int idxOf = 0;
			while ((line = br.readLine()) != null){
				if(line.length() == 0) continue;
				trimLine = line.trim();
				idxOf = trimLine.indexOf(" ");
				if(idxOf != -1){ // 확률이 있다면,
					target = trimLine.substring(0, idxOf);
					trimLine = trimLine.substring(idxOf+1).trim();
					rate = Integer.parseInt(trimLine);
					continue;
				}
				name.add(i, trimLine);
				i++;
			}
		}catch(FileNotFoundException fe){}
	}
	void findFile() throws IOException{
		try{
			fr = new FileReader(fName);
			br = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			pln("not found file. 파일 이름을 다시 입력해주세요. (기본값 : list.txt)");
			br = new BufferedReader(new InputStreamReader(System.in));
			this.fName = br.readLine();
			findFile();
		}
	}
	int doRandom(int i){
		Random r = new Random();
		int ranNum = r.nextInt(i);
		return ranNum;
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		ProLottoC plc = new ProLottoC();
		try{
			plc.findFile();
			plc.divide();
		}catch(IOException ie){}
		plc.showWinner();
	}
}
