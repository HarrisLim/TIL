import java.io.*;
import java.util.*;
class CopyDir
{
	BufferedReader brFile = new BufferedReader(new InputStreamReader(System.in));
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	File f;
	String startedDir;
	boolean isFile;
	ArrayList<File> arrFile = new ArrayList<File>();
	int i;
	int idx;
	String dirPath;
	
	
	void getPath() {
		try{
			String path = null;
			pln("input File or Directory you wanna copy."); // 복사할 파일 및 디렉토리를 입력해주세요.
			path = brFile.readLine();
			f = new File(path);
			idx = f.toString().length();
			is(f);
		}catch(IOException ie){}
	}
	void is(File f){
		if(f.isDirectory()) {
			loop(f);
		}else {
			isFile = true;
			makeFile(f);
		}
	}
	void loop(File f){
		File[] files = f.listFiles();
		for(File file: files){
			if(file.getName().equals(".DS_Store")) continue;
			if(file.isDirectory()) {
				arrFile.add(file);
				makeDir(file);
				is(file);
			} else { // 폴더 안에 있는 파일
				makeFile(file);
			}
		}
	}
	void makeDir(File f) {
		dirPath = f.toString().substring(idx+1);
		pln("path: "+ dirPath);
		f = new File(dirPath);
		if(!f.exists()) {
			f.mkdir();
			pln(f.getName()+"파일 생성");
		}else {
			File fileForMkdir = new File(dirPath, f.getName());
			fileForMkdir.mkdir();
			pln("made "+ f.getName() +" Dir in "+ dirPath);
		}
	}
	void makeFile(File f){
		try{
			fis = new FileInputStream(f);
//			pln("dirPath in makeFile: "+ dirPath);
			//int idx = dirPath.lastIndexOf("/");	//PSY
			String path = f.toString().substring(idx+1);	//PSY
			pln("path in makeFile: "+ path +", dirpath: "+ dirPath);
			fos = new FileOutputStream(new File("./"+path));		//PSY
		}catch(FileNotFoundException fe){
			pln("no file.");
			getPath();
		}
		bis = new BufferedInputStream(fis);
		bos = new BufferedOutputStream(fos);
		try{
			int count = 0;
			byte[] bs = new byte[512];
			while((count = bis.read(bs)) != -1) {
				bos.write(bs, 0, count);
			}
			pln(f.getName()+" 파일 복사 완료.");
			bos.flush();
		}catch(IOException ie){
		}finally{
			try{
				if(bos!=null) bos.close();
				if(bis!=null) bis.close();
				if(fos!=null) fos.close();
				if(fis!=null) fis.close();
			}catch(IOException ie){}
		}
	}
	void test() {
		int idx = 0;
		String path = null;
		idx = arrFile.get(0).toString().lastIndexOf("/");
		for(File file: arrFile) {
			path = file.toString();
			dirPath = path.substring(idx+1);
//			pln("path: "+ path);
			makeDir(file);
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		CopyDir cd = new CopyDir();
		cd.getPath();
//		cd.test();
	}
}
/*

	1. 파일 및 디렉터리 읽어오기
	2. 파일인지 폴더인지 확인 // file is done, should begin directory.
	3. 파일이면 while돌려서 내용을 쓰고 해당 폴더에 파일 복사, 폴더면 mkdir하고 또 그 안에 들어가서 폴더나 파일보기.
	폴더랑 파일은 가져왔는데, 폴더 안에 있는 폴더랑 파일을 가져와야하는데. 어떻게 가져와야할까 ~~
	경로만 바꿔주면 되는데,,,,,,, 
*/