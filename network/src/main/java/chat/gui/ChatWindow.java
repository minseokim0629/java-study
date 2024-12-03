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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import chat.ChatClient;
import chat.ChatServer;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private Socket socket;
	private static final String SERVER_IP = "127.0.0.1";
	private Scanner scanner;
	private PrintWriter pw;
	private BufferedReader br;
	private boolean data;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		data = false;
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
					data = true;
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
		try {
			socket = new Socket();

			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT)); // IP, 포트번호

			pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String nickname = frame.getTitle();
			pw.println("JOIN:" + nickname);

			new ChatClientThread(socket).start();

			while (true) {
				String line = textField.getText();
				if (data) {
					if (line == null) {

					}
					if ("quit".equals(line)) {
						pw.println("QUIT:" + line);
						break;
					} else {
						// message
						pw.println("MSG:" + line);
					}
					sendMessage();
					data = false;
				}
			}
		} catch (IOException e) {
			consoleLog("error:" + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메세지를 보내는 프로토콜 구현!:" + message);

		textField.setText("");
		textField.requestFocus();

		// ChatClientThread에서 서버로 부터 받은 메세지가 있다고 치고~
		// 쓰레드에 있어야 하는 코드임
		// updateTextArea("아무개: " + message);
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

	private void consoleLog(String message) {
		System.out.println("[chat client] " + message);
	}

	private class ChatClientThread extends Thread {
		private Socket socket;

		public ChatClientThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				while (true) {
					String data = br.readLine();
					if (data == null) {
						ChatClient.consoleLog("closed by server");
						break;
					}
					updateTextArea(data);
					// System.out.println(data);
				}
			} catch (SocketException e) {
				ChatClient.consoleLog("Socket Exception " + e);
			} catch (IOException e) {
				ChatClient.consoleLog("error:" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}