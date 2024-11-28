package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;

	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();

			EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			// TCPServer와 달리 읽고 쓸때 byte로 변경하는 작업 불필요
			// flush - true (default : false)
			PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true); 
			// 읽는 쪽은 읽으면 버퍼가 자동으로 비워지니까 autoflush 기능이 없음
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

			while (true) {
				// 개행을 붙이지 않으면 못읽는다 (프로토콜의 약속)
				String data = br.readLine();
				if (data == null) {
					EchoServer.log("closed by client");
					break;
				}

				EchoServer.log("received: " + data);

				pw.println(data);
			}

		} catch (SocketException e) { // SocketException이 IOException의 자식이라 먼저 적어줘야 함. 정상 종료 아닌 경우 대비하여 예외처리 필요
			EchoServer.log("Socket Exception " + e);
		} catch (IOException e) {
			EchoServer.log("error:" + e);
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
