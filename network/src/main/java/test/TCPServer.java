package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 1-1. FIN_WAIT -> TIME_WAIT 상태에서도 소켓이 포트 할당이 가능하도록 하는 옵션
			serverSocket.setReuseAddress(true);
			
			// 2. 바인딩(binding)
			// Socket에 InetSocketAddress[InetAddress(IPAddress) + port]를 바인딩 한다.
			// IPAddress : 0.0.0.0 : 특정 호스트 IP를 바인딩 하지 않는다. (모든 IP 허용)
			// 본인 IP가 아니라 허용하는 IP 주소를 넣는 것
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 50000)); // (IP, 포트번호)

			// 3. accept
			// blocking
			// client가 connect 해줘야 block 해제되고 다음 코드가 실행됨
			Socket socket = serverSocket.accept(); // blocking
			try {
				// 반대편의 IPAddress + 포트
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();

				// block 여부 확인
				System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort + "]");

				// 4. IO Stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					// 패킷이 와서 read되면 깨움
					int readByteCount = is.read(buffer); // blocking
					if (readByteCount == -1) {
						// closed by client
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] receive: " + data);

					// SO_TIMEOUT TEST
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}

			} catch (SocketException e) { // SocketException이 IOException의 자식이라 먼저 적어줘야 함. 정상 종료 아닌 경우 대비하여 예외처리 필요
				System.out.println("[server] Socket Exception " + e);
			} catch (IOException e) {
				System.out.println("[server] error:" + e);
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
			System.out.println("[server] error: " + e);
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

}
