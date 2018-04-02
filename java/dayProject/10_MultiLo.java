import java.io.*;
import java.util.*;

class MultiLo {
	String fileName = "list.txt";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FileReader fr;
	Hashtable<Integer, String> map = new Hashtable<Integer, String>();
	TreeSet<Integer> set = new TreeSet<Integer>();
	int showNum;
	
	void findFile(String str) throws IOException {
		String tempFileName = null;
		try {
			System.out.print ("파일을"+str+"입력해주세요.! (기본값 : list.txt)  ");
			tempFileName = br.readLine();
			if(tempFileName.equals("") || tempFileName.equals("list.txt")) {
				fileName = "list.txt";
				getCount();
				return;
			}
			fileName = tempFileName;
			fr = new FileReader(fileName);
			getCount();
		} catch(FileNotFoundException fe) {
			findFile(" 정확히 ");
		} 
	}
	void getCount() throws IOException{ // get count of people
		try {
			System.out.print("몇 명을 보고싶어요 ? ");
			showNum = Integer.parseInt(br.readLine());
		} catch(NumberFormatException ne) {
			pln("숫자만 입력해주세요. ");
			getCount();
		}
	}
	void saveName() throws IOException{ // save name in map
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String tempLine = null;
			int i = 0;
			while((tempLine = br.readLine()) != null) {
				map.put(i, tempLine);
				i++;
			}
		} catch(FileNotFoundException fe) {} 
	}
	void show() throws IOException{
		if(showNum > map.size()) {
			pln("숫자를 "+map.size()+"이하로 입력해주세요.");
//			getCount(); // why does getCount() loop showNum times? just one time is ok.
			return;
		}
		int i = 0;
		Set<Integer> keys = map.keySet();
		while(showNum > set.size()) { // check repetition
			int ran = doRandom(map.size());
			set.add(ran);
		}
		Iterator<Integer> iSet = set.iterator();
		for(int key: keys) {
			if(showNum == i) break; // keep checking and then, showNum and i are 
			int num = iSet.next();
			pln("번호: " +num+ " 이름: "+ map.get(num));
			i++;
		}
	}
	int doRandom(int r) {
		Random ran = new Random();
		return ran.nextInt(r);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		MultiLo m = new MultiLo();
		try {
			m.findFile(" ");
			m.saveName();
			m.show();
		}catch(IOException ie) {}
	}
}
