package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 9321;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = null;

		try {
			serverSocket = new ServerSocket();
			listWriters = new ArrayList<PrintWriter>();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			consoleLog("starts...[port:" + PORT + "]");
			
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			consoleLog("error: " + e);
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

	public static void consoleLog(String message) {
		System.out.println("[chat server] " + message);
	}
}
