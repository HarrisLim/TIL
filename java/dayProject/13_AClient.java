import java.io.*; 
import java.net.*; 

class AClient extends Thread
{
	BufferedReader brK = new BufferedReader(new InputStreamReader(System.in));
	Socket s;
	InputStream is; // Node
	BufferedReader brS; // Filter
	String ip = "10.10.10.171";
	OutputStream os;
	PrintWriter pw;
	int port = 2000;
	AClient(){
		try{
			s = new Socket(ip, port);
			s.getOutputStream();
			pln("서버와 연결 성공");

			start();
		}catch(UnknownHostException ue){
			pln("네트워크에서 해당 서버를 찾지 못함.");
		}catch(IOException ie){}
	}
	public void run(){
		try{
			listen();
		}catch(IOException ie){
		}
	}
	void speak() throws IOException { // Keyboard -> Socket
			os = s.getOutputStream();
			pw = new PrintWriter(os, true);
		try{
			while(true){
				String line = brK.readLine();
				pw.println(line);
			}
		}catch(IOException ie){
		}finally{
			try{
				if(pw!=null) pw.close();
				if(os!=null) os.close();
			}catch(IOException ie){
			}
		}
	}
	void listen() throws IOException { // Socket -> Monitor
			is = s.getInputStream();
			brS = new BufferedReader(new InputStreamReader(is));
		try{
			while(true){
				String line = brS.readLine();
				pln("server -> " + line);
			}
		}catch(IOException ie){
		}finally{
			try{
//				if(brs!=null) brs.close();
				if(is!=null) is.close();
			}catch(IOException ie){}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		AClient ac = new AClient();
		try{
			ac.speak();
		}catch(IOException ie){}
	}
}
