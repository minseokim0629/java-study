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

import chat.ChatClient;
import chat.ChatServer;

public class ChatWindow {

	private static final String SERVER_IP = "127.0.0.1";
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {

		// 1. 서버 연결 작업
		// 2. IO Stream set
		// 3. JOIN Protocol
		// 4. ChatClientThread 생성
		try {
			socket = new Socket();

			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT)); // IP, 포트번호

			PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String nickname = frame.getTitle();
			pw.println("JOIN:" + nickname);

			String response = br.readLine();

			if ("JOIN:OK".equals(response)) {
				updateTextArea("입장하였습니다. 즐거운 채팅 되세요");
			} else {
				System.out.println("채팅창 로딩에 실패하였습니다. 다시 시도해주세요.");
				System.exit(0);
			}

			ChatClientThread chatClientThread = new ChatClientThread(br);
			chatClientThread.start();

			// Button
			buttonSend.setBackground(Color.GRAY);
			buttonSend.setForeground(Color.WHITE);
			// anonymous class
			buttonSend.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					sendMessage(pw);
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
						sendMessage(pw);
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
					finish(chatClientThread, pw);
				}
			});
			frame.setVisible(true);
			frame.pack();

		} catch (IOException e) {
			consoleLog("error:" + e);
		}
	}

	private void sendMessage(PrintWriter pw) {
		String message = textField.getText();
		// System.out.println("메세지를 보내는 프로토콜 구현!:" + message);
		if (message.trim().isEmpty()) {
			updateTextArea("메세지를 입력해주세요.");
		} else {
			// message
			pw.println("MSG:" + message);
		}
		textField.setText("");
		textField.requestFocus();
	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish(ChatClientThread chatClientThread, PrintWriter pw) {
		// quit protocol 구현
		pw.println("QUIT");
		try {
			// chatClientThread가 종료될때까지 기다린다 (block)
			chatClientThread.join();
		} catch (InterruptedException e) {
			consoleLog("error:" + e);
		}
		// exit java application
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			ChatClient.consoleLog("error:" + e);
		} finally {
			System.exit(0);
		}
	}

	private void consoleLog(String message) {
		System.out.println("[chat client] " + message);
	}

	private class ChatClientThread extends Thread {
		private BufferedReader br;

		public ChatClientThread(BufferedReader br) {
			this.br = br;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String data = br.readLine();

					if (data == null) {
						ChatClient.consoleLog("closed by server");
						break;
					} else if ("QUIT:OK".equals(data)) { // QUIT OK가 오면 종료
						consoleLog("client 채팅 종료");
						break;
					}
					updateTextArea(data);
				}
			} catch (SocketException e) {
				ChatClient.consoleLog("Socket Exception " + e);
			} catch (IOException e) {
				ChatClient.consoleLog("error:" + e);
			} finally {
				ChatClient.consoleLog("closed by server");
			}
		}
	}
}
