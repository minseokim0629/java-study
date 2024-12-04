package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {

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
				}
				else if("QUIT:OK".equals(data)) {
					ChatClient.consoleLog("client 채팅 종료");
					break;
				}
				System.out.println(data);
			}
		} catch (SocketException e) { 
			ChatClient.consoleLog("Socket Exception " + e);
		} catch (IOException e) {
			ChatClient.consoleLog("error:" + e);
		} 
	}
}
