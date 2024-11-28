package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
	// final은 대문자로 선언
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;

		try {
			scanner = new Scanner(System.in);
			socket = new Socket();

			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT)); // IP, 포트번호

			PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				System.out.print(">>");
				String line = scanner.nextLine();
				if ("exit".equals(line)) {
					break;
				}
				// print 쓸거면 flush 안돼서 flush 따로 표기 필요
				// pw.print(line + "\n");
				// pw.flush();
				
				// flush 됨
				pw.println(line);
				String data = br.readLine();
				if (data == null) {
					log("closed by server");
					break;
				}
				System.out.println("<<" + data);
			}
		} catch (SocketException e) { // SocketException이 IOException의 자식이라 먼저 적어줘야 함. 정상 종료 아닌 경우 대비하여 예외처리 필요
			log("Socket Exception " + e);
		} catch (IOException e) {
			log("error:" + e);
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(String message) {
		System.out.println("[Echo client] " + message);
	}
}
