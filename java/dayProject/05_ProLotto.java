import java.util.*; 
import java.io.*;

class ProLotto 
{
	
	int count; // 공백제거한 count
	int wholeCount; // 공백포함 count
	int rate;
	String str[];
	String strRate;
	String name;
	String nameList = "list.txt";
	FileReader fr;
	BufferedReader br;
	String fName;

	void findFile(){
		try{
			BufferedReader brLine = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("파일이름 : ");
			fName = brLine.readLine();
			if(fName.equals(nameList)){
				return;
			}else{
				System.out.println("바보");
				findFile();
			}
		}catch(IOException ie){
		}
	}

	void readFile(){
		findFile();
		try{
			String line = null;
			int idx = 0;
			int i = 0;
			fr = new FileReader(nameList);
			br = new BufferedReader(fr);
			str = new String[count];

			while((line = br.readLine()) != null){
				if(line.length() == 0) continue;
				str[i] = line.trim();
				idx = str[i].indexOf(" ");
				if(idx != -1){
					name = str[i].substring(0, idx);
					strRate = str[i].substring(idx+1).trim();
					rate = Integer.parseInt(strRate);
					continue; // 여기서 확률이 씌여있는 사람을 count++안하고 넘기니까 count가 19
				}
				i++;
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){
		}
	}

	void getCount(){
		try{
			String line = null;
			fr = new FileReader(nameList);
			br = new BufferedReader(fr);
			while((line= br.readLine()) != null){
				if(line.length() == 0){
					wholeCount++;
					continue;
				}
				count++;
				wholeCount++;
			}
		}catch(FileNotFoundException fe){
		}catch(IOException ie){
		}
	}
	void show(){
		int r = doRandom(count);
		int random100 = doRandom(100)+1;
		for(int i=0; i<count; i++){
			if(rate >= random100){
				System.out.println("당첨 ! "+ name +" ("+rate+"%)");
				break;
			} else {
				if(r!=0) r--; // 확률 준 이름을 빼서 -1
				System.out.println("당첨 ! "+ str[r] +" ("+(100-rate)+"%)");
				break;
			}
			
		}
	}

	int doRandom(int a){
		Random r = new Random();
		int ranNum = r.nextInt(a);
		return ranNum;
	}

	public static void main(String[] args) 
	{
		ProLotto lo = new ProLotto();
		lo.getCount();
		lo.readFile();	// readFile 메소드 시작.
		lo.show();
	}
}