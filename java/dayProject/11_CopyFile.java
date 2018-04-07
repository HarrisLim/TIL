import java.io.*; 

class CopyFile
{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String type;
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	String originFile;
	String diPath;
	File f;
	File d;

	void ChooseType(){
		try{
			pln("1. 복사하기  2. 잘라내기");
			type = br.readLine();
			if(!(type.equals("1") || type.equals("2"))){
				pln("1번과 2번 중 선택해주세요.");
				ChooseType();
			}
		}catch(IOException ie){}
	}
	void getPath() {
		try{
			switch(type){
				case "1" : System.out.print("복사하고 싶은 파일 : "); break;
				default : System.out.print("잘라내고 싶은 파일 : ");
			}
			originFile = br.readLine();
			System.out.print("붙여넣을 폴더 위치 : ");
			diPath = br.readLine();
			d = new File(originFile);
			f = new File(diPath+"\\"+d.getName());
		}catch(IOException ie){
		}
	}
	void input(){
		try{
			fis = new FileInputStream(originFile);
			fos = new FileOutputStream(f);
			bis = new BufferedInputStream(fis, 1024);
			bos = new BufferedOutputStream(fos, 1024);
		}catch(FileNotFoundException fe){
			pln("파일을 다시 입력해주세요.");
			getPath();
		}
	}
	void cutOrCopy(){
		input();
		byte bs[] = new byte[256];
		try{
			int i = 0;
			while((i = bis.read(bs)) != -1){
				bos.write(bs, 0, i);
			}
			if(type.equals("2")){
				d.deleteOnExit();
				System.out.println("잘라내기 완료");
			}else{
				System.out.println("복사 완료");
			}
			bos.flush();
		}catch(IOException ie){
		}finally{
			try{
				bos.close();
				bis.close();
				fos.close();
				fis.close();
			}catch(IOException ie){}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		CopyFile cf = new CopyFile();
		cf.ChooseType();
		cf.getPath();
		cf.cutOrCopy();
	}
}
