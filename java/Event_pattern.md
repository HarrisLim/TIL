# 이벤트 처리 모델 (4가지)

  - 위임형 이벤트 (Delegation Event Model)
    - 유명 내부클래스
    - 익명 내부클래스 (객체 생성 가능, 클래스 이름이 없다)
    - 제 3클래스 (다른 클래스로 분리해서 사용)
  - 셀프 이벤트 (Self Event model)

  - 순서
    - 모양꾸미기(배치) -> 이벤트(event) -> 리스너(listener) 등록 -> 이벤트 처리(handling)  

```
// 사용할 때 같은 이름의 주석을 갖다가 붙여넣으면 가능.
// 공통
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class A extends JFrame // 셀프클래스(1)
{

	JButton b;
	// 유명 내부클래스(1)
	// 셀프클래스(1)

	void init(){
		// 유명 내부클래스(2)
		// 익명 내부클래스(1)
		// 제 3클래스(1)
		// 셀프클래스(2)
	}
	void setUI(){
		setTitle("jeongsoo's memo");
		setSize(300, 300);
		setLocation(1500, 100); // 왼쪽 상단 꼭지점 기준
		setVisible(true);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 생성창 종료하면 콘솔 종료
	}
	public static void main(String[] args) 
	{
		A a = new A();
		a.init();
	}
}
// 제 3클래스(2)

//////////////////////////////////////////////////////////////////

// 유명 내부 클래스
	// 유명 내부클래스(1)
	class AHandler implements ActionListenr {
		public void actionPerformed(ActionEvent e){ // ActionListener의 추상메소드.
			Object obj = e.getSource(); // 이벤트 처리 (밑 3줄포함)
			if(obj == b){
				b.setText("클릭되었습니다. by 유명내부클래스");
			}
		}
	}
	// 유명 내부클래스(2)
	b = new JButton("10년 후");
	abb(b); // JFrame에 b(버튼)을 올린다
	ActionListener listener = new AHandler();
	b.addActionListenr(listener); // 리스너 등록
	setUI();
}

// 익명 내부 클래스
	// 익명 내부클래스(1)
	b = new JButton("10년 후..");
	add(b);
	b.addActionListener(new ActionListener(){ // new ActionListener(){}이렇게 객체를 생성하는데,
						  // {}안에 쓰는 것을 내부클래스라고 생각해도 돼.
						  // 근데, ActionListener는 interface야. 생성자가 없으니까,
						  // 객체 생성할 때 기능이 없는 것 뿐이지 객체 생성은 가능.
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj == b){
				b.setText("클릭되었습니다. by 익명내부클래스");
			}
		}
	});
	setUI();

// 제 3클래스
	// 제 3클래스(1)
	b = new JButton("10년 후..");
	add(b);
	ActionListener listener = new AHandler(this);
	b.addActionListener(listener); 
	setUI();
	
	// 제 3클래스(2)
	class AHandler implements ActionListener {
		A a;
		AHandler(A a){
			this.a = a;
		}
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj == a.b){
				a.b.setText("클릭되었습니다. by 제 3클래스");
			}
		}
	}

// 셀프 클래스
	// 셀프클래스(1)
	implements ActionListener

	// 셀프클래스(2)
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == b){
			b.setText("클릭되었습니다. by 셀프클래스")
		}
	}
	
	// 셀프클래스(3)
	b = new JButton("10년 후..");
	add(b);
	b.addActionListener(this);
	setUI();
```  
  
출처 : 김형수선생님