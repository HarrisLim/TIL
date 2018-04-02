import java.util.*;
import java.io.*;

class Lotto 
{
	String fName = "우리반.txt";
	BufferedReader br;
	String[] name = new String[20];

	void StudentList(){
		try{
			FileReader fr = new FileReader(fName);
			br = new BufferedReader(fr);
		}catch (FileNotFoundException fe){
		}
	}
	
	void show(){
		StudentList();
		int i = 0;
		try{
			String line = "";
			while((line	= br.readLine()) != null){
				name[i] = line;
				i++;
			}
		}
		catch (IOException ie){
		}
		
		System.out.println("반갑다 !  -> " + name[getRan()]);
	}

	int getRan(){
		int i = 0;
		Random r = new Random();
		i = r.nextInt(20);

		return i;
	}

	public static void main(String[] args) 
	{
		Lotto lo = new Lotto();
		lo.show();
	}
}
