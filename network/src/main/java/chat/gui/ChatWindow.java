package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		// anonymous class
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});
		// 컴파일러에서 객체로 생성할 뿐, 자바에서는 함수를 파라미터로 넘길 수 없다.(객체만 넘길 수 있을 뿐)
		// buttonSend.addActionListener( (ActionEvent actionEvent ) -> {});
		// Textfield
		textField.setColumns(80);
		// adapter pattern은 요즘 deplicate 되는 추세다 awt?
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();

		// 1. 서버 연결 작업
		// 2. IO Stream set
		// 3. JOIN Protocol
		// 4. ChatClientThread 생성
	}

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메세지를 보내는 프로토콜 구현!:" + message);

		textField.setText("");
		textField.requestFocus();

		// ChatClientThread에서 서버로 부터 받은 메세지가 있다고 치고~
		// 쓰레드에 있어야 하는 코드임
		updateTextArea("아무개: " + message);
	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish() {
		// quit protocol 구현
		
		// quit ok가 오면 종료
		// exit java application
		System.exit(0);
	}
	
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			updateTextArea("...");
		}
	}
}
