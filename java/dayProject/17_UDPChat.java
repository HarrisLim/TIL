import java.io.*; 
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class UDPChat extends Thread
{
	JFrame f;
	Container cp;
	JTextField tfIp, tfName;
	JTextPane tp;
	JButton bt;
	ImageIcon ii;

	int port = 6000;
	String receiverIp = "127.0.0.1";
	String clientName = "Client";
	String clientIp = "Client";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	TreeMap<String, String> map = new TreeMap<String, String>();

	void init(){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e){}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		setUI();
	}
	void setUI(){
		createImageIcon();
		// (1) JFrame 셋팅
		f = new JFrame();
		cp = f.getContentPane();
		cp.setLayout(new BorderLayout());

		// (2) pNorth 셋팅
		Color c1 = new Color(184, 207, 229);
		Color c2 = new Color(99, 130, 191);
		JPanel pNorth = new JPanel(new BorderLayout());
		JPanel pNorthIn = new JPanel(new GridLayout(1, 2));
		JPanel pNorthInIp = new JPanel(new BorderLayout());
		JPanel pNorthInName = new JPanel(new BorderLayout());
		tfIp = new JTextField();
		tfIp.setText("10.10.10.");
		tfName = new JTextField();
		TfHandler tfH = new TfHandler(this);
		pNorthInIp.add(tfIp);
		pNorthInName.add(tfName);
		pNorth.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, c1, c2), "Input"));
		pNorthInIp.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, c1, c2), "IP"));
		pNorthInName.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, c1, c2), "Name"));
		pNorthIn.add(pNorthInIp);
		pNorthIn.add(pNorthInName);
		pNorth.add(pNorthIn);
		tfIp.addActionListener(tfH);
		tfName.addActionListener(tfH);

		// (3) pCenter 셋팅
		JPanel pCenter = new JPanel(new BorderLayout());
		pCenter.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, c1, c2), "Message"));
		tp = new JTextPane();
		bt = new JButton("send");
		JScrollPane sp = new JScrollPane(tp);
		pCenter.add(sp, BorderLayout.CENTER);
		pCenter.add(bt, BorderLayout.SOUTH);
		bt.addActionListener(tfH);

		cp.add(pNorth, BorderLayout.NORTH);
		cp.add(pCenter, BorderLayout.CENTER);
		always();
	}
	void createImageIcon(){
		try{
			BufferedImage bi = ImageIO.read(new File("./imgs/p_play.png"));
			ii = new ImageIcon(bi);
		}catch(IOException ie){}
	}
	void always(){
		f.setTitle("harris's UDPChat - ver2018");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setSize(400, 400);
		f.setLocation(500, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				// UDPChat.this.f는 아우터클래스를 접근하는 것. 그냥 f로 해도 되는데, 안드로이드에서는 일케해야돼 정석도 이거고.
				int answer = JOptionPane.showConfirmDialog(UDPChat.this.f, "종료할래?", "선택", JOptionPane.OK_CANCEL_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					System.exit(-1);
				}else{
					f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
	}
	void w(String msg){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try{
			ds = new DatagramSocket();
			byte[] bs = msg.getBytes();
			InetAddress ia = InetAddress.getByName(receiverIp);
			dp = new DatagramPacket(bs, bs.length, ia, port);
			ds.send(dp);
		}catch(SocketException se){
			System.out.println("Socket Exception");
		}catch(UnknownHostException ue){
			System.out.println("해당 서버 못찾음");
		}catch(IOException ie){
			System.out.println("IO Exception");
		}catch(Exception e){
			System.out.println("Exception");
		}finally{
			ds.close();
		}
	}
	public void run(){
		r();
	}
	void r(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try{
			ds = new DatagramSocket(port);
			byte[] bs = new byte[512];
			dp = new DatagramPacket(bs, bs.length);
			while(true){
				ds.receive(dp);
				String msg = new String(bs);
				if(msg != null) msg = msg.trim();
				JOptionPane.showMessageDialog(null, msg, clientName, 0, ii); // 펭귄 어떻게 나오게 하지 ?
				for(int i =0; i<bs.length; i++){
					bs[i] = 0;
				}
			}
		}catch(SocketException se){
			System.out.println("ds생성 실패 ("+port+"포트 사용중)");
		}catch(IOException ie){
			System.out.println("메시지 수신 중 에러");
		}catch(Exception e){
			System.out.println("Exception");
		}finally{
			ds.close();
		}
	}
	public static void main(String[] args) 
	{
		UDPChat uc = new UDPChat();
		uc.start();
		uc.init();
	}
}

class TfHandler implements ActionListener{
	UDPChat chat;
	TfHandler(UDPChat chat){
		this.chat = chat;
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == chat.tfIp){
			System.out.println("아이피 입력: "+ chat.tfIp.getText());
		}else if(obj == chat.tfName){
			System.out.println("이름 입력: "+ chat.tfName.getText());
		}else if(obj == chat.bt){ // obj == chat.bt
			connect();
		}else{
			
		}
	}
	void connect(){
		String msg = chat.tp.getText();
		chat.clientName = chat.tfName.getText();
		chat.receiverIp = chat.tfIp.getText();
		chat.w(msg);
	}
}