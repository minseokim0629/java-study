package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				System.out.print("> ");
				String line = scanner.nextLine();
				if ("exit".equals(line)) {
					break;
				}
				InetAddress[] InetAddresses = InetAddress.getAllByName(line);

				for (InetAddress inetAddress : InetAddresses) {
					System.out.println(line + " : " + inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("unknown host");
			}
		}

		scanner.close();
	}

}
