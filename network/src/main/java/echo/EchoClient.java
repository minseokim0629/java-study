package echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoClient {
	//final은 대문자로 선언
	private static final String SERVER_IP = "127.0.0.1";
	
	public static void main(String[] args) {
		Socket socket = null;

		try {
			// 1. 소켓 생성
			socket = new Socket();

			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT)); // IP, 포트번호

			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));

			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);

			// 정상적인 종료
			if (readByteCount == -1) {
				log("closed by server");
				return;
			}

			data = new String(buffer, 0, readByteCount, "utf-8");
			log("received:" + data);
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
	}

	public static void log(String message) {
		System.out.println("[Echo client] " + message);
	}
}
