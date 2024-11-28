package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		try {
			while (true) {
				Scanner scanner = new Scanner(System.in);
				System.out.print("> ");
				String s = scanner.nextLine();
				if ("exit".equals(s)) {
					break;
				}
				InetAddress[] InetAddresses = InetAddress.getAllByName(s);

				for (InetAddress inetAddress : InetAddresses) {
					System.out.println(s + " : " + inetAddress.getHostAddress());
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
