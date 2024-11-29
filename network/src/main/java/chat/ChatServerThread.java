package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listWriters;

	public ChatServerThread(Socket socket, List<PrintWriter> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();

			ChatServer.consoleLog("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			PrintWriter pw = new PrintWriter(new OutputStreamWriter((socket.getOutputStream()), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				String request = br.readLine();
				if (request == null) {
					ChatServer.consoleLog("closed by client[" + remoteHostAddress + ":" + remotePort + "]");
					doQuit(pw);
					break;
				}

				ChatServer.consoleLog("received: " + request);

				String[] tokens = request.split(":");

//				for (String token : tokens) {
//					System.out.println(token);
//				}
				if ("JOIN".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("MSG".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("QUIT".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else {
					ChatServer.consoleLog("에러 : 알 수 없는 요청(" + tokens[0] + ")");
				}
			}

		} catch (SocketException e) {
			ChatServer.consoleLog("Socket Exception " + e);
		} catch (IOException e) {
			ChatServer.consoleLog("error:" + e);
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

	private void doJoin(String nickname, PrintWriter pw) {
		this.nickname = nickname;
		// 브로드캐스팅 후 writerpool에 저장
		broadcast(this.nickname + "님이 입장했습니다");
		addWriter(pw);
		pw.println("입장하였습니다. 즐거운 채팅 되세요");
	}

	private void doMessage(String message) {
		broadcast(nickname + ":" + message);
	}

	private void doQuit(PrintWriter pw) {
		removeWriter(pw);
		broadcast(this.nickname + "님이 퇴장하였습니다.");
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.add(pw);
		}
	}
	
	private void removeWriter(PrintWriter pw) {
		synchronized (listWriters) {
			listWriters.remove(pw);
		}
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (PrintWriter printWriter : listWriters) {
				printWriter.println(data);
			}
		}
	}
}
