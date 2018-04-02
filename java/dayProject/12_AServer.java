import java.io.*;
import java.net.*;

class AServer extends Thread
{
	BufferedReader brK = new BufferedReader(new InputStreamReader(System.in));
	int port = 2000;
	ServerSocket ss;
	Socket s;
	InputStream is; // Node
	BufferedReader brS; // Filter
	OutputStream os;
	PrintWriter pw;
	String ip;
	AServer(){
		try{
			ss = new ServerSocket(port);
			System.out.println("서버가 "+port+"포트에서 대기중..");
			s = ss.accept(); // 이걸하면 대기해.
			InetAddress ia = s.getInetAddress();
			ip = ia.getHostAddress();
			System.out.println("연결완료 IP: " + ip);
			is = s.getInputStream();
			brS = new BufferedReader(new InputStreamReader(is));
			os = s.getOutputStream();
			pw = new PrintWriter(os, true);
			
			start();
		}catch(IOException ie){
			System.out.println("ie -> " + ie);
		}
	}
	public void run(){
		try{
			speak();
		}catch(IOException ie){
		}
	}
	void speak() throws IOException { // Keyboard -> Socket
		try{
			while(true){
				String line = brK.readLine();
				pw.println(line);
			}
		}catch(IOException ie){
		}
	}
	void listen() throws IOException { // Socket -> Monitor
		try{
			while(true){
				String line = brS.readLine();
				pln("Client - > " + line);
			}
		}catch(IOException ie){
			pln("클라이언트 퇴장 ! 2초 후에 종료합니다.");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException ite){}
		}finally{
			try{
				closeAll();
				System.exit(-1);
			}catch(IOException ie){}
		}
	}
	void closeAll() throws IOException {
		if(ss != null) ss.close();
		if(s != null) s.close();
		if(brS != null) brS.close();
		if(is != null) is.close();
		if(pw != null) pw.close();
		if(os != null) os.close();
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		AServer as = new AServer();
		try{
			as.listen();
		}catch(IOException ie){
		}
	}
}