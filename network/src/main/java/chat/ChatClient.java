package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import echo.EchoServer;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();

			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT)); // IP, 포트번호

			PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			pw.println("JOIN:"+nickname);
			
			new ChatClientThread(socket).start();
			
			while (true) {
				//System.out.print(">>");
				String line = scanner.nextLine();
				if(line==null) {
					
				}
				if ("quit".equals(line)) {
					pw.println("QUIT:"+line);
					break;
				}
				else {
					// message
					pw.println("MSG:"+line);
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

	public static void consoleLog(String message) {
		System.out.println("[chat client] " + message);
	}
}
