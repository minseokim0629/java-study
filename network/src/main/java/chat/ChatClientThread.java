package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {

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
				System.out.println(data);
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
