import java.io.*; 
import java.util.*;

class ProMultiLottoC
{
	String fName = "list.txt";
	BufferedReader br;
	FileReader fr;
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<String> nameWithNum = new ArrayList<String>();
	ArrayList<String> strNumber = new ArrayList<String>();
	int nameCount;
	String target;
	
	void showWinner(){
		list.add(name);
		list.add(nameWithNum);
		list.add(strNumber);

		int prev=0, now=0, i=0, sum=0;
		int ran100 = doRandom(100)+1;
		String winner = "";

		for(String strNum: strNumber){
			sum += Integer.parseInt(strNum);
		}
		if(sum > 100){
			pln("확율의 합은 100을 초과할 수 없습니다.");
			System.exit(-1);
		}
		for(ArrayList<String> arrList: list){
			for(String names: arrList){
				if(i < strNumber.size()){
					now = Integer.parseInt(strNumber.get(i));
					prev += now;
					if(now>= ran100 || ran100 <= prev){
						winner = nameWithNum.get(i);
						break;
					}
				}
				i++;
				winner = name.get(doRandom(name.size()));
			}
		}
		System.out.print("Winner ! " + winner);
	}
	void divide() throws IOException{
		try{
			fr = new FileReader(fName);
			br = new BufferedReader(fr);
			String line = "";
			String trimLine = "";
			int idxOf = 0;
			while ((line = br.readLine()) != null){
				if(line.length() == 0) continue;
				nameCount++;
				trimLine = line.trim();
				idxOf = trimLine.indexOf(" ");
				if(idxOf != -1){ // 확률이 있다면,
					nameWithNum.add(trimLine.substring(0, idxOf));
					trimLine = trimLine.substring(idxOf+1).trim();
					strNumber.add(trimLine);
					continue;
				}else{
					name.add(trimLine);
				}
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
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		ProMultiLottoC plc = new ProMultiLottoC();
		try{
			plc.findFile();
			plc.divide();
		}catch(IOException ie){}
		plc.showWinner();
	}
}
