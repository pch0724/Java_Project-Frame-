package Frame_Pro;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameTest02 {

	public static void main(String[] args) {
		/*
		JFrame fr = new JFrame("두 번째");
		fr.setSize(400, 500); // 사이즈
		fr.setLocation(800, 250);
		
		//X버튼을 눌렀을 때
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 현재 창의 메모리 소멸
		fr.setVisible(true);
		
		//여러 개의 프레임 중 원하는 하나의 프레임만 종료할 때 사용(JFrame) => disposer()
		// fr.dispose();
		 */
		
		//프레임 설정
		Frame fr = new Frame("문장 입력기");
		fr.setBounds(800, 100, 250, 400); // 사이즈
		fr.setBackground(Color.CYAN);
		
		//폰트(글꼴, 굵기, 크기)
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
		
		//북쪽단
		//컴포넌트를 하나로 묶음 panel
		Panel pN = new Panel();
		pN.setBackground(Color.GREEN);
		
		//입력상자 (입력글자수)
		TextField tf = new TextField(10);
		Button btn = new Button("입력");
		btn.setEnabled(false);
		
		//panel에 입력상자 넣기
		pN.add(tf);
		pN.add(btn);
		pN.setFont(font);
		
		//=====================================================================
		//중앙단
		TextArea ta = new TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		ta.setBackground(Color.GRAY);
		ta.setFont(font);
		ta.setEnabled(false);
		
		//=====================================================================
		//남쪽단
		Panel pS = new Panel();
		pS.setBackground(Color.MAGENTA);
		pS.setFont(font);
		
		Button btnSave = new Button("저장");
		Button btnClose = new Button("닫기");
		
		pS.add(btnSave);
		pS.add(btnClose);
		
		//=====================================================================
		//컴포넌트 배치
		//Panel에 배치하는 코드 : BorderLayout
		fr.add(pN, BorderLayout.NORTH);
		fr.add(ta, BorderLayout.CENTER);
		fr.add(pS, BorderLayout.SOUTH);
		
		fr.setVisible(true);
		fr.setResizable(true); // 창의 크기를 조절 할 수 없도록 고정
		//=====================================================================
		//TextField에 값이 들어간 경우에만 입력버튼 활성화
		tf.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				if(tf.getText().equals("")) {
					btn.setEnabled(false);
				}else {
					btn.setEnabled(true);
				}
			}
		});
		
		
		//========================Key Event==================================
		tf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					ta.append(tf.getText() + "\n");
					
					//텍스트필드 비우고 포커스 이동
					tf.setText("");
					tf.requestFocus();
				}//if
			}
		});
		
		//======================Button Event\\\===============================
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append(tf.getText() + "\n");
				
				//텍스트필드 비우고 포커스 이동
				tf.setText("");
				tf.requestFocus();
			}
		});
		
		//닫기버튼 종료
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//저장버튼
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = ta.getText();
				
				
				try {
					//FileDialog() : save,load에 사용되는 공통모듈
					FileDialog fd = new FileDialog(fr, "저장", FileDialog.SAVE);
					fd.setVisible(true);
					//System.out.println(fd.getDirectory() + fd.getFile());
					String path = fd.getDirectory() + fd.getFile();
					if(!message.equals("")) { // message에 데이터가 비워있지 않으면
						FileWriter fw = new FileWriter(path);
						BufferedWriter bw = new BufferedWriter(fw);
						
						bw.write(message);
						//FileDialog에 취소버튼을 누르지 않고 정상 저장 경우
						//저장한 경우
						if(fd.getFile() != null) {
							JOptionPane.showMessageDialog(fr, path + "\n 경로에 저장했습니다.");
						}//in if
						bw.close();
						
					}else {//TextArea에 기록할게 없다면
						JOptionPane.showMessageDialog(fr, "저장 할 내용이 없습니다.");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}//try catch
						
			}//override
		});//addactionlistener
//=========================================================================================		
		//x버튼 종료
		fr.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		
		});
		
	}

}
