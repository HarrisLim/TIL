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
			pln("input File or Directory you wanna copy."); // ������ ���� �� ���丮�� �Է����ּ���.
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
			} else { // ���� �ȿ� �ִ� ����
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
			pln(f.getName()+"���� ����");
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
			pln(f.getName()+" ���� ���� �Ϸ�.");
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

	1. ���� �� ���͸� �о����
	2. �������� �������� Ȯ�� // file is done, should begin directory.
	3. �����̸� while������ ������ ���� �ش� ������ ���� ����, ������ mkdir�ϰ� �� �� �ȿ� ���� ������ ���Ϻ���.
	������ ������ �����Դµ�, ���� �ȿ� �ִ� ������ ������ �����;��ϴµ�. ��� �����;��ұ� ~~
	��θ� �ٲ��ָ� �Ǵµ�,,,,,,, 
*/