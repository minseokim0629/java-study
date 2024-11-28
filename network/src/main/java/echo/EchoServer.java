package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {

	public static final int PORT = 60000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("starts...[port:" + PORT + "]");

			Socket socket = serverSocket.accept();

			try {
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();

				log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

				// TCPServer와 달리 읽고 쓸때 byte로 변경하는 작업 불필요
				PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true); // flush - true (default : false)
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 읽는 쪽은 읽으면 버퍼가 자동으로 비워지니까 autoflush 기능이 없음

				while (true) {
					// 개행을 붙이지 않으면 못읽는다 (프로토콜의 약속)
					String data = br.readLine();
					if (data == null) {
						log("closed by client");
						break;
					}

					log("received: " + data);

					pw.println(data);
				}

			} catch (SocketException e) { // SocketException이 IOException의 자식이라 먼저 적어줘야 함. 정상 종료 아닌 경우 대비하여 예외처리 필요
				log("Socket Exception " + e);
			} catch (IOException e) {
				log("error:" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void log(String message) {
		System.out.println("[Echo Server] " + message);
	}
}
